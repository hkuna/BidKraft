package remove.classes.from.the.pkg;

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
