package com.ruhoon.jiayu.asynctask.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ruhoon.jiayu.volley.http.HttpMethod;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * HTTP缃戠粶璁块棶鐩稿叧鏂规硶
 * 
 * @author Li bin
 */
public class HttpHelper {
	private static final String LOG_HTTP_POST_INFO = "REQUEST";
	private static final String LOG_HTTP_GET_ERROR = "com.imcore.common.http.GetError";
	private static final String LOG_HTTP_POST_ERROR = "com.imcore.common.http.PostError";

	private static final String CONTENT_TYPE_URL_ENCODED = "application/x-www-form-urlencoded";
	private static final String CHARSET = "utf-8";
	
	/**
	 * 鍙戦�乭ttp璇锋眰锛岃幏寰楀搷搴�?暟鎹�?
	 * 
	 * @param entity
	 *            鍖呭惈璇锋眰瀹炰綋淇℃伅鐨凴equestEntity瀹炰�?
	 * @return 杩斿洖鏈嶅姟鍣ㄧ鍝嶅簲鐨凧SON瀛楃涓茬粨鏋�
	 * @throws Exception
	 */
	public synchronized static String execute(RequestEntity entity)
			throws Exception {
		String jsonResult = "";
//
		String url =entity.getUrl();
		switch (entity.getMethod()) {
		case HttpMethod.GET:
			if (entity.getTextFields() == null) {
				jsonResult = get(url);
			} else {
				jsonResult = get(url, entity.getTextFields());
			}
			break;
		case HttpMethod.POST:
			if (entity instanceof MultipartFormEntity) {
				MultipartFormEntity multipartFormEntity = (MultipartFormEntity) entity;
				jsonResult = postMultipartForm(url, multipartFormEntity);
			} else {
				if (entity.getTextFields() == null) {
					jsonResult = post(url);
				} else {
					jsonResult = post(url, entity.getTextFields());
				}
			}
			break;
		}

		return jsonResult;
	}

	private synchronized static String get(String url) throws Exception {
		return get(url, null);
	}

	/**
	 * 鎵цGET璇锋�?
	 */
	private synchronized static String get(String url, Map<String, Object> params)
			throws Exception {
		String jsonResult = "";
		InputStream is = null;
		try {
			if (params != null && params.size() > 0) {
				String urlEncodedForm = toUrlEncodedFormParams(params);
				url = url + "?" + urlEncodedForm;
			}
			HttpURLConnection conn = getHttpURLConnection(url);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				jsonResult = read(is);
				Log.i(LOG_HTTP_GET_ERROR, jsonResult);
			} else {
				throw (new Exception());
			}
		} catch (MalformedURLException e) {
			Log.e(LOG_HTTP_GET_ERROR, e.getLocalizedMessage());
			e.printStackTrace();
			throw (e);
		} catch (IOException e) {
			Log.e(LOG_HTTP_GET_ERROR, e.getLocalizedMessage());
			e.printStackTrace();
			throw (e);
		} finally {
			closeStream(is);
		}

