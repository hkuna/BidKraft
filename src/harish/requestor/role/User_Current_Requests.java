package harish.requestor.role;

import harish.listadapter.uservendor.UserMainListAdapter;
import harish.requestor.commondata.CommonData;

import java.util.Locale;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import remove.classes.from.the.pkg.AuctionDetails;
import server.ServerConnector;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class User_Current_Requests extends ListFragment {

	String Tag = "User_Current_Requests";
	ListView current_request_list;
	UserMainListAdapter mUser_Vendor_ListAdapter;
	ServerConnector mServerConnector;
	LinearLayout servicell;
	TextView tv_noListMessage;
	EditText et_Search1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater
				.inflate(R.layout.servicesist, container, false);
		servicell = (LinearLayout) rootView.findViewById(R.id.servicell);
		tv_noListMessage =(TextView) rootView.findViewById(R.id.tv_nolistmessage);
		//et_Search = (EditText) rootView.findViewById(R.id.etSearch);	
		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mServerConnector = new ServerConnector(getActivity());
		current_request_list = getListView();
		if (CommonData.isSignUpflag()) {
			Log.d(Tag,"Inside sign up check if ");
  noListMessage();
			
		}
		else
		{
			Log.d(Tag,"from login ");
		getListView().setVisibility(View.VISIBLE);
		//et_Search.setVisibility(View.VISIBLE);
		tv_noListMessage.setVisibility(View.GONE);
		
		if (CommonData.getOpenRequestsData().size() != 0) // dont set when data is not present in list
		{
			mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
					"User_Current_Requests", "requestor", -1);
		current_request_list.setAdapter(mUser_Vendor_ListAdapter);
		tv_noListMessage.setVisibility(View.GONE);
		
	
	/*	et_Search.addTextChangedListener(new TextWatcher() {
			 
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
              /*  String text = et_Search.getText().toString().toLowerCase(Locale.getDefault());
                mUser_Vendor_ListAdapter.filter(text);*/
            /*	if(s.length()>1)*/
                //	mUser_Vendor_ListAdapter.getFilter().filter(s);	
                	/*else
             //   		current_request_list.setAdapter(mUser_Vendor_ListAdapter);
            }
 
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                    int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
 
            @Override
            public void onTextChanged(CharSequence s, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
            	if(s.length()==0)
            	{mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
    					"User_Current_Requests", "requestor", -1);
            	current_request_list.setAdapter(mUser_Vendor_ListAdapter);
            	}
            	
            	
            }
        }); */
		}
		else
		{
			current_request_list.setVisibility(View.GONE);
			tv_noListMessage.setVisibility(View.VISIBLE);
		}

		
		current_request_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent from_current_requests_to_request_bids = new Intent(
						getActivity(), AuctionDetails.class);
				from_current_requests_to_request_bids.putExtra("position", id);
				from_current_requests_to_request_bids.putExtra("fromfragment",
						"User_Current_Requests");
				from_current_requests_to_request_bids.putExtra("role", "User");
				Log.d(Tag, "position is" + id);
				from_current_requests_to_request_bids.putExtra("FromFragment",CommonData.OpenRequestFragment);

				startActivity(from_current_requests_to_request_bids);
			}
		});

		current_request_list
				.setOnItemLongClickListener(new OnItemLongClickListener() {
					// setting onItemLongClickListener and passing the position
					// to the function
					@Override
					public boolean onItemLongClick(AdapterView<?> arg0,
							View arg1, int position, long arg3) {
						
						removeItemFromList(position);

		
						return true;
					}

				});
		
		
		}
	}

	private void noListMessage() {

			getListView().setVisibility(View.GONE);
			tv_noListMessage.setVisibility(View.VISIBLE);
			tv_noListMessage.setText("Request for a Service");
		//	et_Search.setVisibility(View.GONE);
		
	}

	protected void removeItemFromList(final int position) {

		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

		alert.setTitle("Delete");
		alert.setMessage("Do you want delete this item?");
		alert.setPositiveButton("Yes",
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						 ProgressDialog progress = CommonData
								.showProgressBar(getActivity());
						progress.show();
						mServerConnector.deleteRequest(CommonData.getOpenRequestsData().get(position).getRequestId(), new AsyncHttpResponseHandler() {
							
							@Override
							public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
								// TODO Auto-generated method stub
								
								String resp = new String(responseBody);

								Gson gson = new Gson();

								Response response = gson.fromJson(resp,
										Response.class);

								if (response.getStatus().equalsIgnoreCase(
										"success")) {
//not required as server is sending response in return 
									CommonData.getOpenRequestsData()
											.remove(position); // if adapter throws out null value , then just call new adapter on delete
									
									mUser_Vendor_ListAdapter.notifyDataSetChanged();
									CommonData.setOpenRequestsData(response.getData().getOpenRequests());
									
									if (CommonData.getOpenRequestsData().size() != 0) {
										mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
												"User_Current_Requests", "requestor", -1);
									current_request_list.setAdapter(mUser_Vendor_ListAdapter);

									} else {
										noListMessage();
									}
									
								}
								
								//
							}
							
							@Override
							public void onFailure(int statusCode, Header[] headers,
									byte[] responseBody, Throwable error) {
								// TODO Auto-generated method stub
								
							}
						});
						CommonData.hideProgressbar(getActivity(), progress);
					}
				});

		alert.setNegativeButton("CANCEL",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();
					}
				});

		alert.show();
		

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (CommonData.isSignUpflag()) {
			/*Log.d(Tag,"Inside sign up check if ");

			getListView().setVisibility(View.GONE);
			TextView input = new TextView(getActivity());

			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT);
			input.setLayoutParams(lp);
			input.setText("Request For a Service");*/
			noListMessage();
		}
		else{
		Log.d(Tag, "control back from create post");
		
		mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
				"User_Current_Requests", "requestor", -1);
		if (CommonData.getOpenRequestsData().size() != 0)
		{
			mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
					"User_Current_Requests", "requestor", -1);
		current_request_list.setAdapter(mUser_Vendor_ListAdapter);
	
		}
		else
		{noListMessage();}
		
		}

	}
}
