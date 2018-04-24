package org.scut.controller.teacherController;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import org.scut.dao.*;
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
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IClass_paperDao class_paperDao;
	@Resource
	private ITeacher_classDao teacher_classDao;
	@Resource
	private IClassDao classDao;
	@Resource
	private IQuestionDao questionDao;
	@Resource //杩欎釜鍔熻兘杩樻病娴�
	private IPptDao pptDao;
	@Resource
	private IStudent_paperDao student_paperDao;
	@Resource
	private ISolutionDao solutionDao; 
	@Resource
	private ITitleDao titleDao;
	@Resource
	private IPaperDao paperDao;
	@Resource
	private IQuestion_paperDao question_paperDao;
	
	//1.test well
	@RequestMapping(value="/getHomeworkList")
	@ResponseBody
	public HashMap<String,Object> getHomeworkList(@RequestBody Map<String,Object> request) {
	String teacherId=String.valueOf(request.get("teacherId"));
	String classId=String.valueOf(request.get("classId"));
	HashMap<String,Object> result=teacherCourseModuleService.selectList(teacherId, classId);
	return result;
}
	//2.haven't delete table student_paper,other test well
	@RequestMapping(value="/deleteHomeworkList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> deleteHomeworkList(@RequestBody Map<String,Object> paperId){
		@SuppressWarnings("unchecked")
		List<String> paperIdArr=(ArrayList<String>)paperId.get("paperId");
		HashMap<String,Object> result=teacherCourseModuleService.deleteList(paperIdArr);
		return result;
	}
	//3.1test well
	@RequestMapping(value="/getClassList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getClassList(@RequestBody Map<String,Object> request) {
		String teacherId=String.valueOf(request.get("teacherId"));
		
		HashMap<String,Object> result=teacherCourseModuleService.getClassList(teacherId);
		return result;
	}
	//3.3wait for test
	@RequestMapping(value="/getQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getQuestionList(@RequestBody Map<String,Object> request){
		String subjectId=String.valueOf(request.get("subjectId"));
		int grade=Integer.parseInt(String.valueOf(request.get("grade")));
		HashMap<String,Object> result=teacherCourseModuleService.getQuestionList(subjectId,grade);
		return result;
	}
	//4assign homework,all actions were done in controller,completed!!! wait for test
	@RequestMapping(value="/assignHomework", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> assignHomework(@RequestBody Map<String,Object> request){
		String status="1";
		HashMap<String,Object> result=new HashMap<String,Object>();
		/**table paper**/
		String paperId=UUID.randomUUID().toString();
		String paperName=String.valueOf(request.get("paperName"));
		int grade=Integer.parseInt(String.valueOf(request.get("grade")));
		String subjectId=String.valueOf(request.get("subjectId"));
		String createTeacherId=String.valueOf(request.get("teacherId"));
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String createTime=date.format(new Date());
		int maxScore=0;
		@SuppressWarnings("unchecked")
		List<HashMap<String,Object>>questionArr= (List<HashMap<String,Object>>)request.get("questionArr");
		for(int i=0;i<questionArr.size();i++) {
			int temp=Integer.parseInt(String.valueOf(questionArr.get(i).get("point")));
			maxScore+=temp;
		}
		//action
		try {
			this.paperDao.assignHomework(paperId,paperName,grade,subjectId,createTeacherId,createTime,maxScore);
		}catch(Exception e){
			e.printStackTrace();
			status="-2";
			result.put("result", status);
		}
		/**table class_paper**/
		//paperId
		String classId=String.valueOf(request.get("classId"));
		//none ave_time
		//none ave_score
		String assignTeacherId=createTeacherId;
		String deadLine=String.valueOf(request.get("deadLine"));
		int submitNumber=0;
		String assignTime=createTime;
		String paperType=String.valueOf(request.get("paperType"));
		int examTime=Integer.parseInt(String.valueOf(request.get("examTime")));
		//none rank
		//action
		try {
			this.class_paperDao.assignHomework(paperId,classId,assignTeacherId,deadLine,submitNumber,assignTime,paperType,examTime);
		}catch(Exception e) {
			e.printStackTrace();
			status="-2";
			result.put("result", status);
		}
		/**table question_paper**/
		//paperId
		//questionArr:include questionIdArr,pointArr
		//action
		for(HashMap<String,Object> i:questionArr){
			String questionId=String.valueOf(i.get("questionId"));
			int point=Integer.parseInt(String.valueOf(i.get("point")));
			try {
			
				this.question_paperDao.assignHomework(paperId,questionId,point);
			}catch(Exception e) {
				e.printStackTrace();
				status="-2";
				result.put("result", status);
			}
		}
		/**table student**/
		ArrayList<HashMap<String,Object>> studentIdArr=new ArrayList<HashMap<String,Object>>();
		try {
			studentIdArr=this.studentDao.getStudentIdByClassId(classId);
		}catch(Exception e){
			e.printStackTrace();
			status="-2";
			result.put("result", status);
		}
		/**table student_paper**/
		for(HashMap<String,Object> i:studentIdArr) {
			String studentId=String.valueOf(i.get("studentId"));
			try{
				String submit="0";
				int score=0;
				//paperType
				this.student_paperDao.assignmentHomework(paperId,studentId,submit,score,paperType);
			}catch(Exception e){
				e.printStackTrace();
				status="-2";
				result.put("result", status);
			}
		}
		result.put("status", status);
		return result;
	}
	
	//5
	//6
	//7getCorrectionList test well
	@RequestMapping(value="/getCorrectionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String classId=String.valueOf(request.get("classId"));
		HashMap<String,Object> result=teacherCourseModuleService.getCorrectionList(teacherId,classId);
		return result;
	}
	//8.
	//9.test well
	@RequestMapping(value="/getRankList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getRankList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String classId=String.valueOf(request.get("classId"));
		HashMap<String,Object> result=teacherCourseModuleService.getRankList(teacherId,classId);
		return result;
	}
	//10test well
	@RequestMapping(value="/getRankDetails", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getRankDetails(@RequestBody Map<String,Object> request){
		String paperId=String.valueOf(request.get("paperId"));
		HashMap<String,Object> result=teacherCourseModuleService.getRankDetails(paperId);
		return result;
	}
	//11test well
	@RequestMapping(value="/getCorrectStudentList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectStudentList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		HashMap<String,Object> result=teacherCourseModuleService.getCorrectStudentList(teacherId,paperId);
		return result;
	}
	//12test well
	@RequestMapping(value="/getCorrectQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getCorrectQuestionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String paperId=String.valueOf(request.get("paperId"));
		String studentId=String.valueOf(request.get("studentId"));
		HashMap<String,Object> result=teacherCourseModuleService.getCorrectQuestionList(teacherId,paperId,studentId);
		return result;
	}
	//13submitCorrection
	@RequestMapping(value="/submitCorrection", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitCorrection(@RequestBody Map<String,Object> request){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		String teacherId=String.valueOf(request.get("teacherId"));
		String studentId=String.valueOf(request.get("studentId"));
		String paperId=String.valueOf(request.get("paperId"));
		//the method below is ok，test completed
		ArrayList<Map<String,Object>> questionArr=(ArrayList<Map<String,Object>>)request.get("questionArr");
		for(int i=0;i<questionArr.size();i++) {
			 int point=Integer.parseInt(String.valueOf(questionArr.get(0).get("point")));
			 String isright="0";
			 if(point>0) isright="1";
			 else isright="0";
			 String questionId=String.valueOf(questionArr.get(0).get("questionId"));
			 try{
				 this.solutionDao.submitCorrection(studentId,paperId,questionId,point,isright);
				 result.put("result",status);
			 }catch(Exception e) {
					e.printStackTrace();
					status = "-2";
					result.put("result",status);
			}
		} 
		//int resultpre=teacherCourseModuleService.submitCorrection(teacherId,paperId,studentId,questionArr);
		result.put("status", status);
		return result;
	}
	//14
	@RequestMapping(value="/getSubjectiveOrObjectiveList", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getSubjectiveOrObjectiveList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String questionType=String.valueOf(request.get("questionType"));
		String subjectId=String.valueOf(request.get("subjectId"));
		int grade=Integer.parseInt(String.valueOf(request.get("grade")));
		Map<String,Object> result=this.teacherCourseModuleService.getSubjectiveOrObjectiveList(teacherId,questionType,subjectId,grade);
		return result;
		}
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
		MultipartFile picA=null;
		MultipartFile picB = null;
		MultipartFile picC = null;
		MultipartFile picD = null;
		MultipartFile picPathPicture=null;
		if(request.getFile("picA") != null) {
			picA=request.getFile("picA");
		}
		if(request.getFile("picB") != null) {
			picB=request.getFile("picB");
		}
		if(request.getFile("picC") != null) {
			picC=request.getFile("picC");
		}
		if(request.getFile("picD") != null) {
			picD=request.getFile("picD");
		}
		//title'picture
		if(request.getFile("picPathPicture") != null) {
			picPathPicture=request.getFile("picPathPicture");
		}
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
		MultipartFile picSubjective=null;
		MultipartFile picAnswer=null;
		if(request.getFile("picD") != null) {
			picSubjective=request.getFile("pic");
		}
		if(request.getFile("answer") != null) {
			picAnswer=request.getFile("answer");
			}
		String picPath=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picSubjective.getOriginalFilename();
		String answer=request.getSession().getServletContext().getRealPath("/")+"img\\"+createTime+picAnswer.getOriginalFilename();
		return this.teacherCourseModuleService.createSubjective(teacherId,picSubjective,
																picPath,picAnswer,
																answer,subjectId,grade);
	}
	//18.checkTitle test well
	@RequestMapping(value="/checkTitle", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkTitle(@RequestBody Map<String,Object> request) {
		String questionId=String.valueOf(request.get("questionId"));
		return this.teacherCourseModuleService.checkTitle(questionId);
	}
}
