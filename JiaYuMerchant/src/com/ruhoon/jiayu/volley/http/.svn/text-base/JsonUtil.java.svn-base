package com.ruhoon.jiayu.volley.http;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * JSON闁轰胶澧�?畵浣烘喆閿濆棛锟借棄顔忛妷銉ュ緮缂侇偂绱槐婵嬫偨閵娿�?�鑹鹃悘蹇撴崍SON閻庢稒顨堥浣圭▔閼艰埖绁柟骞垮灪閸ㄦ岸骞愰崶褏鏆伴柣銊ュ椤曨喚鎸掗埄鍐ㄧ仐閻庣數顢婇挅鍑﹊st闁挎稑濂�?禍鎺�?矗婵傛獌p闁瑰瓨鐗曠�垫﹢宕ラ悳锟絘p闁汇劌鍤沬st
 * 
 * @author Rao Yuan
 */
public class JsonUtil<T> {
	private static final String LOG_JSON_ERROR = "com.imcore.common.util.JsonError";

	private static final String BYTE = "java.lang.Byte";
	private static final String INTEGER = "java.lang.Integer";
	private static final String SHORT = "java.lang.Short";
	private static final String LONG = "java.lang.Long";
	private static final String BOOLEAN = "java.lang.Boolean";
	private static final String CHAR = "java.lang.Character";
	private static final String FLOAT = "java.lang.Float";
	private static final String DOUBLE = "java.lang.Double";

	private static final String VALUE_BYTE = "byte";
	private static final String VALUE_INTEGER = "int";
	private static final String VALUE_SHORT = "short";
	private static final String VALUE_LONG = "long";
	private static final String VALUE_BOOLEAN = "boolean";
	private static final String VALUE_CHAR = "char";
	private static final String VALUE_FLOAT = "float";
	private static final String VALUE_DOUBLE = "double";

	/**
	 * 闁哄秷顫夊畵涔瞖y闁兼儳鍢茶ぐ鍥╃磼濞嗗繒鏆伴柣銊ュ煀son闁轰胶澧�?畵渚�鎯冮崟顐�?
	 * 
	 * @param json
	 *            缂備焦鐟ラ悾楣冩儍閸戭湙ON閻庢稒顨堥浣圭▔閿燂拷
	 * @param key
	 *            闁圭娲ら悾楣冩儍閸曨噮娓介柤鎯у槻瑜板洭宕愰崗鐓庮暡閻庣數鎳撶花鏌ユ儍閸掔渽y
	 * @return 閺夆晜鏌ㄥú鏍ㄧ▔閿熶粙鍤嬮悗娑欘殘椤戜焦绋夌拠褏�?夐悶娑栧妿閵囨岸寮界憴鍕ウ闁圭娲ら悾楣冩儍閸掔渽y闁圭鎷风欢閬嶅礆閹殿喗鐣遍柛濠勩�嬬槐婵嬫嚔�?�勬澘绲垮鎯扮簿鐟欙箓骞嬮弽褍绲洪柣銏㈠劶SON閻熸瑱绲鹃悗浠嬫煥濞嗘帩鍤栭柛鎺撶懆缁绘垿宕堕悙纰樻晞閻庢稒顨堥浣圭▔閿燂�?
	 */
	public static String getJsonValueByKey(String json, String key) {
		String value = "";
		try {
			JSONObject jo = new JSONObject(json);
			value = jo.getString(key);
		} catch (JSONException e) {
			Log.e(LOG_JSON_ERROR, e.getLocalizedMessage());
		}
		return value;
	}