		return jsonResult;
	}

	private synchronized static String post(String url) throws Exception {
		return post(url, null);
	}

	/**
	 * 鎵цhttp post璇锋�?
	 * 
	 * @param url
	 *            璇锋眰鐨勬湇鍔�?�櫒绔痑pi鐨勯摼鎺�?
	 * @param params
	 *            鍖呭惈璇锋眰鍙傛暟鐨凪ap
	 * @return 杩斿洖Json鏍煎紡鐨勫搷搴旀暟鎹�?
	 * @throws Exception
	 */
	private synchronized static String post(String url,
			Map<String, Object> params) throws Exception {
		String jsonResult = "";
		OutputStream os = null;
		InputStream is = null;

		try {
			HttpURLConnection conn = getHttpURLConnection(url);
			conn.setDoOutput(true);
			conn.setUseCaches(false);

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", CONTENT_TYPE_URL_ENCODED);
			conn.setRequestProperty("Charset", CHARSET);

			os = conn.getOutputStream();
			if (params != null && params.size() > 0) {
				String urlEncodedForm = toUrlEncodedFormParams(params);
				Log.i(LOG_HTTP_POST_INFO, urlEncodedForm);
				os.write(urlEncodedForm.getBytes());
				os.flush();
			}

			if (conn.getResponseCode() == 200) {
				is = conn.getInputStream();
				jsonResult = read(is);
				Log.i("Post", jsonResult);
			} else {
				Log.i("错误响应�?", conn.getResponseCode()+"");
				throw (new Exception());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw (e);
		} catch (IOException e) {
			Log.e(LOG_HTTP_POST_ERROR, e.getLocalizedMessage());
			e.printStackTrace();
			throw (e);
		} finally {
			closeStream(is);
			closeStream(os);
		}
		return jsonResult;
	}
	
	/**
	 * 鎵цhttp GET璇锋眰锛岃幏鍙栭摼鎺ユ垚鍔熷悗鐨勮緭鍏ユ祦锛屼负浜嗕笅杞芥枃浠舵椂浣跨敤
	 * 
	 * @param url
	 *            璇锋眰鐨勬湇鍔�?�櫒绔痑pi鐨勯摼鎺�?
	 * @return 杩斿洖涓�涓緭鍏ユ祦�?�硅薄�?�炰�?
	 */
	public synchronized static InputStream getInputStream(String url) {
		InputStream is = null;
		try {
			HttpURLConnection conn = getHttpURLConnection(url);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() == 0) {
				is = conn.getInputStream();
			}
		} catch (MalformedURLException e) {
			Log.e(LOG_HTTP_GET_ERROR, e.getLocalizedMessage());
			e.printStackTrace();
		} catch (IOException e) {
			Log.e(LOG_HTTP_GET_ERROR, e.getLocalizedMessage());
			e.printStackTrace();
		}
		return is;
	}

	/**
	 * 鑾峰緱HttpURLConnection杩炴帴�?�炰�?
	 * 
	 * @param strURL
	 *            鏈嶅姟鍣ㄧapi鐨勯摼鎺�?
	 * @return 杩斿洖HttpURLConnection瀹炰�?
	 * @throws IOException
	 */
	private static HttpURLConnection getHttpURLConnection(String strURL)
			throws IOException {
		URL url = new URL(strURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(10000);
		conn.setReadTimeout(15000);
		return conn;
	}

	/**
	 * 浠庤緭鍏ユ祦涓鍑烘枃鏈俊鎭�?
	 * 
	 * @param is
	 *            鎸囧畾鐨勮緭鍏ユ�?
	 * @return
	 * @throws IOException
	 */
	private static String read(InputStream is) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[128];
		int len = 0;
		while ((len = is.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}

		String text = new String(out.toByteArray(), "utf-8");
		out.flush();
		closeStream(out);
		return text;
	}

	/**
	 * 灏嗗寘鍚玥ttp post璇锋眰鏁版嵁鐨刴ap锛岃В鏋愪负UrlEncoded鏍煎紡鐨勫瓧绗︿�?
	 * 
	 * @param params
	 *            鍖呭惈璇锋眰鍙傛暟鐨凪ap
	 * @return 杩斿洖瑙ｆ�?�鍚庣殑UrlEncoded鏍煎紡鐨勫瓧绗︿�?
	 */
	private static String toUrlEncodedFormParams(Map<String, Object> params) {
		StringBuffer strBuffer = new StringBuffer();
		Set<String> keySet = params.keySet();
		Iterator<String> i = keySet.iterator();
		while (i.hasNext()) {
			String key = i.next();
			String value = params.get(key).toString();
			strBuffer.append(key);
			strBuffer.append("=");
			strBuffer.append(value);
			if (i.hasNext()) {
				strBuffer.append("&");
			}
		}
		return strBuffer.toString();
	}
	
	/**
	 * 鎵цhttp post璇锋�?,鍙戦�佸鍚堣�?�鍗曟暟鎹紝濡備笂浼犳枃浠舵椂璋冪敤姝ゆ柟娉�
	 * 
	 * @param url
	 *            璇锋眰鐨勬湇鍔�?�櫒绔痑pi鐨勯摼鎺�?
	 * @param entity
	 *            鍖呭惈Multipart Form鏍煎紡鐨勮姹傚疄浣�?
	 * @return 杩斿洖Json鏍煎紡鐨勫搷搴旀暟鎹�?
	 */
	public synchronized static String postMultipartForm(String url,
			MultipartFormEntity entity) {
		String BOUNDARY = java.util.UUID.randomUUID().toString();
		String PREFIX = "--", LINEND = "\r\n";
		String MULTIPART_FROM_DATA = "multipart/form-data";

		String resultStr = null;
		HttpURLConnection conn = null;
		DataOutputStream outStream = null;
		try {
			conn = getHttpURLConnection(url);

			conn.setDoInput(true);// 鍏佽杈撳叆
			conn.setDoOutput(true);// 鍏佽杈撳嚭
			conn.setUseCaches(false);
			conn.setRequestMethod("POST"); // Post鏂瑰�?
			conn.setRequestProperty("connection", "keep-alive");
			conn.setRequestProperty("Charset", CHARSET);

			conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA
					+ ";boundary=" + BOUNDARY);

			outStream = new DataOutputStream(conn.getOutputStream());

			// 棣栧厛缁勬嫾鏂囨湰绫诲�?�鐨勫弬鏁�
			if (entity.getTextFields() != null) {
				StringBuilder sb = new StringBuilder();
				for (Map.Entry<String, Object> entry : entity.getTextFields()
						.entrySet()) {
					sb.append(PREFIX);
					sb.append(BOUNDARY);
					sb.append(LINEND);
					sb.append("Content-Disposition: form-data; name=\""
							+ entry.getKey() + "\"" + LINEND);
					sb.append("Content-Type: text/plain; charset=" + CHARSET
							+ LINEND);
					sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
					sb.append(LINEND);
					sb.append(entry.getValue().toString());
					sb.append(LINEND);
				}
				outStream.write(sb.toString().getBytes());
			}

			if (entity.getFileField() != null) {
				StringBuilder sb1 = new StringBuilder();
				sb1.append(PREFIX);
				sb1.append(BOUNDARY);
				sb1.append(LINEND);
				sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\""
						+ entity.getFileFieldName() + "\"" + LINEND);
				sb1.append("Content-Type: application/octet-stream; charset="
						+ CHARSET + LINEND);
				sb1.append(LINEND);
				outStream.write(sb1.toString().getBytes());

				InputStream is = new FileInputStream(entity.getFileField());
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, len);
				}

				closeStream(is);
				outStream.write(LINEND.getBytes());
			}

			// 璇锋眰缁撴潫鏍囍�?
			byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINEND).getBytes();
			outStream.write(end_data);
			outStream.flush();
			resultStr = read(conn.getInputStream());

			Log.d("httpPost", "url:" + url);
			Log.d("httpPost", "result:" + resultStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(outStream);
			conn.disconnect();
		}
		return resultStr;
	}

	/**
	 * 鍏抽棴IO娴�
	 * 
	 * @param obj
	 *            涓�涓緭鍏ユ祦鎴栬緭鍑烘祦瀵硅薄�?�炰�?
	 */
	public static void closeStream(Object obj) {
		if (obj != null && obj instanceof InputStream) {
			InputStream is = (InputStream) obj;
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (obj != null && obj instanceof OutputStream) {
			OutputStream os = (OutputStream) obj;
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static boolean isNetWokrConnected(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if(info!=null) {
			return info.isConnected();
		}
		return false;
	}
}
