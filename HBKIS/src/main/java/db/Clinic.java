package db;

import java.time.LocalDate;
import java.util.List;

public class Clinic {
//	public static void main(String[] args) {
//		Clinic clinic = new Clinic();
//	}
	
    private DBInterface dbInterface;
    int count = 0;
    
    public Clinic() {
        dbInterface = new DBInterface();
//        System.out.println("Clinic called: " + count++);
    }

    // Method to get all lab tests for a patient by name
    public List<LabTest> getPatientsAllLabTests(String patientName) {
        return dbInterface.getPatientsAllLabTests(patientName);
    }

    // Method to get particular lab tests for a patient by name and lab test type
    public List<LabTest> getPatientsParticularLabTests(String patientName, String labTestType) {
        return dbInterface.getPatientsParticularLabTests(patientName, labTestType);
    }

    // Method to get one particular lab test record for a patient by name and date
    public LabTest getOneParticularLabTest(String patientName, LocalDate date, String labTestType) {
        return dbInterface.getOneParticularLabTest(patientName, date, labTestType);
    }

    // Method to get visitation record of a patient on a specified date/time by patient name and date
    public VisitationRecord getVisitationRecord(String patientName, LocalDate date) {
        return dbInterface.getVisitationRecord(patientName, date);
    }

    // Method to get physicians of a patient by patient name
    public List<Physician> getPhysiciansOfPatient(String patientName) {
        return dbInterface.getPhysiciansOfPatient(patientName);
    }

    // Method to get patients of a physician by physician name
    public List<Patient> getPatientsOfPhysician(String physicianName) {
        return dbInterface.getPatientsOfPhysician(physicianName);
    }

 // Method to get physicians of a nurse by nurse name
    public List<Physician> getPhysiciansOfNurse(String nurseName) {
        return dbInterface.getPhysiciansOfNurse(nurseName);
    }

    // Method to get nurses of a physician by physician name
    public List<Nurse> getNursesOfPhysician(String physicianName) {
        return dbInterface.getNursesOfPhysician(physicianName);
    }
    
    // Method to login operation
    public User login(String username, String password) {
        System.out.println("Clinic.login() is called");
        return dbInterface.login(username, password);
    }
    
    // Method to 2FA to verify if the provided user ID matches the one in the database for the given username
    public boolean performSecondFactorAuthentication(String username, int userId) {
        return dbInterface.verifyUserId(username, userId);
    }
}
