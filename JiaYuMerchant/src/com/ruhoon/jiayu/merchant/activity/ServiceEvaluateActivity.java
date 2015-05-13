package com.ruhoon.jiayu.merchant.activity;

import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.R.drawable;
import com.ruhoon.jiayu.merchant.R.id;
import com.ruhoon.jiayu.merchant.R.layout;
import com.ruhoon.jiayu.merchant.R.menu;
import com.ruhoon.jiayu.merchant.fragment.EvaluateFromFragment;
import com.ruhoon.jiayu.merchant.fragment.EvaluateToFragment;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class ServiceEvaluateActivity extends ActionBarActivity implements
		OnClickListener {
	Button btBack, btEvaluateFrom, btEvaluiateTo;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_evaluate);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		initView();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	private void initView() {
		btBack = (Button) findViewById(R.id.bt_back_evaluate);
		btEvaluateFrom = (Button) findViewById(R.id.bt_apprasinalFrom);
		btEvaluiateTo = (Button) findViewById(R.id.bt_apprasinalTo);
		viewPager = (ViewPager) findViewById(R.id.vp_evaluateViewPager);
		btBack.setOnClickListener(this);
		btEvaluateFrom.setOnClickListener(this);
		btEvaluiateTo.setOnClickListener(this);
		viewPager
				.setAdapter(new EvaluateVPAdapter(getSupportFragmentManager()));
		viewPager.setOnPageChangeListener(pageChangeListener);
	}

	class EvaluateVPAdapter extends FragmentStatePagerAdapter {

		public EvaluateVPAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new EvaluateFromFragment(
						ServiceEvaluateActivity.this, "0");
				break;
			case 1:
				fragment = new EvaluateToFragment(ServiceEvaluateActivity.this,
						"2");
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

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub
			switch (arg0) {
			case 0: {
				btEvaluateFrom
						.setBackgroundResource(R.drawable.bt_evaluate_from);
				btEvaluateFrom.setTextColor(Color.parseColor("#ffffff"));
				btEvaluiateTo.setBackgroundColor(Color.parseColor("#ffffff"));
				btEvaluiateTo.setTextColor(Color.parseColor("#ff805b"));
			}

				break;
			case 1: {
				btEvaluateFrom.setBackgroundColor(Color.parseColor("#ffffff"));
				btEvaluateFrom.setTextColor(Color.parseColor("#ff805b"));
				btEvaluiateTo
						.setBackgroundResource(R.drawable.bt_evaluate_from);
				btEvaluiateTo.setTextColor(Color.parseColor("#ffffff"));
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.service_evaluate, menu);
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
			View rootView = inflater.inflate(
					R.layout.fragment_service_evaluate, container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bt_back_evaluate:
			finish();
			break;
		case R.id.bt_apprasinalFrom: {
			btEvaluateFrom.setBackgroundResource(R.drawable.bt_evaluate_from);
			btEvaluateFrom.setTextColor(Color.parseColor("#ffffff"));
			btEvaluiateTo.setBackgroundColor(Color.parseColor("#ffffff"));
			btEvaluiateTo.setTextColor(Color.parseColor("#ff805b"));
			viewPager.setCurrentItem(0);
		}
			break;
		case R.id.bt_apprasinalTo: {
			btEvaluateFrom.setBackgroundColor(Color.parseColor("#ffffff"));
			btEvaluateFrom.setTextColor(Color.parseColor("#ff805b"));
			btEvaluiateTo.setBackgroundResource(R.drawable.bt_evaluate_from);
			btEvaluiateTo.setTextColor(Color.parseColor("#ffffff"));
			viewPager.setCurrentItem(1);
		}
			break;
		default:
			break;
		}
	}
}
