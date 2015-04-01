package remove.classes.from.the.pkg;

import com.babloosashi.neighbour.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Category_Pager2 extends Fragment{
	
	
	  private String title;
	    private int page;

	    // newInstance constructor for creating fragment with arguments
	    public static Category_Pager2 newInstance(int page, String title) {
	        Category_Pager2 fragmentFirst = new Category_Pager2();
	        Bundle args = new Bundle();
	        args.putInt("someInt", page);
	        args.putString("someTitle", title);
	        fragmentFirst.setArguments(args);
	        return fragmentFirst;
	    }

	    // Store instance variables based on arguments passed
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        page = getArguments().getInt("someInt", 0);
	        title = getArguments().getString("someTitle");
	    }

	    // Inflate the view for the fragment based on layout XML
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        View view = inflater.inflate(R.layout.category_fragmentpager2, container, false);
	       
	        return view;
	    }

}
