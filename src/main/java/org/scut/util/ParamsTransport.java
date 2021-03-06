package org.scut.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ParamsTransport {
/**
 * ����Map��key�Ǻ�ǰ�������Ĳ�������value�ǲ���ֵ
 * @param request
 * @param response
 * @return Map<String, Object>
 * @throws IOException
 */
	public static Map<String, Object>getParams(HttpServletRequest request) throws IOException{
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		while((input = br.readLine()) != null){
			sb.append(input);
		}
		
		Gson gson = new Gson();
		Map<String, Object> paramsMap = gson.fromJson(sb.toString(), HashMap.class);
		return paramsMap;
	}
	
	public static void returnParams(HttpServletResponse response,Map<String, Object> result) throws IOException{
		
		response.setCharacterEncoding("UTF-8"); 
		response.setContentType("text/json");
		
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		
		out.flush();
		out.close();
		
	}
	
	public static List<Map<String, Object>>getParamsList(HttpServletRequest request) throws IOException{
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		while((input = br.readLine()) != null){
			sb.append(input);
		}
		
		Gson gson = new Gson();
		List<Map<String, Object>> paramsMapList = gson.fromJson(sb.toString(), List.class);
		return paramsMapList;
	}
	
	public static String listToJson(List list){
		Gson gson = new Gson();
		String json = gson.toJson(list);
		return json;
	}
	
	public static List stringToList(String json){
		Gson gson = new Gson();
		List list = gson.fromJson(json, ArrayList.class);
		return list;
	}

}
