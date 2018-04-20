package org.scut.service.impl.teacherImpl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.scut.dao.ITeacherDao;
import org.scut.model.Teacher;
import org.scut.service.teacherService.ITeacherInfoService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;

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
				userInfo.put("createTime", teacher.getCreateTime());
				userInfo.put("picPath", GlobalVar.picPath+teacher.getPicPath());
				userInfo.put("birthday", teacher.getBirthday());
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
	public Map<String, Object> modifyTeacherInfo(String teacherId) {
		// TODO Auto-generated method stub
		return null;
	}

}
