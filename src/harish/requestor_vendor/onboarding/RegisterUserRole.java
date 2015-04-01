package harish.requestor_vendor.onboarding;

import harish.requestor.commondata.CommonData;

import java.util.ArrayList;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class RegisterUserRole extends ListFragment {
	Spinner role_spinner;
	Button btn_signup;
	ListView category_list;
	String[] category_list_array;
	String fname, lname, email, password, phone, username;
	String tag = "RegisterUserRole";
	ServerConnector mServerConnector;
	ArrayList<Integer> register_categoryIds;

	private int roleId;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.signuprole, container, false);
		role_spinner = (Spinner) rootView.findViewById(R.id.spinner_role);
		btn_signup = (Button) rootView.findViewById(R.id.btn_signup);
		btn_signup.setEnabled(false);

		if (getArguments() != null) {
			fname = getArguments().getString("firstname");
			lname = getArguments().getString("lastname");
			email = getArguments().getString("email");
			password = getArguments().getString("password");
			phone = getArguments().getString("phone");
			username = getArguments().getString("userName");

			Log.d(tag,
					"bundle values from one frag to other via activitysss    "
							+ fname + lname + email + password + phone
							+ username);
		}
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		category_list = getListView();
		category_list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		register_categoryIds = new ArrayList<Integer>(); // for reading category
															// id's from list
		mServerConnector = new ServerConnector(getActivity());

		category_list_array = getResources().getStringArray(
				R.array.category_list);
		Log.d(tag, "category size from resources " + category_list_array.length);

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.role_array,
				android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		role_spinner.setAdapter(adapter);

		role_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				if (position == 1 || position == 2) {
					roleId = position + 1;
					btn_signup.setEnabled(true);
					category_list.setVisibility(View.VISIBLE);
				}
				if (position == 0) {
					roleId = position + 1;
					category_list.setVisibility(View.GONE);
					btn_signup.setEnabled(true);
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		category_list
				.setAdapter(new ArrayAdapter<String>(getActivity(),
						android.R.layout.simple_list_item_checked,
						category_list_array));

		btn_signup.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// send all the data to server for registering the user
				ProgressDialog progress = CommonData
						.showProgressBar(getActivity());
				progress.show();

				mServerConnector.signUp(fname, lname, email, password, phone,
						 username, 
						new AsyncHttpResponseHandler() {

							@Override
							public void onSuccess(int statusCode,
									Header[] headers, byte[] responseBody) {
								// TODO Auto-generated method stub
								Response response = CommonData
										.convertGSonObjectToResponseClass(responseBody);
								CommonData.setSignUpflag(true);
								
								String status = CommonData
										.onLoginOrRegistrationListener(
												response, getActivity());

								Toast.makeText(getActivity(), status,
										Toast.LENGTH_LONG).show();

								Toast.makeText(getActivity(),
										response.getStatus(), Toast.LENGTH_LONG)
										.show();

							}

							@Override
							public void onFailure(int statusCode,
									Header[] headers, byte[] responseBody,
									Throwable error) {
								// TODO Auto-generated method stub

							}
						});

				CommonData.hideProgressbar(getActivity(), progress);

			}
		});

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		CheckedTextView item = (CheckedTextView) v;
		Toast.makeText(
				getActivity(),
				category_list_array[position] + " checked : "
						+ item.isChecked(), Toast.LENGTH_SHORT).show();
		Integer obj = Integer.valueOf(position + 1);
		if (roleId == 2 || roleId == 3) {

			if (item.isChecked()) {// if it is checked , add it to array
				register_categoryIds.add(position + 1);
				Log.d(tag,
						"category array values "
								+ register_categoryIds.toString());
			} else {
				if (register_categoryIds.contains(obj)) // if checked and later
														// unchecked then remove
														// from array
				{
					Log.d(tag, "category array values before removing "
							+ register_categoryIds.toString());
					register_categoryIds.remove(obj);
					Log.d(tag, "category array values  after removing"
							+ register_categoryIds.toString());

				}
			}

		}

	}
}

/*
 * private class Category_ListAdapter extends BaseAdapter { TextView
 * category_name; CheckBox cb_category_name; String[] category_list_array =
 * getResources().getStringArray(R.array.category_list); private LayoutInflater
 * inflater = null;
 * 
 * 
 * Category_ListAdapter(Context context) {
 * 
 * inflater = (LayoutInflater) context
 * .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 * 
 * }
 * 
 * @Override public int getCount() { // TODO Auto-generated method stub return
 * 5; }
 * 
 * @Override public Object getItem(int arg0) { // TODO Auto-generated method
 * stub return category_list_array[arg0] ; }
 * 
 * @Override public long getItemId(int arg0) { // TODO Auto-generated method
 * stub return 0; }
 * 
 * @Override public View getView(int position, View convertView, final ViewGroup
 * parent) { // TODO Auto-generated method stub View rootView = convertView; if
 * (rootView == null) { rootView = inflater.inflate(R.layout.categorychecklist,
 * null); }
 * 
 * category_name = (TextView)
 * rootView.findViewById(R.id.tv_vendor_categorylist); cb_category_name
 * =(CheckBox) rootView.findViewById(R.id.chk_bx_vendor_categorylist);
 * category_name.setText(category_list_array[position]);
 * 
 * 
 * 
 * 
 * return rootView; }
 * 
 * }
 */

