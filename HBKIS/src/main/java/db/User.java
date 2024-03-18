package db;

import org.json.JSONObject;

/**
 * 
 */
public class User {
	private int userId;
    private String name;
    private String username;
    private String password;
    private String role;
	
    public User(int userId, String name, String username, String password, String role) {
		super();
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
    
	@Override
    public String toString() {
        return String.format("User {userId=%d, name='%s', username='%s', role='%s'}",
                userId, name, username, role);
    }
	
	public JSONObject toJSONObject() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("userId", this.getUserId());
        jsonObj.put("name", this.getName());
        jsonObj.put("username", this.getUsername());
        jsonObj.put("role", this.getRole());

        return jsonObj;
    }
}
