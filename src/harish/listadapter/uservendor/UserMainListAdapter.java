package harish.listadapter.uservendor;

import harish.requestor.commondata.CommonData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.crypto.spec.PSource;

import json.datastructures.Requestor_Json_Data_Structure;

import org.apache.http.Header;

import push.classes.to.other.pkg.Bid;
import push.classes.to.other.pkg.Response;
import remove.classes.from.the.pkg.PayPalActivity;
import remove.classes.from.the.pkg.RatingActivity;
import server.ServerConnector;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class UserMainListAdapter extends BaseAdapter implements OnClickListener {

	private static LayoutInflater inflater = null;

	TextView biddername, bidderoffer, ratingcount;
	//ImageView categoryidimage;

	String tag = "User_Vendor_ListAdapter";

	long startMillis;
	//private TextView date, time, newbids, leastbids, totalbids;
	private TextView tv_serviceSubject , tv_leastbidamount;

	private ImageView icon, bidderpic;
	private RatingBar rating;
	private Button acceptBid;
	private String from_fragment;
	private int clickedid;
	Context mcontext;
	private String role;
	private ServerConnector mServerConnector;
	private ProgressDialog progress;
	int sourceFragmentId;
	int positionId;
	ArrayList<Requestor_Json_Data_Structure> requests,temp;

	public UserMainListAdapter(Context context, String from_fragment,
			String role, int clickedid) {
		mServerConnector = new ServerConnector(context);
		this.from_fragment = from_fragment;
		this.role = role;
		sourceFragmentId = getFromFragment(); // should be after defining
												// from_fragment
		this.mcontext = context;
		this.clickedid = clickedid;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Log.d(tag, "inside 1st constructor " + from_fragment);

	}

	@Override
	public int getCount() {
		// check if role is user
		int count = 0;
		// check if control is from user main fragment or auction activity
		if (role.equalsIgnoreCase(CommonData.getAuction())) {
			// inside the auction screen , return the list of bids for the
			// selected requested data
			count = requests.get(clickedid)
					.getBids().size();
		} else {

		count = requests.size();

		}
		return count;

	}

	private int getFromFragment() {
int id;
		if (from_fragment.equalsIgnoreCase("User_Current_Requests")) {
			requests = CommonData.getOpenRequestsData();
			
		id= 1;
		} else if (from_fragment.equalsIgnoreCase("UserPendingServices")) {
			requests = CommonData.getAcceptedRequestsData();
		id =2;
		} else { // servicedFragment
			requests = CommonData.getServicedRequestsData();
		id =3;

		}
		temp = requests;
		return id;
	

	}

	@Override
	public Object getItem(int arg0) {
		// check if role is user

		// check if control is from user main fragment or auction activity
		if (role.equalsIgnoreCase(CommonData.getAuction())) {
			// inside the auction screen , return the list of bids for the
			// selected requested data
			ArrayList<Bid> bids = new ArrayList<Bid>();
			
			bids = requests.get(clickedid)
					.getBids();
			return bids;
		} else {
			
			return requests;

		}

	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		View rootview = convertView;
		positionId = position;

		if (rootview == null) {
 
			if (role.equalsIgnoreCase(CommonData.getAuction())) {
				rootview = inflater.inflate(R.layout.offersrow, null);
				initializeViewElements(rootview, "R.layout.offersrow");

				switch (sourceFragmentId) {
				case 1:
					acceptBid.setVisibility(View.VISIBLE);

					acceptBid.setOnClickListener(this);

					break;

				case 2:
					acceptBid.setVisibility(View.VISIBLE);
					acceptBid.setText("Close Request");
					acceptBid.setOnClickListener(this);

					break;

				case 3:
					acceptBid.setVisibility(View.GONE);
					break;

				}
				allUserRequests(clickedid, rootview, from_fragment, position);

			} else {

				rootview = inflater.inflate(R.layout.userlistitem, null);
				initializeViewElements(rootview, "R.layout.requestrow");

				allUserRequests(-1, rootview, role, position);

			}

		}

		else {

			if (role.equals("auction")) {

				rootview = inflater.inflate(R.layout.offersrow, null);
				initializeViewElements(rootview, "R.layout.offersrow");

				switch (sourceFragmentId) {
				case 1:
					acceptBid.setVisibility(View.VISIBLE);

					acceptBid.setOnClickListener(this);

					break;

				case 2:
					acceptBid.setVisibility(View.VISIBLE);
					acceptBid.setText("Close Request");
					acceptBid.setOnClickListener(this);

					break;

				case 3:
					acceptBid.setVisibility(View.GONE);
					break;

				}

				allUserRequests(clickedid, rootview, from_fragment, position);

			}

			else {
				rootview = inflater.inflate(R.layout.userlistitem, null);
				initializeViewElements(rootview, "R.layout.requestrow");

				allUserRequests(-1, rootview, role, position);
			}

		}
		return rootview;
	}

	// used for User role only
	private void allUserRequests(int clickedId, View vi, String from_fragment,
			int position) {
		// position here can be clicked id or normal position

		if (role.equalsIgnoreCase("auction")) {

			Requestor_Json_Data_Structure o = requests.get(clickedId);
			biddername.setText(o.getBids().get(position).getOffererName());
			bidderoffer.setText("$ "
					+ o.getBids().get(position).getBidAmount());

		} else {
			Requestor_Json_Data_Structure o = requests.get(position);
			tv_serviceSubject.setText(o.getDescription());
			tv_leastbidamount.setText("$"+o.getLeastBidAmount()+"/hr");
			/*date.setText(o.getRequestStartDate().subSequence(6, 16));
			time.setText(o.getCreatedDate().subSequence(17, 22));
			leastbids.setText("Lowest bid : " + o.getLeastBidAmount());
			totalbids.setText("Total Bids :" + o.getTotalBids());*/
			/*CommonData.setCategoryImage(o.getCategoryId().toString(), vi,
					mcontext, categoryidimage);
*/
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

	@Override
	public void onClick(View arg0) {
		
		progress = new ProgressDialog(mcontext);
		progress.setMessage("One moment!");
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setIndeterminate(false);

		
		Log.d(tag, "inside accept button clcik method " + positionId);

		switch (getFromFragment()) {
		case 1:
			
       
		 serverCall();
			break;
		case 2:
			Intent i = new Intent(((Activity) mcontext), RatingActivity.class);
			i.putExtra("requestId",Integer.parseInt(requests
					.get(clickedid).getRequestId()));
		mcontext.startActivity(i);
			((Activity) mcontext).finish(); 
			/*progress.show();
			mServerConnector.closeRequest(
					Integer.parseInt(requests
							.get(clickedid).getRequestId()),CommonData.ROLE_ID_USER,
					new AsyncHttpResponseHandler() {

						@Override
						public void onSuccess(int statusCode, Header[] headers,
								byte[] responseBody) {
							
							Response response = CommonData
									.convertGSonObjectToResponseClass(responseBody);
							if (response.getStatus()
									.equalsIgnoreCase("success")) {
								// commented because response is being sent to user with data 
								// update the accepted list and serviced list
								CommonData.servicedRequestsData.add(CommonData
										.getAcceptedRequestsData().get(
												clickedid));
								CommonData.acceptedRequestsData
										.remove(clickedid);
								notifyDataSetChanged();
								((Activity) mcontext).finish(); 
								CommonData.setAcceptedRequestsData(response.getData().getAcceptedRequests());
								CommonData.setServicedRequestsData(response.getData().getServicedRequests());
								
							//	notifyDataSetChanged();
								((Activity) mcontext).finish();
								
							}

						}

						@Override
						public void onFailure(int statusCode, Header[] headers,
								byte[] responseBody, Throwable error) {
							// TODO Auto-generated method stub

						}
					});
			CommonData.hideProgressbar(mcontext, progress);*/

			break;
		case 3:
			break;

		}

	}

	
	private void serverCall() {
		

	    
		
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz");
			String dateInString = requests.get(positionId).getRequestStartDate();
			
			Date date = formatter.parse(dateInString);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Calendar beginTime = Calendar.getInstance();
			 String finalDate = sdf.format(date);
			 int year = Integer.parseInt((String) finalDate.subSequence(6, 10));
				int month = Integer.parseInt((String) finalDate.subSequence(0, 2));
				int day = Integer.parseInt((String) finalDate.subSequence(3, 5));
				int hr = Integer.parseInt((String) requests.get(positionId).getRequestStartTime().subSequence(0, 2));
				int mm = Integer.parseInt((String) requests.get(positionId).getRequestStartTime().subSequence(3, 5));
			beginTime.set(year, month, day, hr, mm);
			startMillis = beginTime.getTimeInMillis();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	
		
		ArrayList<Requestor_Json_Data_Structure> openrequests = requests;
		progress.show();
		mServerConnector.acceptBid(Integer.parseInt(openrequests.get(
				clickedid).getRequestId()), Integer.parseInt(openrequests
				.get(clickedid).getBids().get(positionId).getBidId()), new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
						// TODO Auto-generated method stub
						Response response = CommonData
								.convertGSonObjectToResponseClass(responseBody);

						if (response.getStatus().equals("success")) {
// not required as server is sending the updated list
							// if response is success
							/*if (response.getData().getOpenRequests().size() != 0) {
								CommonData.openRequestsData.clear();
								CommonData.setOpenRequestsData(response
										.getData().getOpenRequests());
								notifyDataSetChanged();
								
							}
							if (response.getData().getAcceptedRequests()
									.size() != 0) {
								CommonData.acceptedRequestsData.clear();
								CommonData.setAcceptedRequestsData(response
										.getData().getAcceptedRequests());
								notifyDataSetChanged();
								
							}*/
							Intent i = new Intent(mcontext,PayPalActivity.class);
							i.putExtra(
									"BidAmount",
									requests.get(clickedid).getBids().get(positionId).getBidAmount());
							
							mcontext.startActivity(i);
							
							Long eventId = pushAppointmentsToCalender((Activity) mcontext, "BABY SITTER", "need desperately", "America", 1, startMillis, true, true);
							CommonData.setOpenRequestsData(response
									.getData().getOpenRequests());
							CommonData.setAcceptedRequestsData(response
									.getData().getAcceptedRequests());
							notifyDataSetChanged();
							
							
							
							
							((Activity) mcontext).finish();
					}
					}
					
					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						// TODO Auto-generated method stub
						Log.d(tag, "on click reached failure");

						
					}
				});
		CommonData.hideProgressbar(mcontext, progress);
		/*
		mServerConnector.acceptBid(Integer.parseInt(openrequests.get(
				clickedid).getRequestId()), Integer.parseInt(openrequests
				.get(clickedid).getBids().get(positionId).getBidId()),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						progress.dismiss();
						Log.d(tag, "reached success");

						Response response = CommonData
								.convertGSonObjectToResponseClass(responseBody);

						if (response.getStatus().equals("success")) {

							// if response is success
							if (response.getData().getOpenRequests().size() != 0) {
								CommonData.openRequestsData.clear();
								CommonData.setOpenRequestsData(response
										.getData().getOpenRequests());
								notifyDataSetChanged();
							}
							if (response.getData().getAcceptedRequests()
									.size() != 0) {
								CommonData.acceptedRequestsData.clear();
								CommonData.setAcceptedRequestsData(response
										.getData().getAcceptedRequests());
								notifyDataSetChanged();
							}
							// got new data just update the lists after
							// fininshing the activity , on resume

						}

					}

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						progress.dismiss();
						Log.d(tag, "on click reached failure");

					}
				});
		*/
	}
	
