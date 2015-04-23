package harish.requestor.commondata;

import harish.requestor.role.Requestor_HomeActivity;

import java.io.File;
import java.util.ArrayList;

import push.classes.to.other.pkg.PlacedBidsDataStruct;
import push.classes.to.other.pkg.Response;
import push.classes.to.other.pkg.Vendor_Main_Activity;
import json.datastructures.Requestor_Json_Data_Structure;
import json.datastructures.User;
import json.datastructures.UserAddresses;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;


public class CommonData {
	

    public static boolean signUpflag = false;
	public static boolean isSignUpflag() {
		return signUpflag;
	}
	public static   ArrayList<Requestor_Json_Data_Structure> openRequestsData;
	public static	ArrayList<Requestor_Json_Data_Structure> servicedRequestsData;
	public static	ArrayList<Requestor_Json_Data_Structure> openBidsData;
	public static   ArrayList<Requestor_Json_Data_Structure> placedBidsData;
	public static   ArrayList<Requestor_Json_Data_Structure> acceptedRequestsData;
	public static final int ROLE_ID_USER = 1;
	public static final int ROLE_ID_VENDOR = 2;
   public static String userId;
	
	public static final int OpenRequestFragment= 11 , AcceptedRequestsFragment = 22, CompletedRequestsFragment =33 , OpenBidsFragment =44 , PlacedBidsFragment =55 , BidsWonFragment=66;
	
	public static final String [] requestStatuses = {"OPEN", "BID_ACCEPT", "SERVICED", "CANCELLED", "CLOSED"};
	public static void setSignUpflag(boolean signUpflag) {
		CommonData.signUpflag = signUpflag;
	}
	public static String requesterSettingId , vendorSettingId,requestorRadius,vendorRadius;
	

	public static String getRequesterSettingId() {
		return requesterSettingId;
	}

	public static void setRequesterSettingId(String requesterSettingId) {
		CommonData.requesterSettingId = requesterSettingId;
	}

	public static String getVendorSettingId() {
		return vendorSettingId;
	}

	public static void setVendorSettingId(String vendorSettingId) {
		CommonData.vendorSettingId = vendorSettingId;
	}

	public static String getRequestorRadius() {
		return requestorRadius;
	}

	public static void setRequestorRadius(String requestorRadius) {
		CommonData.requestorRadius = requestorRadius;
	}

	public static String getVendorRadius() {
		return vendorRadius;
	}

	public static void setVendorRadius(String vendorRadius) {
		CommonData.vendorRadius = vendorRadius;
	}
	public static final String auction = "auction";
	public static String token;
	public static UserAddresses mUserAddresses;
	
	public static UserAddresses getmUserAddresses() {
		return mUserAddresses;
	}

	public static void setmUserAddresses(UserAddresses mUserAddresses) {
		CommonData.mUserAddresses = mUserAddresses;
	}
	public static User userDetails;
	public static User getUserDetails() {
		return userDetails;
	}

	public static void setUserDetails(User userDetails) {
		CommonData.userDetails = userDetails;
	}
	public static int roleId1;
	static int requestCode = 1;
	static String TAG = "CommonData";
	public final static String placedBidsFragment = "placedBids";
	public final static String openBidsForVendorFragment = "openBids";
	public final static String userCurrentRequestsFragment = "userCurrentRequests";
	public final static String userPendingServicesFragment = "userPendingServices";


	final static String BidsFragment = "Ope";

	public CommonData() {
		acceptedRequestsData = new ArrayList<Requestor_Json_Data_Structure>();
		openRequestsData = new ArrayList<Requestor_Json_Data_Structure>();
		servicedRequestsData = new ArrayList<Requestor_Json_Data_Structure>();
		placedBidsData = new ArrayList<Requestor_Json_Data_Structure>();
		openBidsData = new ArrayList<Requestor_Json_Data_Structure>();

	}
	
	private static String profilepic;
	
	

	public static String getProfilepic() {
		return profilepic;
	}

	public static void setProfilepic(String profilepic) {
		CommonData.profilepic = profilepic;
	}

	public static void setRequestCode(int requestCode) {
		CommonData.requestCode = requestCode;
	}

	public static int getTabIcons(int i) {
		final int[] ICONS = new int[] { R.drawable.openrequest,
				R.drawable.acceptedbids, R.drawable.createrequest,
				R.drawable.completedrequests, R.drawable.settings, };
		return ICONS[i];
	}

	public static int getCategoryBackground(int i) {
		final int[] categoryBackground = new int[] {
				R.drawable.babysitbackground, R.drawable.books,
				R.drawable.tutoringbackground	 };
		return categoryBackground[i];
	}

	

	


	public static ArrayList<Requestor_Json_Data_Structure> getOpenRequestsData() {
		return openRequestsData;
	}

	public static void setOpenRequestsData(
			ArrayList<Requestor_Json_Data_Structure> openRequestsData) {
		CommonData.openRequestsData = openRequestsData;
	}

