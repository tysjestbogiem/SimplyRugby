package simpleRugby.model;

/**
 * This class represents a staff member in the system.
 * It inherits common fields (like ID, name, surname etc.) from the Member class.
 * */
public class Staff extends Member {
	
	private String username;
	private String password;
	private StaffRole staffRole;
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the staffRole
	 */
	public StaffRole getStaffRole() {
		return staffRole;
	}
	/**
	 * @param staffRole the staffRole to set
	 */
	public void setStaffRole(StaffRole staffRole) {
		this.staffRole = staffRole;
	}
}
