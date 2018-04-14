package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Student;
import org.scut.model.User;
import org.springframework.stereotype.Repository;
@Repository
public interface IStudentDao {

	public Student getStudentByTel(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("studentId")String id, @Param("token")String token);
    
	public void inputStudent(Student student);
	
	public void inputStudent(User user);
}