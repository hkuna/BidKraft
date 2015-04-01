package harish.requestor.role;

import harish.listadapter.uservendor.UserMainListAdapter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.babloosashi.neighbour.R;

public class UserRequestsHistory extends ListFragment{

	ListView user_history_list;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	View rootView = inflater.inflate(R.layout.user_requests_list,
			container, false);

	// Demonstration of navigating to external activities.
	
	return rootView;
}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		user_history_list = getListView();
		user_history_list.setAdapter(new UserMainListAdapter(getActivity(), "UserRequestsHistory","requestor",-1));
		user_history_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

			}
		});

	}
	
	
}
