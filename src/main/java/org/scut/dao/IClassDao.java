package org.scut.dao;

import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.scut.model.Class;

@Repository
public interface IClassDao {
	
	public Map<String,Object> getStudentNumber(@Param("classId")String classId);
	
	public void insertClass(@Param("class")Class newClass);
	
	public void updateStudentNumber(@Param("classId")String classId, @Param("studentNumber")int studentNumber);
	
	public int getStuNumber(@Param("classId")String classId);

	public int getTeacherNumber(@Param("classId")String classId);
	
	public void updateTeacherNumber(@Param("classId")String classId, @Param("teacherNumber")int teacherNumber);

	public String getCreateTeacherId(@Param("classId")String classId);
	
	public Map<String, Object> getClassNameAndPicAndSchoolById(@Param("classId")String classId);
}