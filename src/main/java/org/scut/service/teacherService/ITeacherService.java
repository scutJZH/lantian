package org.scut.service.teacherService;

import java.util.Map;

import org.scut.model.Teacher;

public interface ITeacherService {

	public boolean inputTeacher(String id, String telnumber, String nickname, String password, String token);
	
	public Map<String,String> queryPwdAndToken(String telnumber);
}
