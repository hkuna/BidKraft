 package harish.requestor.role;

import remove.classes.from.the.pkg.CreateNewPost;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.babloosashi.neighbour.R;

public class CreateService extends ListFragment {
	
	ListView services_list;
	ArrayAdapter<String> mAdapter;
	String TAG ="CreateService";
	ServicesListAdapter mServicesListAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.user_requests_list,
				container, false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
	
		services_list = getListView();
	
		//getListView().setPadding(10, 10, 10, 10);
		mServicesListAdapter = new ServicesListAdapter(getActivity());
		
		services_list.setAdapter(mServicesListAdapter);
		services_list.setDivider(this.getResources().getDrawable(R.drawable.transperent_color));
		services_list.setDividerHeight(20);
		services_list.setPadding(20, 20, 20, 20);
	

		services_list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
			// Based on position id , go to the createpost class you need to 
				
				if(position==0)
				{

				Intent i = new Intent(getActivity(), CreateNewPost.class);
				i.putExtra("position", position); 
				startActivity(i);
				}
				else if(position == 1)
				{
					Intent i = new Intent(getActivity(), CreateSellTextBookService.class);
					i.putExtra("position", position); 
					startActivity(i);
				}
				else if (position == 2)
				{
					Intent i = new Intent(getActivity(), CreateTutoringService.class);
					i.putExtra("position", position); 
					startActivity(i);
				}
			
				
				// later convert the CreateNewPost class to fragmentActivity to load different fragments of new post based on category selected
			

			
			}
		});

		

	}
	

}



	 

