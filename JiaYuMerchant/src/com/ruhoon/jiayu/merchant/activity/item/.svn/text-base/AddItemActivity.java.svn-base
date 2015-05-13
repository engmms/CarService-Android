package com.ruhoon.jiayu.merchant.activity.item;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.ProgressDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ruhoon.jiayu.merchant.application.MyApplication;
import com.ruhoon.jiayu.merchant.R;
import com.ruhoon.jiayu.merchant.staticdata.MerchantHttpUrl;
import com.ruhoon.jiayu.merchant.staticdata.MerchantMessage;

@SuppressLint("SimpleDateFormat")
public class AddItemActivity extends ActionBarActivity implements
		OnClickListener, OnDateSetListener, OnTimeSetListener {
	Button btback, btwrite, btsubmit, btcancel;
	EditText etpay, etexplain, etday, etmin, ethour;
	TextView tvService, tvItem;
	ImageButton img1, img2, img3, img4;
	MyApplication application;
	ProgressDialog pd;
	String itempay, itemday, itemmin, itemhour, itemexplain;
	int itemtime;
	int pWhitch;
	int whitchImage = 0;
	Bitmap bitmap;
	SimpleDateFormat format;
	Date date = null;
	String strTime = null;
	File file;
	File[] fileArray = new File[9];
	String[] filenameArray = new String[9];
	String[] fileParamName = { "fileone", "filetwo", "filethree", "filefour",
			"filefive", "filesix", "fileseven", "fileeight", "filenight",
			"fileten" };
	String fileName;
	private static final String HTTP_BOUNDARY = "------------------------bc2de942c586ad8e";
	private static final String MULTIPART_FORM_DATA = "multipart/form-data";
	private static final String LINE_ENTER = System
			.getProperty("line.separator");
	private static final String twoHyphens = "--";
	private static final String CHARSET = "utf-8";
	private static final int RESPONSE_OK = 200;
	boolean isImageSuccess = false;
	IntentFilter mFilter = new IntentFilter();
	public static int PHOTO_REQUEST_CAREMA = 0;
	public static int PHOTO_REQUEST_GALLERY = 1;
	public static int PHOTO_REQUEST_CUT = 3;
	public String PhotoFileName;
	public int currentView = 0;
	public int classid = 0;
	List<File> fileList = new ArrayList<File>();
	File tempFile;
	String msg;
	String serviceName, childName, childId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item);
		ActionBar actionBar = getSupportActionBar();
		actionBar.hide();
		application = (MyApplication) getApplication();
		pd = new ProgressDialog(AddItemActivity.this);
		serviceName = getIntent().getStringExtra("serviceItem");
		childName = getIntent().getStringExtra("childItem");
		childId = getIntent().getStringExtra("childId");

		init();

	}

	public void init() {
		btback = (Button) findViewById(R.id.bt_additem_back);
		btwrite = (Button) findViewById(R.id.choice_item);
		btsubmit = (Button) findViewById(R.id.btn_item_sub);
		btcancel = (Button) findViewById(R.id.btn_item_cancel);
		btback.setOnClickListener(this);
		btwrite.setOnClickListener(this);
		btsubmit.setOnClickListener(this);
		btcancel.setOnClickListener(this);
		etexplain = (EditText) findViewById(R.id.et_item_explain);
		etpay = (EditText) findViewById(R.id.et_item_pay);
		ethour = (EditText) findViewById(R.id.et_item_time);
		etday = (EditText) findViewById(R.id.et_item_day);
		etmin = (EditText) findViewById(R.id.et_item_min);

		img1 = (ImageButton) findViewById(R.id.bt_img1);
		img2 = (ImageButton) findViewById(R.id.bt_img2);
		img3 = (ImageButton) findViewById(R.id.bt_img3);
		img4 = (ImageButton) findViewById(R.id.bt_img4);
		img1.setOnClickListener(this);
		img2.setOnClickListener(this);
		img3.setOnClickListener(this);
		img4.setOnClickListener(this);
		tvService = (TextView) findViewById(R.id.item_id);
		tvItem = (TextView) findViewById(R.id.item_name);

		tvService.setText(serviceName);
		tvItem.setText(childName);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item, menu);
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
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bt_additem_back:
			finish();
			break;
		case R.id.bt_img1:
			getMutilImage(0);
			break;
		case R.id.bt_img2:
			getMutilImage(1);
			break;
		case R.id.bt_img3:
			getMutilImage(2);
			break;
		case R.id.bt_img4:
			getMutilImage(3);
			break;
		case R.id.choice_item:
			finish();
			break;
		case R.id.btn_item_sub:
			collect();
			break;
		case R.id.btn_item_cancel:
			finish();
			break;

		}
	}

	private void getMutilImage(int position) {

		whitchImage = position;

		String[] item = { "拍照", "从手机相册获取", "取消" };
		AlertDialog.Builder builder = new AlertDialog.Builder(
				AddItemActivity.this);
		builder.setIcon(null);
		builder.setTitle("");
		builder.setItems(item, new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				switch (which) {
				case 0:
					camera();
					break;
				case 1:
					gallery();
					break;
				case 2:
					break;
				}
			}
		});
		builder.show();

	}

	public void gallery() {
		Intent intent = new Intent("android.intent.action.PICK");
		intent.setType("image/*");
		startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
	}

	public void camera() {

		String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
		} else {
			Toast.makeText(AddItemActivity.this, "没有SD卡", Toast.LENGTH_LONG)
					.show();
		}

	}

	public void cropImage(Uri uri, int outputX, int outputY, int requestCode) {

		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");

		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);

		intent.putExtra("outputX", outputX);
		intent.putExtra("outputY", outputY);

		intent.putExtra("outputFormat", "JPEG");
		intent.putExtra("noFaceDetection", true);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, requestCode);

	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (data != null) {

			Uri uri = data.getData();
			if (resultCode == RESULT_OK) {
				if (requestCode == PHOTO_REQUEST_GALLERY) {
					cropImage(uri, 180, 180, PHOTO_REQUEST_CUT);
				} else if (requestCode == PHOTO_REQUEST_CAREMA) {
					cropImage(uri, 180, 180, PHOTO_REQUEST_CUT);
				} else if (requestCode == PHOTO_REQUEST_CUT) {
					bitmap = data.getExtras().getParcelable("data");

					switch (whitchImage) {
					case 0:
						img1.setImageBitmap(bitmap);

						break;
					case 1:
						img2.setImageBitmap(bitmap);
						break;
					case 2:
						img3.setImageBitmap(bitmap);
						break;
					case 3:
						img4.setImageBitmap(bitmap);
						break;
					default:
						break;
					}

					String saveDir = Environment.getExternalStorageDirectory()
							+ "/jiakeimage/";
					File dir = new File(saveDir);

					if (!dir.exists()) {
						dir.mkdir();
					}

					format = new SimpleDateFormat("yyyyMMddHHmmss");
					date = new Date();
					strTime = format.format(date);
					strTime = String.valueOf(Long.valueOf(strTime) + 1);
					String imageFileName = "";
					imageFileName = "ZhiCheng" + strTime + ".jpg";
					file = new File(dir, imageFileName);
					fileName = file.getName();
					Log.i("sdcard", Environment.getExternalStorageDirectory()
							+ "");
					Log.i("Path", file.getPath());
					Log.i("fileName", fileName);
					if (file == null) {
						Toast.makeText(AddItemActivity.this, "文件创建失败",
								Toast.LENGTH_SHORT).show();
					} else {
						FileOutputStream fileOutputStream;
						try {
							fileOutputStream = new FileOutputStream(file);
							// 閻㈢喐鍨氶崶鍓у閺傚洣娆�
							bitmap.compress(Bitmap.CompressFormat.JPEG, 50,
									fileOutputStream);
							if (file == null) {
								Toast.makeText(AddItemActivity.this, "文件压缩失败",
										Toast.LENGTH_SHORT).show();
							} else {
								fileArray[whitchImage] = file;
								filenameArray[whitchImage] = file.getName();
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			} else {
				Toast.makeText(AddItemActivity.this, "获取照片失败",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			Toast.makeText(AddItemActivity.this, "获取照片为空", Toast.LENGTH_SHORT)
					.show();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	public void collect() {
		itempay = etpay.getText().toString();
		itemhour = ethour.getText().toString();
		itemday = etday.getText().toString();
		itemmin = etmin.getText().toString();

		itemtime = ((Integer.valueOf(itemday).intValue() * 24) * 60)
				+ (Integer.valueOf(itemhour).intValue() * 60)
				+ Integer.valueOf(itemmin).intValue();

		itemexplain = etexplain.getText().toString();

		if (!"".equals(itempay)) {

			pd.dismiss();
			new PostImageUp().execute();

		} else {
			Toast.makeText(AddItemActivity.this, MerchantMessage.CODE_101,
					Toast.LENGTH_SHORT).show();
		}
	}

	public class PostImageUp extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			URL url;
			Map<String, String> map = null;
			HttpURLConnection conn;
			try {
				url = new URL(MerchantHttpUrl.ADD_SERVICE);
				map = new HashMap<String, String>();
				map.put("mer_session_id", application.getLoginInfo()
						.getMer_session_id());
				map.put("intro", itemexplain);
				map.put("price", itempay);
				map.put("timeout", String.valueOf(itemtime));
				map.put("sub_id", childId);

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				conn.setReadTimeout(12 * 1000);
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.setUseCaches(false);
				conn.setRequestProperty("Connection", "keep-alive");
				conn.setRequestProperty("Charset", "utf-8");
				conn.setRequestProperty("Accept", "*/*");
				conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
						+ "; boundary=" + HTTP_BOUNDARY);
				conn.connect();
				StringBuilder paramData = new StringBuilder();
				for (Map.Entry<String, String> entry : map.entrySet()) {
					paramData.append(twoHyphens);
					paramData.append(HTTP_BOUNDARY);
					paramData.append(LINE_ENTER);
					paramData.append("Content-Disposition: form-data; name=\""
							+ entry.getKey() + "\"" + LINE_ENTER);
					paramData.append("Content-Type: text/plain; charset="
							+ CHARSET + LINE_ENTER);
					paramData.append("Content-Transfer-Encoding: 8bit"
							+ LINE_ENTER);
					paramData.append(LINE_ENTER);
					paramData.append(entry.getValue());
					paramData.append(LINE_ENTER);

				}
				DataOutputStream outStream = new DataOutputStream(
						conn.getOutputStream());

				outStream.write(paramData.toString().getBytes());

				int fileLength = fileArray.length;

				for (int j = 0; j < fileLength; j++) {
					StringBuilder fileSplit = new StringBuilder();
					fileSplit.append(twoHyphens + HTTP_BOUNDARY + LINE_ENTER);

					fileSplit.append("Content-Disposition: form-data;name=\""
							+ fileParamName[j] + "\";filename=\""
							+ filenameArray[j] + "\"" + LINE_ENTER);

					fileSplit.append("Content-Type:"
							+ "application/octet-stream" + LINE_ENTER
							+ LINE_ENTER);
					outStream.write(fileSplit.toString().getBytes());

					if (fileArray[j] != null) {
						byte[] buffer = new byte[1024];
						int length = 0;

						InputStream fileInputStream = new FileInputStream(
								fileArray[j]);
						while ((length = fileInputStream.read(buffer)) != -1) {

							outStream.write(buffer, 0, length);
						}
						fileInputStream.close();
					}
					outStream.write(LINE_ENTER.getBytes());

				}

				byte[] endData = ("--" + HTTP_BOUNDARY + "--" + LINE_ENTER)
						.getBytes();
				outStream.write(endData);
				outStream.flush();
				outStream.close();
				InputStream is = conn.getInputStream();
				int ch;
				StringBuilder b = new StringBuilder();
				while ((ch = is.read()) != -1) {
					b.append((char) ch);
				}
				Log.i("HttpPost", b.toString());
				conn.disconnect();
				int responseCode = conn.getResponseCode();

				if (responseCode != RESPONSE_OK) {
					isImageSuccess = false;

				} else {
					isImageSuccess = true;
					Log.i("图片", "访问服务器成功");
					int code = 0;

					try {
						code = new JSONObject(b.toString()).getInt("code");
						msg = new JSONObject(b.toString()).getString("msg");
						if (code == 0) {
							Log.i("图片", "上传成功");
						} else {
							Log.i("图片失败信息", msg);
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			Toast.makeText(AddItemActivity.this, "提交成功", Toast.LENGTH_SHORT)
					.show();
			finish();
			pd.dismiss();
		}

	}

	@Override
	public void onTimeSet(TimePicker arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

}
