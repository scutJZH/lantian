package org.scut.service.teacherService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ITeacherCourseModuleService {
	public ArrayList<HashMap<String,Object>> selectList(String teacherId,String classId);
	public int deleteList(List<String> paperId);
}
