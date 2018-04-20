package org.scut.service.teacherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ITeacherCourseModuleService {
	public List<HashMap<String,Object>> selectList(String teacherId,String classId);
	public int deleteList(List<String> paperId);
	
	public List<HashMap<String,Object>> getClassList(String teacherId);
	public List<HashMap<String,Object>> getQuestionList(String sunjectId);
	public List<HashMap<String,Object>> getPptList(String teacherId);
	public List<HashMap<String,Object>> getCorrectionList(String teacherId,String classId);
	public List<HashMap<String,Object>> getRankList(String teacherId,String classId);
	public List<HashMap<String,Object>> getRankDetails(String paperId);
	public List<HashMap<String,Object>> getCorrectStudentList(String teacherId,String paperId);
	public List<HashMap<String,Object>> getCorrectQuestionList(String teacherId,String paperId,String studentId);
	public int createObjective(String teacherId,
			String titleContent,
			String optioanA,String optionB,String optionC,String optionD,
			String answer);
	public int createSubjective(String teacherId,String pic);
	public Map<String,Object> checkTitle(String question);
}
