package org.scut.controller.studentController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Mistake;
import org.scut.model.Question;
import org.scut.model.Student;
import org.scut.service.studentService.IMistakeService;
import org.scut.util.GlobalVar;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MistakeController {
	@Resource IMistakeService iMistakeService;
	
	
	@RequestMapping("/mistake/getbysubject")
	@ResponseBody
	public Map<String, Object> getbysubject (HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object> map=ParamsTransport.getParams(request);
		String studentId=(String)map.get("studentId");
		String subjectId=(String)map.get("subjectId");
		List<Mistake> mistakes =iMistakeService.findbysubjectid(subjectId, studentId);
		return Json.getJson(1,mistakes);
	
		
		
	}
	@RequestMapping("/mistake/getbytype")
	@ResponseBody
	public Map<String, Object> getbytype (HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object>map =ParamsTransport.getParams(request);
		String studentId=(String)map.get("studentId");
		String subjectId=(String)map.get("subjectId");
		String questionType=(String)map.get("questionType");
		List<Mistake>mistakes=iMistakeService.findbytype(subjectId, studentId, questionType);
		return Json.getJson(1,mistakes);
		
		
		
	}
	@RequestMapping("/mistake/getbystudentid")
	@ResponseBody
	public Map<String, Object> getbystudentid(HttpServletRequest request ,HttpServletResponse response)throws IOException{
		Map<String, Object>map=ParamsTransport.getParams(request);
		String studentId=(String)map.get("studentId");
		List<Mistake>mistakes=iMistakeService.findbystudentId(studentId);
		return Json.getJson(1,mistakes);
		
	}
	@RequestMapping("/mistake/deletebyid")
	@ResponseBody
	public Map<String, Object>  delete(HttpServletRequest request,HttpServletResponse response) throws	 IOException{
		Map<String, Object> map= ParamsTransport.getParams(request);
		String questionId=(String)map.get("questionId");
		String studentId=(String)map.get("studentId");
		int status=iMistakeService.delete(studentId, questionId);
		 return Json.getJson(status,"");
		
	}
	@RequestMapping("/mistake/addnote")
	@ResponseBody
	public Map<String, Object> addnote(HttpServletRequest request,HttpServletResponse response)throws IOException{
		Map<String, Object>map=ParamsTransport.getParams(request);
		String studentId=(String)map.get("studentId");
		String questionId=(String)map.get("questionId");
		String note=(String)map.get("note");
		int status =iMistakeService.updatenote(studentId, questionId, note);
		return Json.getJson(status,"");
		
	}
	@RequestMapping("/mistake/addmistake")
	@ResponseBody
	public Map<String, Object> addmistake(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object>map=ParamsTransport.getParams(request);
		String studentId=(String)map.get("studentId");
		String titleContent=(String)map.get("titleContent");
		String subjectId=(String)map.get("subjectId");
		String optionA=(String)map.get("optionA");
		String optionB=(String)map.get("optionB");
		String optionC=(String)map.get("optionC");
		String optionD=(String)map.get("optionD");
		String answer=(String)map.get("answer");
		String lastAnswer=(String)map.get("lastAnswer");
		String note=(String)map.get("note");
		int status;
		Question question =new Question() ;
		String titleId=iMistakeService.inserttitle(titleContent);
		
		if (optionA!=null) {
			
			question.setAnswer(answer);
			question.setGrade(7);
			question.setOptionA(optionA);
			question.setOptionB(optionB);
			question.setOptionC(optionC);
			question.setOptionD(optionD);
			String questionId=UUID.randomUUID().toString();
			question.setQuestionId(questionId);
			question.setSubjectId(subjectId);
			question.setTitleId(titleId);
			status= iMistakeService.insertquestion(question);
			if (status>0) {
				
				String questionType ="2";
				status= iMistakeService.insertmistake(questionId, studentId, questionType, note, lastAnswer);
				
			}
		
		}else {
			return Json.getJson(-2, "");
		}
		return Json.getJson(status,"");
		
		
		
	}
	@RequestMapping("/mistake/submitnew")
	@ResponseBody
	public Map<String, Object> submitnew (HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object>map=ParamsTransport.getParams(request);
		String questionId =(String)map.get("questionId");
		String studentId=(String)map.get("studentId");
		String newAnswer=(String)map.get("newAnswer");
		int status=iMistakeService.updateanswer(studentId, questionId, newAnswer);
		return Json.getJson(status,"");
		
		
		
		
		
	}
	@RequestMapping("/mistake/addmistake2")
	@ResponseBody
	public Map<String, Object> addmistake2(HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String, Object>map=ParamsTransport.getParams(request);
		String studentId=(String)map.get("studentId");
		String answer=(String)map.get("answer");
		String lastAnswer=(String)map.get("lastAnswer");
		String note=(String)map.get("note");
		String subjectId=(String)map.get("subjectId");
		String imgbase64=(String)map.get("imgbase64");
		String filePath = this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.picPath;
		String titleId=iMistakeService.inserttitle2(imgbase64, filePath);
		Question question=new Question();
		question.setAnswer(answer);
		question.setGrade(7);
		question.setTitleId(titleId);
		String questionId=UUID.randomUUID().toString();
		question.setQuestionId(questionId);
		question.setSubjectId(subjectId);
		int status=iMistakeService.insertquestion(question);
		if (status>0) {
			String questionType ="1";
			status= iMistakeService.insertmistake(questionId, studentId, questionType, note, lastAnswer);
		}
		else {
			return Json.getJson(status,"");
		}
		return Json.getJson(status,"");
		
		
	}
	
}
