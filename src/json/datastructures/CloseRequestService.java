package json.datastructures;

public class CloseRequestService {
	int requestId;
	int userId;
	int roleId;
	UserReview userReview;
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public UserReview getUserReview() {
		return userReview;
	}
	public void setUserReview(UserReview userReview) {
		this.userReview = userReview;
	}

}
