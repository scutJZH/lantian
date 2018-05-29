package org.scut.service.teacherService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ITeacherCourseModuleService {
	public HashMap<String,Object> selectList(String teacherId,String classId);
	public HashMap<String,Object> deleteList(List<String> studyIdArr);
	
	public HashMap<String,Object> getClassList(String teacherId);
	public HashMap<String,Object> getQuestionList(String sunjectId,String grade,String teacherId);
	public HashMap<String,Object> getPptList(String teacherId);
	public HashMap<String,Object> getCorrectionList(String teacherId,String classId);
	public HashMap<String,Object> getRankList(String teacherId,String classId);
	public HashMap<String,Object> getRankDetails(String StudyId,String paperId);
	public HashMap<String,Object> getCorrectStudentList(String teacherId,String paperId);
	public Map<String, Object> getCorrectQuestionList(String teacherId,String studyId,String studentId);
	public HashMap<String,Object> getSubjectiveOrObjectiveList(String teacherId,String questionType,String subjectId,String grade);
	public HashMap<String,Object> createObjective(String subjectId,String grade,String optionA,String optionB,String optionC,String optionD,
			String answer,String picA,String picB,String picC,String picD,String picPathPicture,String opaPicPath,String opbPicPath,String opcPicPath
			,String opdPicPath,String picPath,String titleContent,
			String picId1,String picId2,String picId3,String picId4,String picId5,String teacherId);
	public HashMap<String,Object> createSubjective(String teacherId,String picSubjective,
			String picPath,
			String picAnswer,String answer,String subjectId,String grade,String picId1,String picId2);
	public HashMap<String,Object> checkTitle(String question);
	public int GenerateImage(String imgStr,String picPath);
	public Map<String, Object> submitCorrection(String teacherId, String studyId, String studentId,
			List<Map<String, Object>> correctionResultList); 
}
