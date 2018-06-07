package org.scut.controller.teacherController;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.teacherService.IClassManagementService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher/class")
public class ClassManagementController {

	@Resource
	private IClassManagementService classManagementService;

	@RequestMapping("/createclass")
	@ResponseBody
	public Map<String, Object> createClass(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> map = ParamsTransport.getParams(request);

		String teacherId = (String) map.get("teacherId");
		String className = (String) map.get("className");
		String schoolId = (String) map.get("schoolId"); 
		//modify
		schoolId="05394a70-afa6-41a4-b928-d19df7dcce5e";
		String grade = (String) map.get("grade");
		String headPic = (String) map.get("headPic");

		Map<String, Object> result = classManagementService.createClass(teacherId, className, schoolId, grade, headPic);
		
		return result;
	}
	
	@RequestMapping("/invitestudent")
	@ResponseBody
	public Map<String, Object> inviteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String teacherId = (String)map.get("teacherId");
		String classId = (String)map.get("classId");
		String studentTelnumber = (String)map.get("studentTelnumber");
		
		Map<String, Object> result = classManagementService.inviteStudent(teacherId, classId, studentTelnumber);
		
		return result;
	}
	
	@RequestMapping("/inviteteacher")
	@ResponseBody
	public Map<String, Object> inviteTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String teacherId = (String)map.get("teacherId");
		String classId = (String)map.get("classId");
		String teacherTelnumber = (String)map.get("teacherTelnumber");
		
		Map<String, Object> result = classManagementService.inviteTeacher(teacherId, classId, teacherTelnumber);
		
		return result;
	}
	
	@RequestMapping("/deleteclass")
	@ResponseBody
	public Map<String, Object> deleteClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String classId = (String)map.get("classId");
		String teacherId = (String)map.get("teacherId");
		
		Map<String, Object> result = classManagementService.deleteClass(classId, teacherId);
	
		return result;
	}
	
	@RequestMapping("/removestudent")
	@ResponseBody
	public Map<String, Object> removeStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String teacherId = (String)map.get("teacherId");
		String studentId = (String)map.get("studentId");
		String classId = (String)map.get("classId");
		
		Map<String, Object> result = classManagementService.removeStudent(teacherId, studentId, classId);
	
		return result;
	}

	@RequestMapping("/removeteacher")
	@ResponseBody
	public Map<String, Object> removeTeacher(HttpServletRequest request, HttpServletResponse response) throws Exception{

		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String teacherId = (String)map.get("teacherId");
		String headTeacherId = (String)map.get("headTeacherId");
		String classId = (String)map.get("classId");
		
		Map<String, Object> result = classManagementService.removeTeacher(headTeacherId, classId, teacherId);
		
		return result;
	}
	
	@RequestMapping("/getclasses")
	@ResponseBody
	public Map<String, Object> getClasses(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String teacherId = (String)map.get("teacherId");
		
		Map<String, Object> result = classManagementService.getClasses(teacherId);
		
		return result;
		
	}
	
	@RequestMapping("/quitclass")
	@ResponseBody
	public Map<String, Object> quitClass(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> map = ParamsTransport.getParams(request);
		
		String teacherId = (String)map.get("teacherId");
		String classId = (String)map.get("classId");
		
		Map<String, Object> result = classManagementService.quitClass(teacherId, classId);
		
		return result;
	}
}
