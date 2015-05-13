package com.ruhoon.jiayu.merchant.aboutjiayu;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.net.ForwardNetState;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class AboutJiaYuActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btContact, btFeedBack, btTerm, btVersion;
	ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about_jia_yu);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		initView();
		versionUpdata();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void initView() {
		btBack = (Button) findViewById(R.id.bt_about_back);
		btVersion = (Button) findViewById(R.id.bt_virson);
		btContact = (Button) findViewById(R.id.bt_contactUs);
		btFeedBack = (Button) findViewById(R.id.bt_fackBack);
		btTerm = (Button) findViewById(R.id.bt_servicerTerm);
		btTerm.setOnClickListener(this);
		btBack.setOnClickListener(this);
		btContact.setOnClickListener(this);
		btFeedBack.setOnClickListener(this);

	}

	private void versionUpdata() {
		if (ForwardNetState.getInstance().isNetworkConnected(
				AboutJiaYuActivity.this)) {
			DataRequestVolley dataRequestVolley = new DataRequestVolley(
					HttpMethod.POST, MerchantHttpUrl.VERSION_UPDATE,
					versionListener, versionErrorListener) {
				@Override
				protected Map<String, String> getParams()
						throws AuthFailureError {
					// TODO Auto-generated method stub
					Map<String, String> map = new HashMap<String, String>();
					map.put("version_code", "1");
					map.put("device", "android");
					map.put("type", "2");

					return map;
				}
			};
			pd = new ProgressDialog(AboutJiaYuActivity.this);
			pd = ProgressDialog.show(AboutJiaYuActivity.this, "", "");
			RequestQueueSingleton.getInstance(AboutJiaYuActivity.this)
					.addToRequestQueue(dataRequestVolley);
		} else {
			Toast.makeText(AboutJiaYuActivity.this, MerchantMessage.CODE_800,
					Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> versionListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			btVersion.setOnClickListener(AboutJiaYuActivity.this);
			if ("".equals(response)) {
				Drawable img_off;
				Resources res = getResources();
				img_off = res.getDrawable(R.drawable.image_notify);
				// 调用setCompoundDrawables时，必须调用Drawable.setBounds()方法,否则图片不显示
				img_off.setBounds(0, 0, img_off.getMinimumWidth(),
						img_off.getMinimumHeight());
				btVersion.setCompoundDrawables(null, null, img_off, null); // 设置左图标
			} else {

			}
			Log.i("返回", response);

		}

	};

	Response.ErrorListener versionErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(AboutJiaYuActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about_jia_yu, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_about_jia_yu,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_about_back:
			finish();
			break;
		case R.id.bt_virson:
			break;
		case R.id.bt_servicerTerm:
			startActivity(new Intent(AboutJiaYuActivity.this,
					TermsOfServiceActivity.class));
			break;
		case R.id.bt_contactUs: {
			startActivity(new Intent(AboutJiaYuActivity.this,
					ContactUsActivity.class));
		}
			break;
		case R.id.bt_fackBack: {
//			startActivity(new Intent(AboutJiaYuActivity.this,
//					FeedbackActivity.class));
		}
			break;
		default:
			break;
		}
	}

}
