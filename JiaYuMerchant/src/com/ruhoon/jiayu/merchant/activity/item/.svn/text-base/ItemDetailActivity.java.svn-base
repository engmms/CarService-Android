package com.ruhoon.jiayu.merchant.activity.item;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.activity.LoginActivity;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.datamodel.ItemDetailInfo;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.JsonUtil;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailActivity extends ActionBarActivity implements
		OnClickListener {
	String strtime, strday, strhour, strmin, strpay, strexplain, strpname,
			strName;
	int ettime;
	Button btback, btwrite, btmodify, btcancel;
	EditText ethour, etpay, etexplain, etday, etmin;
	TextView tvpname, tvname;
	ImageButton img1, img3, img4;
	ImageButton img2;
	MyApplication application;
	ProgressDialog pd;
	String serviceid;
	ItemDetailInfo itemDetailInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_iten_detail);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		application = (MyApplication) getApplication();
		pd = new ProgressDialog(ItemDetailActivity.this);
		serviceid = getIntent().getStringExtra("serverId");
		collect();
		init();

	}

	public void init() {
		btback = (Button) findViewById(R.id.bt_itemdetail_back);
		btwrite = (Button) findViewById(R.id.choice_detailitem);
		btmodify = (Button) findViewById(R.id.btn_detailitem_modification);
		btcancel = (Button) findViewById(R.id.btn_detailitem_delete);
		btback.setOnClickListener(this);
		btwrite.setOnClickListener(this);
		btmodify.setOnClickListener(this);
		btcancel.setOnClickListener(this);

		etday = (EditText) findViewById(R.id.et_detailitem_day);
		ethour = (EditText) findViewById(R.id.et_detailitem_time);
		etmin = (EditText) findViewById(R.id.et_detailitem_min);
		etpay = (EditText) findViewById(R.id.et_detailitem_pay);
		etexplain = (EditText) findViewById(R.id.et_detailitem_explain);

		img1 = (ImageButton) findViewById(R.id.detail_img1);
		img2 = (ImageButton) findViewById(R.id.detail_img2);
		img3 = (ImageButton) findViewById(R.id.detail_img3);
		img4 = (ImageButton) findViewById(R.id.detail_img4);

		tvname = (TextView) findViewById(R.id.detailitem_name);
		tvpname = (TextView) findViewById(R.id.detailitem_id);
	}

	public void collect() {
		String url = MerchantHttpUrl.GET_SERVICE;
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, url, listener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("service_id", serviceid);
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				return map;
			}
		};
		pd = ProgressDialog.show(ItemDetailActivity.this, "", "");
		RequestQueueSingleton.getInstance(ItemDetailActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> listener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			pd.dismiss();
			Log.i("response", response);
			Log.i("返回", response);
			itemDetailInfo = JsonUtil.toObject(response, ItemDetailInfo.class);
			strtime = itemDetailInfo.getTime();
			strpay = itemDetailInfo.getPrice();
			strexplain = itemDetailInfo.getIntro();
			strName = itemDetailInfo.getService_name();
			strpname = itemDetailInfo.getService_pname();

			etday.setText(((Integer.valueOf(strtime) / 60) / 24)+"");
			ethour.setText(((Integer.valueOf(strtime) / 60) % 24)+"");
			etmin.setText((Integer.valueOf(strtime) % 60)+"");

			etpay.setText(strpay);
			etexplain.setText(strexplain);
			tvname.setText(strName);
			tvpname.setText(strpname);
		}

	};

	Response.ErrorListener errorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub

			pd.dismiss();
			Toast.makeText(ItemDetailActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			if (error.getMessage().equals("登录过期")) {
				
				startActivity(new Intent(ItemDetailActivity.this,
						LoginActivity.class));
			}

		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.iten_detail, menu);
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

	@Override
	public void onClick(View arg0) {

		switch (arg0.getId()) {
		case R.id.bt_itemdetail_back:
			finish();
			break;
		case R.id.choice_detailitem:
			startActivity(new Intent(ItemDetailActivity.this,
					ItemChoiceActivity.class));
			break;
		case R.id.btn_detailitem_modification:
			modify();
			break;
		case R.id.btn_detailitem_delete:
			delete();
			break;
		}
	}

	public void modify() {
		String url = MerchantHttpUrl.MOD_SERVICE;
		strday = etday.getText().toString();
		strhour = ethour.getText().toString();
		strmin = etmin.getText().toString();

		ettime = ((Integer.valueOf(strday).intValue() * 24) * 60)
				+ (Integer.valueOf(strhour).intValue() * 60)
				+ Integer.valueOf(strmin).intValue();
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, url, modifylistener, modifyerrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo().getMer_session_id());
				map.put("service_id", serviceid);
				map.put("intro", etexplain.getText().toString());
				map.put("price", etpay.getText().toString());
				map.put("timeout", String.valueOf(ettime));
				return map;
			}
		};
		pd = ProgressDialog.show(ItemDetailActivity.this, "", "");
		RequestQueueSingleton.getInstance(ItemDetailActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> modifylistener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			pd.dismiss();
			Log.i("response", response);
			Log.i("返回", response);
			Toast.makeText(ItemDetailActivity.this, MerchantMessage.CODE_104,
					Toast.LENGTH_SHORT).show();
		}

	};

	Response.ErrorListener modifyerrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ItemDetailActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			if(error.getMessage().equals("登录过期")){
				
				startActivity(new Intent(ItemDetailActivity.this, LoginActivity.class));
			}
		}
	};

	public void delete() {
		String url = MerchantHttpUrl.DEL_SERVICE;
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, url, deletelistener, deleteerrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("service_id", serviceid);
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				return map;
			}
		};
		pd = ProgressDialog.show(ItemDetailActivity.this, "", "");
		RequestQueueSingleton.getInstance(ItemDetailActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> deletelistener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			pd.dismiss();
			Log.i("response", response);
			Log.i("返回", response);
			Toast.makeText(ItemDetailActivity.this, "删除成功", Toast.LENGTH_SHORT)
					.show();
		}

	};

	Response.ErrorListener deleteerrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ItemDetailActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
			
		}
	};
}
