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
	//3.1 获取班级
	public HashMap<String,Object> getClassList(@RequestParam(value="teacherId")String teacherId){
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getClassList(teacherId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//3.3 获取题目
	public HashMap<String,Object> getQuestionList(@RequestParam(value="subjecId")String subjectId){
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getQuestionList(subjectId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//4.布置作业,先不做。
	/*public HashMap<String,Object> getQuestionList(@RequestParam(value="subjecId")String subjectId){
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getQuestionList(subjectId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}*/
	
	/***这两个功能第一版不做
	//5.获取备课资料列表
	public HashMap<String,Object> getPptList(@RequestParam(value="teacherId")String teacherId){
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getPptList(teacherId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//6.获取我的收藏列表
	public HashMap<String,Object> getPptList(@RequestParam(value="teacherId")String teacherId){
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getPptList(teacherId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	***/
	//7.获取作业批改列表
	public HashMap<String,Object> getCorrectionList(@RequestParam(value="teacherId")String teacherId,@RequestParam(value="classId")String classId){
		List<HashMap<String,String>> resultpre=teacherCourseModuleService.getCorrectionList(teacherId,classId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	/***8.查看作业详情,第一版前端未做。
	public HashMap<String,Object> getPptList(@RequestParam(value="teacherId")String teacherId){
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getPptList(teacherId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	***/
	//9.获取统计排名列表
	public HashMap<String,Object> getRankList(@RequestParam(value="teacherId")String teacherId,@RequestParam(value="classId")String classId){
		List<HashMap<String,String>> resultpre=teacherCourseModuleService.getRankList(teacherId,classId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//10.获取统计排名详情
	public HashMap<String,Object> getRankDetails(@RequestParam(value="paperId")String paperId){
		List<HashMap<String,String>> resultpre=teacherCourseModuleService.getRankDetails(paperId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//11.	获取批改作业学生列表（11-13为批改作业补充）
	public HashMap<String,Object> getCorrectStudentList(@RequestParam(value="teacherId")String teacherId,@RequestParam(value="paperId")String paperId){
		List<HashMap<String,String>> resultpre=teacherCourseModuleService.getCorrectStudentList(teacherId,paperId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//12.	获取待批改/修改学生作业详情
	public HashMap<String,Object> getCorrectQuestionList(@RequestParam(value="teacherId")String teacherId,@RequestParam(value="paperId")String paperId,@RequestParam(value="studentId")String studentId){
		List<HashMap<String,String>> resultpre=teacherCourseModuleService.getCorrectQuestionList(teacherId,paperId,studentId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//
}
