/**
 * 
 */
package db;

/**
 * 
 */
public class Patient {
	private int patientId;
    private String patientName;

    // Constructor
    public Patient(int patientId, String patientName) {
        this.patientId = patientId;
        this.patientName = patientName;
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



	@Override
    public String toString() {
        return String.format("Patient {patientId=%d, patientName='%s'}", patientId, patientName);
    }
}
