package harish.requestor_vendor.onboarding;

import harish.requestor.commondata.CommonData;

import org.apache.http.Header;

import push.classes.to.other.pkg.Response;
import server.ServerConnector;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.babloosashi.neighbour.R;
import com.loopj.android.http.AsyncHttpResponseHandler;

public class Register_User_BasicInfo extends Fragment {
	//OnUserbasicDetailsFilledListener mCallback;
	private Button btn_OnBasicInfoFilled;
	EditText et_fname, et_lname, et_email, et_phonenum, et_paswd,et_userName;
	
	boolean flag_et_fname = false, flag_et_lname = false,
			flag_et_email = false, flag_et_phonenum = false,
			flag_et_paswd = false , flag_et_username =false;
String tag ="Register_User_BasicInfo";
String fname, lname, email, password, phone, username;
ServerConnector mServerConnector;
	/*public interface OnUserbasicDetailsFilledListener {
		public void onNextOfRegisterBasicInfoSelected(String fname,
				String lname, String email, String paswd, String phonenum , String userName);
	}*/

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);

		// This makes sure that the container activity has implemented
		// the callback interface. If not, it throws an exception
		/*try {
			mCallback = (OnUserbasicDetailsFilledListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnUserbasicDetailsFilledListener ");
		}*/
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View rootView = inflater.inflate(R.layout.signup, container, false);
		mServerConnector = new ServerConnector(getActivity());
		et_fname = (EditText) rootView.findViewById(R.id.et_fname);
		et_lname = (EditText) rootView.findViewById(R.id.et_lname);
		et_email = (EditText) rootView.findViewById(R.id.et_email);
		et_phonenum = (EditText) rootView.findViewById(R.id.et_phonenum);
		et_paswd = (EditText) rootView.findViewById(R.id.et_paswd);
		et_userName = (EditText) rootView.findViewById(R.id.et_userName);

		btn_OnBasicInfoFilled = (Button) rootView
				.findViewById(R.id.btn_baiscinfo);

		btn_OnBasicInfoFilled.setEnabled(false);

		return rootView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		et_fname.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() != 0) {
					flag_et_fname = true;

					checkVisibilityNextButton();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		et_lname.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (s.length() != 0) {
					flag_et_lname = true;

					checkVisibilityNextButton();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		et_email.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() != 0) {
					flag_et_email = true;

					checkVisibilityNextButton();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		et_phonenum.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() != 0) {
					flag_et_phonenum = true;

					checkVisibilityNextButton();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
		et_paswd.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (s.length() != 0) {
					flag_et_paswd = true;

					checkVisibilityNextButton();
				}

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		
		et_userName.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (s.length() != 0) {
					flag_et_username = true;

					checkVisibilityNextButton();
				}

				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		btn_OnBasicInfoFilled.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				 Log.d(tag ,"values from basic info frag"+et_fname.getText()
							.toString()+ et_lname.getText().toString()+ et_email
							.getText().toString()+ et_paswd.getText().toString()+
							et_phonenum.getText().toString());
			/*	mCallback.onNextOfRegisterBasicInfoSelected(et_fname.getText()
						.toString(), et_lname.getText().toString(), et_email
						.getText().toString(), et_paswd.getText().toString(),
						et_phonenum.getText().toString(),et_userName.getText().toString());*/
				 fname = et_fname.getText()
							.toString();
					lname = et_lname.getText().toString();
					email =et_email
							.getText().toString();
					password = et_paswd.getText().toString();
					phone = et_phonenum.getText().toString();
					username = et_userName.getText().toString();
				 
				 ProgressDialog progress = CommonData
							.showProgressBar(getActivity());
					progress.show();

					mServerConnector.signUp(fname, lname, email, password, phone,
							 username, 
							new AsyncHttpResponseHandler() {

								@Override
								public void onSuccess(int statusCode,
										Header[] headers, byte[] responseBody) {
									// TODO Auto-generated method stub
									Response response = CommonData
											.convertGSonObjectToResponseClass(responseBody);
									CommonData.setSignUpflag(true);
									
									String status = CommonData
											.onLoginOrRegistrationListener(
													response, getActivity());

									Toast.makeText(getActivity(), status,
											Toast.LENGTH_LONG).show();

									Toast.makeText(getActivity(),
											response.getStatus(), Toast.LENGTH_LONG)
											.show();

								}

								@Override
								public void onFailure(int statusCode,
										Header[] headers, byte[] responseBody,
										Throwable error) {
									// TODO Auto-generated method stub

								}
							});

					CommonData.hideProgressbar(getActivity(), progress);

			}
		});

	}

	private void checkVisibilityNextButton() {
		if (flag_et_fname && flag_et_lname && flag_et_email && flag_et_paswd
				&& flag_et_phonenum && flag_et_username)
			btn_OnBasicInfoFilled.setBackgroundColor(Color.BLACK);
			btn_OnBasicInfoFilled.setEnabled(true);

	}

}
