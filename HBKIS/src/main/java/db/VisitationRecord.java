package db;

import java.time.LocalDate;

import org.json.JSONObject;

/**
 * 
 */
public class VisitationRecord {
	private String patientName;
    private int patientId;
    private String physicianName;
    private int physicianId;
    private LocalDate visitDate;
    private String visitTime;
    private String visitLocation;
    private String notes;

    // Constructor
    public VisitationRecord(String patientName, int patientId, String physicianName, int physicianId,
                            LocalDate visitDate, String visitTime, String visitLocation, String notes) {
        this.patientName = patientName;
        this.patientId = patientId;
        this.physicianName = physicianName;
        this.physicianId = physicianId;
        this.visitDate = visitDate;
        this.visitTime = visitTime;
        this.visitLocation = visitLocation;
        this.notes = notes;
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
	 * @return the physicianName
	 */
	public String getPhysicianName() {
		return physicianName;
	}


	/**
	 * @param physicianName the physicianName to set
	 */
	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
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


	/**
	 * @return the visitDate
	 */
	public LocalDate getVisitDate() {
		return visitDate;
	}


	/**
	 * @param visitDate the visitDate to set
	 */
	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}


	/**
	 * @return the visitTime
	 */
	public String getVisitTime() {
		return visitTime;
	}


	/**
	 * @param visitTime the visitTime to set
	 */
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}


	/**
	 * @return the visitLocation
	 */
	public String getVisitLocation() {
		return visitLocation;
	}


	/**
	 * @param visitLocation the visitLocation to set
	 */
	public void setVisitLocation(String visitLocation) {
		this.visitLocation = visitLocation;
	}


	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}


	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}


	@Override
    public String toString() {
        return String.format("VisitationRecord {\npatientName='%s', \npatientId=%d, "
        		+ "\nphysicianName='%s', \nphysicianId=%d, \nvisitDate=%s, "
        		+ "\nvisitTime='%s', \nvisitLocation='%s', \nnotes='%s'\n}",
                patientName, patientId, physicianName, physicianId, visitDate, visitTime, 
                visitLocation, notes);
    }
	
	public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("patientName", this.getPatientName());
        jsonObj.put("patientId", this.getPatientId());
        jsonObj.put("physicianName", this.getPhysicianName());
        jsonObj.put("physicianId", this.getPhysicianId());
        jsonObj.put("visitDate", this.getVisitDate() != null ? this.getVisitDate().toString() : null);
        jsonObj.put("visitTime", this.getVisitTime());
        jsonObj.put("visitLocation", this.getVisitLocation());
        jsonObj.put("notes", this.getNotes());
        return jsonObj;
    }
}
