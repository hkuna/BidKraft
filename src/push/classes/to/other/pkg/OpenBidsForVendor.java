package push.classes.to.other.pkg;

import harish.listadapter.uservendor.VendorMainListAdapter;
import harish.requestor.commondata.CommonData;
import remove.classes.from.the.pkg.AuctionDetails;
import server.ServerConnector;
import android.content.Context;
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

//your Bids
public class OpenBidsForVendor extends ListFragment {

	Context context;
	String Tag = "OpenBids class";
	ListView openBidsList;
	VendorMainListAdapter mUser_Vendor_ListAdapter;
	ServerConnector mServerConnector;
	LinearLayout servicell;
	TextView tv_noListMessage;

	/*
	 * CurrentBids init(Context context) { CurrentBids fragm_obj = new
	 * CurrentBids(); this.context = context; // Supply val input as an
	 * argument. Bundle args = new Bundle(); args.putString("Context",
	 * context.toString()); fragm_obj.setArguments(args); Log.d(tag,
	 * "iniside init   "
	 * +context+"and context value as string "+context.toString());
	 * 
	 * 
	 * return fragm_obj; }
	 */

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater
				.inflate(R.layout.servicesist, container, false);
		servicell = (LinearLayout) rootView.findViewById(R.id.servicell);
		tv_noListMessage = (TextView) rootView
				.findViewById(R.id.tv_nolistmessage);

	

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		mServerConnector = new ServerConnector(getActivity());

		openBidsList = getListView();
		
		if (CommonData.getOpenBidsData().size() != 0) // dont set when data is
														// not present in list
		{
			getListView().setVisibility(View.VISIBLE);
			Log.d(Tag, "opends bids data is "+ CommonData.getOpenBidsData());
			mUser_Vendor_ListAdapter = new VendorMainListAdapter(getActivity(),
					CommonData.openBidsForVendorFragment, "vendor", -1);
			openBidsList.setAdapter(mUser_Vendor_ListAdapter);
			tv_noListMessage.setVisibility(View.GONE);

			openBidsList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent from_current_requests_to_request_bids = new Intent(
							getActivity(), AuctionDetails.class);
					from_current_requests_to_request_bids.putExtra("position",
							id);
					from_current_requests_to_request_bids.putExtra(
							"fromfragment",
							CommonData.openBidsForVendorFragment);
					from_current_requests_to_request_bids.putExtra("role",
							"vendor");
					Log.d(Tag, "position is" + id);

					startActivity(from_current_requests_to_request_bids);
				}
			});

		} else {
			noListMessage();
		}

	}

	private void noListMessage() {

		getListView().setVisibility(View.GONE);
		tv_noListMessage.setVisibility(View.VISIBLE);
		tv_noListMessage.setText("No open Bids to Bid on");

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		Log.d("fragmentA", "control back from vendor");
		mUser_Vendor_ListAdapter = new VendorMainListAdapter(getActivity(),
				CommonData.openBidsForVendorFragment, "vendor", -1);
		mUser_Vendor_ListAdapter.notifyDataSetChanged();
		if (CommonData.getOpenBidsData().size() != 0) {
			getListView().setVisibility(View.VISIBLE);
			
			openBidsList.setAdapter(mUser_Vendor_ListAdapter);

		} else {
			noListMessage();
		}

	}

}
