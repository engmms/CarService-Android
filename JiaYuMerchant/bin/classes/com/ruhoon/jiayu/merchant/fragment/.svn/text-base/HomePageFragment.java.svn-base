package com.ruhoon.jiayu.merchant.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.activity.ServiceEvaluateActivity;
import com.ruhoon.jiayu.merchant.activity.getorder.GetOrderActivity;
import com.ruhoon.jiayu.merchant.activity.item.MyItemActivity;
import com.ruhoon.jiayu.merchant.activity.order.MyOrderActivity;
import com.ruhoon.jiayu.merchant.widgts.AutoSlideImageView;

public class HomePageFragment extends Fragment implements OnClickListener {
	AutoSlideImageView slideImageView;
	RelativeLayout relativeLayout;
	Context context;
	Button btMyItem, myOrder, btGetOrder, btServiceEveluate;
	String[] pictures = {
			"http://h.hiphotos.baidu.com/image/h%3D360/sign=5d6764f5f1d3572c79e29adaba126352/3b87e950352ac65c478e556ff8f2b21193138a26.jpg",
			"http://c.hiphotos.baidu.com/image/h%3D360/sign=c32f72b975094b36c4921deb93cd7c00/810a19d8bc3eb135c1783bdda51ea8d3fd1f441d.jpg",
			"http://b.hiphotos.baidu.com/image/h%3D360/sign=cad3207cd70735fa8ef048bfae500f9f/060828381f30e924d9521e564f086e061c95f7e7.jpg" };

	public HomePageFragment(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.home_fragment, null);
		btMyItem = (Button) view.findViewById(R.id.bt_myItem);
		btMyItem.setOnClickListener(this);
		myOrder = (Button) view.findViewById(R.id.bt_myOrder);
		myOrder.setOnClickListener(this);
		btGetOrder = (Button) view.findViewById(R.id.bt_getOrder);
		btGetOrder.setOnClickListener(this);
		btServiceEveluate = (Button) view.findViewById(R.id.bt_evaluateService);
		btServiceEveluate.setOnClickListener(this);

		relativeLayout = (RelativeLayout) view
				.findViewById(R.id.home_image_scorall);
		slideImageView = new AutoSlideImageView(context);
		slideImageView.getInstance(pictures, 1000);
		relativeLayout.addView(slideImageView);

		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_myItem:
			startActivity(new Intent(context, MyItemActivity.class));
			break;
		case R.id.bt_myOrder:
			startActivity(new Intent(context, MyOrderActivity.class));
			break;
		case R.id.bt_getOrder:
			startActivity(new Intent(context, GetOrderActivity.class));
			break;
		case R.id.bt_evaluateService:
			startActivity(new Intent(context, ServiceEvaluateActivity.class));
			break;
		default:
			break;
		}
	}

}
