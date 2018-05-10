package org.scut.service.teacherService;

import java.util.Map;

public interface IClassManagementService {
	
	public Map<String, Object> createClass(String teacherId, String className, String schoolId, String grade, String headPic);
	
	public Map<String, Object> inviteStudent(String teacherId, String classId, String studentTelnumber);

	public Map<String, Object> inviteTeacher(String teacherId, String classId, String teacherTelnumber);

	public Map<String, Object> deleteClass(String classId, String teacherId);

	public Map<String, Object> removeStudent(String teacherId, String studentId, String classId);

	public Map<String, Object> removeTeacher(String headTeacherId, String classId, String teacherId);

	public Map<String, Object> getClasses(String teacherId);

	public Map<String, Object> quitClass(String teacherId, String classId);
}
