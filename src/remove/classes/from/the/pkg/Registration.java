package remove.classes.from.the.pkg;

import com.babloosashi.neighbour.R;

import harish.requestor_vendor.onboarding.Register_User_BasicInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class Registration extends FragmentActivity /*implements OnUserbasicDetailsFilledListener*/{
private String tag ="Registaration";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);

		if (findViewById(R.id.fragment_container) != null) {

			// However, if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			if (savedInstanceState != null) {
				return;
			}

		}

		// Create a new Fragment to be placed in the activity layout

		Register_User_BasicInfo mRegister_User_BasicInfo = new Register_User_BasicInfo();

		// In case this activity was started with special instructions from an
		// Intent, pass the Intent's extras to the fragment as arguments
		mRegister_User_BasicInfo.setArguments(getIntent().getExtras());

		// Add the fragment to the 'fragment_container' FrameLayout
		getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, mRegister_User_BasicInfo)
				.commit();

	}

	/*@Override
	public void onNextOfRegisterBasicInfoSelected(String fname, String lname,
			String email, String paswd, String phonenum, String userName) {
		
		RegisterUserRole mRegisterUserRole = (RegisterUserRole) getSupportFragmentManager().findFragmentById(R.id.signUpRole_fragment);
		
		if(mRegisterUserRole!=null)
		{
			// If RegisterUserRole frag is available, we're in two-pane layout...

            // Call a method in the RegisterUserRole to update its content
		// write a method to handle that
		}
		else
		{
			RegisterUserRole newFragment = new RegisterUserRole();
			  Bundle args = new Bundle();
	            args.putString("firstname", fname);
	            args.putString("lastname", lname);
	            args.putString("email", email);
	            args.putString("password", paswd);
	            args.putString("phone", phonenum);
	            args.putString("userName", userName);
	            newFragment.setArguments(args);
	            
	            Log.d(tag ,"values from basic info frag"+fname+lname+email+paswd+phonenum);
	            
	            // Replace whatever is in the fragment_container view with this fragment,
	            // and add the transaction to the back stack so the user can navigate back
	            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	            transaction.replace(R.id.fragment_container, newFragment);
	            transaction.addToBackStack(null);

	         // Commit the transaction
	            transaction.commit();
		}
		
	}*/
}
