package remove.classes.from.the.pkg;

import push.classes.to.other.pkg.VendorPreferences;

import com.babloosashi.neighbour.R;

import harish.listadapter.uservendor.SettingsListAdapter;
import harish.requestor.role.UserPreferences;
import server.ServerConnector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsActivity extends Activity {
	ListView settings_list;
	LinearLayout settingsll;
	ServerConnector mServerConnector;
	SettingsListAdapter mSettingsListAdapter;
	String Tag ="Settings";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		getActionBar().setDisplayShowHomeEnabled(false);
       
		settings_list= (ListView) findViewById(android.R.id.list);
		mSettingsListAdapter = new SettingsListAdapter(this);
		settings_list.setAdapter(mSettingsListAdapter);
		
		
		settings_list.setDividerHeight(5);
		settings_list.setPadding(20, 20, 20, 20);
		
		settings_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				onItemClickActionListener(position, id);
				
			}

			
		} );
		
	}

	private void onItemClickActionListener(int position, long id) {
	
	// check for list item and perform action
		switch(position)
		{
		case 0:
			Intent i = new Intent(this,Profile_Activity.class);
			startActivity(i);
			break;
			
		case 1:
			 Intent j = new Intent (this,UserPreferences.class);
			 startActivity(j);
			break;
			
		case 2: 
		     Intent k = new Intent (this, VendorPreferences.class);
		     startActivity(k);
		     
		case 3: 
			
			Intent s = new Intent(this,CustomerSupport.class);
			startActivity(s);
			
		}
	}
	
}
