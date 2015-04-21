package json.datastructures;

public class UserAddresses {
	UserHomeAddress  homeAddress;
	UserOfficeAddress officeAddress;
	UserOtherAddresses otherAddresses[];
	public UserHomeAddress getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(UserHomeAddress homeAddress) {
		this.homeAddress = homeAddress;
	}
	public UserOfficeAddress getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(UserOfficeAddress officeAddress) {
		this.officeAddress = officeAddress;
	}
	public UserOtherAddresses[] getOtherAddresses() {
		return otherAddresses;
	}
	public void setOtherAddresses(UserOtherAddresses[] otherAddresses) {
		this.otherAddresses = otherAddresses;
	}

}
