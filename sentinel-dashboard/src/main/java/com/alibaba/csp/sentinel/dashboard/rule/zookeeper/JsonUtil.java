package com.alibaba.csp.sentinel.dashboard.rule.zookeeper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析json数据工具类
 * fujian
 */
public class JsonUtil {

	private static JsonObject jsonObj;
	private static JsonParser parse;
	private static Gson gson;

	static {
		if (jsonObj == null) {
			jsonObj = new JsonObject();
		}
		if (parse == null) {
			parse = new JsonParser();
		}
		if (gson == null) {
			gson = new Gson();
		}
	}

    /**
     * json转成对象
     * @param jsonStr
     * @param node
     * @param cls
     * @return
     */
	public static Object jsonToBean(String jsonStr, String node, Class<?> cls) {
		Object bean = null;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStr).getAsJsonObject();
		if (gson != null) {
			bean = gson.fromJson(jsonObj.get(node), cls);
		}
		return bean;
	}

    /**
     * json转list
     * @param jsonStr
     * @param node
     * @param typeOfT
     * @param <T>
     * @return
     */
	public static <T> List<T> jsonToListBean(String jsonStr, String node, Type typeOfT) {
		List<T> beanList = new ArrayList<T>();
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStr).getAsJsonObject();
		if (gson != null) {
			beanList = gson.fromJson(jsonObj.get(node), typeOfT);
		}
		return beanList;
	}


    /**
     * 根据node节点，获取node节点内json
     * @param jsonStrSrc
     * @param node
     * @return
     */
	public static String getJsonStrByNode(String jsonStrSrc, String node) {
		String jsonStr = null;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStrSrc).getAsJsonObject();
		if (gson != null) {
			jsonStr = jsonObj.get(node).toString();
		}
		return jsonStr;
	}

    /**
     * json转对象
     * @param jsonStr
     * @param cls
     * @return
     */
	public static Object jsonToBean(String jsonStr, Class<?> cls) {
		Object bean = null;
		gson = new GsonBuilder().create();
		if (gson != null) {
			bean = gson.fromJson(jsonStr, cls);
		}
		return bean;
	}

	/**
	 * 根据node节点，解析json字符串为List对象
	 *
	 * @param jsonStr json字符串
	 * @return Object Bean对象
	 *
	 */
	public static <T> List<T> jsonToListBean(String jsonStr, Type typeOfT) {
		List<T> beanList = new ArrayList<T>();
		gson = new GsonBuilder().create();
		if (gson != null) {
			beanList = gson.fromJson(jsonStr, typeOfT);
		}
		return beanList;
	}

	/**
	 * 生成带有泛型类型的对象
	 *
	 * @param jsonStr json字符串
	 * @param typeOfT 对象Class
	 * @return Object Bean对象
	 *
	 */
	public static Object jsonToBean(String jsonStr, Type typeOfT) {
		gson = new GsonBuilder().create();
		if (gson != null) {
			return gson.fromJson(jsonStr, typeOfT);
		}
		return null;
	}
	/**
	 * 对象转json
	 *
	 * @param bean
	 * @return
	 */
	public static String beanToJson(Object bean) {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(bean);
	}

	/**
	 * 对象转json[1.支持时间格式化转化为yyyy-MM-dd HH:mm:ss  2. json属性key必存在]
	 * @return
	 */
	public static String bean2json(Object o) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").serializeNulls().setPrettyPrinting().create();
		return gson.toJson(o);
	}

	/**
	 * 根据node节点，删除node节点内json
	 *
	 * @return
	 *
	 */
	public static String removeJsonStrByNodes(String jsonStrSrc, String... nodes) {
		String jsonStr = null;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStrSrc).getAsJsonObject();
		if (gson != null) {
			for (String node : nodes) {
				jsonObj.remove(node);
			}
			jsonStr = jsonObj.toString();
		}
		return jsonStr;
	}

	/**
	 * 根据node节点，添加node节点内json
	 *
	 *
	 */
	public static String addJsonStrByNode(String jsonStrSrc, String jsonAddValueStr, String node) {
		String jsonStr = null;
		JsonObject jsonAddValueObj;
		gson = new GsonBuilder().create();
		jsonObj = parse.parse(jsonStrSrc).getAsJsonObject();
		jsonAddValueObj = parse.parse(jsonAddValueStr).getAsJsonObject();
		if (gson != null) {
			jsonObj.add(node, jsonAddValueObj);
			jsonStr = jsonObj.toString();
		}
		return jsonStr;
	}

}
