package com.ruhoon.jiayu.merchant.activity.getorder;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;

import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class AlreadyOfferDetailActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack;
	ProgressDialog pd;
	MyApplication application;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_already_offer_detail);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		application = (MyApplication) getApplication();
		initView();
		getquotedNeeds();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void initView() {
		btBack = (Button) findViewById(R.id.bt_back_QuotedDetail);
		btBack.setOnClickListener(this);

	}

	private void getquotedNeeds() {

		pd = new ProgressDialog(AlreadyOfferDetailActivity.this);
		pd = ProgressDialog.show(AlreadyOfferDetailActivity.this, "", "");
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.GET_MEMBER_DEMAND,
				sucesssListener, ErrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("id", "");
				

				return map;
			}
		};

		RequestQueueSingleton.getInstance(AlreadyOfferDetailActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> sucesssListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

		}

	};

	Response.ErrorListener ErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(AlreadyOfferDetailActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	private void reQuoted() {

		pd = new ProgressDialog(AlreadyOfferDetailActivity.this);
		pd = ProgressDialog.show(AlreadyOfferDetailActivity.this, "", "");
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.MERCHANT_OFFER_PRICE,
				offerListener, offerErrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("id", "");
				map.put("type", "2");
				// id
				// mer_session_id
				// category_ids
				// prices
				// times
				// merchant_remark
				// bidding_ids

				return map;
			}
		};

		RequestQueueSingleton.getInstance(AlreadyOfferDetailActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> offerListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

		}

	};

	Response.ErrorListener offerErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(AlreadyOfferDetailActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	private void deleteQuoted() {

		pd = new ProgressDialog(AlreadyOfferDetailActivity.this);
		pd = ProgressDialog.show(AlreadyOfferDetailActivity.this, "", "");
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.DEL_MERCHANT_BIDDING,
				deleteListener, deleteErrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("id", "");
				map.put("type", "2");

				return map;
			}
		};

		RequestQueueSingleton.getInstance(AlreadyOfferDetailActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> deleteListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

		}

	};

	Response.ErrorListener deleteErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(AlreadyOfferDetailActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.already_offer_detail, menu);
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
					R.layout.fragment_already_offer_detail, container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_back_QuotedDetail:
			finish();
			break;

		default:
			break;
		}
	}

}
