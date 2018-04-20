package org.scut.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Student;
import org.scut.model.User;
import org.springframework.stereotype.Repository;
@Repository
public interface IStudentDao {

	public Student getStudentByTel(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("studentId")String studentId, @Param("token")String token);
    
	public void insertStudent(@Param("student")Student student);
	
	public void insertUser(@Param("user")User user);
	
	public void updateUser(@Param("user")User user);
	
	public void updateStudent(@Param("student")Student student);
	
	public HashMap<String, Object> getchildInfoByTel(@Param("telnumber")String telnumber);
	
	public HashMap<String, Object> getchildInfoById(@Param("studentId")String studentId);
	
	public Student getStudentById(@Param("studentId")String studentId);
	
	public Map<String, Object> getClassIDBySId(@Param("studentId")String studentId);

	public String getClassIdById(@Param("studentId")String studentId);

}