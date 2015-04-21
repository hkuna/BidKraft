package remove.classes.from.the.pkg;

import java.util.ArrayList;

import json.datastructures.Bid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;



public class RequestorItems implements Comparable<RequestorItems>
{
	public	 String requestId , categoryId , createdDate, requestStartDate, requestStatus, requestStatusId, leastBidAmount, description,totalBids , petType , petNature , age , services , numofhours , numofkids , homestyle , sqFt , bedrooms , bathrooms,from_method;
	public	  ArrayList<Bid> bids ;
	public ArrayList<Bid> bids_open;
String tag ="Reqquestor Items";

	
	/*public RequestorItems(String requestId, String categoryId, String description, String createdDate, String requestStartDate, String requestStatus, String requestStatusId, String leastBidAmount,String totalBids, JSONArray bidArray ,JSONObject obj ,String from_method)
	{
		this.requestId = requestId;
		this.categoryId = categoryId;
		this.description = description;
		this.createdDate = createdDate;
		this.requestStartDate = requestStartDate;
		this.requestStatus = requestStatus;
		this.requestStatusId = requestStatusId;
		this.bids = new ArrayList<Bid>();
		this.bids_open = new ArrayList<Bid>();
		this.leastBidAmount = leastBidAmount;
		this.totalBids = totalBids;
		this.from_method = from_method;
		
		Log.d(tag,"category id value "+categoryId + "int value is :"+Integer.parseInt(categoryId)+"request id is "+requestId);
		
		switch(Integer.parseInt(categoryId))
		{
		
		case 1:
			try {
				this.numofhours = obj.getString("numberOfHours");
				this.numofkids = obj.getString("numberOfKids");
				this.petNature="";
				this.petType ="";
				this.age="";
				this.services ="";
				this.homestyle ="";
				this.sqFt ="";
				this.bedrooms ="";
				this.bathrooms ="";
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 2 :
			try {
				this.numofhours="";
				this.numofkids ="";
			this.petType=	obj.getString("petType");
			this.petNature=	obj.getString("petNature");
			this.age =	obj.getString("age");
			this.services =	obj.getString("services");
			this.homestyle = "";
			this.sqFt =  "";
			this.bedrooms = ""; 
			this.bathrooms =  "";
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
			break ;
		case 3 :
			try {
				this.numofhours="";
				this.numofkids ="";
				this.petNature="";
				this.petType ="";
				this.age="";
				this.services ="";
				this.homestyle = obj.getString("homeStyle");
				this.sqFt =  obj.getString("sqFt");
				this.bedrooms =  obj.getString("bedRooms");
				this.bathrooms =  obj.getString("bathRooms");
				
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break ;
		case 4 :
			
			try {
				this.numofhours="";
				this.numofkids ="";
				this.petNature="";
				this.petType ="";
				this.age="";
				this.services ="";
				this.homestyle ="";
				this.sqFt ="";
				this.bedrooms ="";
				this.bathrooms ="";
				
				
				this.services = obj.getString("services");
				
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			break ;
		case 5:
			break;
		
		}
		
		
	
		try {
			
			for(int i=0; i<bidArray.length();i++)
			{
				 JSONObject loopobj  = bidArray.getJSONObject(i);
				 
				//bids.add(i, new RequestorBids(loopobj.getString("bidId"), loopobj.getString("offererUserId"), loopobj.getString("bidAmount"), loopobj.getString("createdDate"), loopobj.getString("offererName"), loopobj.getString("userName"), loopobj.getString("deleted")));
				
				 if(from_method.equals("menu"))
				 {
					 bids_open.add(i, new Bid(loopobj.getString("bidId"), loopobj.getString("offererUserId"), loopobj.getString("bidAmount"), loopobj.getString("createdDate"), loopobj.getString("offererName"), loopobj.getString("userName")));
				 }
				 else
				 {
					 bids.add(i, new Bid(loopobj.getString("bidId"), loopobj.getString("offererUserId"), loopobj.getString("bidAmount"), loopobj.getString("createdDate"), loopobj.getString("offererName"), loopobj.getString("userName")));
				 }
				 
			}
			
			//Log.d("requestorItems","in bids  "+ bids.toString()+"bid size "+bids.size());
			
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}*/
	


