package com.ruhoon.jiayu.merchant.activity;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.merchant.staticdata.Password;
import com.ruhoon.jiayu.merchant.staticdata.UserName;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class RetrievePasswordActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btGetVarCode, btComplete;
	EditText etPhone, etVarCode, etNewPw, etReNewPw;
	ProgressDialog pd;

	MyApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retrieve_password);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		application = (MyApplication) getApplication();
		initView();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void initView() {

		btBack = (Button) findViewById(R.id.bt_forgotPassword_back);
		btBack.setOnClickListener(this);
		btGetVarCode = (Button) findViewById(R.id.bt_getVerification);
		btGetVarCode.setOnClickListener(this);
		btComplete = (Button) findViewById(R.id.bt_forgotPassword_complete);
		btComplete.setOnClickListener(this);
		etPhone = (EditText) findViewById(R.id.et_phoneNumber);
		etVarCode = (EditText) findViewById(R.id.et_VerificationCode);
		etNewPw = (EditText) findViewById(R.id.et_newPassword);
		etReNewPw = (EditText) findViewById(R.id.et_renewword);
	}

	private void getCode() {

		if (!"".equals(etPhone.getText().toString())) {
			if (UserName.isMobileNO(etPhone.getText().toString())) {

				DataRequestVolley dataRequestVolley = new DataRequestVolley(
						HttpMethod.POST, MerchantHttpUrl.MOD_PASSWORD,
						getCodeListener, getCodeErrorListener) {
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						Map<String, String> map = new HashMap<String, String>();
						map.put("username", etPhone.getText().toString());
						return map;
					}
				};
				pd = new ProgressDialog(RetrievePasswordActivity.this);
				pd = ProgressDialog.show(RetrievePasswordActivity.this, "", "");
				RequestQueueSingleton
						.getInstance(RetrievePasswordActivity.this)
						.addToRequestQueue(dataRequestVolley);
			} else {
				Toast.makeText(RetrievePasswordActivity.this,
						MerchantMessage.CODE_3, Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(RetrievePasswordActivity.this,
					MerchantMessage.CODE_3, Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> getCodeListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Log.i("response", response);
			application.setSession_id(response);

			Toast.makeText(RetrievePasswordActivity.this, "验证码已发出，请注意查收",
					Toast.LENGTH_SHORT).show();

			Log.i("返回", response);

		}

	};

	Response.ErrorListener getCodeErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(RetrievePasswordActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	private void complete() {
		if (!"".equals(etPhone.getText().toString())) {
			if (UserName.isMobileNO(etPhone.getText().toString())) {
				if (!"".equals(etVarCode.getText().toString())) {
					if (!"".equals(etNewPw.getText().toString())) {

						if (Password.isRightPassword(etNewPw.getText()
								.toString())) {

							if (etNewPw.getText().toString()
									.equals(etReNewPw.getText().toString())) {
								DataRequestVolley dataRequestVolley = new DataRequestVolley(
										HttpMethod.POST,
										MerchantHttpUrl.MOD_PASSWORD,
										registerListener, registerErrorListener) {
									@Override
									protected Map<String, String> getParams()
											throws AuthFailureError {
										// TODO Auto-generated method stub
										Map<String, String> map = new HashMap<String, String>();
										map.put("username", etPhone.getText()
												.toString());
										map.put("password", etNewPw.getText()
												.toString());
										map.put("repassword", etReNewPw
												.getText().toString());
										map.put("code_verify", etVarCode
												.getText().toString());
										map.put("session_id",
												application.getSession_id());

										return map;
									}
								};
								pd = new ProgressDialog(
										RetrievePasswordActivity.this);
								pd = ProgressDialog.show(
										RetrievePasswordActivity.this, "", "");
								RequestQueueSingleton.getInstance(
										RetrievePasswordActivity.this)
										.addToRequestQueue(dataRequestVolley);
							} else {
								Toast.makeText(RetrievePasswordActivity.this,
										MerchantMessage.CODE_9,
										Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(RetrievePasswordActivity.this,
									MerchantMessage.CODE_5, Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						Toast.makeText(RetrievePasswordActivity.this,
								MerchantMessage.CODE_5, Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(RetrievePasswordActivity.this,
							MerchantMessage.CODE_513, Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(RetrievePasswordActivity.this,
						MerchantMessage.CODE_3, Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(RetrievePasswordActivity.this,
					MerchantMessage.CODE_3, Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> registerListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

			Toast.makeText(RetrievePasswordActivity.this,
					MerchantMessage.CODE_10, Toast.LENGTH_SHORT).show();
			finish();
			Log.i("返回", response);

		}

	};

	Response.ErrorListener registerErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(RetrievePasswordActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.retrieve_password, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_retrieve_password, container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_forgotPassword_back:
			finish();
			break;
		case R.id.bt_getVerification:
			getCode();
			break;
		case R.id.bt_forgotPassword_complete:
			complete();
			break;
		default:
			break;
		}
	}

}