/*	public void filter(String charText)
	{
		charText = charText.toLowerCase(Locale.getDefault());
		
		if(charText.length()==0)
		{	
		setRequests();
		}
		else
		{requests.clear();
			
			for(Requestor_Json_Data_Structure req : temp)
			{
				if(((String) req.getRequestStartDate().subSequence(0, 7)).toLowerCase(Locale.getDefault()).contains(charText))
				{
					requests.add(req);
				}
			}
		}
		notifyDataSetChanged();
	}*/
	
    public android.widget.Filter getFilter() {
        return new android.widget.Filter() {
			
			@SuppressWarnings("unchecked")
			@Override
			protected void publishResults(CharSequence constraint, FilterResults results) {
				// TODO Auto-generated method stub
				 Log.d("CAT", "**** PUBLISHING RESULTS for: " + constraint);
	                requests = (ArrayList<Requestor_Json_Data_Structure>)results.values;
	                UserMainListAdapter.this.notifyDataSetChanged();
	                notifyDataSetInvalidated();
				
			}
			
			@Override
			protected FilterResults performFiltering(CharSequence constraint) {
				// TODO Auto-generated method stub
				 ArrayList<Requestor_Json_Data_Structure> filteredResults = getFilteredResults(constraint);

	                FilterResults results = new FilterResults();
	                results.values = filteredResults;
				return results;
			}
		};
    }
    
    private ArrayList<Requestor_Json_Data_Structure> getFilteredResults(CharSequence cs)
    {
    	ArrayList<Requestor_Json_Data_Structure> temps = new ArrayList<Requestor_Json_Data_Structure>(requests);
    	
    	for(int i = temps.size() - 1; i >= 0; i--)
    	{
    		if(!((String) temps.get(i).getRequestStartDate().subSequence(6, 16)).toLowerCase(Locale.getDefault()).contains(cs))
    			temps.remove(i);
    	}

    	return temps;
    }
	
	/*private void setRequests()
	{
		if (from_fragment.equalsIgnoreCase("User_Current_Requests")) {
			requests.addAll(CommonData.getOpenRequestsData());
		
		} else if (from_fragment.equalsIgnoreCase("UserPendingServices")) {
			requests.addAll(CommonData.getAcceptedRequestsData());
			
		} else { // servicedFragment
			requests.addAll(CommonData.getServicedRequestsData());

		}
		
	}*/



