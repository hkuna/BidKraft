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
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Vendor_Main_Activity extends FragmentActivity implements
		TabListener , OnServerSuccessCallListener{

	ActionBar ab;
	String Tag = "Vendor_MAin_Activity";
	ViewPager mViewPager;
	Context context;
	String tag = "Vendor activity";
    int serversuccescount =0;
    ProgressDialog progress;
    ServerConnector mServerConnector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vendor__main);
		mServerConnector = new ServerConnector(Vendor_Main_Activity.this);
		context = Vendor_Main_Activity.this;
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
		ab = getActionBar();

		ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.Tab currentsBids = ab.newTab();
		currentsBids.setText("Open Bids");
		currentsBids.setTabListener(this);

		ActionBar.Tab openBids = ab.newTab();
		openBids.setText("Placed Bids");
		openBids.setTabListener(this);

		ab.addTab(currentsBids);
		ab.addTab(openBids);

		mViewPager
				.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

					@Override
					public void onPageSelected(int arg0) {
						// TODO Auto-generated method stub

						ab.setSelectedNavigationItem(arg0);

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.vendor_consumer:
			Log.d(tag, "toggle clicked");
			switchScreen();
			return true;

		default:
			return super.onOptionsItemSelected(item);
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
