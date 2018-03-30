package org.scut.controller.publicController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
public class LoginController {
	

	@RequestMapping("/login.do")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		//读取json数据
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = "";
		while((input = br.readLine())!=""){
			sb.append(input);
		}
		
		//把json转为map
		Gson gson = new Gson();
		Map<String,Object> m = gson.fromJson(sb.toString(), Map.class);
		
		String telnumber = (String)m.get("telnumber");
		String password = (String)m.get("password");
		String token = (String)m.get("token");
		String userType = (String)m.get("userType");
		
		Map<String,Object> result = new HashMap<String,Object>();
		
		
	}

}
