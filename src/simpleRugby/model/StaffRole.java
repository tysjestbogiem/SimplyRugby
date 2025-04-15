package simpleRugby.model;

public enum StaffRole {
	
	COACH,
	MEMBERSHIP_SECRETARY,
	CHAIRMAN,
	FIXTURE_SECRETARY;
	
	
	@Override
    public String toString() {
        return name().replace("_", " ").toLowerCase(); 
    }

}
