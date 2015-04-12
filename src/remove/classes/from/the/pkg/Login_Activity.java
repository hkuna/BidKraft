package remove.classes.from.the.pkg;

import harish.requestor.commondata.CommonData;
import harish.requestor.role.Requestor_HomeActivity;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.facebook.UiLifecycleHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.internal.in;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Login_Activity extends FragmentActivity {
	private String message, TAG = "LoginActivity";
	// new web service and format
	
	private String url = "http://rikers.cs.odu.edu:8080/bidding/login";
	private ServerConnector mServerConnector;

	private String Url = "http://rikers.cs.odu.edu/neighbor/getRequests.php?key=bmVpZ2hib3JhcHA";

	private String username = "";
	int screenWidth, screenHeight;
	
	//declare ui elements
     private ImageView iv_title , iv_logonlogo ;
     private   EditText et_username , et_password;
     private  Button btn_login , btn_signup, btn_f_password;
     private View v_ht;
   private    RelativeLayout loginPageView;
	 //------------------------
	
	  private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	   public static final String EXTRA_MESSAGE = "message";
	    public static final String PROPERTY_REG_ID = "registration_id";
	    private static final String PROPERTY_APP_VERSION = "appVersion";
	    
	    /**
	     * Substitute you own sender ID here. This is the project number you got
	     * from the API Console, as described in "Getting Started."
	     */
	    String SENDER_ID = "909686287783";

	    /**
	     * Tag used on log messages.
	     */

	    GoogleCloudMessaging gcm;
	    AtomicInteger msgId = new AtomicInteger();
	    SharedPreferences prefs;
	    Context context;
	    String regid;

	  //-------------------------------

	private UiLifecycleHelper uiLifecycleHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.login_bidkraft);
	
		

		mServerConnector = new ServerConnector(Login_Activity.this);

		 context = getApplicationContext();
		 
		 // Initilizing Ui elements 
		 loginPageView = (RelativeLayout) findViewById(R.id.rlview);
		 iv_title = (ImageView) findViewById(R.id.iv_title);
		 iv_logonlogo = (ImageView) findViewById(R.id.iv_loginlogo);
		 et_username =(EditText) findViewById(R.id.et_username);
		 et_password = (EditText) findViewById(R.id.et_password);
		 btn_login = (Button) findViewById(R.id.btn_login);
		 v_ht = (View) findViewById(R.id.v_ht);
		 btn_signup = (Button) findViewById(R.id.btn_signup);
		 btn_f_password =(Button) findViewById(R.id.btn_f_password);
		 // end of initialization 
		 
		 
		 //calculate the screen width and set the size of ui elements relatively to fit all screens
		    Display display = getWindowManager().getDefaultDisplay();
		     Point size = new Point();
		     display.getSize(size);
		     screenWidth = size.x;
		     screenHeight = size.y;
		     
		     
		    et_username.getLayoutParams().width= (int)(screenWidth/2);
		    
		    MarginLayoutParams usereNameParams = (MarginLayoutParams) et_username.getLayoutParams();
		   
		    usereNameParams.setMargins(screenWidth/4, screenWidth/8, screenWidth/4, 30);
		    
		    et_username.setLayoutParams(usereNameParams);
		    
	
		    
		    et_password.getLayoutParams().width = (int)(screenWidth/2);
		    
		    btn_login.getLayoutParams().width = (int) (screenWidth/4);
	           
		 MarginLayoutParams loginparams =  ( MarginLayoutParams) btn_login.getLayoutParams();
		    loginparams.setMargins(screenWidth/4, 20, screenWidth/32, 20);
		    	 
		     
		     btn_signup.getLayoutParams().width = (int) (screenWidth/4);
		     
		     MarginLayoutParams signupParams =  ( MarginLayoutParams) btn_signup.getLayoutParams();
		     signupParams.setMargins(screenWidth/32, 20, screenWidth/4, 20);
		    
		   btn_f_password.getLayoutParams().width = (int) (screenWidth/2);
		   MarginLayoutParams fPasswordParams =  ( MarginLayoutParams) btn_f_password.getLayoutParams();
		   fPasswordParams.setMargins(screenWidth/4, 10, screenWidth/4, 20);
		 
		

		 // for animation to take effect , hiding the other UI elements 
		 
		 iv_title.setVisibility(View.GONE);
		 et_username.setVisibility(View.GONE);
	     et_password.setVisibility(View.GONE);
	     btn_login.setVisibility(View.GONE);
	     v_ht.setVisibility(View.GONE);;
	     btn_signup.setVisibility(View.GONE);
	     btn_f_password.setVisibility(View.GONE);
	 
	     
	     callForAnimation();  // animating the logo from bottom and fading in the page after little delay

