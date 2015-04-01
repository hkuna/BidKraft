package remove.classes.from.the.pkg;

import harish.requestor.commondata.CommonData;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RatingActivity extends Activity implements OnClickListener {

	private RatingBar ratingBar;
	private EditText etComments;
	private Button btnSubmit;
	private ProgressDialog progress;
	ServerConnector mServerConnector;
	float ratingCount;
	int requestId;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_rating);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			requestId =extras.getInt("requestId"); 
		}
	 mServerConnector = new ServerConnector(this);
	 ratingBar = (RatingBar) findViewById(R.id.ratingBar1);
		etComments = (EditText) findViewById(R.id.etComments);

		btnSubmit = (Button) findViewById(R.id.btn_Submit);
		//if click on me, then display the current rating value.
		addListenerOnRatingBar();
		btnSubmit.setOnClickListener(this);
		
	/*	btnSubmit.setOnClickListener(new OnClickListener() {
			
			
		});*/
		//addListenerOnButton();
 


	  }
	 
	  public void addListenerOnRatingBar() {
	 
		
	 
		//if rating value is changed,
		//display the current rating value in the result (textview) automatically
		ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
			public void onRatingChanged(RatingBar ratingBar, float rating,
				boolean fromUser) {
	    ratingCount = rating;
			}
		});
	  }
	
	/*  public void closeRequest(View v)
	  {
		  
	  }*/
	  @Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			progress = new ProgressDialog(RatingActivity.this);
			progress.setMessage("One moment!");
			progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progress.setIndeterminate(false);
			
			progress.show();
			mServerConnector.closeRequest(requestId, CommonData.ROLE_ID_USER, new AsyncHttpResponseHandler() {
				
				@Override
				public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
					// TODO Auto-generated method stub
					
					Response response = CommonData
							.convertGSonObjectToResponseClass(responseBody);
					if (response.getStatus()
							.equalsIgnoreCase("success")) {
						// commented because response is being sent to user with data 
						// update the accepted list and serviced list
						
						
						Toast.makeText(getApplicationContext(), "Submitted, thank you.", Toast.LENGTH_LONG).show();
						CommonData.setAcceptedRequestsData(response.getData().getAcceptedRequests());
						CommonData.setServicedRequestsData(response.getData().getServicedRequests());
						
					//	notifyDataSetChanged();
					finish();
				}
				}
				
				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
					// TODO Auto-generated method stub
					
				}
			});
			CommonData.hideProgressbar(RatingActivity.this, progress);
		}
	  
}

