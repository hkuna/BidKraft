package harish.requestor.role;

import android.app.Activity;
import android.os.Bundle;

import com.babloosashi.neighbour.R;

import java.util.ArrayList;
import java.util.Arrays;

import remove.classes.from.the.pkg.CreateJobActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

//This application uses some deprecated methods. 
//See UIViewPager for a more modern version of this application

public class CreatePost extends Activity {

	protected static final String EXTRA_RES_ID = "POS";
	
	private ArrayList<Integer> serviceCategories = new ArrayList<Integer>(
			Arrays.asList( R.drawable.rigid_baby,R.drawable.pet,
					 R.drawable.tutor,R.drawable.textbook));

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_post);

		GridView gridview = (GridView) findViewById(R.id.gridview);

		// Create a new ImageAdapter and set it as the Adapter for this GridView
		gridview.setAdapter(new ImageAdapter(this, serviceCategories));

		// Set an setOnItemClickListener on the GridView
		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				
				//Create an Intent to start the ImageViewActivity
				Intent intent = new Intent(CreatePost.this,
						CreateJobActivity.class);
				
				intent.putExtra("IconClickedPosition", position);
				
				// Add the ID of the thumbnail to display as an Intent Extra
				intent.putExtra(EXTRA_RES_ID, (int) id);
				
				// Start the ImageViewActivity
				startActivity(intent);
			}
		});
	}
}