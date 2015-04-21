/*package remove.classes.from.the.pkg;

import harish.custom.view.FlowLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.babloosashi.neighbour.R;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;


@SuppressLint("NewApi")
public class CreateJobActivity extends Activity{

    private TextView tvBiddingEnds;
    private TextView tvRequestDate;
    private EditText etTitle;
    private EditText etDescription;
    private FlowLayout tagsLayout;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        
        
        //
        
    	
		ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar_for_customer_support, null);
		TextView tvPageTitle = (TextView) mCustomView.findViewById(R.id.tv_cs_pagetitle);
        TextView tvActionSend = (TextView) mCustomView
				.findViewById(R.id.tv_cs_send);
        
        tvPageTitle.setText("Create Job");
        
        tvActionSend.setText("Submit"); 
		
		tvActionSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//handle the send action
			}
		});
		
		
		bar.setCustomView(mCustomView);
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowCustomEnabled(true);
        
        //
        

        dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
        Calendar newCalendar = Calendar.getInstance();

        tvBiddingEnds = (TextView)findViewById(R.id.tvBiddingEnds);
        tvRequestDate = (TextView) findViewById(R.id.tvRequestDate);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        tagsLayout = (FlowLayout) findViewById(R.id.tagsLayout);

        Button addBtn = new Button(this);
        addBtn.setText("+");
        addBtn.setHeight(10);
        addBtn.setBackground(getResources().getDrawable(R.drawable.round_corners));
        //addBtn.setBackgroundColor(Color.parseColor("#F39C12"));
        addBtn.setPadding(4,1,4,1);

        final Context finalContext = this;

        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(10, 5, 10, 5);


        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(finalContext);

                alert.setMessage("Enter tag:");

                // Set an EditText view to get user input
                final EditText input = new EditText(finalContext);
                alert.setView(input);

                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @SuppressWarnings("deprecation")
					public void onClick(DialogInterface dialog, int whichButton) {
                        final String value = input.getText().toString();

                        Button newTag = new Button(finalContext, null, android.R.attr.buttonStyleSmall);
                        newTag.setText(value);
                        newTag.setTag(value);
                        newTag.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_corners));
                        //newTag.setBackgroundColor(Color.parseColor("#F39C12"));
                        newTag.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                tagsLayout.removeView(tagsLayout.findViewWithTag(value));
                            }
                        }); //closing the setOnClickListener method

                        tagsLayout.addView(newTag, 0, layoutParams);
                        tagsLayout.invalidate();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.

                    }
                });

                alert.show();
            }
        }); //closing the setOnClickListener method

        tagsLayout.addView(addBtn, 0, layoutParams);

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                tvRequestDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        tvBiddingEnds.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(CreateJobActivity.this, tvBiddingEnds);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.time_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        tvBiddingEnds.setText(item.getTitle());
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

        tvRequestDate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_job, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
*/

package remove.classes.from.the.pkg;

import harish.custom.view.FlowLayout;
import harish.requestor.commondata.CommonData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.TimePicker;

import com.babloosashi.neighbour.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;


public class CreateJobActivity extends Activity implements OnClickListener {

    private TextView tvBiddingEnds;
    private TextView tvRequestDate;
    private TextView tvEndDate;
    private EditText etTitle;
    private EditText etDescription;
    private FlowLayout tagsLayout;
    private ServerConnector mServerConnector;
    private int mHour, mMinute;
    private ArrayList<String> tags;
    int tagCounter;
    private String requestedDate, endDate , biddingEndTime ="abc"; 
    String TAG ="CreateJobActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_job);
        setCustomActionBar();
        mServerConnector = new ServerConnector(this);
        tvBiddingEnds = (TextView)findViewById(R.id.tvBiddingEnds);
        tvRequestDate = (TextView) findViewById(R.id.tvRequestDate);
        tvEndDate = (TextView) findViewById(R.id.tvEndDate);
        etTitle = (EditText) findViewById(R.id.etTitle);
        etDescription = (EditText) findViewById(R.id.etDescription);
        tagsLayout = (FlowLayout) findViewById(R.id.tagsLayout);
        tvRequestDate.setOnClickListener(this);
        tvEndDate.setOnClickListener(this);
        tags = new ArrayList<String>();
        

        //Instantiate tags section
        setUpTags();

        tvBiddingEnds.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(CreateJobActivity.this, tvBiddingEnds);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.time_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        tvBiddingEnds.setText(item.getTitle());
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_job, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setRequestDate(View v)
    {
        setAndChangeDateTimeTextView(tvRequestDate);
    }

