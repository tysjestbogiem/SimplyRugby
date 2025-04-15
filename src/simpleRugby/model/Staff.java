package simpleRugby.model;

public class Staff extends Member {
	
	private String username;
	private String password;
	private StaffRole staffRole;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public StaffRole getStaffRole() {
		return staffRole;
	}
	public void setStaffRole(StaffRole staffRole) {
		this.staffRole = staffRole;
	}

}
