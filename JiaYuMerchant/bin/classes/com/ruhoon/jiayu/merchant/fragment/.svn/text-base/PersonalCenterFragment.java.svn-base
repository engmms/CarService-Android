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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.aboutjiayu.AboutJiaYuActivity;
import com.ruhoon.jiayu.merchant.activity.LoginActivity;

import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.datamodel.MerchantDetailInfo;
import com.ruhoon.jiayu.merchant.net.ForwardNetState;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;
import com.ruhoon.jiayu.volley.http.DataRequestVolley;
import com.ruhoon.jiayu.volley.http.HttpMethod;
import com.ruhoon.jiayu.volley.http.JsonUtil;
import com.ruhoon.jiayu.volley.http.RequestQueueSingleton;

public class PersonalCenterFragment extends Fragment implements OnClickListener {
	View view;
	Context context;
	ProgressDialog pd;
	MyApplication application;
	MerchantDetailInfo detailInfo;
	NetworkImageView networkImageView;
	TextView tvMName, tvApprasinal, tvCollect;
	RatingBar rbAltitude, rbQuality, rbEnvironment;
	Button btMIntro, btMDetail, btModifyPass, btModifyPhone, btAboutJiaYu,
			btLoginOut;

	public PersonalCenterFragment(Context context) {
		this.context = context;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		application = (MyApplication) context.getApplicationContext();
		if (ForwardNetState.getInstance().isNetworkConnected(context)) {
			if (!"".equals(application.getLoginInfo().getMer_session_id())) {
				pd = new ProgressDialog(context);
				pd = ProgressDialog.show(context, "", "");
				DataRequestVolley dataRequestVolley = new DataRequestVolley(
						HttpMethod.POST, MerchantHttpUrl.GET_MERCHANT,
						getMListener, getMErrorListener) {
					@Override
					protected Map<String, String> getParams()
							throws AuthFailureError {
						// TODO Auto-generated method stub
						Map<String, String> map = new HashMap<String, String>();
						map.put("mer_session_id", application.getLoginInfo()
								.getMer_session_id());

						return map;
					}
				};

				RequestQueueSingleton.getInstance(context).addToRequestQueue(
						dataRequestVolley);
			} else {
				Toast.makeText(context, MerchantMessage.CODE_801,
						Toast.LENGTH_SHORT).show();
				startActivity(new Intent(context, LoginActivity.class));
			}
		} else {

		}
		view = inflater.inflate(R.layout.fragment_personal_center, null);
		networkImageView = (NetworkImageView) view
				.findViewById(R.id.niv_header);
		tvMName = (TextView) view.findViewById(R.id.tv_MerchantName);
		tvApprasinal = (TextView) view.findViewById(R.id.tv_Review);
		tvCollect = (TextView) view.findViewById(R.id.tv_Collecte);
		rbAltitude = (RatingBar) view.findViewById(R.id.rb_altitude);
		rbQuality = (RatingBar) view.findViewById(R.id.rb_quality);
		rbEnvironment = (RatingBar) view.findViewById(R.id.rb_environment);
		btMIntro = (Button) view.findViewById(R.id.bt_MerchantIntro);
		btMIntro.setOnClickListener(this);
		btMDetail = (Button) view.findViewById(R.id.bt_MerchantDetail);
		btMDetail.setOnClickListener(this);
		btModifyPass = (Button) view.findViewById(R.id.bt_ChangePass);
		btModifyPass.setOnClickListener(this);
		btModifyPhone = (Button) view.findViewById(R.id.bt_changePhone);
		btModifyPhone.setOnClickListener(this);
		btAboutJiaYu = (Button) view.findViewById(R.id.bt_aboutJiaYu);
		btAboutJiaYu.setOnClickListener(this);
		btLoginOut = (Button) view.findViewById(R.id.bt_loginOut);
		btLoginOut.setOnClickListener(this);
		return view;
	}

	Response.Listener<String> getMListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			detailInfo = new MerchantDetailInfo();
			detailInfo = JsonUtil.toObject(response, MerchantDetailInfo.class);
			networkImageView.setDefaultImageResId(R.drawable.default_image);
			networkImageView.setErrorImageResId(R.drawable.error_image);
			ImageLoader imageLoader = RequestQueueSingleton
					.getInstance(context).getImageLoader();
			networkImageView.setImageUrl(detailInfo.getHeader(), imageLoader);
			tvMName.setText(detailInfo.getMerchant_name());
			tvApprasinal.setText("评论:" + "(" + detailInfo.getComment_count()
					+ ")");
			tvCollect.setText("收藏" + "(" + detailInfo.getCollect_count() + ")");
			rbAltitude
					.setRating(Float.valueOf(detailInfo.getService_attitude()));
			rbQuality.setRating(Float.valueOf(detailInfo.getService_quality()));
			rbEnvironment.setRating(Float.valueOf(detailInfo
					.getMerchant_setting()));
		}

	};

	Response.ErrorListener getMErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT)
					.show();
			if (error.getMessage().equals("登录过期")) {
				startActivity(new Intent(context, LoginActivity.class));
			}
		}
	};

	private void loginOut() {

		pd = new ProgressDialog(context);
		pd = ProgressDialog.show(context, "", "");
		DataRequestVolley dataRequestVolley = new DataRequestVolley(
				HttpMethod.POST, MerchantHttpUrl.LOGINOUT, loginOutListener,
				loginOutErrorListener) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				// TODO Auto-generated method stub
				Map<String, String> map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("member_id", application.getLoginInfo()
						.getMerchant_id());
				return map;
			}
		};

		RequestQueueSingleton.getInstance(context).addToRequestQueue(
				dataRequestVolley);

	}

	Response.Listener<String> loginOutListener = new Response.Listener<String>() {

		@Override
		public void onResponse(String response) {
			// TODO Auto-generated method stub
			pd.dismiss();
			startActivity(new Intent(context, LoginActivity.class));
		}

	};

	Response.ErrorListener loginOutErrorListener = new Response.ErrorListener() {

		@Override
		public void onErrorResponse(VolleyError error) {
			// TODO Auto-generated method stub
			pd.dismiss();
			Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT)
					.show();
			if (error.getMessage().equals("登录过期")) {
				startActivity(new Intent(context, LoginActivity.class));
			}
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_MerchantIntro: {

		}
			break;
		case R.id.bt_MerchantDetail: {

		}
			break;

		case R.id.bt_ChangePass: {
//			startActivity(new Intent(context,
//					ModificationPasswordActivity.class));
		}
			break;
		case R.id.bt_changePhone: {
			// startActivity(new Intent(context,
			// ModificationPhoneActivity.class));
		}
			break;
		case R.id.bt_aboutJiaYu: {
			startActivity(new Intent(context, AboutJiaYuActivity.class));
		}
			break;
		case R.id.bt_loginOut: {
			loginOut();
		}
			break;
		default:
			break;
		}
	}

}
