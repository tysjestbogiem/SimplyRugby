package simpleRugby.model;

/**
 * Represents a player in the rugby club.
 * Extends Member to include extra info like medical and team details.
 */
public class Player extends Member{
	
	private String nextOfKin;
	private String nextOfKinTel;
	private String doctor;
	private String doctorTel;
	private String helthIssues;
	private String teamName;
	private String position;
	
	/**
	 * @return the nextOfKin
	 */
	public String getNextOfKin() {
		return nextOfKin;
	}

	/**
	 * @param nextOfKin the nextOfKin to set
	 */
	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}

	/**
	 * @return the nextOfKinTel
	 */
	public String getNextOfKinTel() {
		return nextOfKinTel;
	}

	/**
	 * @param nextOfKinTel the nextOfKinTel to set
	 */
	public void setNextOfKinTel(String nextOfKinTel) {
		this.nextOfKinTel = nextOfKinTel;
	}

	/**
	 * @return the doctor
	 */
	public String getDoctor() {
		return doctor;
	}

	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	/**
	 * @return the doctorTel
	 */
	public String getDoctorTel() {
		return doctorTel;
	}

	/**
	 * @param doctorTel the doctorTel to set
	 */
	public void setDoctorTel(String doctorTel) {
		this.doctorTel = doctorTel;
	}

	/**
	 * @return the helthIssues
	 */
	public String getHelthIssues() {
		return helthIssues;
	}

	/**
	 * @param helthIssues the helthIssues to set
	 */
	public void setHelthIssues(String helthIssues) {
		this.helthIssues = helthIssues;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
	    return getFullName(); 
	}

}
