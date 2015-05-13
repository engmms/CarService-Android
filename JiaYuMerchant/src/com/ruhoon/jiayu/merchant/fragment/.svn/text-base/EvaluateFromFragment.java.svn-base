package com.ruhoon.jiayu.merchant.fragment;

import java.util.HashMap;
import java.util.Map;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.activity.LoginActivity;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.LoginDataRequestVolley;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class EvaluateFromFragment extends Fragment {
	Context context;
	View view;
	ListView listView;
	ProgressDialog pd;
	String type;
	MyApplication application;

	public EvaluateFromFragment(Context context, String type) {
		this.context = context;
		this.type = type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		application = (MyApplication) context.getApplicationContext();
		pd = new ProgressDialog(context);
		pd = ProgressDialog.show(context, "", "");
		LoginDataRequestVolley dataRequestVolley = new LoginDataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.MERCHANT_COMMENT_LIST,
				merCommentListener, merCommentErrorListener,context) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("type", type);
				map.put("page", "1");
				map.put("num", "15");

				return map;
			}
		};

		RequestQueueSingleton.getInstance(context).addToRequestQueue(
				dataRequestVolley);
		view = inflater.inflate(R.layout.evaluate_from_fragment, null);
		listView = (ListView) view.findViewById(R.id.lv_evaluate_from);

		return view;
	}

	Response.Listener<String> merCommentListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();

		}

	};

	Response.ErrorListener merCommentErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT)
					.show();
			if (error.getMessage().equals("µÇÂ¼¹ýÆÚ")) {
				startActivity(new Intent(context, LoginActivity.class));
			}
		}
	};
}
