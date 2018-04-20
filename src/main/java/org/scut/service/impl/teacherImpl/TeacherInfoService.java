package org.scut.service.impl.teacherImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.scut.dao.ITeacherDao;
import org.scut.model.Teacher;
import org.scut.service.teacherService.ITeacherInfoService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("teacherInfoService")
public class TeacherInfoService implements ITeacherInfoService{
	
	@Resource
	private ITeacherDao teacherDao;

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
				userInfo.put("schoolName", teacher.getSchoolName());
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
	public Map<String, Object> modifyTeacherInfo(String teacherId, List<MultipartFile> filesList, String nickname,
			String birthdayStr, String sex, String name, String schoolName, String filePath) {


		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, Object> teacherInfo = new HashMap<String, Object>();
		try{
			Teacher teacher = teacherDao.getTeacherById(teacherId);
			if(teacher != null){
				String picPath = teacher.getPicPath();
				if(filesList !=null && filesList.size() > 0){
					for(MultipartFile file:filesList){
						String imgOriginalName = file.getOriginalFilename();
						String extensionName = imgOriginalName.substring(imgOriginalName.lastIndexOf("."));
						picPath = UUID.randomUUID().toString()+extensionName;
						File newfile = new File(filePath+picPath);
						FileOutputStream fos = new FileOutputStream(newfile);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						bos.write(file.getBytes());
						fos.close();
						bos.close();
						teacher.setPicPath(picPath);
					}
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date birthday = formatter.parse(birthdayStr);
				teacher.setBirthday(birthday);
				teacher.setNickname(nickname);
				teacher.setSex(sex);
				teacher.setSchoolName(schoolName);
				teacher.setName(name);
				
				teacherDao.updateTeacher(teacher);
				
				teacherInfo.put("picPath", "/img/"+picPath);
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
