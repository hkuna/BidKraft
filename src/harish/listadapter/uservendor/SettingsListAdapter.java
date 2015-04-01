package harish.listadapter.uservendor;

import harish.requestor.commondata.CommonData;

import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.spec.PSource;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.babloosashi.neighbour.R;

public class SettingsListAdapter  extends BaseAdapter {

	private Context mContext;
	LayoutInflater inflater;
	private String TAG ="SettingsListAdapter";
	ArrayList<String> msettingsArrayList;
		public SettingsListAdapter(Activity activity) {
			mContext = activity;
			inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					Log.d(TAG, "inside 1st constructor SettingsAdapter ");
					msettingsArrayList = new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.settings_list)));
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return msettingsArrayList.size();
		}
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return msettingsArrayList.get(position);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
		@SuppressLint("NewApi")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rootview = convertView;
			if (rootview == null) {
				rootview = inflater.inflate(R.layout.settingslistrow, null);
			}
			LinearLayout categoryLayoutItem = (LinearLayout) rootview.findViewById(R.id.settingsListItem);
			TextView categoryName = (TextView) rootview.findViewById(R.id.tv_settingsitem);
		//	ImageView categoryImageView =(ImageView) rootview.findViewById(R.id.categoryImageId);
			categoryName.setText(msettingsArrayList.get(position));
			
			
		 // CommonData.setCategoryImage(""+position, rootview, mContext, categoryImageView);

			return rootview;
		}
}
