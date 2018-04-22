package org.scut.controller.teacherController;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
//@RequestMapping(value="/teacher", method=RequestMethod.POST)
@Controller
public class TeacherCourseModuleController {
	@Resource
	private ITeacherCourseModuleService teacherCourseModuleService;
	
	@RequestMapping(value="/getHomeworkList")
	@ResponseBody
	public HashMap<String,Object> getHomeworkList(@RequestBody Map<String,Object> request) {
	String teacherId=String.valueOf(request.get("teacherId"));
	String classId=String.valueOf(request.get("classId"));
	HashMap<String,Object> result=teacherCourseModuleService.selectList(teacherId, classId);
	return result;
}
	@RequestMapping(value="/deleteHomeworkList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> deleteHomeworkList(@RequestBody List<String> paperId){
		HashMap<String,Object> result=teacherCourseModuleService.deleteList(paperId);
		return result;
	}
	//3.1
	@RequestMapping(value="/getClassList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getClassList(@RequestBody Map<String,Object> request) {
		String teacherId=String.valueOf(request.get("teacherId"));
		
		HashMap<String,Object> result=teacherCourseModuleService.getClassList(teacherId);
		return result;
	}
	//3.3
	@RequestMapping(value="/getQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getQuestionList(@RequestBody Map<String,Object> request){
		String subjectId=String.valueOf(request.get("subjectId"));
		HashMap<String,Object> result=teacherCourseModuleService.getQuestionList(subjectId);
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
	//5
	//6
	//7
	@RequestMapping(value="/getCorrectionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String classId=String.valueOf(request.get("classId"));
		HashMap<String,Object> result=teacherCourseModuleService.getCorrectionList(teacherId,classId);
		return result;
	}
	//8.
	//9.
	@RequestMapping(value="/getRankList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getRankList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String classId=String.valueOf(request.get("classId"));
		HashMap<String,Object> result=teacherCourseModuleService.getRankList(teacherId,classId);
		return result;
	}
	//10
	@RequestMapping(value="/getRankDetails", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getRankDetails(@RequestBody Map<String,Object> request){
		String paperId=String.valueOf(request.get("paperId"));
		HashMap<String,Object> result=teacherCourseModuleService.getRankDetails(paperId);
		return result;
	}
	//11
	@RequestMapping(value="/getCorrectStudentList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectStudentList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		HashMap<String,Object> result=teacherCourseModuleService.getCorrectStudentList(teacherId,paperId);
		return result;
	}
	//12
	@RequestMapping(value="/getCorrectQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectQuestionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		String studentId=String.valueOf(request.get("studentId"));
		HashMap<String,Object> result=teacherCourseModuleService.getCorrectQuestionList(teacherId,paperId,studentId);
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
	//16createObjective
	@RequestMapping(value="/createObjective", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> createObjective(MultipartHttpServletRequest request) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String createTime=date.format(new Date());
		String teacherId=String.valueOf(request.getParameter("teacherId"));
		String subjectId=String.valueOf(request.getParameter("subjectId"));
		int grade=Integer.parseInt(request.getParameter("grade"));
		String optionA=String.valueOf(request.getParameter("optionA"));
		String optionB=String.valueOf(request.getParameter("optionB"));
		String optionC=String.valueOf(request.getParameter("optionC"));
		String optionD=String.valueOf(request.getParameter("optionD"));
		//the answer is one of ABCD
		String answer=String.valueOf(request.getParameter("answer"));
		String titleContent=String.valueOf(request.getParameter("titleContent"));
		
		MultipartFile picA=request.getFile("picA");
		MultipartFile picB=request.getFile("picB");
		MultipartFile picC=request.getFile("picC");
		MultipartFile picD=request.getFile("picD");
		//title'picture
		MultipartFile picPathPicture=request.getFile("picPathPicture");
		String opaPicPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picA.getOriginalFilename();
		String opbPicPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picB.getOriginalFilename();
		String opcPicPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picC.getOriginalFilename();
		String opdPicPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picD.getOriginalFilename();
		String picPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picPathPicture.getOriginalFilename();
		return this.teacherCourseModuleService.createObjective(subjectId,grade,optionA,optionB,optionC,optionD,
				answer,picA,picB,picC,picD,picPathPicture,opaPicPath,opbPicPath,opcPicPath,opdPicPath,picPath,titleContent);
	}
	//17create subjective completed
	@RequestMapping(value="/createSubjective", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> createSubjective(MultipartHttpServletRequest request) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String createTime=date.format(new Date());
		String teacherId=String.valueOf(request.getParameter("teacherId"));
		String subjectId=String.valueOf(request.getParameter("subjectId"));
		int grade=Integer.parseInt(request.getParameter("grade"));
		MultipartFile picSubjective=request.getFile("pic");
		String picPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picSubjective.getOriginalFilename();
		MultipartFile picAnswer=request.getFile("answer");
		String answer=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picAnswer.getOriginalFilename();
		
		
		return this.teacherCourseModuleService.createSubjective(teacherId,picSubjective,
																picPath,picAnswer,
																answer,subjectId,grade);
	}
	//18.checkTitle
	@RequestMapping(value="/checkTitle", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkTitle(@RequestBody Map<String,Object> request) {
		String questionId=String.valueOf(request.get("questionId"));
		return this.teacherCourseModuleService.checkTitle(questionId);
	}
}