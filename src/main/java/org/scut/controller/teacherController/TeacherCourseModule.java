package org.scut.controller.teacherController;
import java.util.Date;
import java.util.*;

import javax.annotation.Resource;

import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class TeacherCourseModule {
	@Resource
	private ITeacherCourseModuleService teacherCourseModuleService;
	@RequestMapping("/TeacherCourseModule_getHomeworkList.do")
	@ResponseBody
	//1.获取往期作业列表
	public HashMap<String,Object> getHomeworkList(@RequestParam(value="teacherId",required=false)String teacherId,@RequestParam(value="classId",required=false)String classId) {
	ArrayList<HashMap<String,Object>> resultpre=teacherCourseModuleService.selectList(teacherId, classId);
	HashMap<String,Object> result=new HashMap<String,Object>();
	result.put("status", "1");
	result.put("result", resultpre);
	return result;
}
	//2.删除往期作业列表
	public HashMap<String,Object> deleteHomeworkList(@RequestParam(value="paperId[]")List<String> paperId){
		int resultPre=teacherCourseModuleService.deleteList(paperId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		if(resultPre<0) {
			resultPre=-2;
		}
		result.put("status", resultPre);
		return result;
	}
}
