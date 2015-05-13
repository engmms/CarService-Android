package com.ruhoon.jiayu.volley.http;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;




public class LoginDataRequestVolley extends Request<String> {

	private Response.Listener<String> mListener;
	private Context context;
	int status;

	public LoginDataRequestVolley(int method, String url,
			Response.Listener<String> listener,
			Response.ErrorListener errorListener, Context context) {
		super(method, url, errorListener);
		// TODO Auto-generated constructor stub
		mListener = listener;
		this.context = context;
	}

	@Override
	protected Response<String> parseNetworkResponse(NetworkResponse response) {
		// TODO Auto-generated method stub
		Log.i("服务器响应码", response.statusCode + "");
		try {

			String json = new String(response.data,
					HttpHeaderParser.parseCharset(response.headers));
			Log.i("响应data", json);
			status = Integer.parseInt(JsonUtil.getJsonValueByKey(json, "code"));
			Log.i("返回状态码", status + "");
			if (status == 0) {
				String dataJson = JsonUtil.getJsonValueByKey(json, "data");
				String message = JsonUtil.getJsonValueByKey(json, "msg");
				Log.i("msg", message);
				return Response.success(dataJson,
						HttpHeaderParser.parseCacheHeaders(response));
			} else if (status == 2) {
				return Response.error(new VolleyError("登录过期"));

			} else {
				String message = JsonUtil.getJsonValueByKey(json, "msg");
				Log.i("msg", message);
				return Response.error(new VolleyError(message));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return Response.error(new ParseError(e));
		}

	}

	@Override
	protected void deliverResponse(String response) {
		// TODO Auto-generated method stub
		mListener.onResponse(response);
	}

}
