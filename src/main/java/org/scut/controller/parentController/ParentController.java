package org.scut.controller.parentController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.parentService.IParentService;
import org.scut.service.studentService.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
@RequestMapping("/parent")
public class ParentController {
	
	@Resource
	private IParentService parentService;
	@Resource
	private IStudentService studentService;

	
	
	
	
	@RequestMapping("/children/exam")
	public void getChildrenExams(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		
		while((input = br.readLine()) != null){
			sb.append(input);
		}
		
		Gson gson = new Gson();
		
		Map<String, String> m = gson.fromJson(sb.toString(), Map.class);
		
		String studentId = m.get("studentId");
		String subjectId = m.get("subjectId");
		
	}
	
	
	
	/**
	 * @return -1代表服务器错误，0代表未登录，1代表成功
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/children/removechild")
	public void removeChild(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		
		while((input = br.readLine()) != null){
			sb.append(input);
		}
		
		Gson gson = new Gson();
		
		Map<String, String> m = gson.fromJson(sb.toString(), Map.class);
		
		String childId = m.get("studentId");
		String id = null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("id")) {
				id = cookies[i].getValue();
			}
		}
		
		
	}
	
	@RequestMapping("/children/homework")
	public void getChildHomework(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		
		while((input = br.readLine()) != null){
			sb.append(input);
		}
		
		Gson gson = new Gson();
		
		Map<String, String> m = gson.fromJson(sb.toString(), Map.class);
		
		String childId = m.get("studentId");
		String subjectId = m.get("subjectId");
		
		
	}
	
	

}
