package simpleRugby.model;

public class Player extends Member{
	
	private String nextOfKin;
	private String nextOfKinTel;
	private String doctor;
	private String doctorTel;
	private String helthIssues;
	private String teamName;
	private String position;
	
	
	public String getNextOfKin() {
		return nextOfKin;
	}
	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}
	public String getNextOfKinTel() {
		return nextOfKinTel;
	}
	public void setNextOfKinTel(String nextOfKinTel) {
		this.nextOfKinTel = nextOfKinTel;
	}
	public String getDoctor() {
		return doctor;
	}
	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	public String getDoctorTel() {
		return doctorTel;
	}
	public void setDoctorTel(String doctorTel) {
		this.doctorTel = doctorTel;
	}
	public String getHelthIssues() {
		return helthIssues;
	}
	public void setHelthIssues(String helthIssues) {
		this.helthIssues = helthIssues;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	@Override
	public String toString() {
	    return getFullName(); 
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}



}