	/**
	 * 閻忓繐妫欑�垫氨锟�?�姘ㄥ▓鎱桽ON閻庢稒顨堥浣圭▔閼艰埖绁柟骞垮灪閸ㄦ瓭ls闁圭娲ら悾楣冩儍閸曨叀顫﹂柣銊ュ閻ゅ嫭绗熺�ｎ亶鍤犻悹鐑囨嫹
	 * 
	 * @param json
	 *            缂備焦鐟ラ悾楣冩儍閸戭湙ON閻庢稒顨堥浣圭▔閿燂拷
	 * @param cls
	 *            闁圭娲ら悾鍓ф啺娴ｈ姤绁�?柟骞垮灪閸ㄦ岸鎯冮崟顐殸閻犵偐鍓濇晶宥囦沪閻愬灚鐣辩紒顐ヮ嚙閻庣álass閻庡湱鍋樼欢锟�
	 * @return 閺夆晜鏌ㄥú鏈縧s闁圭娲ら悾鍓х尵鐠囪尙锟界兘鎯冮崟顐殸閻犵偐锟藉磭鏉藉〒姘炬嫹闁稿繑婀归懙鎴︽儍閸曨偆鎽熸繛鍫濆悁缁楀�?son闁轰胶澧�?畵渚�鏌ㄩ纭锋嫹閻庣敻锟芥稓顏卞☉鎿勬嫹椤曨喗鎯旈敓锟�?
	 */
	public static <T> T toObject(String json, Class<T> cls) {
		T obj = null;
		try {
			JSONObject jsonObject = new JSONObject(json);
			obj = cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				if (Modifier.isFinal(field.getModifiers())
						|| Modifier.isPrivate(field.getModifiers())) {
					continue;
				}
				try {
					String key = field.getName();
					if (jsonObject.get(key) == JSONObject.NULL) {
						field.set(obj, null);
					} else {
						Object value = getValue4Field(jsonObject.get(key),
								jsonObject.get(key).getClass().getName());
						field.set(obj, value);
					}
				} catch (Exception e) {
					field.set(obj, null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG_JSON_ERROR, e.getLocalizedMessage());
		}
		return obj;
	}

	/**
	 * 闁硅泛锕ョ�垫氨锟�?�姘ㄥ▓鎴狅拷鐢殿攰閽栧埣rginalValue閺夌儐鍓氬畷鏌ュ箣閹摽peName闁圭娲ら悾楣冩儍閸曨叀顫﹂柛銊ヮ儑濞堟垹锟界數顢婇挅锟�?
	 * 
	 * @param orginalValue
	 *            閻庣數顢婇挅鍕捶閵娿劍绁柟璇℃�?�缁狅綁宕滃鍥ㄧ暠闁稿鎷�?
	 * @param fieldType
	 *            閻熸洑娴囧ù鍡涘箲閵忋垺鐣辩紒顐ヮ嚙閻庣兘宕ュ鍥嗭拷
	 * @return
	 */
	private static Object getValue4Field(Object orginalValue, String typeName) {
		Log.i("Json_Util", typeName);
		Object value = orginalValue.toString();
		if (typeName.equals(BYTE) || typeName.equals(VALUE_BYTE)) {
			value = Byte.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(INTEGER) || typeName.equals(VALUE_INTEGER)) {
			value = Integer.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(SHORT) || typeName.equals(VALUE_SHORT)) {
			value = Short.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(LONG) || typeName.equals(VALUE_LONG)) {
			value = Long.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(BOOLEAN) || typeName.equals(VALUE_BOOLEAN)) {
			value = Boolean.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(CHAR) || typeName.equals(VALUE_CHAR)) {
			value = Character.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(FLOAT) || typeName.equals(VALUE_FLOAT)) {
			value = Float.class.cast(orginalValue);
			return value;
		}
		if (typeName.equals(DOUBLE) || typeName.equals(VALUE_DOUBLE)) {
			value = Double.class.cast(orginalValue);
			return value;
		}
		return value;
	}

	/**
	 * 閻忓繐妫欑�垫氨锟�?�姘ㄥ▓鎱桽ON閻庢稒顨堥浣圭▔閼艰埖绁柟骞垮灪閸ㄦ岸宕犻崨顓熷創cls闁圭娲ら悾楣冩儍閸曨叀顫﹂柛銊ヮ儑濞堟垹锟藉湱鍋樼紞�?�拷鐢殿攰閽栧嚛ist闂傚棗妫楅幃锟�
	 * 
	 * @param json
	 *            缂備焦鐟ラ悾楣冩儍閸戭湙ON閻庢稒顨堥浣圭▔閿燂拷
	 * @param cls
	 *            闁圭娲ら悾鍓ф啺娴ｈ姤绁�?柟骞垮灪閸ㄦ岸鎯冮崟顐殸閻犵偐鍓濇晶宥囦沪閻愬灚鐣辩紒顐ヮ嚙閻庣álass閻庡湱鍋樼欢锟�
	 * @return 閺夆晜鏌ㄥú鏍ㄧ▔閿熶粙鍤婰ist闂傚棗妫楅幃搴ㄦ晬鐏炶棄寰撳☉鎿冨幖鐎垫﹢宕ラ悳顪筼n濞戞搩鍘惧▓鎴﹀极閻�?牆绁﹂柛蹇撳暟缁�宀勫箥閿熺瓔鍤犻幖瀛樻⒒濞堟垹锟藉湱鍋樼紞瀣拷鐢殿攰閽栧嫮锟藉湱鍋樼欢锟�?
	 */
	public static <T> List<T> toObjectList(String json, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			List<String> jsonStrList = toJsonStrList(json);
			for (String jsonStr : jsonStrList) {
				T obj = toObject(jsonStr, cls);
				list.add(obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.e(LOG_JSON_ERROR, e.getLocalizedMessage());
		}
		return list;
	}

	/**
	 * 閻忓繐妫旂粩瀛樼▔椤忓懏娈剁紓浣稿閻庣son閻庢稒顨堥浣圭▔閼艰埖绁柟骞垮灪閸ㄦ岸宕犻崨顓熷創閻庢稒浜緎on閻庢稒顨堥浣圭▔閼碱剚鐣盠ist闂傚棗妫楅幃锟�
	 * 
	 * @param json
	 *            缂備焦鐟ラ悾楣冩儍閸戭湙ON閻庢稒顨堥浣圭▔閿燂拷
	 * @return 閺夆晜鏌ㄥú鏍ㄧ▔閿熶粙鍤婰ist闂傚棗妫楅幃搴ㄦ晬鐏炶棄鐦堕柛姘煎亗缁斿绱掗崟顐ゆ憻缂佹缂氱憰鍡涙晬鐏炵瓔鍤犻幖瀛樻煣缁剛绱掑▎蹇曟毎闁告鍠庨�?�濂怱ON闁轰胶澧�?畵渚�宕�?崨顓炲笚缂佽京濮峰▓鎴狅拷娑欘殘椤戜焦绋夐幓鎺曞煂鐎殿噯鎷�
	 */
	public static List<String> toJsonStrList(String json) {
		List<String> strList = new ArrayList<String>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				String jsonStr = jsonArray.getString(i);
				strList.add(jsonStr);
			}
		} catch (JSONException e) {
			Log.e(LOG_JSON_ERROR, e.getMessage());
		}
		return strList;
	}

