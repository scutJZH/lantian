package org.scut.service.impl.studentImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.dao.IStudentDao;
import org.scut.model.Student;
import org.scut.service.studentService.IStudentInfoService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;

@Service("studentInfoService")
public class StudentInfoService implements IStudentInfoService{
	
	@Resource
	private IStudentDao studentDao;


	@Override
	public Map<String, Object> getStudentInfo(String studentId) {
		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		Map<String, Object> userInfo = new HashMap<String, Object>();
		try{
			Student student = studentDao.getStudentById(studentId);
			if (student != null) {
				userInfo.put("telnumber", student.getPhone());
				userInfo.put("createTime", student.getCreateTime());
				userInfo.put("picPath", GlobalVar.picPath+student.getPicPath());
				userInfo.put("birthday", student.getBirthday());
				userInfo.put("nickname", student.getNickname());
				userInfo.put("sex", student.getSex());
				userInfo.put("schoolName", student.getSchoolName());
			} else {
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		result.put("result", userInfo);
		result.put("status", status);
		return result;
	}

	@Override
	public Map<String, Object> modifyStudentInfo(String studentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
