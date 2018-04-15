package org.scut.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Student;
import org.scut.model.User;
import org.springframework.stereotype.Repository;
@Repository
public interface IStudentDao {

	public Student getStudentByTel(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("studentId")String id, @Param("token")String token);
    
	public void insertStudent(Student student);
	
	public void insertStudent(User user);
	
	public Map<String, Object> getchildInfoByTel(@Param("telnumber")String telnumber);
	
	public Map<String, Object> getchildInfoById(@Param("studentId")String studentId);
}