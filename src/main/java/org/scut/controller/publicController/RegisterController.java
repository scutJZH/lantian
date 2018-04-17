package org.scut.controller.publicController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.imageio.spi.RegisterableService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.publicService.IRegisterService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Resource
	private IRegisterService registerService;
	/**
	 * ������֤��ע��
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * status Ϊ-1�����˺��Ѿ���ע�ᣬΪ1������Խ�����֤����֤��Ϊ-2����������������ݿⷢ������
	 */
	@RequestMapping("/sendverifyCode")
	@ResponseBody
	public Map<String, Object> regist(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String telnumber = (String)m.get("telnumber");
		String userType = (String)m.get("userType");
		
		Map<String, Object> result = registerService.sendVerifyCode(userType, telnumber);

		return result;
	
	}
	
	/**
	 * ��֤��֤��
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * status��-3������֤����ڣ�-1Ϊ��֤�벻ƥ�䣬-2Ϊ���ݿ����ӷ�������1Ϊ��֤�ɹ���ֱ�ӽ����¼״̬
	 */	
	@RequestMapping("/verify")
	@ResponseBody
	public Map<String, Object> verify(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String telnumber = (String)m.get("telnumber");
		String nickname = (String)m.get("nickname");
		String password = (String)m.get("password");
		String userType = (String)m.get("userType");
		String verifyCode = (String)m.get("verifyCode");
		
		Map<String, Object> result = registerService.verify(userType, telnumber, nickname, password, verifyCode);
		String status = (String)result.get("status");
		if(status.equals("1")){
			Map<String, Object> userInfo = (Map<String, Object>)result.get("result");
			Cookie idCookie = new Cookie("id",(String)userInfo.get("id"));
			Cookie tokenCookie = new Cookie("token",(String)userInfo.get("token"));
			response.addCookie(tokenCookie);
			response.addCookie(idCookie);
			userInfo.remove("token");
		}
		
		return result;
		
		
	}

}
