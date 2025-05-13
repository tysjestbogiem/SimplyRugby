package simpleRugby.model;


/**
 * Represents a general member of the rugby club.
 * This can be a player, coach, or any other type of staff.
 * 
 * Holds basic personal details and membership info.
 */

public class Member {
		
	private int memberId;
	private String firstName;
	private String surname;
	private String address;
	private String phoneNumber;
	private String email;
	private String SRUNumber;
	private MembershipType membershipType;
	
	
	/**
	 * @return the full name (first name + surname)
	 */
	public String getFullName() {
	    return firstName + " " + surname;
	}
	
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the sRUNumber
	 */
	public String getSRUNumber() {
		return SRUNumber;
	}
	/**
	 * @param sRUNumber the sRUNumber to set
	 */
	public void setSRUNumber(String sRUNumber) {
		SRUNumber = sRUNumber;
	}
	/**
	 * @return the membershipType
	 */
	public MembershipType getMembershipType() {
		return membershipType;
	}
	/**
	 * @param membershipType the membershipType to set
	 */
	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}
	
}