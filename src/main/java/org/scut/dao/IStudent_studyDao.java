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
	public Map<String, Object> getStudentStudy(@Param("studentId")String studentId,@Param("studyId")String studyId);

	
	public List<LinkedHashMap<String,Object>> getRankDetails(@Param("paperId")String paperId);

	public List<LinkedHashMap<String,Object>> getRankDetails(@Param("studyId")String studyId,@Param("paperId")String paperId);

	public List<HashMap<String,Object>> getCorrectStudentList(@Param("studyId")String studyId);

	void updateSubmit(@Param("studentId")String studentId, @Param("studyId")String studyId,@Param("choiceScore") int choiceScore);

	public int assignmentHomework(@Param("studyId")String studyId,@Param("studentId")String studentId,@Param("submit")String submit,@Param("score")int score,@Param("paperType")String paperType);
	
	public int autoCorrect(@Param("studentId")String studentId,@Param("paperId")String paperId,@Param("choiceScore")int choiceScore);

	public void updateCorrectedStatus(@Param("studentId")String studentId,@Param("studyId") String studyId,@Param("totalScore") int totalScore,@Param("teacherId")String teacherId);

	public List<Map<String, Object>> getChildStudyInfoList(@Param("studentId")String studentId, @Param("classId")String classId, @Param("subjectId")String subjectId, @Param("studyType")String studyType);


}