	public String getRequestId() {
		return requestId;
	}



	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}



	public String getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}



	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}



	public String getRequestStartDate() {
		return requestStartDate;
	}



	public void setRequestStartDate(String requestStartDate) {
		this.requestStartDate = requestStartDate;
	}



	public String getRequestStatus() {
		return requestStatus;
	}



	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}



	public String getRequestStatusId() {
		return requestStatusId;
	}



	public void setRequestStatusId(String requestStatusId) {
		this.requestStatusId = requestStatusId;
	}



	public String getLeastBidAmount() {
		return leastBidAmount;
	}



	public void setLeastBidAmount(String leastBidAmount) {
		this.leastBidAmount = leastBidAmount;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getTotalBids() {
		return totalBids;
	}



	public void setTotalBids(String totalBids) {
		this.totalBids = totalBids;
	}



	public String getPetType() {
		return petType;
	}



	public void setPetType(String petType) {
		this.petType = petType;
	}



	public String getPetNature() {
		return petNature;
	}



	public void setPetNature(String petNature) {
		this.petNature = petNature;
	}



	public String getAge() {
		return age;
	}



	public void setAge(String age) {
		this.age = age;
	}



	public String getServices() {
		return services;
	}



	public void setServices(String services) {
		this.services = services;
	}



	public String getNumofhours() {
		return numofhours;
	}



	public void setNumofhours(String numofhours) {
		this.numofhours = numofhours;
	}



	public String getNumofkids() {
		return numofkids;
	}



	public void setNumofkids(String numofkids) {
		this.numofkids = numofkids;
	}



	public String getHomestyle() {
		return homestyle;
	}



	public void setHomestyle(String homestyle) {
		this.homestyle = homestyle;
	}



	public String getSqFt() {
		return sqFt;
	}



	public void setSqFt(String sqFt) {
		this.sqFt = sqFt;
	}



	public String getBedrooms() {
		return bedrooms;
	}



	public void setBedrooms(String bedrooms) {
		this.bedrooms = bedrooms;
	}



	public String getBathrooms() {
		return bathrooms;
	}



	public void setBathrooms(String bathrooms) {
		this.bathrooms = bathrooms;
	}



	public String getFrom_method() {
		return from_method;
	}



	public void setFrom_method(String from_method) {
		this.from_method = from_method;
	}



	public ArrayList<Bid> getBids_open() {
		return bids_open;
	}



	public void setBids_open(ArrayList<Bid> bids_open) {
		this.bids_open = bids_open;
	}



	public String getTag() {
		return tag;
	}



	public void setTag(String tag) {
		this.tag = tag;
	}



	



	public ArrayList<Bid> getBids() {
		return bids;
	}



	



	







	















	
	@Override
	public String toString() {
		return "RequestorItems [requestId=" + requestId + ", categoryId="
				+ categoryId + ", createdDate=" + createdDate
				+ ", requestStartDate=" + requestStartDate + ", requestStatus="
				+ requestStatus + ", requestStatusId=" + requestStatusId
				+ ", leastBidAmount=" + leastBidAmount + ", description="
				+ description + ", totalBids=" + totalBids + ", petType="
				+ petType + ", petNature=" + petNature + ", age=" + age
				+ ", services=" + services + ", numofhours=" + numofhours
				+ ", numofkids=" + numofkids + ", homestyle=" + homestyle
				+ ", sqFt=" + sqFt + ", bedrooms=" + bedrooms + ", bathrooms="
				+ bathrooms + ", from_method=" + from_method + ", bids=" + bids
				+ ", bids_open=" + bids_open + ", tag=" + tag
				+ ", getRequestId()=" + getRequestId() + ", getCategoryId()="
				+ getCategoryId() + ", getCreatedDate()=" + getCreatedDate()
				+ ", getRequestStartDate()=" + getRequestStartDate()
				+ ", getRequestStatus()=" + getRequestStatus()
				+ ", getRequestStatusId()=" + getRequestStatusId()
				+ ", getLeastBidAmount()=" + getLeastBidAmount()
				+ ", getDescription()=" + getDescription()
				+ ", getTotalBids()=" + getTotalBids() + ", getPetType()="
				+ getPetType() + ", getPetNature()=" + getPetNature()
				+ ", getAge()=" + getAge() + ", getServices()=" + getServices()
				+ ", getNumofhours()=" + getNumofhours() + ", getNumofkids()="
				+ getNumofkids() + ", getHomestyle()=" + getHomestyle()
				+ ", getSqFt()=" + getSqFt() + ", getBedrooms()="
				+ getBedrooms() + ", getBathrooms()=" + getBathrooms()
				+ ", getFrom_method()=" + getFrom_method()
				+ ", getBids_open()=" + getBids_open() + ", getTag()="
				+ getTag() + ", getBids()=" + getBids() + ", requestor_id()="
				+ requestor_id() + ", category_id()=" + category_id()
				+ ", leastBid()=" + leastBid() + ", description()="
				+ description() + ", date_needed()=" + date_needed()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}



	public String requestor_id()
	{
		return requestId;
	}
	
	
	public String category_id()
	{
		return categoryId;
	}
	
	
	public String leastBid()
	{
		return leastBidAmount;
	}
	
	public String  description()
	{
		
		return description;
	}
	
	
	public String date_needed()
	{
	return requestStartDate;
	}
	
	
	
	
	
/*	public String location()
	{
		return location;
	}
	
	public String zipcode()
	{
		return zipcode;
	}
	
	
	public String selected_offer()
	{
		return selected_offer;
	}
	*/


	@Override
	public int compareTo(RequestorItems another) {
		// TODO Auto-generated method stub
		return 0;
	}



	public void setBids(ArrayList<Bid> bids) {
		// TODO Auto-generated method stub
		this.bids = bids;
		
	}
	
	
}
