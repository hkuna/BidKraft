package json.datastructures;

public class Bid
{
	
	public	 String bidId, offererUserId, bidAmount, createdDate, offererName, userName, deleted;
	float offererRating;
	int numOfRatings;
	


	@Override
	public String toString() {
		return "RequestorBids [bidId=" + bidId + ", offererUserId="
				+ offererUserId + ", bidAmount=" + bidAmount
				+ ", createdDate=" + createdDate + ", offererName="
				+ offererName + ", userName=" + userName + ", deleted="
				+ deleted + "]";
	}
	public float getOffererRating() {
		return offererRating;
	}


	public void setOffererRating(float offererRating) {
		this.offererRating = offererRating;
	}


	public int getNumOfRatings() {
		return numOfRatings;
	}


	public void setNumOfRatings(int numOfRatings) {
		this.numOfRatings = numOfRatings;
	}
	
	public String getBidId() {
		return bidId;
	}
	public void setBidId(String bidId) {
		this.bidId = bidId;
	}
	public String getOffererUserId() {
		return offererUserId;
	}
	public void setOffererUserId(String offererUserId) {
		this.offererUserId = offererUserId;
	}
	public String getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(String bidAmount) {
		this.bidAmount = bidAmount;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public String getOffererName() {
		return offererName;
	}
	public void setOffererName(String offererName) {
		this.offererName = offererName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	public String get_biddingamount()
	{
		return bidAmount;
	}
	
}
