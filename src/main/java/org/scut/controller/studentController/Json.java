package org.scut.controller.studentController;

import java.util.HashMap;
import java.util.Map;

public class Json implements java.io.Serializable {

	private int status ;	

	private Object result ;


//	// 定义json对象
//	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * 格式化返回数据
	 * @return
	 */
public static Map<String, Object>  getJson (int status ,Object result) {
	Map<String , Object> map = new HashMap<String, Object>();
	
	map.put("result", result);
	map.put("status", status);
	return map;
}
	public static Map<String, Object>  getJson () {
	Map<String , Object> map = new HashMap<String, Object>();
	
	map.put("result","");
	map.put("status", 1);
	return map;
}
//	public static Object getJson(int status,Object result){
//		Map<String , Object> map = new HashMap<String, Object>();
//		map.put("status", 1);//true:成功 false；失败
//		map.put("result", result);//返回的result
//		return map;
//	}
//	/**返回成功的结果
//	 * @return
//	 */
//	public static Object getJson(){
//		Map<String , Object> map = new HashMap<String, Object>();
//		map.put("status", 1);
//		map.put("result", "");
//		return map;
////	}
//	/**成功，并有返回值
//	 * @param result
//	 * @return
//	 */
//	public static Object getJson(Object result){
//		Map<String , Object> map = new HashMap<String, Object>();
//		map.put("status",1);
//		map.put("result", result);
//		return map;
//	}
//	public static Object getJsonParmNull(){
//		Map<String , Object> map = new HashMap<String, Object>();
//		map.put("status", 0);
//		map.put("result", "");
//		return map;
//	}
	

	public Object getresult() {
		return result;
	}

	public void setresult(Object result) {
		this.result = result;
	}
	public int getStaus() {
		return status;
	}
	public void setStaus(int staus) {
		this.status = staus;
	}
}
