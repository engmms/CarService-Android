package com.ruhoon.jiayu.merchant.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ForwardNetState {
	private static ForwardNetState forwardNetState = null;

	public static ForwardNetState getInstance() {
		if (forwardNetState == null) {
			forwardNetState = new ForwardNetState();
		}
		return forwardNetState;
	}

	public boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
}
