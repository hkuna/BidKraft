package json.datastructures;


public class UserPreferences {

	String [] categoryIds;
	RequestorSettings requesterSettings;
	VendorSettings vendorSettings;
	
	public String[] getCategoryIds() {
		return categoryIds;
	}
	public void setCategoryIds(String[] categoryIds) {
		this.categoryIds = categoryIds;
	}
	public RequestorSettings getRequesterSettings() {
		return requesterSettings;
	}
	public void setRequesterSettings(RequestorSettings requesterSettings) {
		this.requesterSettings = requesterSettings;
	}
	public VendorSettings getVendorSettings() {
		return vendorSettings;
	}
	public void setVendorSettings(VendorSettings vendorSettings) {
		this.vendorSettings = vendorSettings;
	}
}
