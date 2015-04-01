package harish.requestor.role;

import harish.listadapter.uservendor.UserMainListAdapter;
import harish.requestor.commondata.CommonData;
import remove.classes.from.the.pkg.AuctionDetails;
import server.ServerConnector;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.babloosashi.neighbour.R;

public class CompletedRequests extends ListFragment {

	ListView serviced_list;
	LinearLayout servicell;
	TextView tv_noListMessage;
	UserMainListAdapter mUser_Vendor_ListAdapter;
	ServerConnector mServerConnector;
	
	

String Tag ="CompletedRequests";
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
		serviced_list = getListView();

		serviced_list = getListView();
		if (CommonData.isSignUpflag()) {
			Log.d(Tag,"Inside sign up check if ");
  noListMessage();
			
		}

else{
	Log.d(Tag,"from login ");
getListView().setVisibility(View.VISIBLE);
tv_noListMessage.setVisibility(View.GONE);

if (CommonData.getServicedRequestsData().size() != 0) // dont set when data is not present in list
{
	mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
			"ServicedRequests", "requestor", -1);
	serviced_list.setAdapter(mUser_Vendor_ListAdapter);
tv_noListMessage.setVisibility(View.GONE);

serviced_list.setOnItemClickListener(new OnItemClickListener() {

	@Override
	public void onItemClick(AdapterView<?> parent, View view,
			int position, long id) {
		Intent from_current_requests_to_request_bids = new Intent(
				getActivity(), AuctionDetails.class);
		from_current_requests_to_request_bids.putExtra("position", id);
		from_current_requests_to_request_bids.putExtra("fromfragment",
				"ServicedRequests");
		from_current_requests_to_request_bids.putExtra("role", "User");
		Log.d(Tag, "position is" + id);

		startActivity(from_current_requests_to_request_bids);
	}
});


}
else
{
	serviced_list.setVisibility(View.GONE);
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
				"ServicedRequests", "requestor", -1);
		if (CommonData.getAcceptedRequestsData().size() != 0)
		{
			mUser_Vendor_ListAdapter = new UserMainListAdapter(getActivity(),
					"ServicedRequests", "requestor", -1);
			serviced_list.setAdapter(mUser_Vendor_ListAdapter);
	
		}
		else
		{noListMessage();}
		
		}

	}
}