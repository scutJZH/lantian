package org.scut.service.studentService;

import java.util.Map;


public interface IStudentInfoService {
	public Map<String, Object> getStudentInfo(String studentId);

	public Map<String, Object> modifyStudentInfo(String studentId);
}
