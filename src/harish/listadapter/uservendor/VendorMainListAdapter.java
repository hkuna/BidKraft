package harish.listadapter.uservendor;

import harish.requestor.commondata.CommonData;

import java.util.ArrayList;

import push.classes.to.other.pkg.PlacedBidsDataStruct;
import json.datastructures.Requestor_Json_Data_Structure;
import server.ServerConnector;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.babloosashi.neighbour.R;

public class VendorMainListAdapter extends BaseAdapter {
	private static LayoutInflater inflater = null;

	TextView biddername, bidderoffer, ratingcount;
	ImageView categoryidimage;

	ArrayList<PlacedBidsDataStruct> pb_datastruct;
	String tag = "User_Vendor_ListAdapter";

	private TextView date, time, newbids, leastbids, totalbids;

	private ImageView icon, bidderpic;
	private RatingBar rating;
	private Button acceptBid;
	private String from_fragment;
	private int clickedid;
	private Context context;
	private String role;
	private ServerConnector mServerConnector;
	private ProgressDialog progress;
	private TextView tv_serviceSubject , tv_leastbidamount;
	public VendorMainListAdapter(Context context, String from_fragment,
			String role, int clickedid) {
		this.from_fragment = from_fragment;
		this.role = role;
		this.context = context;
		this.clickedid = clickedid;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.d(tag, "inside 1st constructor " + from_fragment +"clicked Id"+clickedid);
		

	}

	@Override
	public int getCount() {
		// check if role is user
 //return checkForRoleAndSourceFragment();
		
		int count = 0;
		
		// check if control is from user main fragment or auction activity
		if (role.equalsIgnoreCase(CommonData.getAuction())) {
			// inside the auction screen , return the list of bids for the
			// selected requested data
			switch (getFromFragment()) {
			case 1:
				count = CommonData.getOpenBidsData().get(clickedid)
						.getBids().size();
				break;

			case 2:
				count = CommonData.getPlacedBidsData().get(clickedid)
						.getBids().size();
				break;

			}

		} else {

			switch (getFromFragment()) {
			case 1:
				count = CommonData.getOpenBidsData().size();
				break;

			case 2:
				count = CommonData.getPlacedBidsData().size();
				break;

			}

		}
		return count;
		
	

	}
	
	private int getFromFragment() {

		if (from_fragment.equalsIgnoreCase(CommonData.openBidsForVendorFragment)) {
			return 1;
		} else if (from_fragment.equalsIgnoreCase(CommonData.placedBidsFragment)) {
			return 2;
		}
			else
				return 0;
		
		}

	
		
	


