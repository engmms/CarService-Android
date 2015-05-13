package com.ruhoon.jiayu.merchant.activity;

import java.util.HashMap;
import java.util.Map;

import java.util.Timer;
import java.util.TimerTask;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.aboutjiayu.TermsOfServiceActivity;
import com.ruhoon.jiayu.merchant.application.MyApplication;
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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btVerificationCode, btServiceTerm, btComplete;
	EditText etUserName, etVerificationCode, etPasswor, etRepassword;
	CheckBox checkBox;
	ProgressDialog pd;
	String session_id;
	MyApplication application;
	Handler handler;
	int length = 60;
	Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
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
		btBack = (Button) findViewById(R.id.bt_register_back);
		btBack.setOnClickListener(this);
		btVerificationCode = (Button) findViewById(R.id.bt_getVerification_register);
		btVerificationCode.setOnClickListener(this);
		btServiceTerm = (Button) findViewById(R.id.bt_getTerm);
		btServiceTerm.setOnClickListener(this);
		btComplete = (Button) findViewById(R.id.bt_complete_register);
		btComplete.setOnClickListener(this);
		etUserName = (EditText) findViewById(R.id.et_phoneNumber_register);
		etVerificationCode = (EditText) findViewById(R.id.et_VerificationCode_register);
		etPasswor = (EditText) findViewById(R.id.et_Password_register);
		etRepassword = (EditText) findViewById(R.id.et_repassword_register);
		checkBox = (CheckBox) findViewById(R.id.cb_register);
		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					btComplete
							.setBackgroundResource(R.drawable.bt_fogot_pw_comp_bg);
					btComplete.setTextColor(getResources().getColorStateList(
							R.color.registerCompleteNormal));
				} else {
					btComplete
							.setBackgroundResource(R.drawable.bt_complete_register_black);
					btComplete.setTextColor(getResources().getColorStateList(
							R.color.registerCompleteBlack));
				}
			}
		});
	}

	private void register() {
		if (!"".equals(etUserName.getText().toString())) {
			if (UserName.isMobileNO(etUserName.getText().toString())) {
				if (!"".equals(etVerificationCode.getText().toString())) {
					if (!"".equals(etPasswor.getText().toString())) {

						if (Password.isRightPassword(etPasswor.getText()
								.toString())) {

							if (etPasswor.getText().toString()
									.equals(etRepassword.getText().toString())) {
								DataRequestVolley dataRequestVolley = new DataRequestVolley(
										HttpMethod.POST,
										MerchantHttpUrl.REGISTER,
										registerListener, registerErrorListener) {
									@Override
									protected Map<String, String> getParams()
											throws AuthFailureError {
										// TODO Auto-generated method stub
										Map<String, String> map = new HashMap<String, String>();
										map.put("username", etUserName
												.getText().toString());
										map.put("password", etPasswor.getText()
												.toString());
										map.put("code_verify",
												etVerificationCode.getText()
														.toString());
										map.put("session_id",
												application.getSession_id());

										return map;
									}
								};
								pd = new ProgressDialog(RegisterActivity.this);
								pd = ProgressDialog.show(RegisterActivity.this,
										"", "");
								RequestQueueSingleton.getInstance(
										RegisterActivity.this)
										.addToRequestQueue(dataRequestVolley);
							} else {
								Toast.makeText(RegisterActivity.this,
										MerchantMessage.CODE_9,
										Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(RegisterActivity.this,
									MerchantMessage.CODE_5, Toast.LENGTH_SHORT)
									.show();
						}
					} else {
						Toast.makeText(RegisterActivity.this,
								MerchantMessage.CODE_5, Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(RegisterActivity.this,
							MerchantMessage.CODE_513, Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(RegisterActivity.this, MerchantMessage.CODE_3,
						Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(RegisterActivity.this, MerchantMessage.CODE_3,
					Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> registerListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

			Toast.makeText(RegisterActivity.this, MerchantMessage.CODE_11,
					Toast.LENGTH_SHORT).show();
			finish();
			Log.i("返回", response);

		}

	};

	Response.ErrorListener registerErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(RegisterActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	private void getCode() {

		if (!"".equals(etUserName.getText().toString())) {
			if (UserName.isMobileNO(etUserName.getText().toString())) {

				DataRequestVolley dataRequestVolley = new DataRequestVolley(
						HttpMethod.POST, MerchantHttpUrl.REGISTER,
						getCodeListener, getCodeErrorListener) {
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						Map<String, String> map = new HashMap<String, String>();
						map.put("username", etUserName.getText().toString());
						return map;
					}
				};
				pd = new ProgressDialog(RegisterActivity.this);
				pd = ProgressDialog.show(RegisterActivity.this, "", "");
				RequestQueueSingleton.getInstance(RegisterActivity.this)
						.addToRequestQueue(dataRequestVolley);
			} else {
				Toast.makeText(RegisterActivity.this, MerchantMessage.CODE_3,
						Toast.LENGTH_SHORT).show();
			}

		} else {
			Toast.makeText(RegisterActivity.this, MerchantMessage.CODE_3,
					Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> getCodeListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			session_id = response;
			application.setSession_id(session_id);

			handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					if (msg.what == 1) {
						btVerificationCode.setText(length + "秒后重新获取");
					} else {
						btVerificationCode.setText("获取验证码");
					}
				};
			};
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					if (length > 0) {
						Message msg1 = new Message();
						length = length - 1;
						msg1.what = 1;
						msg1.arg1 = length;
						handler.sendMessage(msg1);

					} else {
						Message msg2 = new Message();
						msg2.what = 2;
						handler.sendMessage(msg2);
						timer.cancel();

					}
				}
			};

			timer = new Timer();
			timer.schedule(task, 0, 1000);

			Log.i("返回", response);

		}

	};

	Response.ErrorListener getCodeErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(RegisterActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_register,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_register_back:
			finish();
			break;
		case R.id.bt_getVerification_register:
			getCode();
			break;
		case R.id.bt_complete_register:
			if (checkBox.isChecked()) {
				register();
			} else {

			}
			break;
		case R.id.bt_getTerm:
			startActivity(new Intent(RegisterActivity.this,
					TermsOfServiceActivity.class));
			break;
		default:
			break;
		}
	}

}
