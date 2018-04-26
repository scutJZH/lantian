package org.scut.controller.studentController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;
import org.scut.service.impl.studentImpl.StudentServiceImpl;
import org.scut.service.studentService.IStudentService;
import org.scut.service.studentService.IStudentInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

@Controller
@RequestMapping("/student")
public class StudentInfoController {
	@Resource
	private IStudentService studentService;
	private IStudentInfoService studentInfoService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getStudentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String studentId = (String)m.get("studentId");
		
		Map<String, Object> result = studentInfoService.getStudentInfo(studentId);
		
		return result;
	}
	
	@RequestMapping("/getPaperList")
	@ResponseBody
	public void getPaperList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);
		
		String studentId = (String) m.get("studentId");
		String submit = (String) m.get("submit");
		
		Map<String, Object> responseBody = this.studentService.getPaperList(studentId,submit);
		
		ParamsTransport.returnParams(response, responseBody);
			
	}
	
	@RequestMapping("/getPaperQuestions")
	@ResponseBody
	public void getPaperQuestions(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);
		
		String paperId = (String) m.get("paperId");
		
		Map<String, Object> responseBody = this.studentService.getPaperQuestions(paperId);
		
		ParamsTransport.returnParams(response, responseBody);
		
	}
	
	@RequestMapping("/uploadSolutions")
	@ResponseBody
	public void getPaperDetails(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8"); 

			
		    Iterator<String> fileNames = request.getFileNames();
		    

			
			Map<String, MultipartFile> files = new HashMap<>();			
			for(;fileNames.hasNext();) {
				
				String temp = fileNames.next();
				
//				System.out.print(temp);
//				System.out.println();
//				
				files.put(temp,request.getFile(temp));
			}
			String paperId = request.getParameter("paperId");
			String studentId = request.getParameter("studentId");
			String solutionList = request.getParameter("solutionList");						
			
			Gson gson = new Gson();
			List<Map<String, Object>>sList = gson.fromJson(solutionList, List.class);
			String reqLocation = (request.getSession().getServletContext().getRealPath("/")+"img\\");
			
			Map<String, Object> responseBody = this.studentService.uploadSolutions(studentId,paperId,sList,files,reqLocation);
			
			ParamsTransport.returnParams(response, responseBody);
	}
	
	@RequestMapping("/mine/modify")
	@ResponseBody
	public Map<String, Object> modifyInfo(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> result = null;
		
		String studentId = request.getParameter("studentId");
		List<MultipartFile> filesList = request.getFiles("img");
		String nickname = request.getParameter("nickname");
		String birthdayStr = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String schoolName = request.getParameter("schoolName");
		String filePath = request.getSession().getServletContext().getRealPath("/")+"img\\";
		
		if(filesList.size() > 1){
			String status = "-1";
			result = new HashMap<String, Object>();
			result.put("status", status);
			result.put("result", new HashMap<String, Object>());
		}else{
			result = studentInfoService.modifyStudentInfo(studentId, filesList, nickname, birthdayStr, sex, schoolName, filePath);
		}
		
		return result;
	}

		
	
	
	@RequestMapping("/getSchedules")
	@ResponseBody
	public void getSchedules(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);
		
		String studentId = (String) m.get("studentId");
		
		Map<String, Object> responseBody = this.studentService.getSchedules(studentId);
		
		ParamsTransport.returnParams(response, responseBody);
		
	}
	
	@RequestMapping("/addSchedule")
	@ResponseBody
	public void addSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);	
		
		Map<String, Object> responseBody = this.studentService.addSchedule(m);
		
		ParamsTransport.returnParams(response, responseBody);
		
	}

}