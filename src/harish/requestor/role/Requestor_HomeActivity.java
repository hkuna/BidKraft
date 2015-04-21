package harish.requestor.role;

import harish.requestor.commondata.CommonData;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import push.classes.to.other.pkg.Vendor_Main_Activity;
import push.classes.to.other.pkg.ParalleRequestHandler.OnServerSuccessCallListener;

import remove.classes.from.the.pkg.CreateJobActivity;
import remove.classes.from.the.pkg.SettingsActivity;
import server.ServerConnector;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.babloosashi.neighbour.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Requestor_HomeActivity extends FragmentActivity implements
		ActionBar.TabListener, OnServerSuccessCallListener {
	TextView tabIconOpen , tabIconAccepted ,tabIconServiced ;
	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	int count = 0;
	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the three primary sections of the app. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	
	String TAG = "Requestor_HomeActivity";
	ServerConnector mServerConnector;

	private CharSequence mTitle;
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will display the three primary sections of the
	 * app, one at a time.
	 */
	ViewPager mViewPager;
	ProgressDialog progress;
	LayoutInflater inflater;
	ActionBar bar;
    View v;
    ImageView ib_createPost,ib_settings;
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestor_home);
		mServerConnector = new ServerConnector(Requestor_HomeActivity.this);
	    inflater = LayoutInflater.from(Requestor_HomeActivity.this);
	    
	    ib_createPost = (ImageView) findViewById(R.id.ib_createpost);
	    ib_settings = (ImageView) findViewById(R.id.ib_settings);
	    
	    
	    ib_settings.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
             
				Intent i = new Intent(Requestor_HomeActivity.this, SettingsActivity.class);
				
				startActivity(i);
				
				
			}
		});
	    
	    ib_createPost.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
             Intent i = new Intent(Requestor_HomeActivity.this , CreatePost.class);
             startActivity(i);
				
			}
		});
		

		//setting the custom bar
	    bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar, null);
		TextView mRequestTitle = (TextView) mCustomView.findViewById(R.id.tv_request_subject);
		ImageButton ib_roleicon = (ImageButton) mCustomView
				.findViewById(R.id.ib_roleIcon);
		
		mRequestTitle.setText("User Home");
		
		mRequestTitle.setTextColor(Color.rgb(243, 156, 18));
	
		
		ib_roleicon.setBackgroundDrawable(getResources().getDrawable(R.drawable.client_icon));
		
		
		ib_roleicon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//handle the role icon action
				
				switchScreen();
			}
		});
		
		
		
		// end of custom action bar setting

		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.
		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the action bar.
		//final ActionBar actionBar = getActionBar();

		// Specify that we will be displaying tabs in the action bar.
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Set up the ViewPager, attaching the adapter and setting up a listener
		// for when the
		// user swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections, select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this if
						// we have a reference to the
						// Tab.
						bar.setSelectedNavigationItem(position);
						
						//call the fragment again
						
						
						
	//					setTabNotificationCount(position);
					}
				});

		
		
		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter.
			// Also specify this Activity object, which implements the
			// TabListener interface, as the
			// listener for when this tab is selected.
			/*
			 * actionBar.addTab(actionBar.newTab()
			 * .setText(mAppSectionsPagerAdapter
			 * .getPageTitle(i)).setIcon(Requestor_HomeActivity
			 * .this.getResources().getDrawable(CommonData.getIcons(i)))
			 * .setTabListener(this));
			 */

			bar.addTab(bar
					.newTab().setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));   /*setIcon(
							Requestor_HomeActivity.this.getResources()
							.getDrawable(CommonData.getTabIcons(i)))*/
			
		}
		
		bar.setCustomView(mCustomView);
		bar.setDisplayShowCustomEnabled(true);
		
	}
	
	

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		//mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager.setCurrentItem(tab.getPosition());
	
