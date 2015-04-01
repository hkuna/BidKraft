package remove.classes.from.the.pkg;

import harish.custom.view.CircularImageView;
import harish.requestor.commondata.CommonData;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import server.ServerConnector;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;

public class Profile_Activity extends Activity {

	private static final int PICK_IMAGE = 1;
	private CircularImageView ivProfilePic;
	private TextView tvfirstname, tvlastname;
	private EditText etAddress, etemail, etCity, etState, etZip;
	private Button btnCancel;
	private Button btnSave;
	Bitmap dummyBitmap;
	int orientation;
	String TAG = "ProfileActivity";
	public static final String PREFS_NAME = "NeighborProfilePrefs";
	SharedPreferences settings;
	SharedPreferences.Editor editor;
	Boolean imagechanged = false;
	  byte[] img_upload=null;
	ServerConnector mServerConnector;
	ProgressDialog pd;
	String path;
	Bitmap cropped=null;
	String encimage="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		tvfirstname = (TextView) findViewById(R.id.tvfirstName);
		tvlastname = (TextView) findViewById(R.id.tvlastName);
		etCity = (EditText) findViewById(R.id.etCity);
		etState = (EditText) findViewById(R.id.etState);
		 
		btnSave = (Button) findViewById(R.id.btn_save);
        etZip =(EditText) findViewById(R.id.etZip);
		etAddress = (EditText) findViewById(R.id.etAddress);
		etemail = (EditText) findViewById(R.id.etEmail);
		// SET EDITTEXT CONTENTS
		etAddress.setText("YOUR ADDRESS HERE");

		mServerConnector = new ServerConnector(this);

		settings = getSharedPreferences(PREFS_NAME, 0);
		editor = settings.edit();

		
		tvfirstname.setText(settings.getString("firstName", ""));
		tvlastname.setText(settings.getString("lastName", ""));
		etAddress.setText(settings.getString("Address", ""));
		etState.setText(settings.getString("State", ""));
		etCity.setText(settings.getString("City", ""));
		etZip.setText(settings.getString("Zip", ""));
		etemail.setText(settings.getString("Email", ""));
		
		
		ivProfilePic = (CircularImageView) findViewById(R.id.ivProfile);
		ivProfilePic.setBorderColor(Color.WHITE);
		ivProfilePic.setBorderWidth(10);
		ivProfilePic.addShadow();
 path = PreferenceManager.getDefaultSharedPreferences(this)
				.getString("PROFILE", "");

		if (!(path.equals(""))) {
			/*
			 * Log.d(TAG ,"inside profile Activity"+PreferenceManager.
			 * getDefaultSharedPreferences(this).getString("PROFILE", "")
			 * +"path is "+path);
			 * ivProfilePic.setImageBitmap(BitmapFactory.decodeFile(path));
			 */
			
			ivProfilePic.setImageBitmap(CommonData.createBitmap(path));
		} else
			ivProfilePic.setImageResource(R.drawable.ic_bidder);

		btnSave.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				
			  
			   
			   showProgressBar();
			   
