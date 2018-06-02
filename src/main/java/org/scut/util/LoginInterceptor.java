package org.scut.util;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.util.GlobalVar;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		String id = null;
		String token = null;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length!=0){
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("token")) {
					token = cookies[i].getValue();
				}
				if (cookies[i].getName().equals("id")) {
					id = cookies[i].getValue();
				}
				System.out.println(cookies[i].getName());
			}
		}else{
			System.out.println("cookieΪ��");
		}
		if (id != null && !id.equals("")&&token!=null&&!token.equals("")) {
			if (GlobalVar.tokenMap.containsKey(id)&&token.equals(GlobalVar.tokenMap.get(id))) {
					return true;
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "0");
		result.put("result", "");
		
		Gson gson = new Gson();
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		out.flush();
		out.close();
		return false;

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
