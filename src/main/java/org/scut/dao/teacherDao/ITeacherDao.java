package org.scut.dao.teacherDao;

import org.scut.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacherDao {
	
	public String verificateTelnumber(String telnumber);
	
	public void inputTeacher(Teacher teacher);
}
