package org.scut.dao.teacherDao;

import java.util.Map;

import org.scut.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherDao {
	
	public String verificateTelnumber(String telnumber);
	
	public void inputTeacher(Teacher teacher);
	
	public Map<String, String> queryPwdAndToken(String telnumber);
}
