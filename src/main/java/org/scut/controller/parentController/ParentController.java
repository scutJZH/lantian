package org.scut.controller.parentController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
	
	@RequestMapping("/children/addchildren")
	public void addChildren(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
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
		
		String studentId = m.get("studentTelnumber");
		String id = m.get("id");
		
	}
	

}
