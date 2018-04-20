package org.scut.controller.studentController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.Request;
import org.scut.service.impl.studentImpl.StudentServiceImpl;
import org.scut.service.publicService.IGetMyInfoService;
import org.scut.service.studentService.IStudentService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/student")
public class StudentInfoController {
	@Resource
	private IGetMyInfoService getMyInfoService;
	@Resource
	private IStudentService studentService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getParentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String parentId = (String)m.get("studentId");
		
		Map<String, Object> result = getMyInfoService.getMyInfo("1", parentId);
		
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
		System.out.println(request.getMultipartContentType(null));
		try {
			
			List<MultipartFile> files =  request.getFiles("img");
			String paperId = request.getParameter("paperId");
			String studentId = request.getParameter("studentId");
			String solutionList = request.getParameter("solutionList");
			
			System.out.println(paperId);
			System.out.println();
			
			System.out.println(studentId);
			System.out.println();
			
			System.out.println(solutionList);
			System.out.println();
			
			//this.studentService.uploadSolutions();
			
			for (MultipartFile file : files) {
				if(!file.isEmpty()) {
					File img = new File(request.getSession().getServletContext().getRealPath("/")+"img\\"+file.getOriginalFilename());
					FileOutputStream fos = new FileOutputStream(img);
					BufferedOutputStream bos = new BufferedOutputStream(fos);
					bos.write(file.getBytes());
					bos.close();
					fos.close();
				}
			}
		}catch (Exception e) {

		}
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
