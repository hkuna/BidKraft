package remove.classes.from.the.pkg;

import harish.custom.view.ExpandableDropView;
import harish.listadapter.uservendor.UserMainListAdapter;
import harish.listadapter.uservendor.VendorMainListAdapter;
import harish.requestor.commondata.CommonData;

import java.util.ArrayList;

import json.datastructures.Requestor_Json_Data_Structure;

import org.apache.http.Header;

import push.classes.to.other.pkg.PlacedBidsDataStruct;
import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class AuctionDetails extends Activity implements
android.view.View.OnClickListener  {



	private String tag = "AuctionDetails";

	private int onListItemClickedId;
	ScrollView sv;
	private VendorMainListAdapter mVendorMainListAdapter;
	private UserMainListAdapter mUserMainListAdapter;

	ArrayList<Requestor_Json_Data_Structure> auctionrequest;

	ArrayList<PlacedBidsDataStruct> auction_placedbids;
	Bundle extras;
	ListView offerslist;
	ServerConnector mServerConnector;
	String fromfragment;
	String role;
	ExpandableDropView ed;
	ListView lv;	
	View rowvi ;
	TextView tv_date , tv_username ,tv_description , tv_biduser , tv_bidtime , tv_bidamount, tv_timeleft, tv_lowestbid, tv_vendorbidamount;
	Button btn_bid;
	CheckBox cb_accept_or_complete;
	EditText et_bidValue;
	int whichFragment=11;
	LinearLayout llForBidderslist;
	private ProgressDialog progress;

	@Override         
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details);
		extras = getIntent().getExtras();
		mServerConnector = new ServerConnector(this);

		//setting the custom bar
		setCustomActionBar();


		tv_date = (TextView) findViewById(R.id.tv_date);
		tv_username = (TextView) findViewById(R.id.tv_username);
		tv_description =(TextView) findViewById(R.id.tv_description);
		tv_lowestbid =(TextView) findViewById(R.id.tv_lowestbid);
		tv_timeleft = (TextView) findViewById(R.id.tv_timeleft);
		btn_bid = (Button) findViewById(R.id.btn_bid);
		btn_bid.setOnClickListener(this);
		et_bidValue = (EditText) findViewById(R.id.et_bidAmount);
		preRequistesForPopulatingData();


		}



	private void getBidAmount() {
		AlertDialog.Builder alert = new AlertDialog.Builder(AuctionDetails.this);

		alert.setTitle("Bid Amount");
		alert.setMessage("Please Enter Bid Amount");
		final EditText input = new EditText(AuctionDetails.this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		input.setLayoutParams(lp);
		alert.setView(input);
		alert.setPositiveButton("Yes",
				new android.content.DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final ProgressDialog progress = CommonData
						.showProgressBar(AuctionDetails.this);
				progress.show();

				mServerConnector.placeBid(
						CommonData.getOpenBidsData().get(onListItemClickedId)
						.getRequestId(),
						CommonData.getUserId(), input.getText()
						.toString(),
						new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int statusCode,
									Header[] headers,
									byte[] responseBody) {
								// TODO Auto-generated method stub

								Response response = CommonData
										.convertGSonObjectToResponseClass(responseBody);

								// after succesfully placing bid , the
								// bid should be moved from open bids to
								// placed bids , but cannot be moved
								// because the structure is different

								if (response.getStatus()
										.equalsIgnoreCase("success")) {

									// CommonData.vendorPlacedBidsData.add(CommonData.vendorOpenBidsData.get(onListItemClickedId).getBids());


									CommonData.setOpenBidsData(response.getData().getOpenBids());

									CommonData.setPlacedBidsData(response.getData().getPlacedBids());

									Log.d(tag,"bid placed response for open bids" +response.getData().getOpenBids() );

									Log.d(tag," bid placed response for placed bids"+response.getData().getPlacedBids());

									finish();
								}

							}

							@Override
							public void onFailure(int statusCode,
									Header[] headers,
									byte[] responseBody, Throwable error) {
								// TODO Auto-generated method stub

							}
						});
				CommonData.hideProgressbar(AuctionDetails.this,
						progress);
			}
		});

		alert.setNegativeButton("CANCEL",
				new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

		alert.show();

	}



	/*private void deleteRequest() {
	// TODO Auto-generated method stub
	mServerConnector.deleteRequest(Integer.valueOf(CommonData.getOpenRequestsData().get(onListItemClickedId).getRequestId()), new AsyncHttpResponseHandler() {

		@Override
		public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
Response response =		 CommonData.convertGSonObjectToResponseClass(responseBody);
		 if(response.getStatus().equalsIgnoreCase("success"))
		 {
			 CommonData.setOpenRequestsData(response.getData().getOpenRequests());
			 finish();
		 }

		}

		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error) {
			// TODO Auto-generated method stub

		}
	});

}*/

	private int getFromFragment(String fromfragment) {

		if (fromfragment.equalsIgnoreCase("User_Current_Requests")) {
			return 1;
		} else if (fromfragment.equalsIgnoreCase("UserPendingServices")) {
			return 2;
		} else { // servicedFragment
			return 3;

		}

	}

	private void  preRequistesForPopulatingData()
	{
		if (extras != null) {
			onListItemClickedId = (int) extras.getLong("position");
            whichFragment =(int) extras.getInt("FromFragment");
			role = extras.getString("role");
			btn_bid.setOnClickListener(AuctionDetails.this);
			
				populateDataOnUi(getDataForFragment());
		
			
		}

	}

	private void populateDataOnUi(Requestor_Json_Data_Structure temp) {

		tv_username.setText(CommonData.getUserId());
		tv_date.setText(temp.getRequestStartDate());
		tv_description.setText(temp.getDescription());
		
		if(whichFragment< 44)
		{
		  	handleUiElementsForUserView();
		}
		
		drawListViewUnderScrollViewForDisplayingBidDetails(temp);
		
	}

	private void handleUiElementsForUserView() {
      
		btn_bid.setVisibility(View.GONE);
		et_bidValue.setVisibility(View.GONE);
		
		
	}

	private void drawListViewUnderScrollViewForDisplayingBidDetails(Requestor_Json_Data_Structure temp)

	{

		llForBidderslist = new LinearLayout(this);
		llForBidderslist.setOrientation(LinearLayout.VERTICAL);
		llForBidderslist.setBackgroundColor(Color.rgb(236, 240, 241));

		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rowvi = inflater.inflate(R.layout.detailsrow, null);
		tv_biduser = (TextView) rowvi.findViewById(R.id.tv_biduser);
		tv_bidtime = (TextView) rowvi.findViewById(R.id.tv_biddingtime);
		tv_vendorbidamount = (TextView) rowvi.findViewById(R.id.tv_vendorbidvalue);
		cb_accept_or_complete = (CheckBox) rowvi.findViewById(R.id.cb_accept);
		cb_accept_or_complete.setOnClickListener(this);
		
		if(temp.getBids().size()!=0)
		addRowDataForListViewInEachFragment(rowvi, temp);
        
		


	}

	private Requestor_Json_Data_Structure getDataForFragment()
	{
		Requestor_Json_Data_Structure temp =null;
		switch(whichFragment)
		{
		case 11: 
			temp = CommonData.getOpenRequestsData().get(onListItemClickedId);
		break;
		case 22:  
			temp = CommonData.getAcceptedRequestsData().get(onListItemClickedId);
		break;
		case 33:
			temp = CommonData.getServicedRequestsData().get(onListItemClickedId);
			break;
		case 44:
			temp = CommonData.getOpenBidsData().get(onListItemClickedId);
			break;
		case 55:	
			temp = CommonData.getPlacedBidsData().get(onListItemClickedId);
			break;
		case 66:
			break;

		}

		return temp;

		
	}

	private void addRowDataForListViewInEachFragment(View rowvi , Requestor_Json_Data_Structure temp) {
		for(int i=0 ; i< temp.getBids().size(); i++)
		{
			tv_biduser.setText(temp.getBids().get(i).getOffererName());
			tv_bidtime.setText("5:32 m");
			tv_vendorbidamount.setText(temp.getBids().get(i).getBidAmount());
			cb_accept_or_complete.setTag(i);
			cb_accept_or_complete.setOnClickListener(AuctionDetails.this);
			// here set the data 	
			llForBidderslist.addView(rowvi, i);
			
			
		}
 
		ed = (ExpandableDropView) findViewById(R.id.expandableView);
		sv = (ScrollView) findViewById(R.id.sv);
		ed.setChildView(llForBidderslist, sv); // adding view with data


	}

	
	private void setCustomActionBar()
	{
		ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mRequestTitle = (TextView) mCustomView.findViewById(R.id.tv_request_subject);


		ImageButton ib_roleicon = (ImageButton) mCustomView
				.findViewById(R.id.ib_roleIcon);

		ib_roleicon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//handle the role icon action
			}
		});


		bar.setCustomView(mCustomView);
		bar.setDisplayShowCustomEnabled(true);

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {

		case R.id.btn_bid: 
			getBidAmount();
			break;

		case R.id.cb_accept:
			if(whichFragment==CommonData.OpenRequestFragment)
			  notifyAcceptedBidToServer(v.getTag());
			else
			 notifyCompleteBidsToServer(v.getTag());

		}

	}
	
	private void notifyCompleteBidsToServer(Object tag2) {
		Intent i = new Intent(AuctionDetails.this, RatingActivity.class);
		i.putExtra("requestId",Integer.parseInt(CommonData.getAcceptedRequestsData().get(onListItemClickedId).getRequestId()));
		startActivity(i);
		finish();
		
	
		mServerConnector.closeRequest(Integer.parseInt(CommonData.getAcceptedRequestsData().get(onListItemClickedId).getRequestId()), CommonData.ROLE_ID_USER, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
		Response response = CommonData
						.convertGSonObjectToResponseClass(responseBody);
		
		if (response.getStatus()
				.equalsIgnoreCase("success")) {
			CommonData.setAcceptedRequestsData(response.getData().getAcceptedRequests());
			CommonData.setServicedRequestsData(response.getData().getServicedRequests());
			Intent i = new Intent(AuctionDetails.this, RatingActivity.class);
			i.putExtra("requestId",Integer.parseInt(CommonData.getAcceptedRequestsData().get(onListItemClickedId).getRequestId()));
					startActivity(i);
	       
		//CommonData.hideProgressbar(AuctionDetails.this, progress);
		finish();
	}
		
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}



	private void notifyAcceptedBidToServer(Object rowId) {

		// TODO Auto-generated method stub
	   int clickedid=  (Integer)rowId;
	   
	   mServerConnector.acceptBid(Integer.parseInt(CommonData.getOpenRequestsData().get(onListItemClickedId).getRequestId()), Integer.parseInt(CommonData.getOpenRequestsData().get(onListItemClickedId).getBids().get(clickedid).getBidId()), new AsyncHttpResponseHandler() {
		
		@Override
		public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
			// TODO Auto-generated method stub
			Response response = CommonData
					.convertGSonObjectToResponseClass(responseBody);

			if (response.getStatus().equals("success")) {
				CommonData.setOpenRequestsData(response
						.getData().getOpenRequests());
				CommonData.setAcceptedRequestsData(response
						.getData().getAcceptedRequests());
				AuctionDetails.this.finish();
				
				
				
			}
		}
		
		@Override
		public void onFailure(int statusCode, Header[] headers,
				byte[] responseBody, Throwable error) {
			// TODO Auto-generated method stub
			
		}
	});
	   
	   
	}



	private void callProgressBarOnUI()
	{
		progress = new ProgressDialog(AuctionDetails.this);
		progress.setMessage("One moment!");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(false);
		progress.show();
		
	}



	
}