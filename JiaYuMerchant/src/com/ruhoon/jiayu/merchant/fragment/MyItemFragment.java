package com.ruhoon.jiayu.merchant.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import com.ruhoon.jiayu.merchant.activity.item.ItemDetailActivity;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.datamodel.MyItemListInfo;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.JsonUtil;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class MyItemFragment extends Fragment implements OnScrollListener {
	Context context;
	ListView listView;
	ItemListAdapter adapter;
	MyApplication application;
	ProgressDialog pd;
	public List<MyItemListInfo> myItemlist = new ArrayList<MyItemListInfo>();
	public List<MyItemListInfo> moreItemList = new ArrayList<MyItemListInfo>();
	String type;
	private View moreView;
	private int lastItem;
	private int count;
	private int pagerCount = 1;

	public MyItemFragment(Context context, String type) {
		this.context = context;
		// this.myItemlist = myItemlist;
		this.type = type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_myitem_list, null);
		application = (MyApplication) context.getApplicationContext();
		listView = (ListView) view.findViewById(R.id.myitem_list);
		moreView = inflater.inflate(R.layout.my_order_listfootview, null);
		moreView.setVisibility(View.GONE);
		listView.addFooterView(moreView); // 添加底部view，一定要在setAdapter之前添加，否则会报错。
		adapter = new ItemListAdapter();
		listView.setAdapter(adapter);
		// 设置adapter
		listView.setOnScrollListener(MyItemFragment.this); // 设置listview的滚动事件
		getitemview();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, ItemDetailActivity.class);
				intent.putExtra("serverId", myItemlist.get(position)
						.getService_id());
				startActivity(intent);

			}
		});
		return view;
	}

	public void getitemview() {

		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.SERVICE_LIST, listener,
				errorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("page", String.valueOf(pagerCount));
				map.put("num", String.valueOf(15));
				map.put("classid", type);
				return map;
			}
		};
		if (pagerCount < 2) {
			pd = new ProgressDialog(context);
			pd = ProgressDialog.show(context, "", "");
		}
		RequestQueueSingleton.getInstance(context).addToRequestQueue(
				dataRequestVolley);
	}

	public Response.Listener<String> listener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub

			try {
				JSONObject jsonObject = new JSONObject(response);
				String strList = jsonObject.getString("list");
				if (pagerCount < 2) {
					myItemlist = JsonUtil.toObjectList(strList,
							MyItemListInfo.class);
					pd.dismiss();
				} else {
					moreItemList = JsonUtil.toObjectList(strList,
							MyItemListInfo.class);
					for (MyItemListInfo itemListInfo : moreItemList) {
						myItemlist.add(itemListInfo);
					}
				}
				count = myItemlist.size();
				adapter.notifyDataSetChanged();
				moreView.setVisibility(View.GONE);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	};
	public Response.ErrorListener errorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub

			moreView.setVisibility(View.GONE);
			adapter.notifyDataSetChanged();

			if (pagerCount < 2) {
				Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT)
						.show();
				pd.dismiss();
			} else {
				Toast.makeText(context, "没有更多数据", Toast.LENGTH_SHORT).show();
			}
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

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		Log.i(((Activity) context).getTitle().toString(), "scrollState="
				+ scrollState);
		// 下拉到空闲是，且最后一个item的数等于数据的总数时，进行更新
		if (lastItem == count
				&& scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
			Log.i(((Activity) context).getTitle().toString(), "拉到最底部");
			moreView.setVisibility(View.VISIBLE);
			mHandler.sendEmptyMessage(0);

		}

	}

	// 声明Handler
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0: {

				pagerCount++;
				getitemview();// 加载更多数据，这里可以使用异步加载

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

	public class ItemListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myItemlist.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return myItemlist.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View view = convertView;
			ViewHolder holder = null;
			if (view == null) {
				holder = new ViewHolder();
				view = getLayoutInflater(getArguments()).inflate(
						R.layout.myitem_list, null);
				holder.itemName = (TextView) view
						.findViewById(R.id.tv_item_name);
				holder.itemPrice = (TextView) view
						.findViewById(R.id.tv_item_price);
				holder.itemNum = (TextView) view.findViewById(R.id.tv_item_num);
				holder.itemCheck = (TextView) view
						.findViewById(R.id.tv_item_check);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.itemName.setText((String) myItemlist.get(position)
					.getService_name());
			holder.itemPrice.setText("价格:"
					+ (String) myItemlist.get(position).getPrice() + "元");
			holder.itemNum.setText("近期成交"
					+ (String) myItemlist.get(position).getSell_num() + "笔");
			if ("0".equals(myItemlist.get(position).getEffect())) {
				holder.itemCheck.setText("审核中");
			}
			return view;
		}

		public class ViewHolder {
			TextView itemName;
			TextView itemPrice;
			TextView itemNum;
			TextView itemCheck;
		}
	}
}