	public static ArrayList<Requestor_Json_Data_Structure> getServicedRequestsData() {
		return servicedRequestsData;
	}

	public static void setServicedRequestsData(
			ArrayList<Requestor_Json_Data_Structure> servicedRequestsData) {
		CommonData.servicedRequestsData = servicedRequestsData;
	}

	public static ArrayList<Requestor_Json_Data_Structure> getOpenBidsData() {
		return openBidsData;
	}

	public static void setOpenBidsData(
			ArrayList<Requestor_Json_Data_Structure> openBidsData) {
		CommonData.openBidsData = openBidsData;
	}

	public static ArrayList<Requestor_Json_Data_Structure> getPlacedBidsData() {
		return placedBidsData;
	}

	public static void setPlacedBidsData(
			ArrayList<Requestor_Json_Data_Structure> placedBidsData) {
		CommonData.placedBidsData = placedBidsData;
	}

	public static ArrayList<Requestor_Json_Data_Structure> getAcceptedRequestsData() {
		return acceptedRequestsData;
	}

	public static void setAcceptedRequestsData(
			ArrayList<Requestor_Json_Data_Structure> acceptedRequestsData) {
		CommonData.acceptedRequestsData = acceptedRequestsData;
	}

	public static int getRequestCode() {
		return requestCode;
	}

	public static int getRoleId1() {
		return roleId1;
	}

	public static void setRoleId1(int roleId) {
		CommonData.roleId1 = roleId1;
	}

	public static String getToken() {
		return token;
	}

	public static void setToken(String token) {
		CommonData.token = token;
	}

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		CommonData.userId = userId;
	}

	public static String getAuction() {
		return auction;
	}

	public static ProgressDialog showProgressBar(Context ctx) {
		Log.d("Common DATA", "inside show progress bar" + ctx);
		ProgressDialog progress;
		progress = new ProgressDialog(ctx);
		progress.setMessage("One Moment");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(false);
		return progress;
	}

	public static void hideProgressbar(Context ctx, ProgressDialog progress) {
		progress.dismiss();

	}

	public static String onLoginOrRegistrationListener(Response response,
			Context mContext) {
		if (response.getStatus().equalsIgnoreCase("success")) {
			// set all the common data
			CommonData.setToken(response.getData().getToken());
			CommonData.setUserId(response.getData().getUserId());
			Log.d(TAG,
					"reading response value after sihn up"
							+ CommonData.getToken() + CommonData.getUserId());
		
				Intent signUpToHome = new Intent(mContext,
						Requestor_HomeActivity.class);
				mContext.startActivity(signUpToHome);
				((Activity) mContext).finish();
			
		}

		return response.getStatus();

	}

	public static Response convertGSonObjectToResponseClass(byte[] responseBody) {
		String resp = new String(responseBody);
        Gson gson = new Gson();
        Response response = gson.fromJson(resp, Response.class);
		return response;

	}

	public static void setCategoryImage(String categoryid, View vi,
			Context context, ImageView categoryidimage) {

		switch (Integer.parseInt(categoryid)) {
		case 1:

			Log.d(TAG, "inside switch " + Integer.parseInt(categoryid));

			categoryidimage.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.rigid_baby_icon));
			break;
		case 2:
			categoryidimage.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.pet_icon));
			break;
		case 3:
			categoryidimage.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.tutor_icon));
			break;
		case 4:
			categoryidimage.setImageDrawable(context.getResources()
					.getDrawable(R.drawable.textbook_icon));
			
		}

	}
	
	public static Bitmap createBitmap(String imageFilePath)
    {
		Bitmap  dummyBitmap = BitmapFactory.decodeFile(imageFilePath);
		 int rotate = 0;
		 try {
         
             File imageFile = new File(imageFilePath);
             ExifInterface exif = new ExifInterface(
                     imageFile.getAbsolutePath());
             int orientation = exif.getAttributeInt(
                     ExifInterface.TAG_ORIENTATION,
                     ExifInterface.ORIENTATION_NORMAL);

             switch (orientation) {
             case  ExifInterface.ORIENTATION_ROTATE_270:
                 rotate-=90;break;
            case  ExifInterface.ORIENTATION_ROTATE_180:
                 rotate-=90;break;
            case  ExifInterface.ORIENTATION_ROTATE_90:
                 rotate-=90;break;
            
             }
             Log.v("In CommonData Class", "Exif orientation: " + orientation);
         } catch (Exception e) {
             e.printStackTrace();
         }

         /****** Image rotation ****/
       
         Matrix matrix = new Matrix();
         matrix.postRotate(rotate);
         Bitmap cropped = Bitmap.createBitmap(dummyBitmap, 0, 0, dummyBitmap.getWidth(), dummyBitmap.getHeight(), matrix, true);
         return cropped;
    }
}
