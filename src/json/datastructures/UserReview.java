package json.datastructures;

public class UserReview {

	int reviewerUserId;
	int revieweeUserId;
	int requestId;
	float rating;
	String comment;
	
	
	public int getReviewerUserId() {
		return reviewerUserId;
	}
	public void setReviewerUserId(int reviewerUserId) {
		this.reviewerUserId = reviewerUserId;
	}
	public int getRevieweeUserId() {
		return revieweeUserId;
	}
	public void setRevieweeUserId(int revieweeUserId) {
		this.revieweeUserId = revieweeUserId;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float ratingCount) {
		this.rating = ratingCount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
