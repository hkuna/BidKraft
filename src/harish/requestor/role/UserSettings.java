package harish.requestor.role;

import harish.listadapter.uservendor.SettingsListAdapter;
import push.classes.to.other.pkg.VendorPreferences;
import remove.classes.from.the.pkg.CustomerSupport;
import server.ServerConnector;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.babloosashi.neighbour.R;
public class UserSettings extends ListFragment {
	ListView settings_list;
	LinearLayout settingsll;
	ServerConnector mServerConnector;
	SettingsListAdapter mSettingsListAdapter;
	

String Tag ="Settings";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.settings,
				container, false);

		return rootView;
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		settings_list = getListView();
		
		mSettingsListAdapter = new SettingsListAdapter(getActivity());
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
			Intent i = new Intent(getActivity(),ProfileActivity.class);
			startActivity(i);
			break;
			
		case 1:
			 Intent j = new Intent (getActivity(),UserPreferences.class);
			 startActivity(j);
			break;
			
		case 2: 
		     Intent k = new Intent (getActivity(), VendorPreferences.class);
		     startActivity(k);
		     
		case 3: 
			
			Intent s = new Intent(getActivity(),CustomerSupport.class);
			
		}
	}
}
