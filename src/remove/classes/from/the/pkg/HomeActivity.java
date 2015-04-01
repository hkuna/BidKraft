package remove.classes.from.the.pkg;
/*
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class HomeActivity extends FragmentActivity implements OnClickListener {
Button slideButton, b1, b2, b3;
SlidingDrawer slidingDrawer;

public void onCreate(Bundle savedInstanceState) {
 super.onCreate(savedInstanceState);
 setContentView(R.layout.sliding_layout);
 
 
 
 slideButton = (Button) findViewById(R.id.slideButton);
 slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);
 b1 = (Button) findViewById(R.id.Button01);
 b2 = (Button) findViewById(R.id.Button02);
 b3 = (Button) findViewById(R.id.Button03);
 b1.setOnClickListener(this);
 b2.setOnClickListener(this);
 b3.setOnClickListener(this);
 
 
 
 
 
 
ListView lv= (ListView)   findViewById(android.R.id.list);

String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        "Blackberry" ,"Ubuntu", "Windows7", "Max OS X",
	        "Linux", "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux",
	        "OS/2", "Ubuntu", "Windows7", "Max OS X", "Linux", "OS/2",
	        "Android", "iPhone", "WindowsMobile"};
ArrayAdapter<String> abc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values);
lv.setAdapter(abc);
 
 
 
 
 
 
 
 slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
  @Override
  public void onDrawerOpened() {
   slideButton.setText("Close");
  }
 });
 slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
  @Override
  public void onDrawerClosed() {
  slideButton.setText("Createpost");
  }
 });
}

@Override
public void onClick(View v) {
 Button b = (Button) v;
 Toast.makeText(HomeActivity.this,
   b.getText() + " is Clicked :)", Toast.LENGTH_SHORT).show();
}
}
*/import harish.requestor.commondata.CommonData;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import push.classes.to.other.pkg.Response;
import push.classes.to.other.pkg.Vendor_Main_Activity;
import push.classes.to.other.pkg.ParalleRequestHandler.OnServerSuccessCallListener;
import server.ServerConnector;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerCloseListener;
import android.widget.SlidingDrawer.OnDrawerOpenListener;
import android.widget.TextView;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;

@SuppressLint("NewApi") public class HomeActivity extends FragmentActivity implements OnServerSuccessCallListener{
	
	FrameLayout mFrameLayout;
	SlidingPaneLayout mSlidingPaneLayout;
	SlidingDrawer mSlidingDrawer;
	Button slideButton;
	Button settings, profile, newpost;
	 SlidingDrawer slidingDrawer; 
	 private static int NUM_ITEMS = 2;
	
	 MyPagerAdapter adapterViewPager;
	 ListView lv;
	 String tag="Home activity";

	 ServerConnector mServerConnector;
	 int count=0;
	 
	 ProgressDialog progress;
	 
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	
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
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.vendor_consumer:
	            Log.d(tag, "toggle clicked");
	            switchScreen();
	            return true;
	        
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	
	

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	    progress = new ProgressDialog(this);
		    
		mFrameLayout = new FrameLayout(this);
		setContentView(R.layout.sliding_layout);
		
	//	final LinearLayout l1 = (LinearLayout) findViewById(R.id.tabbar);
		
		profile =(Button) findViewById(R.id.settings);
		settings =(Button) findViewById(R.id.profile);
		newpost = (Button) findViewById(R.id.newpost);
		
		
		
		
		slideButton =(Button) findViewById(R.id.slideButton);
		slidingDrawer = (SlidingDrawer) findViewById(R.id.SlidingDrawer);
		
		//from here
		
		lv = (ListView) findViewById(R.id.listView);
		  
		/* ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, list);*/
			
	 ///   lv.setAdapter(new EListAdapter(HomeActivity.this, "Home", "auctionormainxcr"));
		  
	
	lv.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			
			
			Intent i1 = new Intent(HomeActivity.this, AuctionDetails.class);
			i1.putExtra("position", arg3);
			i1.putExtra("fromActivity", "cancel");
			Log.d(tag,"position is"+arg3);
			
