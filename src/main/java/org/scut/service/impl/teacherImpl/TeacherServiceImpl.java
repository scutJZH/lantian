package org.scut.service.impl.teacherImpl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.ITeacherDao;
import org.scut.model.Teacher;
import org.scut.service.teacherService.ITeacherService;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherServiceImpl implements ITeacherService {
	
	@Resource
	private Teacher teacher;
	@Resource
	private ITeacherDao teacherDao;

	@Override
	public boolean inputTeacher(String id, String telnumber, String nickname, String password, String token) {
		
		Date createTime = new Date();
		
		this.teacher.setId(id);
		this.teacher.setPhone(telnumber);
		this.teacher.setNickname(nickname);
		this.teacher.setPassword(password);
		this.teacher.setCreateTime(createTime);
		this.teacher.setToken(token);
		try{
			this.teacherDao.inputTeacher(teacher);
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
