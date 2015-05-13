package com.ruhoon.jiayu.merchant.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class MyOrderFragment extends Fragment implements OnScrollListener {
	View view;
	ListView listView;

	private View moreView; // 加载更多页面

	private MyOrderListAdapter adapter;
	private ArrayList<String> listData;

	private int lastItem;
	private int count;
	Context context;
	ProgressDialog pd;
	MyApplication application;
	String orderType = "";

	public MyOrderFragment(Context context, String orderType) {
		this.context = context;
		this.orderType = orderType;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		application = (MyApplication) context.getApplicationContext();
		view = inflater.inflate(R.layout.myorder_fragment, null);
		listView = (ListView) view.findViewById(R.id.lv_myOrder);
		moreView = inflater.inflate(R.layout.my_order_listfootview, null);
		listData = new ArrayList<String>();
		moreView.setVisibility(View.GONE);
		
		prepareData(); // 准备数据

		return view;
	}

	private void prepareData() { // 准备数据
		getOrder();
		for (int i = 0; i < 10; i++) {

			listData.add("测试数据" + i);
		}
		count = listData.size();
	}

	private void loadMoreData() { // 加载更多数据
		count = adapter.getCount();
		for (int i = count; i < count + 5; i++) {

			listData.add("测试数据" + i);
		}
		count = listData.size();
	}

	private void getOrder() {

		pd = new ProgressDialog(context);
		pd = ProgressDialog.show(context, "", "");
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.MERCHANT_ORDER_LIST, getOrderListener,
				getOrderErrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("pagenum", 15 + "");
				map.put("type", orderType);
				return map;
			}
		};

		RequestQueueSingleton.getInstance(context).addToRequestQueue(
				dataRequestVolley);

	}

	Response.Listener<String> getOrderListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			adapter = new MyOrderListAdapter();
			listView.addFooterView(moreView); // 添加底部view，一定要在setAdapter之前添加，否则会报错。

			listView.setAdapter(adapter); // 设置adapter
			listView.setOnScrollListener(MyOrderFragment.this); // 设置listview的滚动事件
			listView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// TODO Auto-generated method stub
//					startActivity(intent);
				}
			});;
		}

	};

	Response.ErrorListener getOrderErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			adapter = new MyOrderListAdapter();
			listView.addFooterView(moreView); // 添加底部view，一定要在setAdapter之前添加，否则会报错。

			listView.setAdapter(adapter); // 设置adapter
			listView.setOnScrollListener(MyOrderFragment.this); // 设置listview的滚动事件
		}
	};

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
		// visibleItemCount：当前能看见的列表项个数（小半个也算）
		// totalItemCount：列表项共数
		Log.i(((Activity) context).getTitle().toString(), "firstVisibleItem="
				+ firstVisibleItem + "\nvisibleItemCount=" + visibleItemCount
				+ "\ntotalItemCount" + totalItemCount);

		lastItem = firstVisibleItem + visibleItemCount - 1; // 减1是因为上面加了个addFooterView

	}

	@SuppressWarnings("static-access")
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		Log.i(((Activity) context).getTitle().toString(), "scrollState="
				+ scrollState);
		// 下拉到空闲是，且最后一个item的数等于数据的总数时，进行更新
		if (lastItem == count && scrollState == this.SCROLL_STATE_IDLE) {
			Log.i(((Activity) context).getTitle().toString(), "拉到最底部");
			moreView.setVisibility(view.VISIBLE);

			mHandler.sendEmptyMessage(0);

		}

	}

	// 声明Handler
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				loadMoreData(); // 加载更多数据，这里可以使用异步加载
				adapter.notifyDataSetChanged();
				moreView.setVisibility(View.GONE);

				if (count > 30) {
					Toast.makeText(context, "木有更多数据！", 3000).show();
					listView.removeFooterView(moreView); // 移除底部视图
				}
				Log.i(((Activity) context).getTitle().toString(), "加载更多数据");
				break;
			case 1:

				break;
			default:
				break;
			}
		};
	};

	public class MyOrderListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return listData.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = getLayoutInflater(getArguments()).inflate(
					R.layout.layout_myorder_listview, null);
			TextView textView = (TextView) view.findViewById(R.id.tv_myorder);
			textView.setText(listData.get(position));
			return view;
		}

	}

}
