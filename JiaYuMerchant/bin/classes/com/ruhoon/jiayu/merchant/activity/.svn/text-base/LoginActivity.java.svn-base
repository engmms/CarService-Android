package com.ruhoon.jiayu.merchant.activity;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.datamodel.MerchantLoginInfo;
import com.ruhoon.jiayu.merchant.net.ForwardNetState;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.merchant.staticdata.UserName;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.JsonUtil;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class LoginActivity extends ActionBarActivity implements OnClickListener {
	Button btForgotPassword, btRegister, btLogin;
	EditText etUserName, etPassword;
	ProgressDialog pd;
	SharedPreferences sp;
	SharedPreferences.Editor editor;
	MerchantLoginInfo mMerchantLoginInfo;
	MyApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		sp = getSharedPreferences("userMessage", MODE_PRIVATE);
		editor = sp.edit();
		initView();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void initView() {

		btForgotPassword = (Button) findViewById(R.id.bt_forgot_password);
		btForgotPassword.setOnClickListener(this);
		btRegister = (Button) findViewById(R.id.bt_register);
		btRegister.setOnClickListener(this);
		btLogin = (Button) findViewById(R.id.bt_login);
		btLogin.setOnClickListener(this);
		etUserName = (EditText) findViewById(R.id.et_userName);
		etPassword = (EditText) findViewById(R.id.et_password);
		if (!"".equals(sp.getString("username", ""))) {
			etUserName.setText(sp.getString("username", ""));
		}

		if (!"".equals(sp.getString("password", ""))) {
			etPassword.setText(sp.getString("password", ""));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_login,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_forgot_password:
			startActivity(new Intent(LoginActivity.this,
					RetrievePasswordActivity.class));
			break;
		case R.id.bt_register:
			startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
			break;
		case R.id.bt_login:
			login();

			break;
		default:
			break;
		}
	}

	private void login() {
		if (ForwardNetState.getInstance()
				.isNetworkConnected(LoginActivity.this)) {
			if (!"".equals(etUserName.getText())) {
				if (UserName.isMobileNO(etUserName.getText().toString())) {
					if (!"".equals(etPassword.getText().toString())) {

						pd = new ProgressDialog(LoginActivity.this);
						pd = ProgressDialog.show(LoginActivity.this, "", "");
						DataRequestVolley dataRequestVolley = new DataRequestVolley(
								HttpMethod.POST, MerchantHttpUrl.LOGIN,
								loginListener, loginErrorListener) {
							@Override
							protected Map<String, String> getParams()
									throws AuthFailureError {
								// TODO Auto-generated method stub
								Map<String, String> map = new HashMap<String, String>();
								map.put("username", etUserName.getText()
										.toString());
								map.put("password", etPassword.getText()
										.toString());
								return map;
							}
						};

						RequestQueueSingleton.getInstance(LoginActivity.this)
								.addToRequestQueue(dataRequestVolley);
					} else {
						Toast.makeText(LoginActivity.this,
								MerchantMessage.CODE_4, Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(LoginActivity.this, MerchantMessage.CODE_2,
							Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(LoginActivity.this, MerchantMessage.CODE_1,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(LoginActivity.this, "您没有连接网络", Toast.LENGTH_SHORT)
					.show();
		}
	}

	Response.Listener<String> loginListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			editor.putString("username", etUserName.getText().toString());
			editor.putString("password", etPassword.getText().toString());
			editor.commit();
			mMerchantLoginInfo = new MerchantLoginInfo();

			mMerchantLoginInfo = JsonUtil.toObject(response,
					MerchantLoginInfo.class);
			application = (MyApplication) getApplication();

			application.setLoginInfo(mMerchantLoginInfo);
			Log.i("返回", response);
			startActivity(new Intent(LoginActivity.this, MainActivity.class));

		}

	};

	Response.ErrorListener loginErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(LoginActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};
}