			   Log.d(TAG,"values from feilds before saving"+etAddress.getText().toString()+etState.getText().toString()+etCity.getText().toString()+etZip.getText().toString()+etemail.getText().toString());
			   editor.putString("Address", etAddress.getText().toString());
			   editor.putString("State", etState.getText().toString());
			   editor.putString("City", etCity.getText().toString());
			   editor.putString("Zip", etZip.getText().toString());
			   editor.putString("Email", etemail.getText().toString());
				editor.commit();
			   if(imagechanged)
			   {
				
				   ByteArrayOutputStream buffer = new ByteArrayOutputStream(cropped.getWidth() * cropped.getHeight());
				    cropped.compress(CompressFormat.PNG, 100, buffer);
				    img_upload = buffer.toByteArray();
				 encimage=   android.util.Base64.encodeToString(img_upload, Base64.DEFAULT);
				   
				  /* int bytes = cropped.getByteCount();
				   ByteBuffer buffer = ByteBuffer.allocate(bytes);
				   cropped.copyPixelsToBuffer(buffer);
				   img_upload = buffer.array();*/
				    
				    Log.d(TAG,"image changed "+ img_upload +"count is"+cropped.getByteCount());
				   // Bitmap bmp = BitmapFactory.decodeByteArray(img_upload, 0, img_upload.length);
				  //  ivProfilePic.setImageBitmap(bmp);
				  //  ivProfilePic.invalidate();
				    
				    
			   }
			   
                      
			}
		});

	}

	public void openGallery(View v) {
		Intent pickIntent = new Intent();
		pickIntent.setType("image/*");
		pickIntent.setAction(Intent.ACTION_GET_CONTENT);

		Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		String pickTitle = "Select or take a new Picture"; // Or get from
															// strings.xml
		Intent chooserIntent = Intent.createChooser(pickIntent, pickTitle);
		chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
				new Intent[] { takePhotoIntent });

		startActivityForResult(chooserIntent, 1);
	}
 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
			Uri _uri = data.getData();

			Log.d(TAG, "_uri value " + _uri);

			// User had pick an image.
			Cursor cursor = getContentResolver()
					.query(_uri,
							new String[] { android.provider.MediaStore.Images.Media.DATA },
							null, null, null);
			Log.d(TAG, "cursor value before move " + cursor);
			cursor.moveToFirst();

			Log.d(TAG, "cursor value after move " + cursor);

			// Link to the image
			final String imageFilePath = cursor.getString(0);
			CommonData.setProfilepic(imageFilePath);
			PreferenceManager.getDefaultSharedPreferences(this).edit()
					.putString("PROFILE", imageFilePath).commit();
			imagechanged = true;

			Log.d("CAT",
					imageFilePath + "common path " + CommonData.getProfilepic());
			cursor.close();
			dummyBitmap = BitmapFactory.decodeFile(imageFilePath);

			int rotate = 0;
			try {
				getContentResolver().notifyChange(_uri, null);
				File imageFile = new File(imageFilePath);
				ExifInterface exif = new ExifInterface(
						imageFile.getAbsolutePath());
				int orientation = exif.getAttributeInt(
						ExifInterface.TAG_ORIENTATION,
						ExifInterface.ORIENTATION_NORMAL);

				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_270:
					rotate -= 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					rotate -= 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_90:
					rotate -= 90;
					break;

				}
				Log.v(TAG, "Exif orientation: " + orientation);
			} catch (Exception e) {
				e.printStackTrace();
			}

			/****** Image rotation ****/

			Matrix matrix = new Matrix();
			matrix.postRotate(rotate);
			 cropped = Bitmap.createBitmap(dummyBitmap, 0, 0,
					dummyBitmap.getWidth(), dummyBitmap.getHeight(), matrix,
					true);
			// dummyBitmap=getCroppedBitmap(cropped, 200, 200);

			// ivProfilePic.setImageBitmap(getCroppedBitmap(cropped, 200, 200));
			ivProfilePic.setImageBitmap(cropped);

			

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/*
	 * public Bitmap getCroppedBitmap(Bitmap bitmap, int x, int y) { Bitmap
	 * output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
	 * Bitmap.Config.ARGB_8888); Canvas canvas = new Canvas(output);
	 * 
	 * final int color = 0xff424242; final Paint paint = new Paint(); final Rect
	 * rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	 * 
	 * paint.setAntiAlias(true); canvas.drawARGB(0, 0, 0, 0);
	 * paint.setColor(color); // canvas.drawRoundRect(rectF, roundPx, roundPx,
	 * paint); canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
	 * bitmap.getWidth() / 2, paint); paint.setXfermode(new
	 * PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); canvas.drawBitmap(bitmap,
	 * rect, rect, paint); Bitmap _bmp = Bitmap.createScaledBitmap(output, x, y,
	 * true); return _bmp; //return output; }
	 */
	public void showProgressBar() {
		// mFrameLayout.bringChildToFront(mProgressLayout);
		// mProgressLayout.setVisibility(View.VISIBLE);
		pd = new ProgressDialog(this); 
		pd.setMessage("Querying Server...");
		pd.setCancelable(false);
		pd.show();
	}
 
	public void hideProgressBar() {
		if (pd != null)
			pd.dismiss();
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Log.d(TAG,"inside On back press");
		Intent returnIntent = new Intent();
		returnIntent.putExtra("result", CommonData.getProfilepic());
		setResult(RESULT_OK, returnIntent);
		finish();
	}
}