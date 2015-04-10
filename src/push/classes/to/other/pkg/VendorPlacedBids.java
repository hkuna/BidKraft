package push.classes.to.other.pkg;

import org.apache.http.Header;

import harish.listadapter.uservendor.VendorMainListAdapter;
import harish.requestor.commondata.CommonData;
import remove.classes.from.the.pkg.AuctionDetails;
import server.ServerConnector;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;

// open requests available 

public class VendorPlacedBids extends ListFragment  {

	
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
					removeItemFromList(position);

					return true;
				}

			});

		} else {
			noListMessage();
		}

	}

	protected void removeItemFromList(int position) {
		final int deletePosition = position;

		AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

		alert.setTitle("Delete");
		alert.setMessage("Do you want delete this Bid?");
		alert.setPositiveButton("YES", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TOD O Auto-generated method stub

				// main code on after clicking yes

				ProgressDialog progress = CommonData
						.showProgressBar(getActivity());
				progress.show();
				// calling server to notify delete bid

				mServerConnector.deleteBid(CommonData.getPlacedBidsData().get(deletePosition).getRequestId(), CommonData.getUserId(), new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
						// TODO Auto-generated method stub
						String resp = new String(responseBody);

						Gson gson = new Gson();

						Response response = gson.fromJson(resp,
								Response.class);
						
						if (response.getStatus().equalsIgnoreCase(
								"success")) {
// This is commented because , server is sending data after deleting bid, so update the data into common data deilds
					/*		CommonData.getPlacedBidsData()
									.remove(deletePosition); // if adapter throws out null value , then just call new adapter on delete
							
							mUser_Vendor_ListAdapter.notifyDataSetChanged();	*/
							
						CommonData.setPlacedBidsData(response.getData().getPlacedBids());
							
							CommonData.setOpenBidsData(response.getData().getOpenBids());
							
							mUser_Vendor_ListAdapter.notifyDataSetChanged();
					
							if (CommonData.getPlacedBidsData().size() != 0) {
								mUser_Vendor_ListAdapter = new VendorMainListAdapter(getActivity(),
										CommonData.placedBidsFragment, "vendor", -1);
								placedBidsList.setAdapter(mUser_Vendor_ListAdapter);

							} else {
								noListMessage();
							}
							
							
							Toast.makeText(getActivity(), "Deleted", Toast.LENGTH_LONG).show(); 
							
							//after deleting when list becomes zero size , it should pop a message
							if(CommonData.getPlacedBidsData().isEmpty())
							{
								noListMessage();
							}
						
						}
						
					}
					
					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub
						
						Toast.makeText(getActivity(), "Cannot be deleted", Toast.LENGTH_LONG).show();
						
					}
				});
				CommonData.hideProgressbar(getActivity(), progress);
			}
			});
			
		alert.setNegativeButton("CANCEL", new OnClickListener() {
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

		Log.d("FragmentB", "control back from open bids");

		if (CommonData.getPlacedBidsData().size() != 0) {
			mUser_Vendor_ListAdapter = new VendorMainListAdapter(getActivity(),
					CommonData.placedBidsFragment, "vendor", -1);
			placedBidsList.setAdapter(mUser_Vendor_ListAdapter);

		} else {
			noListMessage();
		}

	}

}
