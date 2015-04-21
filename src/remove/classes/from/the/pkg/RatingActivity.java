package remove.classes.from.the.pkg;

import harish.requestor.commondata.CommonData;
import json.datastructures.CloseRequestService;
import json.datastructures.UserReview;

import org.apache.http.Header;


import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RatingActivity extends Activity implements OnClickListener , OnRatingBarChangeListener {

	private RatingBar ratingBar;
	private EditText etComments;
	private Button btnSubmit;
	private ProgressDialog progress;
	ServerConnector mServerConnector;
	float ratingCount;
	int requestId;
	int onChildListItemClicked,onListItemClickedId;
	String Tag = "RatingActivity";
	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_rating);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			requestId =extras.getInt("requestId"); 
			onChildListItemClicked = extras.getInt("onChildListItemClicked");
			onListItemClickedId = extras.getInt("onListItemClickedId");
		}
	    mServerConnector = new ServerConnector(this);
	    ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		etComments = (EditText) findViewById(R.id.etComments);
		btnSubmit = (Button) findViewById(R.id.btn_Submit);
		btnSubmit.setOnClickListener(this);
		ratingBar.setOnRatingBarChangeListener((OnRatingBarChangeListener) this);
	
	  }
	
	  @Override
		public void onClick(View v) {

		  switch(v.getId())
		  {
		  case R.id.btn_Submit:
			  closeService();
			  break;
		  }		
		}
	  
	  
	  private void closeService()
	  {
		  
		    progress = new ProgressDialog(RatingActivity.this);
			progress.setMessage("One moment!");
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIndeterminate(false);
			progress.show();
			
			CloseRequestService mCloseRequest = new CloseRequestService();

			UserReview userReview = new UserReview();
			
			userReview.setReviewerUserId(Integer.parseInt(CommonData.getUserId()));
			userReview.setRevieweeUserId(Integer.parseInt(CommonData.acceptedRequestsData.get(onListItemClickedId).getBids().get(onChildListItemClicked).getOffererUserId()));
			userReview.setRequestId(requestId);
			userReview.setRating(ratingCount);
			userReview.setComment(etComments.getText().toString());
			mCloseRequest.setRequestId(requestId);
			mCloseRequest.setUserId(Integer.parseInt(CommonData.userId));
			mCloseRequest.setRoleId(CommonData.ROLE_ID_USER);
			
			mCloseRequest.setUserReview(userReview);
			
			notifyServer(new Gson().toJson(mCloseRequest).toString());

		
		  
	  }

	private void notifyServer(String jsonString) {
		// TODO Auto-generated method stub
		mServerConnector.closeRequest(jsonString,new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				Response response = CommonData
						.convertGSonObjectToResponseClass(responseBody);
				if (response.getStatus()
						.equalsIgnoreCase("success")) {
					
					
					Toast.makeText(getApplicationContext(), "Submitted, thank you.", Toast.LENGTH_LONG).show();
					CommonData.setAcceptedRequestsData(response.getData().getAcceptedRequests());
					CommonData.setServicedRequestsData(response.getData().getServicedRequests());
					CommonData.hideProgressbar(RatingActivity.this, progress);
				     finish();
			}
				else
				{
					CommonData.hideProgressbar(RatingActivity.this, progress);
					Log.d(Tag, "Error message"+responseBody.toString());
					Toast.makeText(getApplicationContext(), "Server sent errror .", Toast.LENGTH_LONG).show();
				     finish();
				}
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				CommonData.hideProgressbar(RatingActivity.this, progress);
				Toast.makeText(getApplicationContext(), "Unable to submit.", Toast.LENGTH_LONG).show();
				
			}
		});
		
	}






	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		ratingCount = rating;
		
	}
}

