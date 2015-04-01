package harish.requestor.role;

import harish.requestor.commondata.CommonData;

import java.util.Calendar;

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
public class CreateTutoringService extends Activity {

	Button post;
	String code;

	JSONObject vollresponse, jsonBody;
	String Url = "http://rikers.cs.odu.edu:8080/bidding/request/create";

	EditText et_requestdate, et_fromtime, et_totime, et_subjects, et_description;
			
	String TAG = "CreateTutoringService";

	ServerConnector mServerConnector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_tutoringservice);

		mServerConnector = new ServerConnector(CreateTutoringService.this);

		et_requestdate = (EditText) findViewById(R.id.et_RequestedtutoringOn);
		et_fromtime = (EditText) findViewById(R.id.et_tutoringfromtime);
		et_totime = (EditText) findViewById(R.id.et_tutoringtotime);
		et_subjects = (EditText) findViewById(R.id.et_tutoringsubjects);
		
		et_description = (EditText) findViewById(R.id.et_tutoringdescription);

		post = (Button) findViewById(R.id.btn_createtutoringservice);

		post.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				pushRequest();

			}
		});

	}

	@SuppressLint("ValidFragment")
	public void showTimePickerDialogToTime(View v) {
		DialogFragment newFragment = new TimePickerFragment2();
		newFragment.show(getFragmentManager(), "timePicker");
	}

	@SuppressLint("ValidFragment")
	public void showTimePickerDialogFromTime(View v) {
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
				et_fromtime.setText("" + hour + ":" + minute + " " + amOrPm);
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

	public void showDatePickerDialogForRequestOn(View v) {
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
			et_requestdate.setText("" + (month+1) + "/" + day + "/" + year);
		}
	}

	private void pushRequest() {
		// TODO Auto-generated method stub
Log.d(TAG,"create post values"+et_description.getText()
				.toString()+ et_requestdate.getText().toString()+ et_totime.getText()
				.toString()+et_fromtime.getText().toString()
				);

		mServerConnector.createTutoringservice(et_requestdate.getText().toString(), et_fromtime.getText().toString(), et_totime.getText()
				.toString(), et_subjects.getText().toString(), et_description.getText().toString(), new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
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
						
					}
				});

	}
}
