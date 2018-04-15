package org.scut.controller.studentController;

import java.util.HashMap;
import java.util.Map;

public class Json implements java.io.Serializable {

	private boolean success = false;

	private String error = "";

	private Object data = null;


//	// 定义json对象
//	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 格式化返回数据
	 * @return
	 */

	public static Object getJson(boolean success,String error,Object data){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", success);//true:成功 false；失败
		map.put("error", error);//错误信息
		map.put("data", data);//返回的data
		return map;
	}
	/**返回成功的结果
	 * @return
	 */
	public static Object getJson(){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("error", "");
		map.put("data", "");
		return map;
	}
	/**成功，并有返回值
	 * @param data
	 * @return
	 */
	public static Object getJson(Object data){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("error", "");
		map.put("data", data);
		return map;
	}
	public static Object getJsonParmNull(){
		Map<String , Object> map = new HashMap<String, Object>();
		map.put("success", false);
		map.put("error", "参数为空！");
		map.put("data", "");
		return map;
	}
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
