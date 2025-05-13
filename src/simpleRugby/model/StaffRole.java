package simpleRugby.model;


/**
 * Enum representing the different roles a staff member can have at the club.
 * These roles help determine what access or responsibilities the staff member has.
 */

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
