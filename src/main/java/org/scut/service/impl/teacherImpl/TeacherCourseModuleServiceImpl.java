package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.scut.dao.IClass_paperDao;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Service;

@Service(value="teacherCourseModuleService")
public class TeacherCourseModuleServiceImpl implements ITeacherCourseModuleService{
	@Resource
	private IClass_paperDao class_paperDao;
	public ArrayList<HashMap<String,Object>> selectList(String teacherId,String classId){
		return class_paperDao.selectList(teacherId, classId);
	}
	public int deleteList(List<String> paperId) {
		return class_paperDao.deleteList(paperId);
	}
}
