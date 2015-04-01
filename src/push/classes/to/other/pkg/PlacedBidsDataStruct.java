package push.classes.to.other.pkg;

import json.datastructures.Requestor_Json_Data_Structure;

public class PlacedBidsDataStruct {
	
	//String requestId,categoryId,description,createdDate,requestStartDate,requestStatus,requestStatusId,leastBidAmount,totalBids,numberOfHours;
	
	Requestor_Json_Data_Structure request;
	Bid bid;
	
	public Requestor_Json_Data_Structure getRequest() {
		return request;
	}
	public void setRequest(Requestor_Json_Data_Structure request) {
		this.request = request;
	}
	public Bid getBid() {
		return bid;
	}
	public void setBid(Bid bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "PlacedBids [request=" + request + ", bid=" + bid + "]";
	}
	
	
	
}
