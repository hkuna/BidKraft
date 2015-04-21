package push.classes.to.other.pkg;

import json.datastructures.UserData;


public class Response {
	
	String status ;
	UserData data;

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserData getData() {
		return data;
	}
	public void setData(UserData data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}

	
	
}
