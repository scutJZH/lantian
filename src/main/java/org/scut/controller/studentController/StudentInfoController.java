package org.scut.controller.studentController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.studentService.IStudentInfoService;
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
	private IStudentInfoService studentInfoService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getStudentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String studentId = (String)m.get("studentId");
		
		Map<String, Object> result = studentInfoService.getStudentInfo(studentId);
		
		return result;
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

}
