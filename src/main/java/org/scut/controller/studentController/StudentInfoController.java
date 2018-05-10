package org.scut.controller.studentController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.studentService.IStudentService;
import org.scut.service.studentService.IStudentInfoService;
import org.scut.util.GlobalVar;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/student")
public class StudentInfoController {
	@Resource
	private IStudentService studentService;
	@Resource
	private IStudentInfoService studentInfoService;

	@RequestMapping("/student/mine")
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
	public Map<String, Object> getPaperQuestions(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);
		
		String paperId = (String) m.get("paperId");
		
		Map<String, Object> responseBody = this.studentService.getPaperQuestions(paperId);
		
		return responseBody;
		
	}
	
	@RequestMapping("/uploadSolutions")
	@ResponseBody

	public  Map<String, Object> getPaperDetails(HttpServletRequest request, HttpServletResponse response) throws IOException{
		    
		    request.setCharacterEncoding("UTF-8");
		    
		    Map<String, Object> m  = ParamsTransport.getParams(request);
						
			String paperId = (String) m.get("paperId");
			String studentId = (String) m.get("studentId");
			List<Map<String, Object>> solutionList =  (List<Map<String, Object>>) m.get("solutionList");
						
			Map<String, Object> responseBody = this.studentService.uploadSolutions(studentId,paperId,solutionList);
			
			return responseBody;
	}
	
	@RequestMapping("/student/mine/modify")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
	/*	Map<String, Object> result = null;
		
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
		*/
		Map<String, Object> m = ParamsTransport.getParams(request);
		 String studentId = (String)m.get("studentId");
		 String imgBase64 = (String)m.get("img");
		 String nickname = (String)m.get("nickname");
		 String birthdayStr = (String)m.get("birthday");
		 String sex = (String)m.get("sex");
		 String schoolId = (String)m.get("schoolId");
		 String filePath = this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.picPath;
		 Map<String, Object> result = studentInfoService.modifyStudentInfo(studentId, imgBase64, nickname, birthdayStr, sex, schoolId, filePath);
		 return result;
	}

		
	
	@RequestMapping("/getSchedules")
	@ResponseBody
	public Map<String, Object> getSchedules(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);
		
		String studentId = (String) m.get("studentId");
		
		Map<String, Object> responseBody = this.studentService.getSchedules(studentId);
		
		return responseBody;
		
	}
	
	@RequestMapping("/addSchedule")
	@ResponseBody
	public Map<String, Object> addSchedule(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		Map<String, Object> m  = ParamsTransport.getParams(request);	
		
		Map<String, Object> responseBody = this.studentService.addSchedule(m);
		
		return responseBody;
		
	}
	
	@RequestMapping("/addclass")
	@ResponseBody
	public Map<String, Object> addclass (HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object>map=ParamsTransport.getParams(request);
		String classId=(String)map.get("classId");
		String studentId=(String)map.get("studentId");
		int status =studentService.addclass(classId, studentId);
		return Json.getJson(status,"");
		
		
	}

}