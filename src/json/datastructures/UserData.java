package json.datastructures;

import java.util.ArrayList;



public class UserData {

	ArrayList<Requestor_Json_Data_Structure> requests;
	ArrayList<Requestor_Json_Data_Structure> openRequests;
	ArrayList<Requestor_Json_Data_Structure> acceptedRequests;
	ArrayList<Requestor_Json_Data_Structure> servicedRequests;
	ArrayList<Requestor_Json_Data_Structure> openBids;
	ArrayList<Requestor_Json_Data_Structure> placedBids;
	String userId, token ;
	UserPreferences userPreferences;
	User user;
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserAddresses getAddresses() {
		return addresses;
	}

	public void setAddresses(UserAddresses addresses) {
		this.addresses = addresses;
	}

	UserAddresses addresses;
	

	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
	}

	public ArrayList<Requestor_Json_Data_Structure> getServicedRequests() {
		return servicedRequests;
	}

	public void setServicedRequests(
			ArrayList<Requestor_Json_Data_Structure> servicedRequests) {
		this.servicedRequests = servicedRequests;
	}

	public ArrayList<Requestor_Json_Data_Structure> getOpenBids() {
		return openBids;
	}

	public void setOpenBids(ArrayList<Requestor_Json_Data_Structure> openBids) {
		this.openBids = openBids;
	}

	public ArrayList<Requestor_Json_Data_Structure> getPlacedBids() {
		return placedBids;
	}

	public void setPlacedBids(ArrayList<Requestor_Json_Data_Structure> placedBids) {
		this.placedBids = placedBids;
	}

	
	public ArrayList<Requestor_Json_Data_Structure> getAcceptedRequests() {
		return acceptedRequests;
	}

	public void setAcceptedRequests(
			ArrayList<Requestor_Json_Data_Structure> acceptedRequests) {
		this.acceptedRequests = acceptedRequests;
	}
	
	public ArrayList<Requestor_Json_Data_Structure> getOpenRequests() {
		return openRequests;
	}

	public void setOpenRequests(
			ArrayList<Requestor_Json_Data_Structure> openRequests) {
		this.openRequests = openRequests;
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public ArrayList<Requestor_Json_Data_Structure> getRequests() {
		return requests;
	}

	public void setRequests(ArrayList<Requestor_Json_Data_Structure> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "Data [requests=" + requests + "]";
	}
	
	
}
