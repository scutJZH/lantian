package org.scut.service.impl.teacherImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;

import org.scut.dao.ISchoolDao;
import org.scut.dao.ITeacherDao;
import org.scut.model.School;
import org.scut.model.Teacher;
import org.scut.service.teacherService.ITeacherInfoService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;

@Service("teacherInfoService")
public class TeacherInfoServiceImpl implements ITeacherInfoService{
	
	@Resource
	private ITeacherDao teacherDao;
	@Resource
	private ISchoolDao schoolDao;

	@Override
	public Map<String, Object> getTeacherInfo(String teacherId) {
		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try{
			Teacher teacher = teacherDao.getTeacherById(teacherId);
			if (teacher != null) {
				userInfo.put("telnumber", teacher.getPhone());
				userInfo.put("createTime", String.valueOf(teacher.getCreateTime().getTime()));
				userInfo.put("picPath", GlobalVar.picPath+teacher.getPicPath());
				String birthdayStamp = null;
				if(teacher.getBirthday() != null){
					long timeStamp = teacher.getBirthday().getTime();
					birthdayStamp = String.valueOf(timeStamp);
				}
				userInfo.put("birthday", birthdayStamp);
				userInfo.put("nickname", teacher.getNickname());
				userInfo.put("sex", teacher.getSex());
				String schoolId = teacher.getSchoolId();
				School schoolInfo = null;
				if(schoolId != null){
					schoolInfo = schoolDao.getSchoolById(schoolId);
					userInfo.put("schoolName", schoolInfo.getSchoolName());
					userInfo.put("city", schoolInfo.getCity());
				}else{
					userInfo.put("schoolName", null);
					userInfo.put("city", null);
				}
				userInfo.put("schoolId", schoolId);
				
				userInfo.put("name", teacher.getName());
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
	public Map<String, Object> modifyTeacherInfo(String teacherId, String imgBase64, String nickname,
			String birthdayStr, String sex, String name, String schoolId, String filePath) {


		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, Object> teacherInfo = new HashMap<String, Object>();
		try{
			Teacher teacher = teacherDao.getTeacherById(teacherId);
			if(teacher != null){
				String picPath = teacher.getPicPath();
				if(imgBase64 !=null){
					picPath = Base64Analysis.analysisPic(UUID.randomUUID().toString(), filePath, imgBase64);
					teacher.setPicPath(picPath);
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date birthday = formatter.parse(birthdayStr);
				teacher.setBirthday(birthday);
				teacher.setNickname(nickname);
				teacher.setSex(sex);
				teacher.setSchoolId(schoolId);
				teacher.setName(name);
				
				teacherDao.updateTeacher(teacher);
				
				teacherInfo.put("picPath", GlobalVar.picPath+picPath);
			}else{
				status = "-1";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("result", teacherInfo);
		result.put("status", status);
		
		return result;
		
	}

}
