package simpleRugby.model;

public class Member {
	
	private int memberId;
	private String firstName;
	private String surname;
	private String address;
	private String phoneNumber;
	private String email;
	private String SRUNumber;
	private MembershipType membershipType;
	
	
	public String getFullName() {
	    return firstName + " " + surname;
	}


	public int getMemberId() {
		return memberId;
	}
	
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSRUNumber() {
		return SRUNumber;
	}
	public void setSRUNumber(String sRUNumber) {
		SRUNumber = sRUNumber;
	}
	public MembershipType getMembershipType() {
		return membershipType;
	}
	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	
	@Override
	public String toString() {
	    return firstName + " " + surname;
	}

}
