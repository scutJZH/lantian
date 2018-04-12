package org.scut.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class ParamsTransport {
/**
 * 返回Map的key是和前端商量的参数名，value是参数值
 * @param request
 * @param response
 * @return Map<String, Object>
 * @throws IOException
 */
	public static Map<String, Object>getParams(HttpServletRequest request) throws IOException{
		request.setCharacterEncoding("utf-8");
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
		
		response.setCharacterEncoding("utf-8");
		
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		
		out.flush();
		out.close();
		
	}

}
