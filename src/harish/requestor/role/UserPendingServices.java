package harish.requestor.role;

import remove.classes.from.the.pkg.AuctionDetails;
import server.ServerConnector;
import harish.listadapter.uservendor.UserMainListAdapter;
import harish.requestor.commondata.CommonData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.babloosashi.neighbour.R;

public class UserPendingServices extends ListFragment {
	ListView pending_services_list;
	LinearLayout servicell;
	TextView tv_noListMessage;
	UserMainListAdapter mUser_Vendor_ListAdapter;
	ServerConnector mServerConnector;

String Tag ="UserPendingServices";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.servicesist,
				container, false);
		servicell = (LinearLayout) rootView.findViewById(R.id.servicell);
		tv_noListMessage =(TextView) rootView.findViewById(R.id.tv_nolistmessage);
		

		return rootView;
	}



	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		mServerConnector = new ServerConnector(getActivity());
		pending_services_list = getListView();

		if (CommonData.isSignUpflag()) {
			Log.d(Tag,"Inside sign up check if ");
  noListMessage();
			
		}

else{
	Log.d(Tag,"from login ");
getListView().setVisibility(View.VISIBLE);
tv_noListMessage.setVisibility(View.GONE);

if (CommonData.getAcceptedRequestsData().size() != 0) // dont set when data is not present in list
{
	mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
			"UserPendingServices", "requestor", -1);
	pending_services_list.setAdapter(mUser_Vendor_ListAdapter);
tv_noListMessage.setVisibility(View.GONE);

pending_services_list.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		Intent from_current_requests_to_request_bids = new Intent(
				getActivity(), AuctionDetails.class);
		from_current_requests_to_request_bids.putExtra("position", id);
		from_current_requests_to_request_bids.putExtra("fromfragment",
				"UserPendingServices");
		from_current_requests_to_request_bids.putExtra("role", "User");
		Log.d(Tag, "position is" + id);
		from_current_requests_to_request_bids.putExtra("FromFragment",CommonData.AcceptedRequestsFragment);
		startActivity(from_current_requests_to_request_bids);
	}
});


}
else
{
	pending_services_list.setVisibility(View.GONE);
	tv_noListMessage.setVisibility(View.VISIBLE);
}


}
	}
	
	private void noListMessage() {

		getListView().setVisibility(View.GONE);
		tv_noListMessage.setVisibility(View.VISIBLE);
		tv_noListMessage.setText("Request for a Service");
	
}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (CommonData.isSignUpflag()) {
			
			noListMessage();
		}
		else{
		Log.d(Tag, "control back from create post");
		
		mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
				"UserPendingServices", "requestor", -1);
		if (CommonData.getAcceptedRequestsData().size() != 0)
		{
			mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
					"UserPendingServices", "requestor", -1);
		pending_services_list.setAdapter(mUser_Vendor_ListAdapter);
	
		}
		else
		{noListMessage();}
		
		}

	}
}
