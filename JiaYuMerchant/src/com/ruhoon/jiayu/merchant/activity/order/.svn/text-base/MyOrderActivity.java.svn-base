package com.ruhoon.jiayu.merchant.activity.order;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.ruhoon.jiayu.merchant.fragment.MyOrderFragment;
import com.ruhoon.jiayu.merchant.R;

public class MyOrderActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btUncomplete, btComplete, btFail;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_order);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		initView();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void initView() {
		btBack = (Button) findViewById(R.id.bt_back_myOrder);
		btBack.setOnClickListener(this);
		btUncomplete = (Button) findViewById(R.id.bt_uncompleteOrder);
		btUncomplete.setOnClickListener(this);
		btComplete = (Button) findViewById(R.id.bt_completeOrder);
		btComplete.setOnClickListener(this);
		btFail = (Button) findViewById(R.id.bt_failOrder);
		btFail.setOnClickListener(this);
		viewPager = (ViewPager) findViewById(R.id.vp_myOrder);

		viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(pageChangeListener);
	}

	class ViewPagerAdapter extends FragmentStatePagerAdapter {

		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new MyOrderFragment(MyOrderActivity.this,"0");
				break;
			case 1:
				fragment = new MyOrderFragment(MyOrderActivity.this,"1");
				break;
			case 2:
				fragment = new MyOrderFragment(MyOrderActivity.this,"2");
				break;
			default:
				break;

			}
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 3;
		}

	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0: {
				btUncomplete.setBackgroundResource(R.drawable.bt_myorder_bg);
				btUncomplete.setTextColor(getResources().getColorStateList(
						R.color.white));
				btComplete.setBackgroundResource(R.color.white);
				btComplete.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btFail.setBackgroundResource(R.color.white);
				btFail.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
			}

				break;
			case 1: {
				btUncomplete.setBackgroundResource(R.color.white);
				btUncomplete.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btComplete.setBackgroundResource(R.drawable.bt_myorder_bg);
				btComplete.setTextColor(getResources().getColorStateList(
						R.color.white));
				btFail.setBackgroundResource(R.color.white);
				btFail.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
			}
				break;
			case 2: {
				btUncomplete.setBackgroundResource(R.color.white);
				btUncomplete.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btComplete.setBackgroundResource(R.color.white);
				btComplete.setTextColor(getResources().getColorStateList(
						R.color.myItemOrange));
				btFail.setBackgroundResource(R.drawable.bt_myorder_bg);
				btFail.setTextColor(getResources().getColorStateList(
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

	private void uncomplete() {
		btUncomplete.setBackgroundResource(R.drawable.bt_myorder_bg);
		btUncomplete.setTextColor(getResources().getColorStateList(
				R.color.white));
		btComplete.setBackgroundResource(R.color.white);
		btComplete.setTextColor(getResources().getColorStateList(
				R.color.myItemOrange));
		btFail.setBackgroundResource(R.color.white);
		btFail.setTextColor(getResources().getColorStateList(
				R.color.myItemOrange));
		viewPager.setCurrentItem(0);
	}

	private void complete() {
		btUncomplete.setBackgroundResource(R.color.white);
		btUncomplete.setTextColor(getResources().getColorStateList(
				R.color.myItemOrange));
		btComplete.setBackgroundResource(R.drawable.bt_myorder_bg);
		btComplete
				.setTextColor(getResources().getColorStateList(R.color.white));
		btFail.setBackgroundResource(R.color.white);
		btFail.setTextColor(getResources().getColorStateList(
				R.color.myItemOrange));
		viewPager.setCurrentItem(1);
	}

	private void fail() {
		btUncomplete.setBackgroundResource(R.color.white);
		btUncomplete.setTextColor(getResources().getColorStateList(
				R.color.myItemOrange));
		btComplete.setBackgroundResource(R.color.white);
		btComplete.setTextColor(getResources().getColorStateList(
				R.color.myItemOrange));
		btFail.setBackgroundResource(R.drawable.bt_myorder_bg);
		btFail.setTextColor(getResources().getColorStateList(R.color.white));
		viewPager.setCurrentItem(2);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_order, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_my_order,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_uncompleteOrder:
			uncomplete();

			break;
		case R.id.bt_completeOrder:
			complete();
			break;
		case R.id.bt_failOrder:
			fail();
			break;
		case R.id.bt_back_myOrder:
			finish();
			break;
		default:
			break;
		}

	}
}
