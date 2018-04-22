package org.scut.service.teacherService;

import java.util.HashMap;
import java.util.List;

public interface IClassManageModuleService {
	public HashMap<String,Object> getClassListTwo(String teacherId);
	public int addStudent(String teacherId,String studentId,String classId);
	public HashMap<String,Object> addClass(String schoolName,String teacherId,int grade,String classId,String className);
}
