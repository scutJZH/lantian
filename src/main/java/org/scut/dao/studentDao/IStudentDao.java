package org.scut.dao.studentDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Homework;
import org.scut.model.Student;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao {
	
	public void inputStudent(Student student);
	
	public void inputStudent(User user);
	
	public Student getStudentById(@Param("id")String studentId);
	
	public Student getStudentByTel(@Param("telnumber")String telnumber);
	
	public String getStateByTel(@Param("telnumber")String telnumber);
}