//setTabNotificationCount(tab.getPosition());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the primary sections of the app.
	 */
	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			Fragment fragment = null;
			switch (i) {
			case 0:
				fragment = new User_Current_Requests();
				break;

			case 1:

				fragment = new UserPendingServices();
				break;

			
			case 2:
				fragment = new CompletedRequests();
				break;

			
			}
			return fragment;
		}

		@Override
		public int getCount() {
			return 3;
		}
		// Not useed , because of icons

		
		  @Override
		  
		  
		  public CharSequence getPageTitle(int position) {
		  CharSequence appSectionTitle = "";
		  
		  
		  switch (position) { 
		  
		  case 0:
		  appSectionTitle = "Open"; break;
		  
		 case 1: appSectionTitle = "Accepted"; break;
		  
		  case 2: appSectionTitle = "Completed"; break;
		  
		  } return appSectionTitle;
		  
		  }
		 
	}

	


	@Override
	public void setTitle(CharSequence title) {
		mTitle = "Create Request " + title;
		getActionBar().setTitle(mTitle);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.d(TAG, "reached on activity result");

		// lv.setAdapter(new EListAdapter(HomeActivity.this, "Home",
		// "auctionormainxcr"));

	}


	private void switchScreen() {
		// TODO Auto-generated method stub
		progress = CommonData.showProgressBar(Requestor_HomeActivity.this);
		progress.show();

		mServerConnector = new ServerConnector(Requestor_HomeActivity.this);
		 String[] requeststatuses = {"OPEN"};

		mServerConnector.fetchOpenRequest(CommonData.getUserId(), ""+CommonData.ROLE_ID_VENDOR,  requeststatuses , new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						// TODO Auto-generated method stub
						Response response = CommonData
								.convertGSonObjectToResponseClass(responseBody);

						if (response.getStatus().equalsIgnoreCase("success"))

						{
							CommonData.setOpenBidsData((response
									.getData().getOpenBids())); // vendor open
																// bids are
																// nothing but
																// requestors
																// open requests

							// vendor open bids are nothing but requestors open
							// requests

						
							
						}

						Log.d(TAG, " gson check for openbids " + "data struct is "
								+ CommonData.getOpenBidsData());
						serverSuccessResponse(1);
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub

					}
				});

		mServerConnector.vendorPlacedBids(CommonData.getUserId(),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						// TODO Auto-generated method stub

						Response response = CommonData
								.convertGSonObjectToResponseClass(responseBody);
						if (response.getStatus().equalsIgnoreCase("success"))
						{
						CommonData.setPlacedBidsData(response.getData().getPlacedBids());

						Log.d(TAG,
								" gson check  for vendor open and placved bids"
										+ "placed bids data struct is "
										+ CommonData.getOpenBidsData()
										+ "size is "
										+ CommonData.getPlacedBidsData()
												.size());
						serverSuccessResponse(1);
						}
					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub

					}
				});

	}

	// after we get the data for placed bids and vendoropenbids we switch the
	// screen
	@Override
	public void serverSuccessResponse(int a) {

		count = count + a;

		if (count == 2) {
			CommonData.hideProgressbar(Requestor_HomeActivity.this, progress);
			Intent i = new Intent(Requestor_HomeActivity.this,
					Vendor_Main_Activity.class);
			startActivity(i);
			finish();
			count = 0;
		}

	}

	
	@Override
	protected void onResume() {
	    super.onResume();
	    checkPlayServices();
	}
	
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(TAG, "This device is not supported.");
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	
/*public View getCustomTabIconLayoutId(int position)
{

	switch(position)
	{
	
	case 0:  v = inflater.inflate(R.layout.tabicon_openrequest, null, false);
		break;
	case 1: v = inflater.inflate(R.layout.tabicon_acceptedrequests, null, false);
		break;
	case 2:  v = inflater.inflate(R.layout.tabicon_createrequest, null, false);
		break;
		
	case 3:  v = inflater.inflate(R.layout.tabicon_servicedrequests, null, false);
		break;
	case 4: v = inflater.inflate(R.layout.tabicon_settings, null, false);
		break;
	}
	return v;
} 
	public void setTabNotificationCount(int position )
	{
		switch(position)
		{
		
		case 0:  //later check the count and make it visbile if count greater than zero
		
			
            tabIconOpen =(TextView) findViewById(R.id.tv_tabIconOpen);
       
			tabIconOpen.setText("");
			break;
		case 1:
			
			  tabIconAccepted =(TextView) findViewById(R.id.tv_tabIconAccepted);
			  tabIconAccepted.setText("");
			
			break;
	
			
		case 2:
		
			
			
			  tabIconServiced =(TextView) findViewById(R.id.tv_tabIconServiced);
			  tabIconServiced.setText("");
			 
			break;
		
		}
		
	}*/
}
