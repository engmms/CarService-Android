package com.ruhoon.jiayu.merchant.activity;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import com.ruhoon.jiayu.merchant.net.ForwardNetState;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.merchant.staticdata.Password;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.LoginDataRequestVolley;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class ModificationPasswordActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btComplete;
	EditText etOldPass, etNewPass, etRenewPass;
	ProgressDialog pd;
	MyApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modification_password);
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
		btBack = (Button) findViewById(R.id.bt_back_ModifyPass);
		btComplete = (Button) findViewById(R.id.bt_modifyComplete);
		etOldPass = (EditText) findViewById(R.id.et_modify_oldePass);
		etNewPass = (EditText) findViewById(R.id.et_modify_newPass);
		etRenewPass = (EditText) findViewById(R.id.et_modify_renewPass);
		btBack.setOnClickListener(this);
		btComplete.setOnClickListener(this);
	}

	private void modifyComplete() {
		if (ForwardNetState.getInstance().isNetworkConnected(
				ModificationPasswordActivity.this)) {
			if (!"".equals(etOldPass.getText().toString())) {
				if (Password.isRightPassword(etNewPass.getText().toString())) {
					if (etNewPass.getText().toString()
							.equals(etRenewPass.getText().toString())) {
						LoginDataRequestVolley dataRequestVolley = new LoginDataRequestVolley(
								HttpMethod.POST, MerchantHttpUrl.MOD_PASSWORD_BY_NOR,
								modifyListener, modifyErrorListener,
								ModificationPasswordActivity.this) {
							@Override
							protected Map<String, String> getParams()
									throws AuthFailureError {
								// TODO Auto-generated method stub
								Map<String, String> map = new HashMap<String, String>();
								map.put("mer_session_id", application
										.getLoginInfo().getMer_session_id());
								map.put("oldpwd", etOldPass.getText()
										.toString());
								map.put("newpwd", etNewPass.getText()
										.toString());
								map.put("renewpwd", etRenewPass.getText()
										.toString());

								return map;
							}
						};
						pd = new ProgressDialog(
								ModificationPasswordActivity.this);
						pd = ProgressDialog.show(
								ModificationPasswordActivity.this, "", "");
						RequestQueueSingleton.getInstance(
								ModificationPasswordActivity.this)
								.addToRequestQueue(dataRequestVolley);
					} else {
						Toast.makeText(ModificationPasswordActivity.this,
								MerchantMessage.CODE_9, Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(ModificationPasswordActivity.this,
							MerchantMessage.CODE_5, Toast.LENGTH_SHORT).show();
				}
			} else {
				Toast.makeText(ModificationPasswordActivity.this,
						MerchantMessage.CODE_5, Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(ModificationPasswordActivity.this,
					MerchantMessage.CODE_800, Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> modifyListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

			Toast.makeText(ModificationPasswordActivity.this,
					MerchantMessage.CODE_10, Toast.LENGTH_SHORT).show();
			finish();
			Log.i("·µ»Ø", response);

		}

	};

	Response.ErrorListener modifyErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ModificationPasswordActivity.this,
					error.getMessage(), Toast.LENGTH_SHORT).show();
			if (error.getMessage().equals("µÇÂ¼¹ýÆÚ")) {
				startActivity(new Intent(ModificationPasswordActivity.this,
						LoginActivity.class));
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modification_password, menu);
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
					R.layout.fragment_modification_password, container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_back_ModifyPass:
			finish();
			break;
		case R.id.bt_modifyComplete:
			modifyComplete();
			break;
		default:
			break;
		}
	}

}