    public void setEndDate(View v)
    {
        setAndChangeDateTimeTextView(tvEndDate);
    }

    private void setAndChangeDateTimeTextView(final TextView updatedTextView)
    {
        final View dialogView = View.inflate(this, R.layout.date_time_picker, null);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();

        dialogView.findViewById(R.id.date_time_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePicker datePicker = (DatePicker) dialogView.findViewById(R.id.date_picker);
                TimePicker timePicker = (TimePicker) dialogView.findViewById(R.id.time_picker);

                Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                        datePicker.getMonth(),
                        datePicker.getDayOfMonth(),
                        timePicker.getCurrentHour(),
                        timePicker.getCurrentMinute());

                updatedTextView.setText(calendar.getTime().toString());
                
                switch(updatedTextView.getId())
                {
                	case R.id.tvBiddingEnds:
                		biddingEndTime = "abc";
                		break;
                		
                	case R.id.tvRequestDate:
                		requestedDate = calendar.getTime().toString();
                		break;
                	case R.id.tvEndDate :
                		endDate = calendar.getTime().toString();
                		break;
                }
                
                
                
                alertDialog.dismiss();
            }});
        alertDialog.setView(dialogView);
        alertDialog.show();
    }

    private void setUpTags()
    {
        Button addBtn = new Button(this);
        addBtn.setText("+");
        addBtn.setHeight(10);
        addBtn.setBackground(getResources().getDrawable(R.drawable.round_corners));
        //addBtn.setBackgroundColor(Color.parseColor("#F39C12"));
        addBtn.setPadding(4,1,4,1);

        final Context finalContext = this;

        final LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(10, 5, 10, 5);


        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(finalContext);

                alert.setMessage("Enter tag:");

                // Set an EditText view to get user input
                final EditText input = new EditText(finalContext);
                alert.setView(input);

                alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        final String value = input.getText().toString();

                        Button newTag = new Button(finalContext, null, android.R.attr.buttonStyleSmall);
                        newTag.setText(value);
                        newTag.setTag(value);
                        newTag.setBackground(getResources().getDrawable(R.drawable.round_corners));
                        
                        tags.add(value);
                        
                        
                        //newTag.setBackgroundColor(Color.parseColor("#F39C12"));
                        newTag.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                tagsLayout.removeView(tagsLayout.findViewWithTag(value));
                                
                                tags.remove(value);
                            }
                        }); //closing the setOnClickListener method

                        tagsLayout.addView(newTag, 0, layoutParams);
                        tagsLayout.invalidate();
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.

                    }
                });

                alert.show();
            }
        }); //closing the setOnClickListener method

        tagsLayout.addView(addBtn, 0, layoutParams);
    }
    
    private void setCustomActionBar()
	{
    	ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar_for_customer_support, null);
		TextView tvPageTitle = (TextView) mCustomView.findViewById(R.id.tv_cs_pagetitle);
		tvPageTitle.setText("Create Job");
        TextView tvActionSend = (TextView) mCustomView
				.findViewById(R.id.tv_cs_send);
		
		tvActionSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//handle the send action
				
				if(validateForm())
				   notifyCreatePostToserver();
			
				
				
			}

			
		});
		
		
		bar.setCustomView(mCustomView);
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowCustomEnabled(true);

	}

    private void notifyCreatePostToserver() {
    	
    	
    	mServerConnector.createService(etTitle.getText().toString(), 5, etDescription.getText().toString(), requestedDate, endDate,  endDate,tags.toArray(), new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
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

	private boolean validateForm() {
		
		boolean form_valid = false;
		
		if (!(etTitle.getText().toString()==null || etTitle.getText().toString().equals("")))
			form_valid= true;
		if(!(requestedDate.equals("") || requestedDate==null))
			form_valid = true;
		
		if(!(endDate.equals("") || endDate==null))
			form_valid = true;
		
		if(!(biddingEndTime.equals("") || biddingEndTime==null))
			form_valid = true;
		
		if(tags.size()!= 0 && tags!=null )
			form_valid=true;
		
		if(!(etDescription.getText().toString()==null || etDescription.getText().toString().equals("")))
			form_valid = true;
		
		
		return form_valid;
	}
    
    
	@Override
	public void onClick(View v) {
		switch(v.getId())
		{
		
		case R.id.tvRequestDate:
			setAndChangeDateTimeTextView(tvRequestDate);
			break;
			
		case R.id.tvEndDate:
			setAndChangeDateTimeTextView(tvEndDate);
			break;

		}
		 
		
	}
}
