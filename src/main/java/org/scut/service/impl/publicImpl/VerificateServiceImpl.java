package org.scut.service.impl.publicImpl;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.dao.teacherDao.ITeacherDao;
import org.scut.service.publicService.IVerificateService;
import org.springframework.stereotype.Service;

@Service("verificateService")
public class VerificateServiceImpl implements IVerificateService{
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IParentDao parentDao;
	@Resource
	private ITeacherDao teacherDao;

	public boolean verificateTelnumber(String telnumber, String userType) {
		
		String flag = null;
		
		if(userType.equals("1")){
			flag = this.studentDao.verificateTelnumber(telnumber);
		}else if(userType.equals("2")){
			flag = this.teacherDao.verificateTelnumber(telnumber);
		}else{
			flag = this.parentDao.verificateTelnumber(telnumber);
		}
		
//		System.out.println("flag = "+flag);
		
		return (flag == null||flag == "")?false:true;
	}

	

}
