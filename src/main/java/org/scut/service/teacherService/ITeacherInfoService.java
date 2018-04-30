package org.scut.service.teacherService;

import java.util.Map;

public interface ITeacherInfoService {
public Map<String, Object> getTeacherInfo(String teacherId);
	
	public Map<String, Object> modifyTeacherInfo(String teacherId, String base64Str, String nickname, String birthdayStr, String sex, String name, String schoolName, String filePath);
}
