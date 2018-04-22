package org.scut.service.teacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ITeacherCourseModuleService {
	public HashMap<String,Object> selectList(String teacherId,String classId);
	public HashMap<String,Object> deleteList(List<String> paperId);
	
	public HashMap<String,Object> getClassList(String teacherId);
	public HashMap<String,Object> getQuestionList(String sunjectId);
	public HashMap<String,Object> getPptList(String teacherId);
	public HashMap<String,Object> getCorrectionList(String teacherId,String classId);
	public HashMap<String,Object> getRankList(String teacherId,String classId);
	public HashMap<String,Object> getRankDetails(String paperId);
	public HashMap<String,Object> getCorrectStudentList(String teacherId,String paperId);
	public HashMap<String,Object> getCorrectQuestionList(String teacherId,String paperId,String studentId);
	public HashMap<String,Object> createObjective(String subjectId,int grade,String optionA,String optionB,String optionC,String optionD,
			String answer,MultipartFile picA,MultipartFile picB,MultipartFile picC,MultipartFile picD,MultipartFile picPathPicture,String opaPicPath,String opbPicPath,String opcPicPath,String opdPicPath,String picPath,String titleContent);
	public HashMap<String,Object> createSubjective(String teacherId,MultipartFile picSubjective,
			String picPath,
			MultipartFile picAnswer,String answer,String subjectId,int grade);
	public HashMap<String,Object> checkTitle(String question);
}