// calculate the width of the screen and arrange the UI elements accordingly
	        
		btn_login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				performLogin();

			}
		});

		
		btn_signup.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent i = new Intent(Login_Activity.this, Registration.class);

				startActivity(i);
			}
		});

	} 
	
	
	
	
	private void callForAnimation() {
		// TODO Auto-generated method stub
	// start the animation by calling the method
		
		
		Animation animTranslate = AnimationUtils.loadAnimation(
				Login_Activity.this, R.anim.translate);
		animTranslate.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation arg0) {
			}

			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationEnd(Animation arg0) {
				loginPageView.setVisibility(View.VISIBLE);
				 iv_title.setVisibility(View.VISIBLE);
				 et_username.setVisibility(View.VISIBLE);
			     et_password.setVisibility(View.VISIBLE);
			     btn_login.setVisibility(View.VISIBLE);
			     v_ht.setVisibility(View.VISIBLE);;
			     btn_signup.setVisibility(View.VISIBLE);
			     btn_f_password.setVisibility(View.VISIBLE);
				 
                
			 
			  
				
			}
		});
		
		iv_logonlogo.startAnimation(animTranslate);
		// end of animation logic
		
		
		
		/*Animation animFade = AnimationUtils.loadAnimation(
		Login_Activity.this, R.anim.fade);*/
	}




	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
	
	
	
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return getSharedPreferences(Login_Activity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	/**
	 * Registers the application with GCM servers asynchronously.
	 * <p>
	 * Stores the registration ID and app versionCode in the application's
	 * shared preferences.
	 */
	
	/**
     * Registers the application with GCM servers asynchronously.
     * <p>
     * Stores the registration ID and the app versionCode in the application's
     * shared preferences.
     */
    private void registerInBackground() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(context);
                    }
                    regid = gcm.register(SENDER_ID);
                    msg = "Device registered, registration ID=" + regid;
                    
                    Log.d(TAG , "reg id  "+msg);

                    // You should send the registration ID to your server over HTTP, so it
                    // can use GCM/HTTP or CCS to send messages to your app.
                    Login_Activity.this.runOnUiThread(new Runnable() {
                    	  public void run() {
                    		  storeRegistrationId(context, regid);
                    		  sendRegistrationIdToBackend();
                    	  }
                    	});
                    

                 
                } catch (IOException ex) {
                    msg = "Error :" + ex.getMessage();
                    // If there is an error, don't just keep trying to register.
                    // Require the user to click a button again, or perform
                    // exponential back-off.
                }
                return msg;
            }

            @Override
            protected void onPostExecute(String msg) {
            	/* */
            }
        }.execute(null, null, null);
    }

	
	  
	   

	    /**
	     * Stores the registration ID and app versionCode in the application's
	     * {@code SharedPreferences}.
	     *
	     * @param context application's context.
	     * @param regId registration ID
	     */
	    private void storeRegistrationId(Context context, String regId) {
	        final SharedPreferences prefs = getGCMPreferences(context);
	        int appVersion = getAppVersion(context);
	        Log.i(TAG, "Saving regId on app version " + appVersion);
	        SharedPreferences.Editor editor = prefs.edit();
	        editor.putString(PROPERTY_REG_ID, regId);
	        editor.putInt(PROPERTY_APP_VERSION, appVersion);
	        editor.commit();
	       
	    }
	
	private void performLogin() {

		// TODO Auto-generated method stub
		  //------------------------------------------------------------------------------------------------------------------------------------
			 // Check device for Play Services APK.
			
			    	
			        // Check device for Play Services APK. If check succeeds, proceed with
			        //  GCM registration.
			        if (checkPlayServices()) {
			            gcm = GoogleCloudMessaging.getInstance(this);
			            regid = getRegistrationId(context);

			            if (regid.isEmpty()) {
			                registerInBackground();
			            }
			            else
			            {
			            	sendRegistrationIdToBackend();
			            }
			        } else {
			            Log.i(TAG, "No valid Google Play Services APK found.");
			        }
			    
			    
			    //------------------------------------------------------------------------------------------------------------------------------------
	

	}
	
	@Override
	protected void onResume() {
	    super.onResume(); 
	    checkPlayServices();
	}
	
	/**
	 * Sends the registration ID to your server over HTTP, so it can use GCM/HTTP
	 * or CCS to send messages to your app. Not needed for this demo since the
	 * device sends upstream messages to a server that echoes back the message
	 * using the 'from' address in the message.
	 */
	private void sendRegistrationIdToBackend() {
	    // Your implementation here.
		Log.d(TAG, "login clicked");
		

		String emailId = et_username.getText().toString();
		String pswd = et_password.getText().toString();
		final ProgressDialog progress = CommonData
				.showProgressBar(Login_Activity.this);
		progress.show();

		mServerConnector.checkLogin(emailId, pswd,regid,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						Log.d(TAG, "entered success");
						CommonData.hideProgressbar(Login_Activity.this,
								progress); // hide progress bar after the
											// response
						// TODO Auto-generated method stub
					

						Response response = CommonData
								.convertGSonObjectToResponseClass(responseBody);
						 CommonData.setSignUpflag(false);
						
						if(response.getStatus().equalsIgnoreCase("success"))
						{
							
							CommonData.setToken(response.getData().getToken());
							CommonData.setUserId(response.getData().getUserId());
							Log.d(TAG,
									"reading response value login"
											+ CommonData.getToken() + CommonData.getUserId());
							
							Log.d(TAG,
									"reading response value login"
											+ responseBody);
							
							
						//	CommonData.setRoleId(response.getData().getRoleId());
							
							
						//	if(CommonData.getRoleId()!=0 &&( CommonData.getRoleId()==1 || CommonData.getRoleId()==3 ))
									//{
								            CommonData.setAcceptedRequestsData(response.getData().getAcceptedRequests());
								            CommonData.setServicedRequestsData(response.getData().getServicedRequests());
								            CommonData.setOpenRequestsData(response.getData().getOpenRequests());
								            CommonData.setRequesterSettingId(response.getData().getUserPreferences().getRequesterSettings().getUserSettingId());
								            CommonData.setVendorSettingId(response.getData().getUserPreferences().getVendorSettings().getUserSettingId());
								            CommonData.setVendorRadius(response.getData().getUserPreferences().getVendorSettings().getVendorRadius());
								            CommonData.setRequestorRadius(response.getData().getUserPreferences().getRequesterSettings().getRequesterRadius());
								            
								          /*  Intent signUpToHome = new Intent(Login_Activity1.this,
													Requestor_HomeActivity.class);
											startActivity(signUpToHome);
											finish();*/
								
								//	}
							/*else if (CommonData.getRoleId()!=(Integer)null && CommonData.getRoleId()==2 )
							{
								CommonData.setPlacedBidsData(response.getData().getPlacedBids());
								CommonData.setOpenBidsData(response.getData().getOpenBids());
								Intent i = new Intent(Login_Activity1.this, Vendor_Main_Activity.class);
								startActivity(i);
								finish();
							}*/						       
								            // For this demo: we don't need to send it because the device will send
						                    // upstream messages to a server that echo back the message using the
						                    // 'from' address in the message.

						                    // Persist the regID - no need to register again.
								            Intent signUpToHome = new Intent(Login_Activity.this,
							     					Requestor_HomeActivity.class);
							     			startActivity(signUpToHome);
							     			finish();
							
						}
						
		
               
						Toast.makeText(Login_Activity.this, response.getStatus(),
								Toast.LENGTH_LONG).show();

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub
						Log.d(TAG, "entered failure" + error);
						CommonData.hideProgressbar(Login_Activity.this,
								progress);
					}

				});
	}

	
	/**
	 * Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
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
}
