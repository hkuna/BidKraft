package remove.classes.from.the.pkg;

import com.babloosashi.neighbour.R;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomerSupport extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_support);
		
		ActionBar bar = getActionBar();
		bar.setDisplayShowHomeEnabled(false);
		bar.setDisplayShowTitleEnabled(false);
		LayoutInflater mInflater = LayoutInflater.from(this);
		View mCustomView = mInflater.inflate(R.layout.custom_actionbar_for_customer_support, null);
		TextView tvPageTitle = (TextView) mCustomView.findViewById(R.id.tv_cs_pagetitle);
        TextView tvActionSend = (TextView) mCustomView
				.findViewById(R.id.tv_cs_send);
		
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
		
	}
}
