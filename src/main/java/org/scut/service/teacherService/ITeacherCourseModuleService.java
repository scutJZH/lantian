package org.scut.service.teacherService;

import java.util.HashMap;
import java.util.List;

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
	public HashMap<String,Object> getSubjectiveOrObjectiveList(String teacherId,String questionType,String subjectId,int grade);
	public HashMap<String,Object> createObjective(String subjectId,int grade,String optionA,String optionB,String optionC,String optionD,
			String answer,String picA,String picB,String picC,String picD,String picPathPicture,String opaPicPath,String opbPicPath,String opcPicPath,String opdPicPath,String picPath,String titleContent);
	public HashMap<String,Object> createSubjective(String teacherId,String picSubjective,
			String picPath,
			String picAnswer,String answer,String subjectId,int grade);
	public HashMap<String,Object> checkTitle(String question);
}
