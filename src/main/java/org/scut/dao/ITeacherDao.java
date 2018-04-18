package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Teacher;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherDao {

	public Teacher getTeacherByTel(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("teacherId")String id, @Param("token")String token);
    
	public void insertTeacher(Teacher Teacher);
	
	public void insertUser(@Param("user")User user);
	
	public void updateTeacher(@Param("user")User user);
	
	public Teacher getTeacherById(@Param("teacherId")String teacherId);
}
