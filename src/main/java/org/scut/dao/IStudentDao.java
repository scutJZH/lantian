package org.scut.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	public Student getStudentById(@Param("studentId")String studentId);
	
	public Map<String, Object> getClassIDBySId(@Param("studentId")String studentId);

	public String getClassIdById(@Param("studentId")String studentId);
	
	public List<HashMap<String,Object>> getStudentByClassId(@Param("classId")String classId);
	
	public HashMap<String,Object> checkStudentExist(@Param("studentId")String studentId);
	
	public int addStudent(@Param("studentId")String studentId,@Param("classId")String classId);
	
	public ArrayList<HashMap<String,Object>> getStudentIdByClassId(@Param("classId")String classId);

	public void updateClassList(@Param("studentId")String studentId);
	
	public List<String> getClassList(@Param("studentId")String studentId);
	
}