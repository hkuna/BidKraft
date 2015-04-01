package push.classes.to.other.pkg;

public class Bid
{
	
	public	 String bidId, offererUserId, bidAmount, createdDate, offererName, userName, deleted;
 
	//public	 RequestorBids(String bidId, String offererUserId, String bidAmount, String createdDate, String offererName, String userName, String deleted)
	/*public	 Bid(String bidId, String offererUserId, String bidAmount, String createdDate, String offererName, String userName)
	{
	
		this.bidId = bidId;
		this.offererUserId = offererUserId;
		this.bidAmount = bidAmount;
		this.createdDate = createdDate;
		this.offererName = offererName;
		this.userName = userName;
		//this.deleted = deleted;
	}*/
	@Override
	public String toString() {
		return "RequestorBids [bidId=" + bidId + ", offererUserId="
				+ offererUserId + ", bidAmount=" + bidAmount
				+ ", createdDate=" + createdDate + ", offererName="
				+ offererName + ", userName=" + userName + ", deleted="
				+ deleted + "]";
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
