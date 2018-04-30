package org.scut.controller.teacherController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.teacherService.ITeacherInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/teacher")
public class TeacherInfoController {

	@Resource
	private ITeacherInfoService teacherInfoService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getParentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String teacherId = (String)m.get("teacherId");
		
		Map<String, Object> result = teacherInfoService.getTeacherInfo(teacherId);
		
		return result;
	}

	@RequestMapping("/mine/modify")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		Map<String, Object> result = null;
		
		String teacherId = request.getParameter("teacherId");
		List<MultipartFile> filesList = request.getFiles("img");
		String nickname = request.getParameter("nickname");
		String birthdayStr = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String name = request.getParameter("name");
		String schoolName = request.getParameter("schoolName");
		String filePath = request.getSession().getServletContext().getRealPath("/")+"img\\";
		
		if(filesList.size() > 1){
			String status = "-1";
			result = new HashMap<String, Object>();
			result.put("status", status);
			result.put("result", new HashMap<String, Object>());
		}else{
			result = teacherInfoService.modifyTeacherInfo(teacherId, filesList, nickname, birthdayStr, sex, name, schoolName, filePath);
		}
		
		return result;
		*/
		Map<String, Object> m = ParamsTransport.getParams(request);
		 String teacherId = (String)m.get("teacherId");
		 String imgBase64 = (String)m.get("img");
		 String nickname = (String)m.get("nickname");
		 String birthdayStr = (String)m.get("birthday");
		 String sex = (String)m.get("sex");
		 String name = (String)m.get("name");
		 String schoolName = (String)m.get("schoolName");
		 String filePath = request.getSession().getServletContext().getRealPath("/")+"img\\";
		 Map<String, Object> result = teacherInfoService.modifyTeacherInfo(teacherId, imgBase64, nickname, birthdayStr, sex, name, schoolName, filePath);
		 return result;
	}
}
