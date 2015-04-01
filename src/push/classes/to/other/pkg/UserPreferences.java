package push.classes.to.other.pkg;

public class UserPreferences {

	RequestorSettings requesterSettings;
	VendorSettings vendorSettings;
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
