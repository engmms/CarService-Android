package com.ruhoon.jiayu.merchant.activity;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.merchant.staticdata.UserName;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.LoginDataRequestVolley;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class ModificationPhoneActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btGetOldCode, btGetNewCode, btComplete;
	EditText etOldPhone, etOldCode, etNewPhone, etNewCode;
	ProgressDialog pd;
	String session_id;
	MyApplication application;
	Handler handler, handler1;
	Timer timer, timer1;
	int length = 60, length1 = 60;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modification_phone_password);
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
		btBack = (Button) findViewById(R.id.bt_back_ModifyPhone);
		btGetOldCode = (Button) findViewById(R.id.bt_oldGetVerification);
		btGetNewCode = (Button) findViewById(R.id.bt_newGetVerification);
		btComplete = (Button) findViewById(R.id.bt_modifyPhone_complete);
		etOldPhone = (EditText) findViewById(R.id.et_oldPhoneNumber);
		etOldCode = (EditText) findViewById(R.id.et_oldVerificationCode);
		etNewPhone = (EditText) findViewById(R.id.et_newPhoneNumber);
		etNewCode = (EditText) findViewById(R.id.et_newVerificationCode);
		btBack.setOnClickListener(this);
		btGetOldCode.setOnClickListener(this);
		btGetNewCode.setOnClickListener(this);
		btComplete.setOnClickListener(this);
	}

	private void getOldCode() {

		if (UserName.isMobileNO(etOldPhone.getText().toString())) {

			LoginDataRequestVolley dataRequestVolley = new LoginDataRequestVolley(
					HttpMethod.POST, MerchantHttpUrl.MOD_USERNAME,
					oldCodeListener, oldCodeErrorListener,
					ModificationPhoneActivity.this) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					Map<String, String> map = new HashMap<String, String>();
					map.put("orgname", etOldPhone.getText().toString());
					return map;
				}
			};
			RequestQueueSingleton.getInstance(ModificationPhoneActivity.this)
					.addToRequestQueue(dataRequestVolley);
			pd = new ProgressDialog(ModificationPhoneActivity.this);
			pd = ProgressDialog.show(ModificationPhoneActivity.this, "", "");
		} else {
			Toast.makeText(ModificationPhoneActivity.this,
					MerchantMessage.CODE_517, Toast.LENGTH_SHORT).show();
		}

	}

	Response.Listener<String> oldCodeListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Log.i("response", response);
