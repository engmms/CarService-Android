package com.ruhoon.jiayu.asynctask.http;

import java.util.Map;

import com.ruhoon.jiayu.volley.http.HttpMethod;


/**
 * Http璇锋眰鏁版嵁杞戒綋锛屽寘鎷綉缁滃湴鍧�url,浠ュ強璇锋眰鎵�闇�鐨刦orm鍙傛�?
 * 
 * @author Li Bin
 */
public class RequestEntity {
	private String url;
	private int method = HttpMethod.POST;
	private Map<String, Object> textFields;

	/**
	 * 鏋勯�犲嚱鏁�?
	 */
	public RequestEntity() {
	}

	/**
	 * 鏋勯�犲嚱鏁�?
	 * 
	 * @param url
	 *            鎸囧畾鐨勮姹傞摼鎺ュ湴鍧�
	 */
	public RequestEntity(String url) {
		this.url = url;
	}

	/**
	 * 鏋勯�犲嚱鏁�?
	 * 
	 * @param url
	 *            鎸囧畾鐨勮姹傞摼鎺ュ湴鍧�
	 * @param textFields
	 *            璇锋眰鍙傛暟锛岀函鏂囨湰锛屼笉鍖呭惈鏂囦欢鍩�?
	 */
	public RequestEntity(String url, Map<String, Object> textFields) {
		this.url = url;
		this.textFields = textFields;
	}

	/**
	 * 鏋勯�犲嚱鏁�?
	 * 
	 * @param url
	 *            鎸囧畾鐨勮姹傞摼鎺ュ湴鍧�
	 * @param method
	 *            鎸囧畾鐨凥TTP璇锋眰鏂规硶
	 * @param textFields
	 *            璇锋眰鍙傛暟锛岀函鏂囨湰锛屼笉鍖呭惈鏂囦欢鍩�?
	 */
	public RequestEntity(String url, int method, Map<String, Object> textFields) {
		this.url = url;
		this.method = method;
		this.textFields = textFields;
	}

	/**
	 * 鑾峰彇缃戠粶鍦板潃url
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 璁剧疆缃戠粶鍦板潃url
	 * 
	 * @return
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 鑾峰緱璇锋眰鏂规�?
	 * 
	 * @return
	 */
	public int getMethod() {
		return method;
	}

	/**
	 * 璁剧疆璇锋眰鏂规�?
	 * 
	 * @param method
	 */
	public void setMethod(int method) {
		this.method = method;
	}

	/**
	 * 鑾峰彇璇锋眰鍙傛�?
	 * 
	 * @return
	 */
	public Map<String, Object> getTextFields() {
		return textFields;
	}

	/**
	 * 璁剧疆璇锋眰鍙傛�?
	 * 
	 * @param textFields
	 */
	public void setTextFields(Map<String, Object> textFields) {
		this.textFields = textFields;
	}

}
