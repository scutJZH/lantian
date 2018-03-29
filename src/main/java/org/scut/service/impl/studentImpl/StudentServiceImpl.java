package org.scut.service.impl.studentImpl;

import javax.annotation.Resource;

import org.scut.dao.studentDao.IStudentDao;
import org.scut.model.Student;
import org.scut.service.studentService.IStudentService;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements IStudentService{

	@Resource
	private IStudentDao studentDao;
	
	public boolean inputStudent(Student student) {
		try{
			this.studentDao.inputStudent(student);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	

}
