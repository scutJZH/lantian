package org.scut.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_paperDao {

	
	List<Map<String, Object>> getStudentPaperBySId(@Param("studentId")String studentId,@Param("submit")String submit);



	public List<Map> getHomeworkInfo(@Param("studentId") String studentId, @Param("classId") String classid,
			@Param("subjectId") String subjectId, @Param("paperType") String paperType);

	public List<HashMap<String,Object>> getRankDetails(@Param("paperId")String paperId);
	public List<HashMap<String,Object>> getCorrectStudentList(@Param("teacherId")String teacherId,@Param("paperId")String paperId);
	public int assignmentHomework(String paperId,String studentId,String submit,int score,String paperType);

}
