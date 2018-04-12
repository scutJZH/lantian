package org.scut.dao.studentDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Homework;
import org.scut.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao {
	
	public void inputStudent(Student student);
	
	public String verificateTelnumber(@Param("telnumber")String telnumber);
	
	public Map<String,String> getIdAndPwdAndToken(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("id")String id, @Param("token")String token);
	
	public Student getStudent(@Param("id")String studentId);
	
	public Map<String, String> getStudentIdAndName(@Param("telnumber")String telnumber);
	
	public String getName(@Param("id")String studentId);
	
	public List<Map<String, String>> getPractice(@Param("id")String studentId, @Param("subjectId")String subjectId);
	
	public List<Map<String, String>> getExam(@Param("id")String studentId, @Param("subjectId")String subjectId);
	
	public String getClassIdFromJoinClass(@Param("id")String studentId);
	
	//取得作业的id、分数、排名
	public List<Map<String, String>> getHomeworkList(@Param("studentId")String studentId);
}
