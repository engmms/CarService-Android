package com.ruhoon.jiayu.merchant.aboutjiayu;

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
import com.ruhoon.jiayu.merchant.activity.LoginActivity;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.net.ForwardNetState;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.LoginDataRequestVolley;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class FeedbackActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btCommit;
	EditText etFeedback;
	ProgressDialog pd;
	MyApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
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
		btBack = (Button) findViewById(R.id.bt_back_feedback);
		btCommit = (Button) findViewById(R.id.bt_commitFeedback);
		etFeedback = (EditText) findViewById(R.id.et_feedback);
		btBack.setOnClickListener(this);
		btCommit.setOnClickListener(this);
	}

	private void commitFeedback() {
		if (ForwardNetState.getInstance().isNetworkConnected(
				FeedbackActivity.this)) {
			if (!"".equals(etFeedback.getText().toString())) {
				if (etFeedback.getText().toString().length() <= 200) {
					LoginDataRequestVolley dataRequestVolley = new LoginDataRequestVolley(
							HttpMethod.POST, MerchantHttpUrl.FEEDBACK,
							feedbackListener, feedbackErrorListener,
							FeedbackActivity.this) {
						@Override
						protected Map<String, String> getParams()
								throws AuthFailureError {
							// TODO Auto-generated method stub
							Map<String, String> map = new HashMap<String, String>();
							map.put("content", etFeedback.getText().toString());
							map.put("mer_session_id", application
									.getLoginInfo().getMer_session_id());
							return map;
						}
					};
					pd = new ProgressDialog(FeedbackActivity.this);
					pd = ProgressDialog.show(FeedbackActivity.this, "", "");
					RequestQueueSingleton.getInstance(FeedbackActivity.this)
							.addToRequestQueue(dataRequestVolley);
				} else {
					Toast.makeText(FeedbackActivity.this,
							MerchantMessage.CODE_514, Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(FeedbackActivity.this, MerchantMessage.CODE_515,
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(FeedbackActivity.this, MerchantMessage.CODE_800,
					Toast.LENGTH_SHORT).show();
		}
	}

	Response.Listener<String> feedbackListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			etFeedback.setText("");
			Toast.makeText(FeedbackActivity.this, MerchantMessage.CODE_516,
					Toast.LENGTH_SHORT).show();
			Log.i("·µ»Ø", response);

		}

	};

	Response.ErrorListener feedbackErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(FeedbackActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			if (error.getMessage().equals("µÇÂ¼¹ýÆÚ")) {
				startActivity(new Intent(FeedbackActivity.this,
						LoginActivity.class));
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feedback, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_feedback,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_back_feedback:
			finish();
			break;
		case R.id.bt_commitFeedback:
			commitFeedback();
			break;

		default:
			break;
		}
	}

}
