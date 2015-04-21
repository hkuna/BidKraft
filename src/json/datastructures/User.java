package json.datastructures;

public class User {

	private String userName,name,cellPhone,emailId,description;
	private int userPoints , vendorPoints ;
	float rating ;
	private boolean fromSignup;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getUserPoints() {
		return userPoints;
	}
	public void setUserPoints(int userPoints) {
		this.userPoints = userPoints;
	}
	public int getVendorPoints() {
		return vendorPoints;
	}
	public void setVendorPoints(int vendorPoints) {
		this.vendorPoints = vendorPoints;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public boolean isFromSignup() {
		return fromSignup;
	}
	public void setFromSignup(boolean fromSignup) {
		this.fromSignup = fromSignup;
	}
	
}