	/**
	 * 閻忓繐鏀瑂on閻庢稒顨堥浣圭▔閼艰埖绁柟璇℃�?�鐠愮兂ap
	 * 
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = null;
		try {
			JSONObject jo = new JSONObject(json);
			map = convertJSONObjectToMap(jo);
		} catch (Exception e) {
			Log.e(LOG_JSON_ERROR, e.getMessage());
		}
		return map;
	}

	/**
	 * 閻忓繐鏀瑂on閻庢稒顨堥浣圭▔閼艰埖绁柟璇℃�?�鐠愮喖宕犻崨顓熷創Map闁汇劌鍤沬st闂傚棗妫楅幃锟�
	 * 
	 * @param json
	 * @return
	 */
	public static List<Map<String, Object>> toMapList(String json) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jo = jsonArray.getJSONObject(i);
				Map<String, Object> map = convertJSONObjectToMap(jo);
				mapList.add(map);
			}
		} catch (JSONException e) {
			Log.e(LOG_JSON_ERROR, e.getMessage());
		}
		return mapList;
	}

	/**
	 * 閻忓繐妫旂粩瀛樼▔閻欑巿ONObject閻庣數顢婇挅鍕姜椤掍礁搴婂☉鎾剁槻ap
	 * 
	 * @param jo
	 * @return
	 * @throws JSONException
	 */
	private static Map<String, Object> convertJSONObjectToMap(JSONObject jo)
			throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject newJo = mergeJsonNodes(jo);

		JSONArray names = newJo.names();
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			Object value = newJo.get(key);
			if ((value != null) && (!value.toString().equals(""))
					&& (!value.toString().equals("null"))) {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 閻忓繐鎹圫ON閻庣數顢婇挅鍕儍閸曨垱濮滃☉鎿勬嫹濡胶锟芥稒鍔楃划銊╂�?�闁稓鐟㈠☉鎿勬嫹濡胶绱掗幘璺轰化闁告艾鐗嗛懟锟�?
	 * 
	 * @param oldJo
	 *            闁告牕鎳庨幆鍫ユ閻愭壆顏遍梻鍐ㄥ缁劑鎮欓崷顓熺暠Json閻庣數顢婇挅锟�
	 * @return 閺夆晜鏌ㄥú鏍触閸繆�?�欏☉鏂款儏閹鎯冮崟鍓佺闁告瑯浜滅�垫﹢宕ラ锛勵伇闂傚啫澧庣划銊╂�?�閸︻厽鐣盝son閻庣數顢婇挅锟�
	 */
	private static JSONObject mergeJsonNodes(JSONObject oldJo)
			throws JSONException {
		JSONObject newJo = oldJo;
		JSONArray names = newJo.names();
		List<String> delKeys = new ArrayList<String>(); // 鐎垫澘鎳庨崹褰掓⒔閵堝洦鐣遍梻鍫㈠仒缁旀挳姊奸崜浣烘尝闁绘劕�?卞▓鎱梥on閻庣數顢婇挅鍕儍閸掔渽y

		// 闁瑰灚鍎抽崵顓㈡閿熺瓔娓介柛姘墕閼荤喖鎯冮崟顐ゆ憤缂備焦鎸鹃崑锝夋儍閸掔渽y
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			if (newJo.optJSONObject(key) != null) {
				delKeys.add(key);
			}
		}
		// 闁告艾鐗嗛懟鐔煎箥閹冪厒闁汇劌瀚悺娆戠磼閹捐泛浠☉鎾崇凹缁�?挳姊奸崜浣烘尝闁绘劗娅㈢槐婵嬬嵁鐠哄搫鐏╅梻鍕╁�曠敮顐�?礂閸垺鐣遍悗娑欏姉缁劑鎮欓敓锟�
		for (String key : delKeys) {
			JSONObject subJo = newJo.getJSONObject(key);
			subJo = mergeJsonNodes(subJo); // 闂侇偅甯掔紞濠囧极鐎靛憡鍊為悗娑欏姉缁劑鎮欓崷顓熺暠闁圭鎷峰﹢浣猴拷娑欏姉缁劑鎮欓敓锟�?
			newJo = merge(newJo, subJo);
			newJo.remove(key);
		}
		return newJo;
	}

	/**
	 * 闁告艾鐗嗛懟鐔哥▔閵堝嫰鍤婮SON閻庣數顢婇挅锟�
	 * 
	 * @param jo1
	 * @param jo2
	 * @return 閺夆晜鏌ㄥú鏍触閸繆�?�欏☉鏂款儏閹鎯冮崙顪橭N閻庣數顢婇挅锟�
	 */
	private static JSONObject merge(JSONObject jo1, JSONObject jo2)
			throws JSONException {
		JSONObject newJo = jo1;
		JSONArray names = jo2.names();
		for (int i = 0; i < names.length(); i++) {
			String key = names.getString(i);
			newJo.put(key, jo2.get(key));
		}
		return newJo;
	}

	/**
	 * 闁告帇鍊栭弻鍥ㄧ▔閿熶粙鍤婮SON閻庢稒顨堥浣圭▔閸欏笑闁告熬闄勫Σ鍝ョ矚閻戞ɑ娈堕柟鐧告�?
	 * 
	 * @param json
	 * @return
	 */
	public static boolean isJsonNull(String json) {
		if (json == null || json.equals("") || json.equals("null")
				|| json.equals("{}") || json.equals("[]")) {
			return true;
		} else {
			return false;
		}
	}
}