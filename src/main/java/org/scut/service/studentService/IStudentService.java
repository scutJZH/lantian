package org.scut.service.studentService;

import java.util.Map;

import org.scut.model.Student;

public interface IStudentService {

	public boolean inputStudent(String id, String telnumber, String nickname, String password, String token);
	
	public Map<String,String> queryPwdAndToken(String telnumber);
}
