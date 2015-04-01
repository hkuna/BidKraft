package push.classes.to.other.pkg;


public class Response {
	
	String status ;
	Data data;

	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [status=" + status + ", data=" + data + "]";
	}

	
	
}
