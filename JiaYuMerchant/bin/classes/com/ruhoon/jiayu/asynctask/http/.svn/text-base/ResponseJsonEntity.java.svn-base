package com.ruhoon.jiayu.asynctask.http;

/**
 * JSON鏍煎紡http鍝嶅簲�?�炰綋缁撴�?��?�氫箟锛屾寜鐓у悗鍙版帴鍙ｈ鏄庯紝鎶婂瓧娈典俊鎭皝瑁呬负璇ョ被鐨勫疄渚�?
 * 
 * @author Li Bin
 */
public class ResponseJsonEntity {
	// 鍝嶅簲鐘舵�侊細200琛ㄧず鎴愬姛
	private int status;

	// 鍝嶅簲鏁版嵁瀹炰綋鐨刯son瀛楃涓插舰寮�
	private String data;

	// 褰搒tatus涓嶄�?200鏃讹紝閿欒鎻忚�?
	private String message;

	// 绉佹湁鏋勯�犲嚱鏁帮紝閬垮厤寮�鍙戜汉鍛樼洿鎺ユ瀯閫犲疄渚�
	private ResponseJsonEntity() {
	}

	/**
	 * 鏍规嵁缁欏畾鐨刯son瀛楃涓叉�?�閫犺绫诲疄渚嬶紝鎶妀son涓殑鏁版嵁缁撴瀯鏄犲皠鍒拌�?�炰緥涓�?
	 * 
	 * @param json
	 * @return
	 */
	public static ResponseJsonEntity fromJSON(String json) {
		ResponseJsonEntity entity = new ResponseJsonEntity();
		entity.status = Integer.parseInt(JsonUtil.getJsonValueByKey(json,"code"));
		if (entity.status == 0) {
			entity.data = JsonUtil.getJsonValueByKey(json, "data");
			entity.message = JsonUtil.getJsonValueByKey(json, "msg");
		} else {
			entity.message = JsonUtil.getJsonValueByKey(json, "msg");
		}

		return entity;
	}

	public int getStatus() {
		return this.status;
	}

	public String getData() {
		return this.data;
	}

	public String getMessage() {
		return this.message;
	}
}
