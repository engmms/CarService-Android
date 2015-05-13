package com.ruhoon.jiayu.merchant.activity.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.datamodel.ServiceItem;
import com.ruhoon.jiayu.merchant.datamodel.ServiceItemChild;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.JsonUtil;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class ItemChoiceActivity extends ActionBarActivity {
	Button btback;
	MyApplication application;
	ProgressDialog pd;
	TableLayout tableLayout;
	List<ServiceItem> serviceItems = new ArrayList<ServiceItem>();
	List<ServiceItemChild> serviceItemchild = new ArrayList<ServiceItemChild>();
	String serviceItemName, ChildItemName, ChildItemId;
	int mutilScreenWithOne = 720;
	int mutilScreenWithTwo = 1080;
	int screenWidth;
	int pPosition = 0;
	int subPosition = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_choice);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		Log.i("screenWidth", screenWidth + "");
		application = (MyApplication) getApplication();
		pd = new ProgressDialog(ItemChoiceActivity.this);
		btback = (Button) findViewById(R.id.bt_itemchoice_back);
		btback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		collect();
		tableLayout = (TableLayout) findViewById(R.id.tb_add_item);
		tableLayout.setStretchAllColumns(true);
	}

	public void collect() {
		String url = MerchantHttpUrl.MER_SERVICE_SELECT_LIST;
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, url, listener, errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				return map;
			}
		};
		pd = ProgressDialog.show(ItemChoiceActivity.this, "", "");
		RequestQueueSingleton.getInstance(ItemChoiceActivity.this)
				.addToRequestQueue(dataRequestVolley);
	}

	Response.Listener<String> listener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			pd.dismiss();
			Log.i("response", response);
			Log.i("返回", response);
			serviceItems = JsonUtil.toObjectList(response, ServiceItem.class);
			for (ServiceItem item : serviceItems) {

				serviceItemchild = JsonUtil.toObjectList(item.getChild(),
						ServiceItemChild.class);
				item.setChildList(serviceItemchild);
			}
			fillTablayout();

		}
	};

	Response.ErrorListener errorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(ItemChoiceActivity.this, error.getMessage(),
					Toast.LENGTH_SHORT).show();
		}
	};

	public void fillTablayout() {

		int pLength = serviceItems.size();
		Log.i("serviceItems.size()", serviceItems.size() + "");
		int subLength = 0;
		int row = 0;
		int col = 0;
		for (int j = 0; j < pLength; j++) {

			TextView firstText = new TextView(ItemChoiceActivity.this);
			firstText.setTextColor(Color.parseColor("#4d4d4d"));
			firstText.setTextSize(18);
			firstText.setText(serviceItems.get(j).getName());
			tableLayout.addView(firstText);
			TextView tvNull = new TextView(ItemChoiceActivity.this);
			tvNull.setHeight(18);
			tableLayout.addView(tvNull);
			subLength = serviceItems.get(j).getChildList().size();
			pPosition = j;
			if ((subLength % 3) == 0) {
				row = subLength / 3;
			} else {
				row = (subLength / 3) + 1;
			}

			for (int k = 0; k < row; k++) {
				TableRow tableRow = new TableRow(ItemChoiceActivity.this);
				if (k == (row - 1)) {
					if ((subLength % 3) == 0) {
						col = 3;
					} else {
						col = subLength % 3;
					}
				} else {
					col = 3;
				}

				for (int t = 0; t < col; t++) {
					TextView button = new TextView(ItemChoiceActivity.this);

					if (screenWidth == mutilScreenWithOne) {
						button.setWidth(150);
						button.setHeight(60);
					} else {
						button.setWidth(225);
						button.setHeight(90);
					}

					button.setGravity(Gravity.CENTER);
					button.setText(serviceItems.get(j).getChildList()
							.get(k * 3 + t).getName());
					subPosition = k * 3 + t;
					button.setTextSize(12);

					if (serviceItems.get(j).getChildList().get(k * 3 + t)
							.getOwn().equals("1")) {
						button.setBackgroundColor(Color.parseColor("#ff805b"));
						button.setTextColor(Color.parseColor("#ffffff"));
					} else {
						button.setBackgroundResource(R.drawable.bt_fogot_pw_comp_bg);

						button.setTextColor(Color.parseColor("#ff8000"));
					}
					button.setOnClickListener(new OnClickListener() {
						int p = pPosition;
						int s = subPosition;

						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(ItemChoiceActivity.this,
									AddItemActivity.class);
							intent.putExtra("serviceItem",
									serviceItems.get(p).getName());
							intent.putExtra("childItem",
									serviceItems.get(p).getChildList()
											.get(s).getName());
							intent.putExtra("childId",
									serviceItems.get(p).getChildList()
											.get(s).getId());
							startActivity(intent);
						}
					});
					tableRow.addView(button);
					if (t != 2) {
						TextView tvCloNull = new TextView(
								ItemChoiceActivity.this);
						if (screenWidth == mutilScreenWithOne) {
							tvCloNull.setWidth(20);
						} else {
							tvCloNull.setWidth(30);
						}

						tableRow.addView(tvCloNull);
					}
				}
				tableLayout.addView(tableRow);
				TextView tvNUll2 = new TextView(ItemChoiceActivity.this);
				if (screenWidth == mutilScreenWithOne) {
					tvNUll2.setHeight(30);
				} else {
					tvNUll2.setHeight(45);
				}

				tableLayout.addView(tvNUll2);
			}

			ImageView firstview = new ImageView(ItemChoiceActivity.this);
			firstview.setBackgroundResource(R.drawable.bg_09);

			tableLayout.addView(firstview);

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_choice, menu);
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

}
