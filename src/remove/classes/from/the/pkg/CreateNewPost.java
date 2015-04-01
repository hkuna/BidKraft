package remove.classes.from.the.pkg;

import harish.requestor.commondata.CommonData;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONObject;

import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

@SuppressLint("ValidFragment")
public class CreateNewPost extends Activity {

	Button post;
	String code;

	JSONObject vollresponse, jsonBody;
	String Url = "http://rikers.cs.odu.edu:8080/bidding/request/create";

	EditText et_date, et_fromtime, et_totime, et_numkids, et_typeOfService,
			et_description, et_address;
	String TAG = "New Post";

	ServerConnector mServerConnector;
	List<Address> addresses;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_post_homerepair);

		mServerConnector = new ServerConnector(CreateNewPost.this);

		et_date = (EditText) findViewById(R.id.et_hr_date);
		et_fromtime = (EditText) findViewById(R.id.et_hr_fromtime);
		et_totime = (EditText) findViewById(R.id.et_hr_totime);
		et_numkids = (EditText) findViewById(R.id.et_numKids);
		
		et_description = (EditText) findViewById(R.id.et_hr_description);
		et_address = (EditText) findViewById(R.id.et_hr_address);
		

		post = (Button) findViewById(R.id.post);

		post.setOnClickListener(new View.OnClickListener() {

			@Override 
			public void onClick(View v) {
				// TODO Auto-generated method stub
        
				
				try {
					Geocoder geocoder = new Geocoder(CreateNewPost.this);  
					addresses = geocoder.getFromLocationName(et_address.getText().toString(), 1);
					if(addresses.size() > 0) {
					    double latitude= addresses.get(0).getLatitude();
					    double longitude= addresses.get(0).getLongitude();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				pushRequest();
				
				

			}
		});

	}

	@SuppressLint("ValidFragment")
	public void showTimePickerDialog2(View v) {
		DialogFragment newFragment = new TimePickerFragment2();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	@SuppressLint("ValidFragment")
	public void showTimePickerDialog(View v) {
		DialogFragment newFragment = new TimePickerFragment();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	public class TimePickerFragment extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					false);
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// Do something with the time chosen by the user

			String AM_PM;
			if (hourOfDay < 12)
				AM_PM = "AM";
			else {
				if(hourOfDay!=12)
				hourOfDay -= 12;
				AM_PM = "PM";
			}
			Log.d(TAG, "value of view" + view + "and" + R.id.et_hr_fromtime);
			et_fromtime.setText("" + hourOfDay + ":" + minute + " " + AM_PM);

		}
	}

	public class TimePickerFragment2 extends DialogFragment implements
			TimePickerDialog.OnTimeSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current time as the default values for the picker
			final Calendar c = Calendar.getInstance();
			int hour = c.get(Calendar.HOUR_OF_DAY);
			int minute = c.get(Calendar.MINUTE);

			// Create a new instance of TimePickerDialog and return it
			return new TimePickerDialog(getActivity(), this, hour, minute,
					false);
		}

		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
			// Do something with the time chosen by the user

			int hour;
			   String minute, amOrPm;
			   if (hourOfDay > 12) {
			    hour = hourOfDay - 12;
			    amOrPm= "PM";
			   } else {
			    hour = hourOfDay;
			    amOrPm = "AM";
			   }
			   if(minuteOfDay < 10) {
			      minute = "0"+minuteOfDay;
			   } else {
			      minute = "" + minuteOfDay;
			   }
				Log.d(TAG, "value of view" + view + "and" + R.id.et_hr_fromtime);
				et_totime.setText("" + hour + ":" + minute + " " + amOrPm);

			
		}
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	public class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			et_date.setText("" + (month+1) + "/" + day + "/" + year);
		}
	}

	private void pushRequest() {
		// TODO Auto-generated method stub
Log.d(TAG,"create post values"+et_description.getText()
				.toString()+ et_date.getText().toString()+ et_totime.getText()
				.toString()+et_fromtime.getText().toString()+ 
				et_numkids.getText().toString());

		mServerConnector.createHomerepairPost(et_description.getText()
				.toString(), et_date.getText().toString(), et_totime.getText()
				.toString(), et_fromtime.getText().toString(), Integer
				.parseInt(et_numkids.getText().toString()), 10, 10,
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						// TODO Auto-generated method stub
						

						String resp = new String(responseBody);

						Gson gson = new Gson();
						

						Response response = gson.fromJson(resp, Response.class);
						if(response.getStatus().equalsIgnoreCase("success"))
						{
						CommonData.openRequestsData = response.getData()
								.getRequests();
						Log.d(TAG, "Creating New post" + resp);
						CommonData.setSignUpflag(false); // because this will populate the create request
						finish();
						}

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub
						Log.d(TAG,"inside create failure ");
						

					}

				});

	}

}
