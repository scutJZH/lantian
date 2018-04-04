package org.scut.interceptor;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scut.model.TokenMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;

		while ((input = br.readLine()) != null) {
			sb.append(input);
		}

		Gson gson = new Gson();

		Map<String, String> m = gson.fromJson(sb.toString(), Map.class);

		String id = m.get("id");
		System.out.println(TokenMap.tokenMap);
		if (id != null && !id.equals("")) {
			if (TokenMap.tokenMap.containsKey(id)) {
				String token = null;
				Cookie[] cookies = request.getCookies();
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("token")) {
						token = cookies[i].getValue();
					}
				}
				if (token.equals(TokenMap.tokenMap.get(id))) {
					return true;
				}
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "0");
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
