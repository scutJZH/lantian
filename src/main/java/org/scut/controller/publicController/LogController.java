package org.scut.controller.publicController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.publicService.ILogService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LogController {
	
	@Resource
	private ILogService logService;
	
/**
 * 用户登录
 * @param request
 * @param response
 * @return
 * @throws IOException
 * status：1代表登陆成功，-1用户未注册，0为密码输入错误，-2为数据库连接发生错误
 */
	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		Map<String, Object> m = ParamsTransport.getParams(request);

		String userType = (String)m.get("userType");
		String telnumber = (String)m.get("telnumber");
		String password = (String)m.get("password");
		
		Map<String, Object> result = this.logService.login(userType, telnumber, password);
		
		String status = (String)result.get("status");
		if(status.equals("1")){
			Map<String, Object> userInfo = (Map<String, Object>)result.get("result");
			Cookie idCookie = new Cookie("id",(String)userInfo.get("id"));
			idCookie.setPath("/lantian");
			Cookie tokenCookie = new Cookie("token",(String)userInfo.get("token"));
			tokenCookie.setPath("/lantian");
			response.addCookie(tokenCookie);
			response.addCookie(idCookie);
			userInfo.remove("token");
		}
		
		return result;
		
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Map<String, Object> m = ParamsTransport.getParams(request);

		String id = (String) m.get("id");
		String token = null;
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length != 0) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookies[i].getName().equals("token")) {
					token = cookies[i].getValue();
					break;
				}
			}
		}
		
		Map<String, Object> result = null;
		
		if(token != null){
			result = logService.logout(id, token);
		}else{
			result = new HashMap<String, Object>();
			result.put("status", "1");
			
		}
		
		return result;
		
	}

}
