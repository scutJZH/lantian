package org.scut.dao.teacherDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Teacher;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherDao {
	
	public void inputTeacher(Teacher teacher);
	
	public void inputTeacher(User user);
	
	public Teacher getTeacherByTel(@Param("telnumber")String telnumber);
	
	public String getStateByTel(@Param("telnumber")String telnumber);
}
