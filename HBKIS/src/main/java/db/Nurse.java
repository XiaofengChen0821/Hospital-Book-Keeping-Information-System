/**
 * 
 */
package db;

import org.json.JSONObject;

/**
 * 
 */
public class Nurse extends User{

//	List<Physician> listOfPhysician;
//	/**
//	 * @return the listOfPhysician
//	 */
//	public List<Physician> getListOfPhysician() {
//		return listOfPhysician;
//	}
//	/**
//	 * @param listOfPhysician the listOfPhysician to set
//	 */
//	public void setListOfPhysician(List<Physician> listOfPhysician) {
//		this.listOfPhysician = listOfPhysician;
//	}
//	
	public Nurse(int userId, String name, String username, String password, String role) {
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
