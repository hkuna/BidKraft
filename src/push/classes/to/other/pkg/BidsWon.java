package push.classes.to.other.pkg;


import harish.listadapter.uservendor.VendorMainListAdapter;
import harish.requestor.commondata.CommonData;
import remove.classes.from.the.pkg.AuctionDetails;
import server.ServerConnector;

import com.babloosashi.neighbour.R;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class BidsWon extends ListFragment {
	ListView placedBidsList;
	Context context;
	String Tag = "VendorPlacedBids";
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
	
		View rootView = inflater
				.inflate(R.layout.servicesist, container, false);
		servicell = (LinearLayout) rootView.findViewById(R.id.servicell);
		tv_noListMessage = (TextView) rootView
				.findViewById(R.id.tv_nolistmessage);

		return rootView;

	}
	private void noListMessage() {

		getListView().setVisibility(View.GONE);
		tv_noListMessage.setVisibility(View.VISIBLE);
		tv_noListMessage.setText("No Placed Bids , Place A Bid");

	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		mServerConnector = new ServerConnector(getActivity());

		placedBidsList = getListView();
		

		if (CommonData.getPlacedBidsData().size() != 0) // dont set when data is
														// not present in list
		{ getListView().setVisibility(View.VISIBLE);
		Log.d(Tag, "placed bids data is "+ CommonData.getPlacedBidsData());
		
		mUser_Vendor_ListAdapter = new  VendorMainListAdapter(getActivity(),
				CommonData.placedBidsFragment, "vendor", -1);
			placedBidsList.setAdapter(mUser_Vendor_ListAdapter);
	
			tv_noListMessage.setVisibility(View.GONE);

			placedBidsList.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					Intent from_current_requests_to_request_bids = new Intent(
							getActivity(), AuctionDetails.class);
					from_current_requests_to_request_bids.putExtra("position",
							id);
					from_current_requests_to_request_bids.putExtra("fromfragment", CommonData.placedBidsFragment);
					from_current_requests_to_request_bids.putExtra("role",
							"vendor");
					from_current_requests_to_request_bids.putExtra("FromFragment",CommonData.PlacedBidsFragment);
					Log.d(Tag, "position is" + id);

					startActivity(from_current_requests_to_request_bids);
				}
			});
			
			
			
			placedBidsList.setOnItemLongClickListener(new OnItemLongClickListener() {
				// setting onItemLongClickListener and passing the position
				// to the function
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0,
						View arg1, int position, long arg3) {
					
					return true;
				}

			});

		} else {
			noListMessage();
		}

}
}
