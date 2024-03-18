package db;

import java.time.LocalDate;

import org.json.JSONObject;

/**
 * 
 */
public class LabTest {
	private int patientId;
    private String patientName;
    private LocalDate testDate;
    private String labTest;
    private double testValue;
    private int physicianId;

    // Constructor
    public LabTest(int patientId, String patientName, LocalDate testDate, String labTest, double testValue, int physicianId) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.testDate = testDate;
        this.labTest = labTest;
        this.testValue = testValue;
        this.physicianId = physicianId;
    }


    /**
	 * @return the patientId
	 */
	public int getPatientId() {
		return patientId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	/**
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * @return the testDate
	 */
	public LocalDate getTestDate() {
		return testDate;
	}

	/**
	 * @param testDate the testDate to set
	 */
	public void setTestDate(LocalDate testDate) {
		this.testDate = testDate;
	}

	/**
	 * @return the labTest
	 */
	public String getLabTest() {
		return labTest;
	}

	/**
	 * @param labTest the labTest to set
	 */
	public void setLabTest(String labTest) {
		this.labTest = labTest;
	}

	/**
	 * @return the testValue
	 */
	public double getTestValue() {
		return testValue;
	}

	/**
	 * @param testValue the testValue to set
	 */
	public void setTestValue(double testValue) {
		this.testValue = testValue;
	}

	/**
	 * @return the physicianId
	 */
	public int getPhysicianId() {
		return physicianId;
	}

	/**
	 * @param physicianId the physicianId to set
	 */
	public void setPhysicianId(int physicianId) {
		this.physicianId = physicianId;
	}

	@Override
    public String toString() {
        return String.format("LabTest {patientId=%d, patientName='%s', testDate=%s, labTest='%s', testValue=%.2f, physicianId=%d}",
                patientId, patientName, testDate, labTest, testValue, physicianId);
    }
	
	public JSONObject toJSONObject() {
	    JSONObject jsonObj = new JSONObject();
	    jsonObj.put("patientId", this.getPatientId());
	    jsonObj.put("patientName", this.getPatientName());
	    jsonObj.put("testDate", this.getTestDate() != null ? this.getTestDate().toString() : null);
	    jsonObj.put("labTest", this.getLabTest());
	    jsonObj.put("testValue", this.getTestValue());
	    jsonObj.put("physicianId", this.getPhysicianId());
	    return jsonObj;
	}

}
