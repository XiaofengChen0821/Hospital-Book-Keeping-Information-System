package db;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBInterface {

    // Method to get all lab tests for a patient by name
    public List<LabTest> getPatientsAllLabTests(String patientName) {
        List<LabTest> labTests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LabTests WHERE patient_name = ?");
            stmt.setString(1, patientName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LabTest labTest = new LabTest(rs.getInt("patient_id"), rs.getString("patient_name"),
                        rs.getDate("test_date").toLocalDate(), rs.getString("lab_test"),
                        rs.getDouble("test_value"), rs.getInt("physician_id"));
                labTests.add(labTest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labTests;
    }

    // Method to get particular lab tests for a patient by name and lab test type
    public List<LabTest> getPatientsParticularLabTests(String patientName, String labTestType) {
        List<LabTest> labTests = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LabTests WHERE patient_name = ? AND lab_test = ?");
            stmt.setString(1, patientName);
            stmt.setString(2, labTestType);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LabTest labTest = new LabTest(rs.getInt("patient_id"), rs.getString("patient_name"),
                        rs.getDate("test_date").toLocalDate(), rs.getString("lab_test"),
                        rs.getDouble("test_value"), rs.getInt("physician_id"));
                labTests.add(labTest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labTests;
    }

    // Method to get one particular lab test record for a patient by name, date and lab test type
    public LabTest getOneParticularLabTest(String patientName, LocalDate date, String labTestType) {
        LabTest labTest = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM LabTests WHERE patient_name = ? AND test_date = ? AND lab_test = ?");
            stmt.setString(1, patientName);
            stmt.setDate(2, Date.valueOf(date));
            stmt.setString(3, labTestType);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                labTest = new LabTest(rs.getInt("patient_id"), rs.getString("patient_name"),
                        rs.getDate("test_date").toLocalDate(), rs.getString("lab_test"),
                        rs.getDouble("test_value"), rs.getInt("physician_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return labTest;
    }

    // Method to get visitation record of a patient on a specified date/time by patient name and date
    public VisitationRecord getVisitationRecord(String patientName, LocalDate date) {
        VisitationRecord visitationRecord = null;
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM visitationRecord WHERE patient_name = ? AND visit_date = ?");
            stmt.setString(1, patientName);
            stmt.setDate(2, Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                visitationRecord = new VisitationRecord(rs.getString("patient_name"), rs.getInt("patient_id"),
                        rs.getString("physician_name"), rs.getInt("physician_id"), rs.getDate("visit_date").toLocalDate(),
                        rs.getString("visit_time"), rs.getString("location"), rs.getString("notes"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visitationRecord;
    }

    // Method to get physicians of a patient by patient name
    public List<Physician> getPhysiciansOfPatient(String patientName) {
        List<Physician> physicians = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PhysicianPatients pp " +
                    "JOIN Users u ON pp.physician_id = u.user_id WHERE pp.patient_id = (SELECT patient_id FROM Patients WHERE patient_name = ?)");
            stmt.setString(1, patientName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Physician physician = new Physician(rs.getInt("user_id"), rs.getString("name"),
                        rs.getString("username"), rs.getString("password"), rs.getString("role"));
                physicians.add(physician);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return physicians;
    }
    
    // Method to get patients of a physician by physician name
    public List<Patient> getPatientsOfPhysician(String physicianName) {
        List<Patient> patients = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM PhysicianPatients pp " +
                    "JOIN Patients p ON pp.patient_id = p.patient_id " +
                    "WHERE pp.physician_id = (SELECT user_id FROM Users WHERE name = ?)");
            stmt.setString(1, physicianName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient(rs.getInt("patient_id"), rs.getString("patient_name"));
                patients.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    public User login(String username, String password) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int userId = rs.getInt("user_id");
                        String name = rs.getString("name");
                        String role = rs.getString("role");

                        // Creating a User object with the retrieved data
                        return new User(userId, name, username, password, role);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if authentication fails or an exception occurs
    }
    
    public boolean verifyUserId(String username, int userId) {
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT user_id FROM Users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int dbUserId = rs.getInt("user_id");
                return dbUserId == userId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Physician> getPhysiciansOfNurse(String nurseName) {
        List<Physician> physicians = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT u.* FROM Users u " +
                         "JOIN PhysicianNurses pn ON u.user_id = pn.physician_id " +
                         "WHERE pn.nurse_id = (SELECT user_id FROM Users WHERE name = ? AND role = 'Nurse')";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nurseName);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Physician physician = new Physician(rs.getInt("user_id"), rs.getString("name"),
                                                            rs.getString("username"), rs.getString("password"),
                                                            rs.getString("role"));
                        physicians.add(physician);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return physicians;
    }

    public List<Nurse> getNursesOfPhysician(String physicianName) {
        List<Nurse> nurses = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT u.* FROM Users u " +
                         "JOIN PhysicianNurses pn ON u.user_id = pn.nurse_id " +
                         "WHERE pn.physician_id = (SELECT user_id FROM Users WHERE name = ? AND role = 'Physician')";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, physicianName);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Nurse nurse = new Nurse(rs.getInt("user_id"), rs.getString("name"),
                                                rs.getString("username"), rs.getString("password"),
                                                rs.getString("role"));
                        nurses.add(nurse);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurses;
    }


}