public static long pushAppointmentsToCalender(Activity curActivity, String title, String addInfo, String place, int status, long startDate, boolean needReminder, boolean needMailService) {
    /***************** Event: note(without alert) *******************/

    String eventUriString = "content://com.android.calendar/events";
    ContentValues eventValues = new ContentValues();

    eventValues.put("calendar_id", 1); // id, We need to choose from
                                        // our mobile for primary
                                        // its 1
    eventValues.put("title", title);
    eventValues.put("description", addInfo);
    eventValues.put("eventLocation", place);

    long endDate = startDate + 1000 * 60 * 60; // For next 1hr

    eventValues.put("dtstart", startDate);
    eventValues.put("dtend", endDate);
    eventValues.put("eventTimezone", "UTC/GMT +2:00");

    // values.put("allDay", 1); //If it is bithday alarm or such
    // kind (which should remind me for whole day) 0 for false, 1
    // for true
    eventValues.put("eventStatus", status); // This information is
    // sufficient for most
    // entries tentative (0),
    // confirmed (1) or canceled
    // (2):

   /*Comment below visibility and transparency  column to avoid java.lang.IllegalArgumentException column visibility is invalid error */

    /*eventValues.put("visibility", 3); // visibility to default (0),
                                        // confidential (1), private
                                        // (2), or public (3):
    eventValues.put("transparency", 0); // You can control whether
                                        // an event consumes time
                                        // opaque (0) or transparent
                                        // (1).
      */
    eventValues.put("hasAlarm", 1); // 0 for false, 1 for true

    Uri eventUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(eventUriString), eventValues);
    long eventID = Long.parseLong(eventUri.getLastPathSegment());

    if (needReminder) {
        /***************** Event: Reminder(with alert) Adding reminder to event *******************/

        String reminderUriString = "content://com.android.calendar/reminders";

        ContentValues reminderValues = new ContentValues();

        reminderValues.put("event_id", eventID);
        reminderValues.put("minutes", 5); // Default value of the
                                            // system. Minutes is a
                                            // integer
        reminderValues.put("method", 1); // Alert Methods: Default(0),
                                            // Alert(1), Email(2),
                                            // SMS(3)

        Uri reminderUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(reminderUriString), reminderValues);
    }

    /***************** Event: Meeting(without alert) Adding Attendies to the meeting *******************/

    if (needMailService) {
        String attendeuesesUriString = "content://com.android.calendar/attendees";

        /********
         * To add multiple attendees need to insert ContentValues multiple
         * times
         ***********/
        ContentValues attendeesValues = new ContentValues();

        attendeesValues.put("event_id", eventID);
        attendeesValues.put("attendeeName", "xxxxx"); // Attendees name
        attendeesValues.put("attendeeEmail", "hkuna@cs.odu.edu");// Attendee
                                                                            // E
                                                                            // mail
                                                                            // id
        attendeesValues.put("attendeeRelationship", 0); // Relationship_Attendee(1),
                                                        // Relationship_None(0),
                                                        // Organizer(2),
                                                        // Performer(3),
                                                        // Speaker(4)
        attendeesValues.put("attendeeType", 0); // None(0), Optional(1),
                                                // Required(2), Resource(3)
        attendeesValues.put("attendeeStatus", 0); // NOne(0), Accepted(1),
                                                    // Decline(2),
                                                    // Invited(3),
                                                    // Tentative(4)

        Uri attendeuesesUri = curActivity.getApplicationContext().getContentResolver().insert(Uri.parse(attendeuesesUriString), attendeesValues);
    }

    return eventID;

}
}
