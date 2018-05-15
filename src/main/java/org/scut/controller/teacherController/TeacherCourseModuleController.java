package org.scut.controller.teacherController;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.dao.*;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v2.sql.filter.SynchronizedFilterCallableStatement;
//@RequestMapping(value="/teacher", method=RequestMethod.POST)
@Controller
public class TeacherCourseModuleController {
	@Resource
	private ITeacherCourseModuleService teacherCourseModuleService;
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IStudyDao studyDao;
	@Resource
	private ITeacher_classDao teacher_classDao;
	@Resource
	private IClassDao classDao;
	@Resource
	private IQuestionDao questionDao; 
	@Resource //鏉╂瑤閲滈崝鐔诲厴鏉╂ɑ鐥呭ù锟�
	private IPptDao pptDao;
	@Resource
	private IStudent_studyDao student_studyDao;
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
		List<String> paperIdArr=(List<String>)paperId.get("paperId");
		HashMap<String,Object> result=teacherCourseModuleService.deleteList(paperIdArr);
		return result;
	}
	//3.1test well
	@RequestMapping(value="/getClassList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getClassList(@RequestBody Map<String,Object> request) {
		//autoCorrect
		/**ArrayList<HashMap<String,Object>> r1=this.solutionDao.autoCorrectSelect();
		int choiceScore=0;
		for(int i=0;i<r1.size();i++) {
		if(r1.get(i).get("content").toString()==this.questionDao.autoCorrectSelect(r1.get(i).get("questionId").toString()).toString()) {
		this.solutionDao.autoCorrectRight(r1.get(i).get("studentId").toString(),r1.get(i).get("questionId").toString());
		choiceScore+=5;
		this.student_paperDao.autoCorrect(r1.get(i).get("studentId").toString(),r1.get(i).get("paperId").toString(),choiceScore);
		}
		else {
			this.solutionDao.autoCorrectFalse(r1.get(i).get("studentId").toString(),r1.get(i).get("questionId").toString());
			this.student_paperDao.autoCorrect(r1.get(i).get("studentId").toString(),r1.get(i).get("paperId").toString(),choiceScore);
		}
		}**/
		
		String teacherId=String.valueOf(request.get("teacherId"));
		
		HashMap<String,Object> result=teacherCourseModuleService.getClassList(teacherId);
		return result;
	}
	//3.3wait for test
	@RequestMapping(value="/getQuestionList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getQuestionList(@RequestBody Map<String,Object> request){
		String subjectId=String.valueOf(request.get("subjectId"));
		//we drop the grade function for test
		//int grade=Integer.parseInt(String.valueOf(request.get("grade")));
		HashMap<String,Object> result=teacherCourseModuleService.getQuestionList(subjectId);
		return result;
	}
	//4assign homework,all actions were done in controller,completed!!! test well!
	@RequestMapping(value="/assignHomework", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> assignHomework(@RequestBody Map<String,Object> request){
		String status="1";
		HashMap<String,Object> result=new HashMap<String,Object>();
		/**table paper test well**/
		String paperId=UUID.randomUUID().toString();
		String paperName=String.valueOf(request.get("paperName"));
		//for test,use the value=7
		int grade=7;//Integer.parseInt(String.valueOf(request.get("grade")));
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
		int examTime=0;
		if(request.get("examTime")!=null) {
		examTime=Integer.parseInt(String.valueOf(request.get("examTime")));
		}
		//none rank
		//action
		try {
			this.studyDao.assignHomework(paperId,classId,assignTeacherId,deadLine,submitNumber,assignTime,paperType,examTime);
		}catch(Exception e) {
			e.printStackTrace();
			status="-2";
			result.put("result", status);
		}
		/**table question_paper test well**/
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
		/**table student test well**/
		ArrayList<HashMap<String,Object>> studentIdArr=new ArrayList<HashMap<String,Object>>();
		try {
			studentIdArr=this.studentDao.getStudentIdByClassId(classId);
		}catch(Exception e){
			e.printStackTrace();
			status="-2";
			result.put("result", status);
		}
		/**table student_paper test well**/
		for(HashMap<String,Object> i:studentIdArr) {
			String studentId=String.valueOf(i.get("studentId"));
			try{
				String submit="0";
				int score=0;
				//paperType
				this.student_studyDao.assignmentHomework(paperId,studentId,submit,score,paperType);
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
	public Map<String,Object> getCorrectQuestionList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String studyId=String.valueOf(request.get("studyId"));
		String studentId=String.valueOf(request.get("studentId"));
		Map<String,Object> responsBody=teacherCourseModuleService.getCorrectQuestionList(teacherId,studyId,studentId);
		return responsBody;
	}
	//13submitCorrection test well
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/submitCorrection", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> submitCorrection(@RequestBody HashMap<String,Object> request,HttpServletResponse response) throws IOException {
		@SuppressWarnings("unused")
		String teacherId=String.valueOf(request.get("teacherId"));
		String studentId=String.valueOf(request.get("studentId"));
		String studyId=String.valueOf(request.get("studyId"));
		List<Map<String,Object>> correctionResultList=(List<Map<String,Object>>)request.get("correctionResultList");
		Map<String,Object> responseBody=teacherCourseModuleService.submitCorrection(teacherId,studyId,studentId,correctionResultList);
		return responseBody;
	}
	//14test well
	@RequestMapping(value="/getSubjectiveOrObjectiveList", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> getSubjectiveOrObjectiveList(@RequestBody Map<String,Object> request){
		String teacherId=String.valueOf(request.get("teacherId"));
		String questionType=String.valueOf(request.get("questionType"));
		String subjectId="1";
		int grade=7;
		HashMap<String,Object> result=teacherCourseModuleService.getSubjectiveOrObjectiveList(teacherId,questionType,subjectId,grade);
		return result;
	}
	/**@RequestMapping(value="/deleteQuestion", method=RequestMethod.POST)
	@ResponseBody
	public int deleteQuestion(@RequestBody Map<String,Object> request) {
		String teacherId=String.valueOf(request.get("teacherId"));
		String questionId=String.valueOf(request.get("questionId"));
		return this.teacherCourseModuleService.deleteQuestion(teacherId,questionId);
	}
	**/
	//16createObjective test well
	@RequestMapping(value="/createObjective", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> createObjective(@RequestBody HashMap<String,Object> request,HttpServletRequest request2,HttpServletResponse response) throws IOException {
				//SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		//String createTime=date.format(new Date());
		@SuppressWarnings("unused")
		String teacherId=String.valueOf(request.get("teacherId"));
		String subjectId=String.valueOf(request.get("subjectId"));
		int grade=7;//Integer.parseInt(String.valueOf(request.get("grade")));
		String optionA=String.valueOf(request.get("optionA"));
		String optionB=String.valueOf(request.get("optionB"));
		String optionC=String.valueOf(request.get("optionC"));
		String optionD=String.valueOf(request.get("optionD"));
		//the answer is one of ABCD
		String answer=String.valueOf(request.get("answer"));
		String titleContent=String.valueOf(request.get("titleContent"));
		String picA=null;
		String picB = null;
		String picC = null;
		String picD = null;
		String picPathPicture=null;
		String picId1=UUID.randomUUID().toString();
		String picId2=UUID.randomUUID().toString();
		String picId3=UUID.randomUUID().toString();
		String picId4=UUID.randomUUID().toString();
		String picId5=UUID.randomUUID().toString();
		if(request.get("picA") != null) {
			picA=String.valueOf(request.get("picA"));
		}
		if(request.get("picB") != null) {
			picB=String.valueOf(request.get("picB"));
		}
		if(request.get("picC") != null) {
			picC=String.valueOf(request.get("picC"));
		}
		if(request.get("picD") != null) {
			picD=String.valueOf(request.get("picD"));
		}
		//title'picture
		if(request.get("picPathPicture") != null) {
			picPathPicture=String.valueOf(request.get("picPathPicture"));
		}
		String opaPicPath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
+picId1+".jpg";
		String opbPicPath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
				+picId2+".jpg";
		String opcPicPath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
				+picId3+".jpg";
		String opdPicPath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
				+picId4+".jpg";
		String picPath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
				+picId5+".jpg";
		return this.teacherCourseModuleService.createObjective(subjectId,grade,optionA,optionB,optionC,optionD,
				answer,picA,picB,picC,picD,picPathPicture,opaPicPath,opbPicPath,opcPicPath,opdPicPath,picPath,titleContent
				,picId1,picId2,picId3,picId4,picId5);
	}
	//17create subjective completed test well
	@RequestMapping(value="/createSubjective", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> createSubjective(@RequestBody HashMap<String,Object> request,HttpServletRequest request2,HttpServletResponse response) throws IOException {
		//SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		//String createTime=date.format(new Date());
		String teacherId=String.valueOf(request.get("teacherId"));
		String subjectId=String.valueOf(request.get("subjectId"));
		int grade=7;//=Integer.parseInt(request.get("grade"));
		String picId1=UUID.randomUUID().toString();
		String picId2=UUID.randomUUID().toString();
		String picSubjective=null;
		String picAnswer=null;
		if(String.valueOf(request.get("pic")) != null) {
			picSubjective=String.valueOf(request.get("pic"));
		}if(String.valueOf(request.get("answer")) != null) {
			picAnswer=String.valueOf(request.get("answer"));
		}
		System.out.println(String.valueOf(request.get("pic")));
		System.out.println(String.valueOf(request.get("answer")));
		System.out.println(String.valueOf(request.get("subjectId")));
		System.out.println(String.valueOf(request.get("teacherId")));
		//twice!!!error!!!not next time!!!
		String picPath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
				+picId1+".jpg";
		String answer=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.questionPicPath
				+picId2+".jpg";
		return this.teacherCourseModuleService.createSubjective(teacherId,picSubjective,
																picPath,picAnswer,
																answer,subjectId,grade,picId1,picId2);
	}
	//18.checkTitle test well.         
	@RequestMapping(value="/checkTitle", method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> checkTitle(@RequestBody Map<String,Object> request) {
		String questionId=String.valueOf(request.get("questionId"));
		return this.teacherCourseModuleService.checkTitle(questionId);
	}
	//19.autoCorrect will not be call
	/**@RequestMapping(value="/autoCorrect", method=RequestMethod.POST)
	@ResponseBody
	public void autoCorrect(@RequestBody HashMap<String,Object> request){
		
		
	}**/
}
