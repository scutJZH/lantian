package org.scut.service.impl.studentImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.scut.dao.IClassDao;
import org.scut.dao.ISchoolDao;
import org.scut.dao.IStudentDao;
import org.scut.model.School;
import org.scut.dao.IStudent_classDao;
import org.scut.dao.ITeacher_classDao;
import org.scut.model.Student;
import org.scut.service.studentService.IStudentInfoService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;


@Service("studentInfoService")
public class StudentInfoServiceImpl implements IStudentInfoService{
	
	@Resource
	private IStudentDao studentDao;
	@Resource
	private ISchoolDao schoolDao;
	@Resource
	private IClassDao classdao;
	@Resource
	private IStudent_classDao student_classdao;
	@Resource
	private ITeacher_classDao teacher_classdao;

	


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
				String schoolId = student.getSchoolId();
				School schoolInfo = null;
				if(schoolId != null){
					schoolInfo = schoolDao.getSchoolById(schoolId);
				}
				userInfo.put("schoolId", schoolId);
				if (schoolInfo!=null) {
					userInfo.put("schoolName", schoolInfo.getSchoolName());
					userInfo.put("city", schoolInfo.getCity());
				}else {
					userInfo.put("schoolName", "");
					userInfo.put("city", "");
				}
				
				
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
			String birthdayStr, String sex, String schoolId, String filePath) {

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
				student.setSchoolId(schoolId);
				
				studentDao.updateStudent(student);
				
				studentInfo.put("picPath", GlobalVar.picPath+picPath);
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


	@Override
	public int joinclass(String studentId, String classId) {
		int status;
		int studentN=classdao.getStuNumber(classId);
		if (student_classdao.isRelationshipExist(studentId, classId)) {
			status=-3;
		}else {
			try {
				classdao.updateStudentNumber(classId, studentN+1);
				student_classdao.insertRelationship(studentId, classId);
				Student student=studentDao.getStudentById(studentId);
				if (student.getClassIdList()!=null&&student.getClassIdList().length()>0) {
					
					String string=","+classId;
					student.setClassIdList(string);
					studentDao.updateStudent(student);
				}else {
					student.setClassIdList(classId);
					studentDao.updateStudent(student);
				}
				
				status=1;
				
			} catch (Exception e) {
				status=-2;
			}
		}
		
		return status;
	}


	@Override
	public Map<String, Object> getclasslist(String studentId) {
		Map<String, Object> result = new HashMap<String, Object>();
		String stastus="1";
		try {
			List<Map<String, Object>>classinfolist=student_classdao.getClassInfoListByStudentId(studentId);
			for(Map<String, Object>classinfo:classinfolist) {
				String classId=(String)classinfo.get("classId");
				String picpath=(String)classinfo.get("picPath");
				classinfo.put("picPath", "/img/"+picpath);
				List<Map<String, Object>> studentList = student_classdao.getStudentInfoListByClassId(classId);
				for(Map<String, Object>studentinfo:studentList) {
					String picpath1=(String)studentinfo.get("picPath");
					studentinfo.put("picPath", "/img/"+picpath1);
				}
				List<Map<String, Object>> teacherList = teacher_classdao.getTeacherInfoListByClassId(classId);
				for(Map<String, Object>teacherinfo:teacherList) {
					String picpath2=(String)teacherinfo.get("picPath");
					teacherinfo.put("picPath","/img/"+ picpath2);
				}
				classinfo.put("studentList", studentList);
				classinfo.put("teacherList", teacherList);
			}
			result.put("result", classinfolist);
		} catch (Exception e) {
			e.printStackTrace();
			stastus="-2";
		}
		
		result.put("status", stastus);
		return result;
	}


	@Override
	public int quitclass(String studentId, String classId) {
		int status;
		int studentN=classdao.getStuNumber(classId);
		try {
			classdao.updateStudentNumber(classId, studentN-1);
			student_classdao.deleteRelationship(studentId, classId);
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
			status=-2;
		}
		return status;
	}

	
}
