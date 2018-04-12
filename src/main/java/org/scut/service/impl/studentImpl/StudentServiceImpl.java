package org.scut.service.impl.studentImpl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.dao.studentDao.IStudentDao;
import org.scut.model.Student;
import org.scut.service.studentService.IStudentService;
import org.springframework.stereotype.Service;

@Service("studentService")
public class StudentServiceImpl implements IStudentService{

	@Resource
	private Student student;
	@Resource
	private IStudentDao studentDao;
	
	@Override
	public boolean inputStudent(String id, String telnumber, String nickname, String password, String token) {
		
		Date createTime = new Date();
		
		this.student.setId(id);
		this.student.setCreateTime(createTime);
		this.student.setToken(token);
		this.student.setPhone(telnumber);
		this.student.setNickname(nickname);
		this.student.setPassword(password);
		try{
			this.studentDao.inputStudent(student);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public List<Map<String, String>> getHomework(String studentId, String subjectId){
		
//		List<Map<String, String>> result = this.studentDao.getHomework(studentId, subjectId);
		
//		return result;
		return null;
	}

	@Override
	public List<Map<String, String>> getPractice(String studentId, String subjectId) {
		
		List<Map<String, String>> result = this.studentDao.getPractice(studentId, subjectId);
		
		return result;
	}
	
	@Override
	public List<Map<String, String>> getExam(String studentId, String subjectId) {
		
		List<Map<String, String>> result = this.studentDao.getExam(studentId, subjectId);
		
		return result;
	}
	
	@Override
	public Student getStudent(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
