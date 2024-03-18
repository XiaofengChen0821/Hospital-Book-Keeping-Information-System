package db;

import org.json.JSONObject;

/**
 * 
 */
public class Physician extends User{
	
//	List<Patient> listOfPatient; 
	/**
	 * @return the listOfPatient
	 */
//	public List<Patient> getListOfPatient() {
//		return listOfPatient;
//	}
	/**
	 * @param listOfPatient the listOfPatient to set
	 */
//	public void setListOfPatient(List<Patient> listOfPatient) {
//		this.listOfPatient = listOfPatient;
//	}
	public Physician(int userId, String name, String username, String password, String role) {
		super(userId, name, username, password, role);
		// TODO Auto-generated constructor stub
	}

	public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", this.getUserId());
        jsonObj.put("name", this.getName());
        jsonObj.put("username", this.getUsername());
        jsonObj.put("password", this.getPassword()); // Note: Be cautious about including passwords
        jsonObj.put("role", this.getRole());

        return jsonObj;
    }
}
