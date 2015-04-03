package push.classes.to.other.pkg;

import org.apache.http.Header;

import push.classes.to.other.pkg.ParalleRequestHandler.OnServerSuccessCallListener;
import server.ServerConnector;
import harish.requestor.commondata.CommonData;
import harish.requestor.role.Requestor_HomeActivity;
import android.app.ActionBar;
import android.app.ProgressDialog;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.babloosashi.neighbour.R;
import com.google.android.gms.internal.ib;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Vendor_Main_Activity extends FragmentActivity implements
		TabListener , OnServerSuccessCallListener{


	String Tag = "Vendor_MAin_Activity";
	ViewPager mViewPager;
	Context context;
	String tag = "Vendor activity";
    int serversuccescount =0;
    ProgressDialog progress;
    ServerConnector mServerConnector;
    ImageView ib_createPost,ib_settings;
    ActionBar bar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestor_home); //changed here
		mServerConnector = new ServerConnector(Vendor_Main_Activity.this);
		context = Vendor_Main_Activity.this;
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		//changes 
		  ib_createPost = (ImageView) findViewById(R.id.ib_createpost);
		    ib_settings = (ImageView) findViewById(R.id.ib_settings);
		    ib_createPost.setVisibility(View.GONE);
		    ib_settings.setVisibility(View.GONE);
		    
		    
		    
		    bar = getActionBar();
			bar.setDisplayShowHomeEnabled(false);
			bar.setDisplayShowTitleEnabled(false);
			LayoutInflater mInflater = LayoutInflater.from(this);
			View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
			TextView mRequestTitle = (TextView) mCustomView.findViewById(R.id.tv_request_subject);
			ImageButton ib_roleicon = (ImageButton) mCustomView
					.findViewById(R.id.ib_roleIcon);
			
			mRequestTitle.setText("Vendor Home");
			
			mRequestTitle.setTextColor(Color.rgb(243, 156, 18));
		
			
			ib_roleicon.setBackgroundDrawable(getResources().getDrawable(R.drawable.vendor_icon));
			
			
			ib_roleicon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					//handle the role icon action
					
					switchScreen();
				}
			});
		    
		
			bar.setCustomView(mCustomView);
			bar.setDisplayShowCustomEnabled(true);

		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab currentsBids = bar.newTab();
		currentsBids.setText("Open Bids");
		currentsBids.setTabListener(this);

		ActionBar.Tab openBids = bar.newTab();
		openBids.setText("Placed Bids");
		openBids.setTabListener(this);

		ActionBar.Tab bidswon = bar.newTab();
		bidswon.setText("Bids Won");
		bidswon.setTabListener(this);
		
		bar.addTab(currentsBids);
		bar.addTab(openBids);
		bar.addTab(bidswon);
	

		mViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub
						bar.setSelectedNavigationItem(arg0);

						

					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		Log.d(Tag, "tab selected " + arg0.getPosition());

	}

	@Override
	public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		Log.d(Tag, "tab selected " + arg0.getPosition());

		mViewPager.setCurrentItem(arg0.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		Log.d(Tag, "tab selected " + arg0.getPosition());

	}

	class MyAdapter extends FragmentPagerAdapter {

		public MyAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fm = null;

			if (arg0 == 0) {
				Log.d(Tag, "open Bids");
				fm = new OpenBidsForVendor();
			} else {
				Log.d(Tag, "Placed bids");
				fm = new VendorPlacedBids();
			}

			return fm;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

	}

	

	private void switchScreen() {
		// call webservices to update the data of user role
		progress = CommonData.showProgressBar(Vendor_Main_Activity.this);
		progress.show();
	 String[] openrequeststatuses = {"OPEN"};
		mServerConnector.fetchOpenRequest(CommonData.getUserId(), ""+CommonData.ROLE_ID_USER, openrequeststatuses, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
				
				Response response = CommonData
						.convertGSonObjectToResponseClass(responseBody);
				if (response.getStatus().equalsIgnoreCase("success"))

				{
					CommonData.setOpenRequestsData(response.getData().getOpenRequests());

					
				}

				Log.d(Tag, " gson check for open requests " + "data struct is "
						+ CommonData.getOpenRequestsData());
				serverSuccessResponse(1);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				
			}
		});
		 String[] acceptrequeststatuses = {"BID_ACCEPT"};
		mServerConnector.fetchOpenRequest(CommonData.getUserId(), ""+CommonData.ROLE_ID_USER, acceptrequeststatuses, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
				Response response = CommonData
						.convertGSonObjectToResponseClass(responseBody);
				
				CommonData.convertGSonObjectToResponseClass(responseBody);
				if (response.getStatus().equalsIgnoreCase("success"))

				{
					CommonData.setAcceptedRequestsData(response.getData().getAcceptedRequests());

					
				}
				Log.d(Tag, " gson check for open requests " + "data struct is "
						+ CommonData.getAcceptedRequestsData());
				serverSuccessResponse(1);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		 String[] servicedrequeststatuses = {"SERVICED"};
		mServerConnector.fetchOpenRequest(CommonData.getUserId(), ""+CommonData.ROLE_ID_USER, servicedrequeststatuses, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				// TODO Auto-generated method stub
				Response response = CommonData
						.convertGSonObjectToResponseClass(responseBody);
				
				CommonData.convertGSonObjectToResponseClass(responseBody);
				if (response.getStatus().equalsIgnoreCase("success"))

				{
					CommonData.setServicedRequestsData(response.getData().getServicedRequests());

					
				}
				Log.d(Tag, " gson check for open requests " + "data struct is "
						+ CommonData.getServicedRequestsData());
				serverSuccessResponse(1);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
				
			}
		});

		
	}

	@Override
	public void serverSuccessResponse(int count) {
		serversuccescount = count+serversuccescount;
		
		if(serversuccescount==3)
		{
			
			CommonData.hideProgressbar(Vendor_Main_Activity.this, progress);
			Intent i = new Intent(Vendor_Main_Activity.this,
					Requestor_HomeActivity.class);
			startActivity(i);
			finish();
			count = 0;
		}
		
	}

}
