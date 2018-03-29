package org.scut.service.impl.teacherImpl;

import javax.annotation.Resource;

import org.scut.dao.teacherDao.ITeacherDao;
import org.scut.model.Teacher;
import org.scut.service.teacherService.ITeacherService;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
	
	@Resource
	private ITeacherDao teacherDao;

	public boolean inputTeacher(Teacher teacher) {
		try{
			this.teacherDao.inputTeacher(teacher);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	
	

}
