package push.classes.to.other.pkg;

import java.util.ArrayList;

import json.datastructures.Requestor_Json_Data_Structure;



public class Data {

	ArrayList<Requestor_Json_Data_Structure> requests;
	//ArrayList<PlacedBidsDataStruct> placedBids; // not using as they have changed the format of placed bids
	ArrayList<Requestor_Json_Data_Structure> openRequests;
	ArrayList<Requestor_Json_Data_Structure> acceptedRequests;
	ArrayList<Requestor_Json_Data_Structure> servicedRequests;
	ArrayList<Requestor_Json_Data_Structure> openBids;
	ArrayList<Requestor_Json_Data_Structure> placedBids;
	String userId;
	String token ;
	UserPreferences userPreferences;
	//int roleId;

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
	
	/*public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}*/

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

	

	

/*	public ArrayList<PlacedBidsDataStruct> getPlacedBids() {
		return placedBids;
	}

	public void setPlacedBids(ArrayList<PlacedBidsDataStruct> placedBids) {
		this.placedBids = placedBids;
	}*/

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
