package org.scut.service.impl.studentImpl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

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
	public Map<String, String> queryPwdAndToken(String telnumber) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
