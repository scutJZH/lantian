package org.scut.service.impl.studentImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.scut.dao.IStudentDao;
import org.scut.model.Student;
import org.scut.service.studentService.IStudentInfoService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;

@Service
public class StudentInfoServiceImpl implements IStudentInfoService{
	
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
				userInfo.put("createTime", String.valueOf(student.getCreateTime().getTime()));
				userInfo.put("picPath", GlobalVar.picPath+student.getPicPath());
				String birthdayStamp = null;
				if(student.getBirthday() != null){
					long timeStamp = student.getBirthday().getTime();
					birthdayStamp = String.valueOf(timeStamp);
				}
				userInfo.put("birthday", birthdayStamp);
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
	public Map<String, Object> modifyStudentInfo(String studentId, String imgBase64, String nickname,
			String birthdayStr, String sex, String schoolName, String filePath) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, Object> studentInfo = new HashMap<String, Object>();
		try{
			Student student = studentDao.getStudentById(studentId);
			if(student != null){
				String picPath = student.getPicPath();
				if(imgBase64 !=null){
					picPath = Base64Analysis.analysisPic(UUID.randomUUID().toString(), filePath, imgBase64);
					student.setPicPath(picPath);
//					System.out.println(picPath);
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date birthday = formatter.parse(birthdayStr);
				student.setBirthday(birthday);
				student.setNickname(nickname);
				student.setSex(sex);
				student.setSchoolName(schoolName);
				
				studentDao.updateStudent(student);
				
				studentInfo.put("picPath", "/img/"+picPath);
			}else{
				status = "-1";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("result", studentInfo);
		result.put("status", status);
		
		return result;
	}

	
}
