package harish.requestor.role;

import harish.requestor.commondata.CommonData;

import java.util.Calendar;

import org.apache.http.Header;
import org.json.JSONObject;

import push.classes.to.other.pkg.Response;
import remove.classes.from.the.pkg.CreateNewPost;
import remove.classes.from.the.pkg.CreateNewPost.DatePickerFragment;
import remove.classes.from.the.pkg.CreateNewPost.TimePickerFragment;
import remove.classes.from.the.pkg.CreateNewPost.TimePickerFragment2;
import server.ServerConnector;

import com.babloosashi.neighbour.R;
import com.babloosashi.neighbour.R.layout;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class CreateSellTextBookService extends Activity {
	
	Button post;
	String code;

	JSONObject vollresponse, jsonBody;
	String Url = "http://rikers.cs.odu.edu:8080/bidding/request/create";

	EditText et_sellrequestdateon, et_author, et_edition, et_cost, et_description,et_title;
	String TAG = "New Post";

	ServerConnector mServerConnector;
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_sell_text_book_service);

		mServerConnector = new ServerConnector(CreateSellTextBookService.this);

		et_sellrequestdateon = (EditText) findViewById(R.id.et_sellrequestdateon);
		et_author = (EditText) findViewById(R.id.et_sellbookauthor);
		et_edition = (EditText) findViewById(R.id.et_sellbookedition);
		et_cost = (EditText) findViewById(R.id.et_sellbookcost);
		et_title = (EditText) findViewById(R.id.et_sellbooktitle);
		et_description = (EditText) findViewById(R.id.et_bookselldescription);

		post = (Button) findViewById(R.id.btn_createselltextbookpost);

		post.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				pushRequest();

			}
		});

	}

	

	

	public void showDatePickerDialogForSellBookRequestOn(View v) {
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
			et_sellrequestdateon.setText("" + (month+1) + "/" + day + "/" + year);
		}
	}

	private void pushRequest() {
		// TODO Auto-generated method stub


		mServerConnector.createSellBookService(et_title.getText().toString(), et_author.getText().toString(), et_edition.getText().toString(), et_sellrequestdateon.getText().toString(), et_description.getText().toString(), et_cost.getText().toString(), new AsyncHttpResponseHandler() {
			
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
