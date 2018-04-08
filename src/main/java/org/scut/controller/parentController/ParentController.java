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

	@RequestMapping("/children")
	public void getChildren(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
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
		
		String id = m.get("id");
		
		List<Map<String, String>> childrenList = parentService.getChildren(id);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("status", "1");
		result.put("result", childrenList);
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		out.flush();
		out.close();
		
		
	}
	
	@RequestMapping("/children/homework")
	public void getChilerenHomework(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
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
	
	
	@RequestMapping("/children/practice")
	public void getChildrenPractice(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
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
	 * status:0�û�δ��¼��-1�����˺Ų����ڣ�1�ɹ���-2���ݿⷢ������2�Ѿ����ڴ˹�ϵ
	 */
	@RequestMapping("/children/addchild")
	public void addChild(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
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
		
		String childTelnumber = m.get("childTelnumber");
		String id = null;
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			if (cookies[i].getName().equals("id")) {
				id = cookies[i].getValue();
			}
		}

		
		Map<String, Object> result = parentService.addChild(id, childTelnumber);
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		out.flush();
		out.close();
		
	}
	
	/**
	 * @return -1�������������0����δ��¼��1����ɹ�
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
