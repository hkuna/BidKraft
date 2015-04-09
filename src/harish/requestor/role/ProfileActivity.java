package harish.requestor.role;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;



import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

import com.babloosashi.neighbour.R;
import com.babloosashi.neighbour.R.id;
import com.babloosashi.neighbour.R.layout;
import com.babloosashi.neighbour.R.menu;


public class ProfileActivity extends Activity {

    private static final int PICK_IMAGE = 1;
    private ImageView ivProfilePic;
    private String TAG = "ProfileActivity";
    private Boolean imageChanged = false;
    private Bitmap dummyBitmap;
    private Bitmap cropped = null;

    private static final int CAMERA_REQUEST = 1888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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

        startActivityForResult(chooserIntent, PICK_IMAGE);
    }

    public void takePicture(View v)
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //Image from camera
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ivProfilePic.setImageBitmap(photo);
        }

        //Image from gallery
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
            //CommonData.setProfilepic(imageFilePath);
            PreferenceManager.getDefaultSharedPreferences(this).edit()
                    .putString("PROFILE", imageFilePath).commit();
            imageChanged = true;

            cursor.close();
            dummyBitmap = BitmapFactory.decodeFile(imageFilePath);

            ivProfilePic.setImageBitmap(dummyBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
