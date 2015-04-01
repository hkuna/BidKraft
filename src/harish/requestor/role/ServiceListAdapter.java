package harish.requestor.role;

import harish.requestor.commondata.CommonData;
import java.util.ArrayList;
import java.util.Arrays;
import android.annotation.SuppressLint;
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


class  ServicesListAdapter  extends BaseAdapter
{
private Context mContext;
LayoutInflater inflater;
private String TAG ="ServicesListAdapter";
ArrayList<String> mservicesArrayList;
	public ServicesListAdapter(FragmentActivity activity) {
		mContext = activity;
		inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				Log.d(TAG, "inside 1st constructor ");
			mservicesArrayList = new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.category_list)));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mservicesArrayList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mservicesArrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View rootview = convertView;

		if (rootview == null) {
			rootview = inflater.inflate(R.layout.servicelistrow, null);
		}
		
		LinearLayout categoryLayoutItem = (LinearLayout) rootview.findViewById(R.id.categoryListItem);
		TextView categoryName = (TextView) rootview.findViewById(R.id.tv_categoryName);
	//	ImageView categoryImageView =(ImageView) rootview.findViewById(R.id.categoryImageId);
		categoryName.setText(mservicesArrayList.get(position));
		if (Build.VERSION.SDK_INT >= 16) {

			categoryLayoutItem.setBackground(mContext.getResources().getDrawable(CommonData.getCategoryBackground(position)));

		} else {

			categoryLayoutItem.setBackgroundDrawable(mContext.getResources().getDrawable(CommonData.getCategoryBackground(position)));
		}
		
	 // CommonData.setCategoryImage(""+position, rootview, mContext, categoryImageView);
		
		return rootview;
	}
}
