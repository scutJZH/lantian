package org.scut.service.impl.publicImpl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.dao.IParentDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.ITeacherDao;
import org.scut.model.Parent;
import org.scut.model.Student;
import org.scut.model.Teacher;
import org.scut.service.publicService.IGetMyInfoService;
import org.springframework.stereotype.Service;

@Service("myInfoService")
public class GetMyInfoService implements IGetMyInfoService {

	@Resource
	private IParentDao parentDao;
	@Resource
	private ITeacherDao teacherDao;
	@Resource
	private IStudentDao studentDao;

	@Override
	public Map<String, Object> getMyInfo(String userType, String id) {

		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		Map<String, Object> userInfo = new HashMap<String, Object>();
		try {
			switch (userType) {
			case "1":
				Student student = studentDao.getStduetnById(id);
				if (student != null) {
					userInfo.put("telnumber", student.getPhone());
					userInfo.put("createTime", student.getCreateTime());
					userInfo.put("picPath", student.getPicPath());
					userInfo.put("birthday", student.getBirthday());
					userInfo.put("nickname", student.getNickname());
					userInfo.put("sex", student.getSex());
					userInfo.put("schoolName", student.getSchoolName());
				} else {
					status = "-1";
				}
				break;
			case "2":
				Teacher teacher = teacherDao.getTeacherById(id);
				if (teacher != null) {
					userInfo.put("telnumber", teacher.getPhone());
					userInfo.put("createTime", teacher.getCreateTime());
					userInfo.put("picPath", teacher.getPicPath());
					userInfo.put("birthday", teacher.getBirthday());
					userInfo.put("nickname", teacher.getNickname());
					userInfo.put("sex", teacher.getSex());
					userInfo.put("schoolName", teacher.getSchoolName());
					userInfo.put("name", teacher.getName());
				} else {
					status = "-1";
				}
				break;
			case "3":
				Parent parent = parentDao.getParentById(id);
				if (parent != null) {
					userInfo.put("telnumber", parent.getPhone());
					userInfo.put("createTime", parent.getCreateTime());
					userInfo.put("picPath", parent.getPicPath());
					userInfo.put("birthday", parent.getBirthday());
					userInfo.put("nickname", parent.getNickname());
					userInfo.put("sex", parent.getSex());
				} else {
					status = "-1";
				}
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "-2";
		}
		result.put("result", userInfo);
		result.put("status", status);
		return result;
	}
}
