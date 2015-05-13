package com.ruhoon.jiayu.merchant.activity.getorder;

import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.fragment.GetOrderFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class GetOrderActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btUnquote, btQuoted;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_order);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		initView();
	}

	private void initView() {
		btBack = (Button) findViewById(R.id.bt_back_getOrder);
		btBack.setOnClickListener(this);
		btUnquote = (Button) findViewById(R.id.bt_unquote);
		btUnquote.setOnClickListener(this);
		btQuoted = (Button) findViewById(R.id.bt_quoted);
		btQuoted.setOnClickListener(this);
		viewPager = (ViewPager) findViewById(R.id.vp_getOrder);
		viewPager.setAdapter(new GetOrderVP(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(pageChangeListener);
	}

	private void unquote() {
		btUnquote.setBackgroundResource(R.drawable.btn_unquote_press);
		btQuoted.setBackgroundResource(R.drawable.btn_quoted);
		btUnquote.setTextColor(Color.parseColor("#ffffff"));
		btQuoted.setTextColor(Color.parseColor("#f8673d"));
		viewPager.setCurrentItem(0);
	}

	private void quoted() {
		btUnquote.setBackgroundResource(R.drawable.btn_unquote);
		btQuoted.setBackgroundResource(R.drawable.btn_quoted_press);
		btUnquote.setTextColor(Color.parseColor("#f8673d"));
		btQuoted.setTextColor(Color.parseColor("#ffffff"));
		viewPager.setCurrentItem(1);
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0: {
				btUnquote.setBackgroundResource(R.drawable.btn_unquote_press);
				btQuoted.setBackgroundResource(R.drawable.btn_quoted);
				btUnquote.setTextColor(Color.parseColor("#ffffff"));
				btQuoted.setTextColor(Color.parseColor("#f8673d"));
			}
				break;
			case 1: {
				btUnquote.setBackgroundResource(R.drawable.btn_unquote);
				btQuoted.setBackgroundResource(R.drawable.btn_quoted_press);
				btUnquote.setTextColor(Color.parseColor("#f8673d"));
				btQuoted.setTextColor(Color.parseColor("#ffffff"));
			}
			default:
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub

		}
	};

	class GetOrderVP extends FragmentStatePagerAdapter {

		public GetOrderVP(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new GetOrderFragment(GetOrderActivity.this, "1");
				break;
			case 1:
				fragment = new GetOrderFragment(GetOrderActivity.this, "2");
				break;
			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 2;
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.get_order, menu);
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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_back_getOrder:
			finish();
			break;
		case R.id.bt_unquote:
			unquote();
			break;
		case R.id.bt_quoted:
			quoted();
			break;
		default:
			break;
		}
	}

}
