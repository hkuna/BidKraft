package remove.classes.from.the.pkg;

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
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class AuctionDetails extends ListActivity implements
		android.view.View.OnClickListener {

	private TextView tvUserName, tvDescription, offers, timeLeft;

	private String tag = "AuctionDetails";
	private Button placeBid_Or_CancelRequest;
	private int onListItemClickedId;
	private VendorMainListAdapter mVendorMainListAdapter;
	private UserMainListAdapter mUserMainListAdapter;

	ArrayList<Requestor_Json_Data_Structure> auctionrequest;

	ArrayList<PlacedBidsDataStruct> auction_placedbids;
	Bundle extras;
	ListView offerslist;
	ServerConnector mServerConnector;
	String fromfragment;
	String role;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auction_details);
		extras = getIntent().getExtras();
		
		
		//setting the custom bar
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
				// TODO Auto-generated method stub
				
				//handle the role icon action
			}
		});
		
		
		bar.setCustomView(mCustomView);
		bar.setDisplayShowCustomEnabled(true);
		
		// end of custom action bar setting
		

		placeBid_Or_CancelRequest = (Button) findViewById(R.id.placecancl);
		tvDescription = (TextView) findViewById(R.id.description);
		timeLeft = (TextView) findViewById(R.id.timeleft);
		offers = (TextView) findViewById(R.id.offers);
		tvUserName = (TextView) findViewById(R.id.username);
		placeBid_Or_CancelRequest.setOnClickListener(this);
		// checking if the control came from user side or vendor side to display
		// cancel or place bid based on it

		if (extras != null) {
			onListItemClickedId = (int) extras.getLong("position");
			fromfragment = extras.getString("fromfragment");
			role = extras.getString("role");
			placeBid_Or_CancelRequest.setOnClickListener(this);
			
			if (role.equalsIgnoreCase("vendor")) {
				
				if (fromfragment.equalsIgnoreCase(
						CommonData.openBidsForVendorFragment))    // open bids
				{
					tvDescription.setText("Description : "+CommonData.getOpenBidsData().get(onListItemClickedId).getDescription());
					tvUserName.setText("UserName : "+CommonData.getUserId());
					timeLeft.setText("Request Start Date : "+CommonData.getOpenBidsData().get(onListItemClickedId).getRequestStartDate().subSequence(0, 16));
					
					placeBid_Or_CancelRequest.setText("Place Bid");
					Log.d(tag, "reached open bid control inside auctions page");

					if(CommonData.getOpenBidsData().get(onListItemClickedId).getBids().size()!=0)
					{
						getListView().setVisibility(View.VISIBLE);
					mVendorMainListAdapter = new VendorMainListAdapter(
							AuctionDetails.this,
							fromfragment, "auction",
							onListItemClickedId);
					setListAdapter(mVendorMainListAdapter);
					}
					else
					{getListView().setVisibility(View.GONE);
					offers.setText("No offers");
					}
					

				}
				
				else
				{
					placeBid_Or_CancelRequest.setVisibility(View.GONE);
					Log.d(tag, "reached placed bid control inside auctions page");
					tvDescription.setText("Description : "+CommonData.getPlacedBidsData().get(onListItemClickedId).getDescription());
					tvUserName.setText("UserName : "+CommonData.getUserId());
					timeLeft.setText("Request Start Date : "+CommonData.getPlacedBidsData().get(onListItemClickedId).getRequestStartDate().subSequence(0, 16));
					
					if(CommonData.getPlacedBidsData().get(onListItemClickedId).getBids().size()!=0)
					{
						getListView().setVisibility(View.VISIBLE);
					mVendorMainListAdapter = new VendorMainListAdapter(
							AuctionDetails.this,
							fromfragment, "auction",
							onListItemClickedId);
					setListAdapter(mVendorMainListAdapter);
					}
					else
					{getListView().setVisibility(View.GONE);
					offers.setText("No offers");
					}
					
				}
				// else if () for vendor [placed bids , disable placed bid
				// button
			}
			
			// user auction page
			
			else {           
				onListItemClickedId = (int) extras.getLong("position");
				Log.d(tag, "clicked id " + onListItemClickedId);
		
				mUserMainListAdapter = new UserMainListAdapter(
						AuctionDetails.this, fromfragment, "auction",
						onListItemClickedId);

				Log.d(tag, "reached else part");
				switch (getFromFragment(fromfragment)) {
				case 1:
					placeBid_Or_CancelRequest.setVisibility(View.GONE);
					
					
					Requestor_Json_Data_Structure ob = CommonData
							.getOpenRequestsData().get(onListItemClickedId);
					tvDescription.setText("Description : "+ob.getDescription());
					tvUserName.setText("UserName : "+CommonData.getUserId());
					timeLeft.setText("Request Start Date : "+ob.getRequestStartDate().subSequence(0, 16));
					
					if (ob.getBids().size() != 0) {
						getListView().setVisibility(View.VISIBLE);
						setListAdapter(mUserMainListAdapter);
					} else
						
						{getListView().setVisibility(View.GONE);
						offers.setText("No offers");
						}

					break;

				case 2:
					placeBid_Or_CancelRequest.setVisibility(View.GONE);
					Requestor_Json_Data_Structure ob_acp  = CommonData
							.getAcceptedRequestsData().get(onListItemClickedId);
					tvDescription.setText("Description : "+ob_acp.getDescription());
					tvUserName.setText("UserName : "+CommonData.getUserId());
					timeLeft.setText("Request Start Date : "+ob_acp.getRequestStartDate().subSequence(0,16));
					if (ob_acp.getBids().size() != 0) {
						getListView().setVisibility(View.VISIBLE);
						setListAdapter(mUserMainListAdapter);
					} else
					{
						getListView().setVisibility(View.GONE);
						offers.setText("No offers");
					}

					break;

				case 3:
					placeBid_Or_CancelRequest.setVisibility(View.GONE);
					Requestor_Json_Data_Structure ob_serv_req_data  = CommonData
							.getServicedRequestsData().get(onListItemClickedId);
					tvDescription.setText("Description : "+ob_serv_req_data.getDescription());
					tvUserName.setText("UserName : "+CommonData.getUserId());
					timeLeft.setText("Request Start Date : "+ob_serv_req_data.getRequestStartDate().subSequence(0,16));
					if (ob_serv_req_data.getBids().size() != 0) {
						getListView().setVisibility(View.VISIBLE);
						setListAdapter(mUserMainListAdapter);
					} else
					{
						getListView().setVisibility(View.GONE);
						offers.setText("No Serviced Requests");
					}
					break;
				}

			}

		}

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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (role.equalsIgnoreCase("vendor")) {

			// pop up a dialog box to take input for bidAmount
			getBidAmount();

		}
		/*else
		{
			if(fromfragment.equalsIgnoreCase("User_Current_Requests"))
				deleteRequest();
		}*/

	//	

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
}