package org.scut.service.teacherService;

import java.util.HashMap;
import java.util.List;

public interface IClassManageModuleService {
	public HashMap<String,Object> getClassListTwo(String teacherId);
	public HashMap<String,Object> addStudent(String teacherId,String studentId,String classId);
}
