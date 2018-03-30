package org.scut.dao.studentDao;

import java.util.Map;

import org.scut.model.Student;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao {
	
	public Student selectStudent(String id);
	
	public void inputStudent(Student student);
	
	public String verificateTelnumber(String telnumber);
	
	public Map<String, String> queryPwdAndToken(String telnumber);
}
