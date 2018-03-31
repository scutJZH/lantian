package org.scut.dao.teacherDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherDao {
	
	public String verificateTelnumber(String telnumber);
	
	public void inputTeacher(Teacher teacher);
	
	public Map<String,String> queryIdAndPwdAndToken(String telnumber);
	
	public void updateToken(@Param("id")String id, @Param("token")String token);
}
