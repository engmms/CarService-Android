package com.ruhoon.jiayu.merchant.activity;

import java.util.ArrayList;
import java.util.List;

import com.ruhoon.jiayu.merchant.fragment.ChatFragment;
import com.ruhoon.jiayu.merchant.fragment.HomePageFragment;
import com.ruhoon.jiayu.merchant.fragment.PersonalCenterFragment;
import com.ruhoon.jiayu.merchant.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	Button btPersonal, btChat, btHomePage;
	TextView tvPersonal, tvChat, tvHomepager;
	ViewPager vpViewPager;
	ViewPagerAdapter mViewPagerAdapter;
	List<Button> buttonList = new ArrayList<Button>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		btPersonal = (Button) findViewById(R.id.bt_home_personalCenter);
		btPersonal.setOnClickListener(this);
		btChat = (Button) findViewById(R.id.bt_home_chat);
		btChat.setOnClickListener(this);
		btHomePage = (Button) findViewById(R.id.bt_home_homePager);
		btHomePage.setOnClickListener(this);
		tvPersonal = (TextView) findViewById(R.id.tv_personal);
		tvChat = (TextView) findViewById(R.id.tv_chat);
		tvHomepager = (TextView) findViewById(R.id.tv_homepager);
		buttonList.add(btHomePage);
		buttonList.add(btChat);
		buttonList.add(btPersonal);
		vpViewPager = (ViewPager) findViewById(R.id.vp_home_viewpager);
		mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
		vpViewPager.setAdapter(mViewPagerAdapter);
		vpViewPager.setOnPageChangeListener(mPageChangeListener);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	public class ViewPagerAdapter extends FragmentStatePagerAdapter {
		public ViewPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		//
		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public Object instantiateItem(View container, int position) {
			// TODO Auto-generated method stub
			return super.instantiateItem(container, position);
		}

		//
		@Override
		public Fragment getItem(int position) {
			Fragment frgmt = null;
			switch (position) {
			case 0:

				frgmt = new HomePageFragment(MainActivity.this);

				break;
			case 1:
				frgmt = new ChatFragment(MainActivity.this);
				break;
			case 2: {

				frgmt = new PersonalCenterFragment(MainActivity.this);

				break;

			}

			}
			return frgmt;
		}
	}

	//
	public OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int position) {
			switch (position) {
			case 0: {
				btHomePage.setBackgroundResource(R.drawable.home_pager_press);
				tvHomepager.setTextColor(R.color.mainOrange);
				btChat.setBackgroundResource(R.drawable.chat);
				tvChat.setTextColor(R.color.mainNormal);
				btPersonal.setBackgroundResource(R.drawable.personal_center);
				btPersonal.setTextColor(R.color.mainNormal);
			}
				break;
			case 1: {
				btHomePage.setBackgroundResource(R.drawable.home_pager);
				tvHomepager.setTextColor(R.color.mainNormal);
				btChat.setBackgroundResource(R.drawable.chat_press);
				tvChat.setTextColor(R.color.mainOrange);
				btPersonal.setBackgroundResource(R.drawable.personal_center);
				tvPersonal.setTextColor(R.color.mainNormal);
			}
				break;
			case 2: {
				btHomePage.setBackgroundResource(R.drawable.home_pager);
				tvHomepager.setTextColor(R.color.mainNormal);
				btChat.setBackgroundResource(R.drawable.chat);
				tvChat.setTextColor(R.color.mainNormal);
				btPersonal
						.setBackgroundResource(R.drawable.personal_center_press);
				tvPersonal.setTextColor(R.color.mainOrange);
			}
				break;
			}
		}

		@Override
		public void onPageScrolled(int position, float arg1, int arg2) {
			//

		}

		@Override
		public void onPageScrollStateChanged(int position) {
			//
		}
	};

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bt_home_homePager:
			vpViewPager.setCurrentItem(0);
			btHomePage.setBackgroundResource(R.drawable.home_pager_press);
			tvHomepager.setTextColor(getResources().getColorStateList(
					R.color.mainOrange));
			btChat.setBackgroundResource(R.drawable.chat);
			tvChat.setTextColor(getResources().getColorStateList(
					R.color.mainNormal));
			btPersonal.setBackgroundResource(R.drawable.personal_center);
			btPersonal.setTextColor(getResources().getColorStateList(
					R.color.mainNormal));
			break;
		case R.id.bt_home_chat:
			vpViewPager.setCurrentItem(1);
			btHomePage.setBackgroundResource(R.drawable.home_pager);
			tvHomepager.setTextColor(getResources().getColorStateList(
					R.color.mainNormal));
			btChat.setBackgroundResource(R.drawable.chat_press);
			tvChat.setTextColor(getResources().getColorStateList(
					R.color.mainOrange));
			btPersonal.setBackgroundResource(R.drawable.personal_center);
			tvPersonal.setTextColor(getResources().getColorStateList(
					R.color.mainNormal));
			break;
		case R.id.bt_home_personalCenter:
			vpViewPager.setCurrentItem(2);
			btHomePage.setBackgroundResource(R.drawable.home_pager);
			tvHomepager.setTextColor(getResources().getColorStateList(
					R.color.mainNormal));
			btChat.setBackgroundResource(R.drawable.chat);
			tvChat.setTextColor(getResources().getColorStateList(
					R.color.mainNormal));
			btPersonal.setBackgroundResource(R.drawable.personal_center_press);
			tvPersonal.setTextColor(getResources().getColorStateList(
					R.color.mainOrange));
			break;

		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder builder = new AlertDialog.Builder(
					MainActivity.this);
			builder.setTitle("退出提示");
			builder.setMessage("是否退出驾客?");
			builder.setPositiveButton("确定退出",
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							finish();
						}
					});
			builder.setNegativeButton("暂不退出",
					new AlertDialog.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}

					});
			builder.show();
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
