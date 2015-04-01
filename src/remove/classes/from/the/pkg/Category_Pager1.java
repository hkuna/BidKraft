package remove.classes.from.the.pkg;

import com.babloosashi.neighbour.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Category_Pager1 extends Fragment {

	private String title;
	private int page;
	ImageView babySit;
	ImageView houseClean;
	ImageView homeRepair;
	ImageView petCare;
	int requestCode = 1;
	String tag = "Category Pager 1";

	public static Category_Pager1 newInstance(int page, String title) {
		Category_Pager1 fragmentFirst = new Category_Pager1();
		Bundle args = new Bundle();
		args.putInt("someInt", page);
		args.putString("someTitle", title);
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = getArguments().getInt("someInt", 0);
		title = getArguments().getString("someTitle");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.category_fragmentpager1,
				container, false);
		
		babySit = (ImageView) view.findViewById(R.id.imageView1);
		houseClean = (ImageView) view.findViewById(R.id.imageView2);
		homeRepair = (ImageView) view.findViewById(R.id.imageView3);
		petCare = (ImageView) view.findViewById(R.id.imageView4);
		
		
		
		babySit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*Intent i = new Intent(getActivity(), CreateNewPost.class);
				getActivity().startActivityForResult(i, requestCode);*/
				// startActivity(i);
				Log.d(tag, "received back " + requestCode);

			}
		});
		
		houseClean.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), HouseClean_NewPost.class);
				getActivity().startActivityForResult(i, requestCode);
				// startActivity(i);
				Log.d(tag, "received back " + requestCode);

			}
		});
		
		
		return view;

	}

}