//			session_id = response;
			application.setSession_id(session_id);
			handler = new Handler() {
				public void handleMessage(android.os.Message msg) {
					if (msg.what == 1) {
						btGetOldCode.setText(length + "秒后重新获取");
					} else {
						btGetOldCode.setText("获取验证码");
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

		}

	};

	Response.ErrorListener oldCodeErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ModificationPhoneActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			if (error.getMessage().equals("登录过期")) {
				startActivity(new Intent(ModificationPhoneActivity.this,
						LoginActivity.class));
			}

		}
	};

	private void getNewCode() {

		if (UserName.isMobileNO(etOldPhone.getText().toString())) {
			if (!"".equals(etOldCode.getText().toString())) {

				if (UserName.isMobileNO(etNewPhone.getText().toString())) {

					LoginDataRequestVolley dataRequestVolley = new LoginDataRequestVolley(
							HttpMethod.POST, MerchantHttpUrl.MOD_USERNAME,
							newCodeListener, newCodeErrorListener,
							ModificationPhoneActivity.this) {
						@Override
						protected Map<String, String> getParams()
								throws AuthFailureError {
							// TODO Auto-generated method stub
							Map<String, String> map = new HashMap<String, String>();
							map.put("orgname", etOldPhone.getText().toString());
							map.put("orgcode_verify", etOldCode.getText()
									.toString());
							map.put("session_id", application.getSession_id());
							map.put("username", etNewPhone.getText().toString());
							return map;
						}
					};
					RequestQueueSingleton.getInstance(
							ModificationPhoneActivity.this).addToRequestQueue(
							dataRequestVolley);
					pd = new ProgressDialog(ModificationPhoneActivity.this);
					pd = ProgressDialog.show(ModificationPhoneActivity.this,
							"", "");
				} else {
					Toast.makeText(ModificationPhoneActivity.this,
							MerchantMessage.CODE_517, Toast.LENGTH_SHORT)
							.show();
				}

			} else {
				Toast.makeText(ModificationPhoneActivity.this,
						MerchantMessage.CODE_519, Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(ModificationPhoneActivity.this,
					MerchantMessage.CODE_517, Toast.LENGTH_SHORT).show();
		}

	}

	Response.Listener<String> newCodeListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Log.i("response", response);
			handler1 = new Handler() {
				public void handleMessage(android.os.Message msg) {
					if (msg.what == 1) {
						btGetNewCode.setText(length1 + "秒后重新获取");
					} else {
						btGetNewCode.setText("获取验证码");
					}
				};
			};
			TimerTask task = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub

					if (length1 > 0) {
						Message msg1 = new Message();
						length1 = length1 - 1;
						msg1.what = 1;
						msg1.arg1 = length;
						handler1.sendMessage(msg1);

					} else {
						Message msg2 = new Message();
						msg2.what = 2;
						handler1.sendMessage(msg2);
						timer1.cancel();

					}
				}
			};

			timer1 = new Timer();
			timer1.schedule(task, 0, 1000);

		}

	};

	Response.ErrorListener newCodeErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ModificationPhoneActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			if (error.getMessage().equals("登录过期")) {
				startActivity(new Intent(ModificationPhoneActivity.this,
						LoginActivity.class));
			}

		}
	};

	private void confirmChangePhone() {

		if (UserName.isMobileNO(etOldPhone.getText().toString())) {
			if (!"".equals(etOldCode.getText().toString())) {

				if (UserName.isMobileNO(etNewPhone.getText().toString())) {
					if (!"".equals(etNewCode.getText().toString())) {

						LoginDataRequestVolley dataRequestVolley = new LoginDataRequestVolley(
								HttpMethod.POST, MerchantHttpUrl.MOD_USERNAME,
								phoneListener, phoneErrorListener,
								ModificationPhoneActivity.this) {
							@Override
							protected Map<String, String> getParams()
									throws AuthFailureError {
								// TODO Auto-generated method stub
								Map<String, String> map = new HashMap<String, String>();
								map.put("orgname", etOldPhone.getText()
										.toString());
								map.put("orgcode_verify", etOldCode.getText()
										.toString());
								map.put("session_id",
										application.getSession_id());
								map.put("username", etNewPhone.getText()
										.toString());
								map.put("now_code_verify", etNewCode.getText()
										.toString());
								return map;
							}
						};
						RequestQueueSingleton.getInstance(
								ModificationPhoneActivity.this)
								.addToRequestQueue(dataRequestVolley);
						pd = new ProgressDialog(ModificationPhoneActivity.this);
						pd = ProgressDialog.show(
								ModificationPhoneActivity.this, "", "");
					} else {
						Toast.makeText(ModificationPhoneActivity.this,
								MerchantMessage.CODE_519, Toast.LENGTH_SHORT)
								.show();
					}

				} else {
					Toast.makeText(ModificationPhoneActivity.this,
							MerchantMessage.CODE_517, Toast.LENGTH_SHORT)
							.show();
				}

			} else {
				Toast.makeText(ModificationPhoneActivity.this,
						MerchantMessage.CODE_519, Toast.LENGTH_LONG).show();
			}
		} else {
			Toast.makeText(ModificationPhoneActivity.this,
					MerchantMessage.CODE_517, Toast.LENGTH_SHORT).show();
		}

	}

	Response.Listener<String> phoneListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Log.i("response", response);
			Toast.makeText(ModificationPhoneActivity.this,
					MerchantMessage.CODE_518, Toast.LENGTH_SHORT).show();
			finish();
		}
	};

	Response.ErrorListener phoneErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ModificationPhoneActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			if (error.getMessage().equals("登录过期")) {
				startActivity(new Intent(ModificationPhoneActivity.this,
						LoginActivity.class));
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modification_phone_password, menu);
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
					R.layout.fragment_modification_phone_password, container,
					false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_oldGetVerification:
			getOldCode();
			break;
		case R.id.bt_newGetVerification:
			getNewCode();
			break;
		case R.id.bt_modifyPhone_complete:
			confirmChangePhone();
			break;
		case R.id.bt_back_ModifyPhone:
			finish();
			break;
		default:
			break;
		}

	}

}
