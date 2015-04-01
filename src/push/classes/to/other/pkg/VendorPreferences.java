package push.classes.to.other.pkg;

import org.apache.http.Header;

import server.ServerConnector;
import harish.requestor.commondata.CommonData;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class VendorPreferences extends Activity {
EditText et_vendorRadius;
public static final String PREFS_NAME = "NeighborVendorPrefs";
/*SharedPreferences settings;
SharedPreferences.Editor editor;*/
Button btn_submitVendorPreferences ;
/*String path;*/
ServerConnector mServerConnector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vendor_preferences);
		mServerConnector = new ServerConnector(this);
		et_vendorRadius = (EditText) findViewById(R.id.et_vendorRadius);
		btn_submitVendorPreferences =(Button) findViewById(R.id.btn_vendorPreferencesSubmit);
	/*	path = PreferenceManager.getDefaultSharedPreferences(this)
				.getString("VendorRadius", "");*/
	/*	settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();*/
		
			et_vendorRadius.setText(CommonData.getVendorRadius());
		
		
		btn_submitVendorPreferences.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				if((et_vendorRadius.getText().toString().equals(""))  )
				{
					et_vendorRadius.setText(CommonData.getVendorRadius());
				}
				else
				{
					CommonData.setVendorRadius(et_vendorRadius.getText().toString());
					
					mServerConnector.updateUserSettings("2", et_vendorRadius.getText().toString(), new AsyncHttpResponseHandler() {
						
						@Override
						public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
							// TODO Auto-generated method stub
							Toast.makeText(VendorPreferences.this, "Radius is updated", Toast.LENGTH_SHORT).show();
						}
						
						@Override
						public void onFailure(int statusCode, Header[] headers,
								byte[] responseBody, Throwable error) {
							// TODO Auto-generated method stub
							
						}
					});
					
					
					
					
				}
				
			}
		});
		

		
	}
}
