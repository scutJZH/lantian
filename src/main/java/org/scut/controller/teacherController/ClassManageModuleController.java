package org.scut.controller.teacherController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.service.teacherService.IClassManageModuleService;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ClassManageModuleController {
	@Resource
	private IClassManageModuleService classManageModuleService;
	@Resource
	private ITeacherCourseModuleService teacherCourseModuleService;
	//获取班级列表
	@RequestMapping(value="/getClassListTwo")
	@ResponseBody
	public HashMap<String,Object> getClassListTwo(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		HashMap<String,Object> result=this.classManageModuleService.getClassListTwo(teacherId);
		return result;
	}
//	@RequestMapping(value="/addStudent")
//	@ResponseBody
//	public HashMap<String,Object> addStudent(@RequestBody Map<String,Object> request){
//		String teacherId=String.valueOf(request.get("teacherId"));
//		String studentId=String.valueOf(request.get("studentId"));
//		String classId=String.valueOf(request.get("classId"));
//		
//		int r1=this.classManageModuleService.addStudent(teacherId,studentId,classId);
//		HashMap<String,Object> resultpre1=new HashMap<String,Object>();
//		resultpre1.put("status", r1);
//		HashMap<String,Object> result=new HashMap<String,Object>();
//		result.put("status",1);
//		result.put("result",resultpre1);
//		return result;
//	}

//	@RequestMapping(value="/addClass")
//	@ResponseBody
//	public HashMap<String,Object> addClass(@RequestBody Map<String,Object> request){
//		String schoolName=String.valueOf(request.get("schoolName"));
//		String teacherId=String.valueOf(request.get("teacherId"));
//		int grade=Integer.parseInt(String.valueOf(((request.get("grade")))));
//		String classId=String.valueOf(request.get("classId"));
//		String className=String.valueOf(request.get("className"));
//		HashMap<String,Object> result= this.classManageModuleService.addClass(schoolName,teacherId,grade,classId,className);
//		return result;
//	}	
}
 