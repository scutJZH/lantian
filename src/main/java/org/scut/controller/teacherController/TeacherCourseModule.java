package org.scut.controller.teacherController;
import java.util.Date;
import java.io.IOException;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//@RequestMapping(value="/teacher", method=RequestMethod.POST)
@Controller
public class TeacherCourseModule {
	@Resource
	private ITeacherCourseModuleService teacherCourseModuleService;
	
	//1.获取往期作业列表，测试通过
	@RequestMapping(value="/getHomeworkList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getHomeworkList(@RequestBody Map<String,Object> request) {
	String teacherId=String.valueOf(request.get("teacherId"));
	String classId=String.valueOf(request.get("classId"));
	List<HashMap<String,Object>> resultpre=teacherCourseModuleService.selectList(teacherId, classId);
	HashMap<String,Object> result=new HashMap<String,Object>();
	result.put("status", "1");
	result.put("result", resultpre);
	return result;
}
	//2.删除往期作业列表 因为是删除数据功能，所以暂时没测
	@RequestMapping(value="/deleteHomeworkList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> deleteHomeworkList(@RequestBody List<String> paperId){
		int resultPre=teacherCourseModuleService.deleteList(paperId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		if(resultPre<0) {
			resultPre=-2;
		}
		result.put("status", resultPre);
		return result;
	}
	//3.1 获取班级,test completed
	@RequestMapping(value="/getClassList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getClassList(@RequestBody Map<String,Object> request) {
		String teacherId=String.valueOf(request.get("teacherId"));
		
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getClassList(teacherId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
		result.put("result", resultpre);
		
		return result;
	}
	//3.3 获取题目,test completed
	@RequestMapping(value="/getQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getQuestionList(@RequestBody Map<String,Object> request){
		String subjectId=String.valueOf(request.get("subjectId"));
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getQuestionList(subjectId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("success", true);
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
	@RequestMapping(value="/getCorrectionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String classId=String.valueOf(request.get("classId"));
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getCorrectionList(teacherId,classId);
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
	@RequestMapping(value="/getRankList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getRankList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String classId=String.valueOf(request.get("classId"));
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getRankList(teacherId,classId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//10.获取统计排名详情
	@RequestMapping(value="/getRankDetails", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getRankDetails(@RequestBody Map<String,Object> request){
		String paperId=String.valueOf(request.get("paperId"));
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getRankDetails(paperId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//11.	获取批改作业学生列表（11-13为批改作业补充）
	@RequestMapping(value="/getCorrectStudentList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectStudentList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getCorrectStudentList(teacherId,paperId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	//12.	获取待批改/修改学生作业详情
	@RequestMapping(value="/getCorrectQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectQuestionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		String studentId=String.valueOf(request.get("studentId"));
		List<HashMap<String,Object>> resultpre=teacherCourseModuleService.getCorrectQuestionList(teacherId,paperId,studentId);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("status", "1");
		result.put("result", resultpre);
		return result;
	}
	/***13.	提交/修改批改结果，比较难，先放着
	@RequestMapping(value="/submitCorrection", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> submitCorrection(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		String studentId=String.valueOf(request.get("studentId"));
		//下面的做法是可以的，test completed。
		List<Map<String,Object>> questionArr=(List<Map<String,Object>>)request.get("questionArr");
		questionArr.get(0).get("point");
		//int resultpre=teacherCourseModuleService.submitCorrection(teacherId,paperId,studentId,questionArr);
		return questionArr;
	}***/
	//14.获取主观/客观题列表
	/**@RequestMapping(value="/getSubjectiveOrObjectiveList", method=RequestMethod.POST)
	@ResponseBody
	public List<Map<String,Object>> getSubjectiveOrObjectiveList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String questionType=String.valueOf(request.get("questionType"));	
		//下面的做法是可以的，test completed。
		List<Map<String,Object>> result=(List<Map<String,Object>>)request.getSubjectiveOrObjectiveList(teacherId,questionType);
		return result;
		}
	**/
	//15.删除一道题目
	/**@RequestMapping(value="/deleteQuestion", method=RequestMethod.POST)
	@ResponseBody
	public int deleteQuestion(@RequestBody Map<String,Object> request) {
		String teacherId=String.valueOf(request.get("teacherId"));
		String questionId=String.valueOf(request.get("questionId"));
		return this.teacherCourseModuleService.deleteQuestion(teacherId,questionId);
	}
	**/
	//16.	创建客观题
	/**@RequestMapping(value="/createObjective", method=RequestMethod.POST)
	@ResponseBody
	public int createObjective(@RequestBody Map<String,Object> request) {
		String teacherId=String.valueOf(request.get("teacherId"));
		//图片路径也存到titleContent
		String titleContent=String.valueOf(request.get("titleContent"));
		String optioanA=String.valueOf(request.get("optioanA"));
		String optionB=String.valueOf(request.get("optionB"));
		String optionC=String.valueOf(request.get("optionC"));
		String optionD=String.valueOf(request.get("optionD"));
		String answer=String.valueOf(request.get("answer"));

		
		return this.teacherCourseModuleService.createObjective(teacherId,
																titleContent,
																optioanA,optionB,optionC,optionD,
																answer);
	}
	**/
}
