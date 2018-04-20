package org.scut.service.teacherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ITeacherCourseModuleService {
	public ArrayList<HashMap<String,Object>> selectList(String teacherId,String classId);
	public int deleteList(List<String> paperId);
	
	public List<HashMap<String,Object>> getClassList(String teacherId);
	public List<HashMap<String,Object>> getQuestionList(String sunjectId);
	public List<HashMap<String,Object>> getPptList(String teacherId);
	public List<HashMap<String,String>> getCorrectionList(String teacherId,String classId);
	public List<HashMap<String,String>> getRankList(String teacherId,String classId);
	public List<HashMap<String,String>> getRankDetails(String paperId);
	public List<HashMap<String,String>> getCorrectStudentList(String teacherId,String paperId);
	public List<HashMap<String,String>> getCorrectQuestionList(String teacherId,String paperId,String studentId);

}
