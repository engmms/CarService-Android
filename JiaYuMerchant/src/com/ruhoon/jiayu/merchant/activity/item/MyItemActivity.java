package com.ruhoon.jiayu.merchant.activity.item;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.fragment.MyItemFragment;

public class MyItemActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btMyBeauty, btMyDecorate, btMyRepair, btRefit, btAddItem;
	ViewPager viewPager;
	MyApplication application;
	ViewPagerAdapter pagerAdapter;

	int count = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_item);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		initView();

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	private void initView() {
		btBack = (Button) findViewById(R.id.bt_back_myItem);
		btBack.setOnClickListener(this);
		btMyBeauty = (Button) findViewById(R.id.bt_my_bueaty);
		btMyBeauty.setOnClickListener(this);
		btMyDecorate = (Button) findViewById(R.id.bt_my_decorate);
		btMyDecorate.setOnClickListener(this);
		btMyRepair = (Button) findViewById(R.id.bt_my_repair);
		btMyRepair.setOnClickListener(this);
		btRefit = (Button) findViewById(R.id.bt_my_refit);
		btRefit.setOnClickListener(this);
		btAddItem = (Button) findViewById(R.id.bt_addItem);
		btAddItem.setOnClickListener(this);
		viewPager = (ViewPager) findViewById(R.id.vp_myItem);
		pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		viewPager.setAdapter(pagerAdapter);
		viewPager.setOnPageChangeListener(mChangeListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_item, menu);
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_my_item,
					container, false);
			return rootView;
		}
	}

	private OnPageChangeListener mChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0: {
				btMyBeauty.setBackgroundResource(R.drawable.bt_my_beauty);
				btMyBeauty.setTextColor(getResources().getColorStateList(
						R.color.white));
				btMyDecorate.setBackgroundResource(R.color.white);
				btMyDecorate.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btMyRepair.setBackgroundResource(R.color.white);
				btMyRepair.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btRefit.setBackgroundResource(R.color.white);
				btRefit.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));

			}

				break;
			case 1: {
				btMyBeauty.setBackgroundResource(R.color.white);
				btMyBeauty.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btMyDecorate.setBackgroundResource(R.drawable.bt_my_beauty);
				btMyDecorate.setTextColor(getResources().getColorStateList(
						R.color.white));
				btMyRepair.setBackgroundResource(R.color.white);
				btMyRepair.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btRefit.setBackgroundResource(R.color.white);
				btRefit.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));

			}

				break;
			case 2: {
				btMyBeauty.setBackgroundResource(R.color.white);
				btMyBeauty.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btMyDecorate.setBackgroundResource(R.color.white);
				btMyDecorate.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btMyRepair.setBackgroundResource(R.drawable.bt_my_beauty);
				btMyRepair.setTextColor(getResources().getColorStateList(
						R.color.white));
				btRefit.setBackgroundResource(R.color.white);
				btRefit.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));

			}

				break;
			case 3: {
				btMyBeauty.setBackgroundResource(R.color.white);
				btMyBeauty.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btMyDecorate.setBackgroundResource(R.color.white);
				btMyDecorate.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btMyRepair.setBackgroundResource(R.color.white);
				btMyRepair.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btRefit.setBackgroundResource(R.drawable.bt_my_beauty);
				btRefit.setTextColor(getResources().getColorStateList(
						R.color.white));

			}

				break;

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

	class ViewPagerAdapter extends FragmentStatePagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
			Log.i("Test", "ViewPagerAdapter");
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			MyItemFragment fragment = null;
			Log.i("Test", "getItem" + arg0);
			switch (arg0) {
			case 0: {

				fragment = new MyItemFragment(MyItemActivity.this, "1");

			}
				break;
			case 1: {

				fragment = new MyItemFragment(MyItemActivity.this, "2");

			}
				break;
			case 2: {

				fragment = new MyItemFragment(MyItemActivity.this, "3");

			}
				break;
			case 3: {

				fragment = new MyItemFragment(MyItemActivity.this, "4");

			}
				break;
			default:
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			Log.i("Test", "getCount");
			return 4;
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_back_myItem:
			finish();
			break;
		case R.id.bt_addItem:
			startActivity(new Intent(MyItemActivity.this,
					ItemChoiceActivity.class));
			break;
		case R.id.bt_my_bueaty: {
			btMyBeauty.setBackgroundResource(R.drawable.bt_my_beauty);
			btMyBeauty.setTextColor(this.getResources().getColorStateList(
					R.color.white));
			btMyDecorate.setBackgroundResource(R.color.white);
			btMyDecorate.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btMyRepair.setBackgroundResource(R.color.white);
			btMyRepair.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btRefit.setBackgroundResource(R.color.white);
			btRefit.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			viewPager.setCurrentItem(0);
		}
			break;
		case R.id.bt_my_decorate: {
			btMyBeauty.setBackgroundResource(R.color.white);
			btMyBeauty.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btMyDecorate.setBackgroundResource(R.drawable.bt_my_beauty);
			btMyDecorate.setTextColor(this.getResources().getColorStateList(
					R.color.white));
			btMyRepair.setBackgroundResource(R.color.white);
			btMyRepair.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btRefit.setBackgroundResource(R.color.white);
			btRefit.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			viewPager.setCurrentItem(1);
		}
			break;
		case R.id.bt_my_repair: {
			btMyBeauty.setBackgroundResource(R.color.white);
			btMyBeauty.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btMyDecorate.setBackgroundResource(R.color.white);
			btMyDecorate.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btMyRepair.setBackgroundResource(R.drawable.bt_my_beauty);
			btMyRepair.setTextColor(this.getResources().getColorStateList(
					R.color.white));
			btRefit.setBackgroundResource(R.color.white);
			btRefit.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			viewPager.setCurrentItem(2);
		}
			break;
		case R.id.bt_my_refit: {
			btMyBeauty.setBackgroundResource(R.color.white);
			btMyBeauty.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btMyDecorate.setBackgroundResource(R.color.white);
			btMyDecorate.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btMyRepair.setBackgroundResource(R.color.white);
			btMyRepair.setTextColor(this.getResources().getColorStateList(
					R.color.myItemOrange));
			btRefit.setBackgroundResource(R.drawable.bt_my_beauty);
			btRefit.setTextColor(this.getResources().getColorStateList(
					R.color.white));
			viewPager.setCurrentItem(3);
		}
			break;
		default:
			break;
		}
	}

}
