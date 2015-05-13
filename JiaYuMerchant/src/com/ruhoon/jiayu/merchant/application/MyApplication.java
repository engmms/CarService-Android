package com.ruhoon.jiayu.merchant.application;

import com.ruhoon.jiayu.merchant.datamodel.MerchantLoginInfo;

import android.app.Application;

public class MyApplication extends Application {
	String session_id;
	MerchantLoginInfo loginInfo;

	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public MerchantLoginInfo getLoginInfo() {
		return loginInfo;
	}

	public void setLoginInfo(MerchantLoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}

	
	
}
