package org.scut.service.studentService;

import java.util.List;
import java.util.Map;

import org.scut.model.Student;

public interface IStudentService {

	public boolean inputStudent(String id, String telnumber, String nickname, String password, String token);
	
	public List<Map<String, String>> getHomework(String studentId, String subjectId);
	
	public List<Map<String, String>> getPractice(String studentId, String subjectId);
	
	public List<Map<String, String>> getExam(String studentId, String subjectId);
	
	public Student getStudent(String studentId);
	
}