			startActivity(i1);
		
			
		}
		
		
	});
		  ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
		
	        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
	        vpPager.setAdapter(adapterViewPager);
	        
	        

		 
	       slidingDrawer.setOnDrawerOpenListener(new OnDrawerOpenListener() {
	 		   @Override
	 		   public void onDrawerOpened() {
	 			 //  slidingDrawer.setBackgroundColor(Color.rgb(221,30,49));
	 		//	   l1.setVisibility(View.GONE);
	 		//    slideButton.setText("close");
	 		   }
	 		  });
	 		  slidingDrawer.setOnDrawerCloseListener(new OnDrawerCloseListener() {
	 		   @Override
	 		   public void onDrawerClosed() {
	 			  
	 		   slideButton.setText("CreatePost");
	 			 newpost.setText("CreatePost");
	 			   

	 		   }
	 		  });
	 		   
	 		  
		  
		  vpPager.setOnPageChangeListener(new OnPageChangeListener() {

			    // This method will be invoked when a new page becomes selected.
			    @Override
			    public void onPageSelected(int position) {
			        Toast.makeText(HomeActivity.this, 
			                    "Selected page position: " + position, Toast.LENGTH_SHORT).show();
			    }

			    // This method will be invoked when the current page is scrolled
			    @Override
			    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			        // Code goes here
			    }

			    // Called when the scroll state changes: 
			    // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
			    @Override
			    public void onPageScrollStateChanged(int state) {
			        // Code goes here
			    }
			});
		  
		  
		  
		

	}

	
	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		   Log.d(tag,"reached on activity result");
		 slidingDrawer.close();
	//	 lv.setAdapter(new EListAdapter(HomeActivity.this, "Home", "auctionormainxcr"));
		 
	}
	
	
	public static class MyPagerAdapter extends FragmentPagerAdapter {
	   
	    
	    
	    
	       @Override
		public float getPageWidth(int position) {
			// TODO Auto-generated method stub
			 super.getPageWidth(position);
			 return 0.93f;
		}

			public MyPagerAdapter(FragmentManager fragmentManager) {
	            super(fragmentManager);
	        }

	        // Returns total number of pages
	        @Override
	        public int getCount() {
	            return NUM_ITEMS;
	        }

	        // Returns the fragment to display for that page
	        @Override
	        public Fragment getItem(int position) {
	            switch (position) {
	            case 0: // Fragment # 0 - This will show FirstFragment
	            	 return Category_Pager1.newInstance(0, "bac");
				case 1: // Fragment # 0 - This will show FirstFragment different title
	            	return Category_Pager2.newInstance(1, "Page # 3");
	            
	                
	            default:
	                return null;
	            }
	        } 
	        
	        // Returns the page title for the top indicator
	        @Override
	        public CharSequence getPageTitle(int position) {
	            return "Page " + position;
	        }
	        
	        
	        

	    }

	
	private void switchScreen() {
		// TODO Auto-generated method stub
		progress.setMessage("Loading..");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(false);
		progress.show();
		
		mServerConnector = new ServerConnector(HomeActivity.this);
		
		
	/*	mServerConnector.fetchOpenRequest("36", "2", "",new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				
				
				
				String resp = new String(responseBody);
	
				Gson gson = new Gson();
	
				Response response = gson.fromJson(resp, Response.class); 
		//		CommonData.openrequestdatastruct = response.getData().getRequests();
	
			//	Log.d(tag," gson check "+ "data struct is "+CommonData.openrequestdatastruct);
				serverSuccessResponse(1);
				
				
			}
			 
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				// TODO Auto-generated method stub
	
			}
		});
		
	*/	
		mServerConnector.vendorPlacedBids("37", new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
			
				String res = new String(responseBody);
				Gson gson = new Gson();
				
				Response response = gson.fromJson(res, Response.class);
		//		CommonData.placedbids = response.getData().getPlacedBids();
				
			//	Log.d(tag," gson check "+ "placed bids data struct is "+CommonData.placedbids+"size is "+CommonData.placedbids.size());
				serverSuccessResponse(1);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				
				
				
			}
		});
		
		
		
		
		
	
		
		
	
		
	}
	

	@Override
	public void serverSuccessResponse(int a) {
		
		 count = count +a; 
		 
		 if(count==2)
		 {
			 progress.dismiss();
		 Intent i = new Intent(HomeActivity.this, Vendor_Main_Activity.class);
			startActivity(i);
			 count=0;
		 }
		
		
	}
		

	
	

}

