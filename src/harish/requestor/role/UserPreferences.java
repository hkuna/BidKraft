package harish.requestor.role;

import org.apache.http.Header;

import push.classes.to.other.pkg.VendorPreferences;
import harish.requestor.commondata.CommonData;
import server.ServerConnector;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class UserPreferences extends Activity {
	EditText et_userRadius;
	Button btn_submitUserPreferences ;
	ServerConnector mServerConnector;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_preferences);
		mServerConnector = new ServerConnector(this);
		et_userRadius = (EditText) findViewById(R.id.et_userRadius);
		btn_submitUserPreferences =(Button) findViewById(R.id.btn_userPreferencesSubmit);
		et_userRadius.setText(CommonData.getRequestorRadius());
		
		
		
		btn_submitUserPreferences.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if((et_userRadius.getText().toString().equals(""))  )
				{
					et_userRadius.setText(CommonData.getRequestorRadius());
				}
				else
				
					CommonData.setVendorRadius(et_userRadius.getText().toString());
				
				mServerConnector.updateUserSettings("1", et_userRadius.getText().toString(), new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
						// TODO Auto-generated method stub
						Toast.makeText(UserPreferences.this, "Requestor Radius is updated", Toast.LENGTH_SHORT).show();
					}
					
					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						
						
					}
				});
				
			}
		});
		
	}
}