	@Override
	public Object getItem(int arg0) {
		// check if role is user

if (role.equalsIgnoreCase("auction")) {
			
			// check if it from vendor open bids or vendor placed bids
			if (from_fragment
					.equalsIgnoreCase(CommonData.openBidsForVendorFragment)) {
				return CommonData.getOpenBidsData();
			} else if (from_fragment
					.equalsIgnoreCase(CommonData.placedBidsFragment)) {
				// vendor placed bids
				return  CommonData.getPlacedBidsData();
			} else {
				return  null;
			}

		} 
		else {
			if (from_fragment
					.equalsIgnoreCase(CommonData.openBidsForVendorFragment)) {
				// check if it is coming from "open bids or placed bids"
				return CommonData.getOpenBidsData();
			} else { // return placed bids
				return CommonData.getPlacedBidsData();

			}
		}

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView( int position, View convertView,
			 ViewGroup parent) {

		View rootview = convertView;

		if (rootview == null) {
			if (role.equalsIgnoreCase(CommonData.getAuction())) {
				rootview = inflater.inflate(R.layout.offersrow, null);
				initializeViewElements(rootview, "R.layout.offersrow");
				acceptBid.setVisibility(View.GONE);
				if(from_fragment.equalsIgnoreCase(CommonData.openBidsForVendorFragment))
				{
		
				vendorBids(clickedid, rootview, role, position,
						from_fragment);
				}
				else if (from_fragment.equalsIgnoreCase(CommonData.placedBidsFragment))
				{
					
					vendorBids(clickedid, rootview, role, position,
							from_fragment);
				}

			} else {
				// if not from auction screen

				rootview = inflater.inflate(R.layout.userlistitem, null);
				initializeViewElements(rootview, "R.layout.requestrow");

				if (from_fragment
						.equalsIgnoreCase(CommonData.openBidsForVendorFragment))
					vendorBids(-1, rootview, role, position,
							from_fragment);
				else if (from_fragment
						.equalsIgnoreCase(CommonData.placedBidsFragment)) {
					vendorBids(-1, rootview, role, position,
							from_fragment);
				}

			}
		}
		// from second time the view control goes from here
		// auction check
		if (role.equalsIgnoreCase(CommonData.getAuction())) {
			rootview = inflater.inflate(R.layout.offersrow, null);
			initializeViewElements(rootview, "R.layout.offersrow");
			acceptBid.setVisibility(View.GONE);
			if(from_fragment.equalsIgnoreCase(CommonData.openBidsForVendorFragment))
			{
	
			vendorBids(clickedid, rootview, role, position,
					from_fragment);
			}
			else if (from_fragment.equalsIgnoreCase(CommonData.placedBidsFragment))
			{
				
				vendorBids(clickedid, rootview, role, position,
						from_fragment);
			}


		} 
		// if not auction
		
		else {
			rootview = inflater.inflate(R.layout.userlistitem, null);
			 initializeViewElements(rootview, "R.layout.requestrow");
			if (from_fragment
					.equalsIgnoreCase(CommonData.openBidsForVendorFragment))
				vendorBids(-1, rootview, role, position, from_fragment);
			else if (from_fragment
					.equalsIgnoreCase(CommonData.placedBidsFragment)) {
				vendorBids(-1, rootview, role, position, from_fragment);
			}
		}
		return rootview;
	}

	private void vendorBids(int clickedId, View vi,
			String role, int position, String from_fragment) {

		if (role.equalsIgnoreCase("auction")) { 
			Requestor_Json_Data_Structure o = new Requestor_Json_Data_Structure() ;
			if(from_fragment.equalsIgnoreCase(CommonData.openBidsForVendorFragment))
			{
				o =CommonData.getOpenBidsData().get(clickedId);

			}
			else
			{
			 o = CommonData.getPlacedBidsData().get(clickedId);
		
			}
			biddername.setText(o.getBids().get(position).getOffererName());
			bidderoffer.setText("$ " + o.getBids().get(position).getBidAmount());

		} else {
			Requestor_Json_Data_Structure o  = new Requestor_Json_Data_Structure();
			if(from_fragment.equalsIgnoreCase(CommonData.openBidsForVendorFragment))
			{
		o = CommonData
					.getOpenBidsData().get(position);
		
			
			}
			
			else
			{
				o = CommonData
						.getPlacedBidsData().get(position);
			}
			
		/*	date.setText(o.getCreatedDate().subSequence(0, 7)); 
			time.setText(o.getCreatedDate().subSequence(17, 22));
			leastbids.setText("Lowest bid : " + o.getLeastBidAmount());
			totalbids.setText("Toatal Bids :" + o.getTotalBids());*/
			
			tv_serviceSubject.setText(o.getDescription());
			tv_leastbidamount.setText("$"+o.getLeastBidAmount()+"/hr");

			Log.d(tag, "category id value " + o.getCategoryId().toString());

			/*CommonData.setCategoryImage(o.getCategoryId().toString(), vi,
					context, categoryidimage);*/
		}

	}

	

	private void initializeViewElements(View rootview, String from_xml_file) {
		if (from_xml_file.equalsIgnoreCase("R.layout.requestrow")) {
			/*date = (TextView) rootview.findViewById(R.id.tvdate);
			time = (TextView) rootview.findViewById(R.id.tvtime);
			newbids = (TextView) rootview.findViewById(R.id.tvNewbids);
			categoryidimage = (ImageView) rootview
					.findViewById(R.id.categoryid);
			leastbids = (TextView) rootview.findViewById(R.id.tvleastbid);
			totalbids = (TextView) rootview.findViewById(R.id.tvtotalbids);*/
			
			tv_serviceSubject = (TextView) rootview.findViewById(R.id.tv_servicedetails);
			tv_leastbidamount =(TextView) rootview.findViewById(R.id.tv_bidamount);
		}

		else if (from_xml_file.equalsIgnoreCase("R.layout.offersrow")) {
			biddername = (TextView) rootview.findViewById(R.id.biddername);

			acceptBid = (Button) rootview.findViewById(R.id.bidAccept);

			bidderoffer = (TextView) rootview.findViewById(R.id.bidderoffer);

		}
	}
	
	

}
