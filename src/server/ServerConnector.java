package server;

import harish.requestor.commondata.CommonData;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class ServerConnector {

	private String TAG = "Sever Connector";

	private String BASE_URL = "http://54.88.17.100:8080/bidding/";

	private Context context;

	interface DummyResponseListener {
		public void onSuccess(String Resp);

		public void onFailure(String Resp);
	}

	MyAsyncHttpClient mAsyncHttpClient;

	public ServerConnector(Context context) {

		Activity activity = (Activity) context;
		mAsyncHttpClient = new MyAsyncHttpClient(activity);
		mAsyncHttpClient.setURLEncodingEnabled(true);
		this.context = context;
	}

	public void signUp(String fname, String lname, String email,
			String password, String phonenumber, String username,
			AsyncHttpResponseHandler mHandler) {

		JSONObject mRequestParams = new JSONObject();
		

		try {
			mRequestParams.put("userName", username);
			mRequestParams.put("name", fname);
			mRequestParams.put("emailId", email);
			mRequestParams.put("cellPhone", phonenumber);
			mRequestParams.put("password", password);

		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// mAsyncHttpClient.post(BASE_URL + "login", mRequestParams, mHandler);

		try {
			StringEntity entity = new StringEntity(mRequestParams.toString());

			Log.d(TAG, "signup values " + mRequestParams.toString());
			// AsyncHttpClient client = new AsyncHttpClient();
			mAsyncHttpClient.post(context, BASE_URL + "signup", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void checkLogin(String userName, String password, String deviceId,
			AsyncHttpResponseHandler mHandler) {
		JSONObject mRequestParams = new JSONObject();
		try {
			mRequestParams.put("userName", userName);
			mRequestParams.put("password", password);
			mRequestParams.put("deviceId",deviceId);
			mRequestParams.put("deviceType","android");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// mAsyncHttpClient.post(BASE_URL + "login", mRequestParams, mHandler);

		try {
			StringEntity entity = new StringEntity(mRequestParams.toString());
			// AsyncHttpClient client = new AsyncHttpClient();
			mAsyncHttpClient.post(context, BASE_URL + "login", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
 public void updateUserSettings(String roleId, String radius , AsyncHttpResponseHandler mHandler)
 {
	 JSONObject mRequestParams = new JSONObject();
	 try{
		 mRequestParams.put("roleId", roleId);
		 mRequestParams.put("userSettingId",CommonData.getRequesterSettingId());
		 if (roleId.equals("1"))
		 {
			 mRequestParams.put("requesterRadius",radius);
		 }
		 else
			 mRequestParams.put("vendorRadius",radius);
	 }
	 catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 try {
			StringEntity entity = new StringEntity(mRequestParams.toString());
			// AsyncHttpClient client = new AsyncHttpClient();
			mAsyncHttpClient.post(context, BASE_URL + "user/setting/update", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 }

	@SuppressLint("NewApi")
	public void fetchOpenRequest(String userId, String roleId,
			String[] requestStatuses, AsyncHttpResponseHandler mHandler) {
		JSONObject mRequestParams = new JSONObject();
		
		try {

			// make sure you change the string value of role and userid to
			// integer
			JSONArray requeststatuses = new JSONArray(requestStatuses);
			mRequestParams.put("userId", userId);
			mRequestParams.put("roleId", roleId);
			mRequestParams.put("requestStatuses", requeststatuses);
		
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// mAsyncHttpClient.post(BASE_URL + "login", mRequestParams, mHandler);

		try {
			StringEntity entity = new StringEntity(mRequestParams.toString());
			Log.d(TAG, "fetrequest " + mRequestParams.toString());
			// AsyncHttpClient client = new AsyncHttpClient();
			mAsyncHttpClient.post(context, BASE_URL + "request/get", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * public void createBabySittingPost(String desc, String StartDate, int
	 * numHours, int numKids, int lat, int lon, AsyncHttpResponseHandler
	 * mHandler){
	 * 
	 * JSONObject jsonBody = new JSONObject(); try {
	 * 
	 * 
	 * Log.d(TAG, "create post " +
	 * desc+"--"+lat+"--"+lon+"--"+numHours+"--"+numKids);
	 * jsonBody.put("categoryId", 1); jsonBody.put("requesterUserId", 36);
	 * jsonBody.put("description", desc); jsonBody.put("requestStartDate",
	 * "Sat, 27 Nov 2014 10:00:00 EDT"); jsonBody.put("latitude", lat);
	 * jsonBody.put("longitude", lon); jsonBody.put("numberOfHours", numHours);
	 * jsonBody.put("numberOfKids", numKids);
	 * 
	 * Log.d(TAG, "create post 2 " + jsonBody.toString());
	 * 
	 * //Log.d(tag,"the json string is"+jsonBody.toString());
	 * 
	 * } catch (JSONException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * 
	 * try { StringEntity entity = new StringEntity(jsonBody.toString());
	 * 
	 * mAsyncHttpClient.post(context, BASE_URL + "request/create", entity,
	 * "application/json", mHandler);
	 * 
	 * } catch (UnsupportedEncodingException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	
	public void createHomerepairPost(String desc, String StartDate,
			String totime, String fromtime, int numberOfKids, int lat, int lon,
			AsyncHttpResponseHandler mHandler) {

		JSONObject jsonBody = new JSONObject();

		try {
			Log.d(TAG, "create post " + desc + "--" + lat + "--" + lon + "--"
					+ fromtime + "--" + totime);
			jsonBody.put("categoryId", 1);
			jsonBody.put("requesterUserId", CommonData.getUserId());
			jsonBody.put("description", desc);
			
			
			jsonBody.put("latitude", lat);
			jsonBody.put("longitude", lon);
			jsonBody.put("requestStartTime", fromtime);
			jsonBody.put("requestEndTime", totime);
			jsonBody.put("numberOfKids", numberOfKids);
			
		
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String dateInString = StartDate;
			Date date = formatter.parse(dateInString);
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		
			
			
			jsonBody.put("requestStartDate",  sdf.format(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d(TAG, "the json string is" + jsonBody.toString());

		try {
			StringEntity entity = new StringEntity(jsonBody.toString());

			mAsyncHttpClient.post(context, BASE_URL + "request/create", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

	}
	
	public void createService(String jobTitle ,int categoryId,String description , String requestStartDate , String requestEndDate  ,String bidEndDateTime,Object [] tags, AsyncHttpResponseHandler mHandler)
	{
		JSONObject jsonBody = new JSONObject();
		
		String[] alltags = Arrays.copyOf(tags, tags.length, String[].class);
		
try {
	        JSONArray tag = new JSONArray(alltags);
			jsonBody.put("categoryId", categoryId);
			jsonBody.put("requesterUserId", CommonData.getUserId());
			jsonBody.put("description",description);
			jsonBody.put("tags",tag);
			jsonBody.put("requestEndDate", convertDate(requestEndDate));
			jsonBody.put("requestStartDate",convertDate(requestStartDate));
			jsonBody.put("bidEndDateTime",convertDate(bidEndDateTime));
			jsonBody.put("tags",tag);
			jsonBody.put("jobTitle",jobTitle);	
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d(TAG, "the json string is" + jsonBody.toString());

		try {
			StringEntity entity = new StringEntity(jsonBody.toString());

			mAsyncHttpClient.post(context, BASE_URL + "request/create", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		
	}
	
	
	private String convertDate(String inputDate) {
		
		String outputDate =  null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("ddd MMM dd HH:mm:ss zzz yyyy");
			Date date = formatter.parse(inputDate);
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
			outputDate = sdf.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	 return outputDate;
	
	}

	public void createTutoringservice(String requestedDate, String fromTime , String toTime , String subjects , String description,AsyncHttpResponseHandler mHandler )
	{
		JSONObject jsonBody = new JSONObject();
		
		try {
			
			jsonBody.put("categoryId", 5);
			jsonBody.put("requesterUserId", CommonData.getUserId());
			jsonBody.put("tutoringSubject",subjects);
			jsonBody.put("description", description);
			
			jsonBody.put("requestStartTime", fromTime);
			jsonBody.put("requestEndTime", toTime);
			jsonBody.put("latitude", 10);
			jsonBody.put("longitude", 11);
			
		
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String dateInString = requestedDate;
			Date date = formatter.parse(dateInString);
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		
			
			
			jsonBody.put("requestStartDate",  sdf.format(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d(TAG, "the json string is" + jsonBody.toString());

		try {
			StringEntity entity = new StringEntity(jsonBody.toString());

			mAsyncHttpClient.post(context, BASE_URL + "request/create", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		
	}
	
	
	public void createSellBookService(String title, String author , String edition , String requestedOn ,String description, String cost,AsyncHttpResponseHandler mHandler )
	{
		JSONObject jsonBody = new JSONObject();
		
		try {
			
			jsonBody.put("categoryId", 6);
			jsonBody.put("requesterUserId", CommonData.getUserId());
		
			jsonBody.put("description", description);
			jsonBody.put("bookTitle",title );
			jsonBody.put("latitude", 10);
			jsonBody.put("longitude", 11);
			jsonBody.put("isbn", "12345");
			jsonBody.put("author",author);
			jsonBody.put("edition", edition);
			jsonBody.put("cost", cost);
			
		
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			String dateInString = requestedOn;
			Date date = formatter.parse(dateInString);
			
			SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
		
			
			
			jsonBody.put("requestStartDate",  sdf.format(date));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log.d(TAG, "the json string is" + jsonBody.toString());

		try {
			StringEntity entity = new StringEntity(jsonBody.toString());

			mAsyncHttpClient.post(context, BASE_URL + "request/create", entity,
					"application/json", mHandler);

		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		
	}
	

	public void vendorPlacedBids(String userId,
			AsyncHttpResponseHandler mHandler) {
		JSONObject mRequestParams = new JSONObject();
		try {
			mRequestParams.put("userId", userId);

			StringEntity entity = new StringEntity(mRequestParams.toString());
			Log.d(TAG, "inside vendor placed bids" + mRequestParams.toString());
			mAsyncHttpClient.post(context, BASE_URL + "bid/my-bids", entity,
					"application/json", mHandler);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void acceptBid(int requestId, int BidId,
			AsyncHttpResponseHandler mHandler) {
		Log.d(TAG, "server connector accept Bid called" + requestId + " , "
				+ BidId);
		JSONObject mRequestParams = new JSONObject();
		try {
			mRequestParams.put("userId", CommonData.getUserId());
			mRequestParams.put("requestId", requestId);
			mRequestParams.put("bidId", BidId);

			StringEntity entity = new StringEntity(mRequestParams.toString());

			mAsyncHttpClient.post(context, BASE_URL + "bid/accept", entity,
					"application/json", mHandler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public void deleteRequest(String requestId, AsyncHttpResponseHandler mHandler) {
		Log.d(TAG, "server connector" + requestId + " , " + requestId);
		JSONObject mRequestParams = new JSONObject();
		try {
			mRequestParams.put("requestId", requestId);
			mRequestParams.put("userId" ,CommonData.getUserId());

			StringEntity entity = new StringEntity(mRequestParams.toString());

			mAsyncHttpClient.post(context, BASE_URL + "request/delete", entity,
					"application/json", mHandler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void refreshData(String userId) {

		// http://rikers.cs.odu.edu:8080/bidding/request/get
		
	}

	public void placeBid(String requestId, String offererUserId,
			String bidAmount, AsyncHttpResponseHandler mHandler) {
		Log.d(TAG, "server connector" + requestId + " , " + requestId
				+ offererUserId + bidAmount); 
		JSONObject mRequestParams = new JSONObject();
		try {
			mRequestParams.put("requestId", Integer.parseInt(requestId));
			mRequestParams.put("offererUserId", Integer.parseInt(offererUserId));
			mRequestParams.put("bidAmount", bidAmount);

			StringEntity entity = new StringEntity(mRequestParams.toString());

			mAsyncHttpClient.post(context, BASE_URL + "bid/create", entity,
					"application/json", mHandler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeRequest(String string,AsyncHttpResponseHandler mHandler) {
		

			StringEntity entity;
			try {
				Log.d(TAG, "server connector close request data " +string.toString() );
				entity = new StringEntity(string.toString());
				mAsyncHttpClient.post(context, BASE_URL
						+ "request/status/update/serviced", entity,
						"application/json", mHandler);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			
		
	}
	
	
	public void deleteBid( String requestId, String offererUserId ,AsyncHttpResponseHandler mHandler)
	{
		JSONObject mRequestParams = new JSONObject();
		try {
			mRequestParams.put("requestId", requestId);
			mRequestParams.put("offererUserId",offererUserId);

			StringEntity entity = new StringEntity(mRequestParams.toString());

			mAsyncHttpClient.post(context, BASE_URL
					+ "bid/delete", entity,
					"application/json", mHandler);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}