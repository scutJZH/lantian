package org.scut.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_studyDao {

	public List<Map<String, Object>> getStudentStudyBySId(@Param("studentId")String studentId,@Param("submit")String submit);

	public List<Map> getHomeworkInfo(@Param("studentId") String studentId, @Param("classId") String classid,
			@Param("subjectId") String subjectId, @Param("paperType") String paperType);

	public List<LinkedHashMap<String,Object>> getRankDetails(@Param("paperId")String paperId);
	public List<HashMap<String,Object>> getCorrectStudentList(@Param("teacherId")String teacherId,@Param("paperId")String paperId);

	void updateSubmit(@Param("studentId")String studentId, @Param("paperId")String paperId);

	public int assignmentHomework(@Param("paperId")String paperId,@Param("studentId")String studentId,@Param("submit")String submit,@Param("score")int score,@Param("paperType")String paperType);
	
	public int autoCorrect(@Param("studentId")String studentId,@Param("paperId")String paperId,@Param("choiceScore")int choiceScore);

	public List<Map<String, Object>> getChildStudyInfoList(@Param("studentId")String studentId, @Param("classId")String classId, @Param("subjectId")String subjectId, @Param("studyType")String studyType);

}