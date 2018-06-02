package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.scut.service.teacherService.IClassManagementService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;
import org.scut.dao.IClassDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_classDao;
import org.scut.dao.IStudent_studyDao;
import org.scut.dao.ITeacherDao;
import org.scut.dao.ITeacher_classDao;
import org.scut.model.Class;
import org.scut.model.Student;
import org.scut.model.Teacher;

@Service
public class ClassManagementServiceImpl implements IClassManagementService{
	
	@Resource
	private Class newClass;
	@Resource
	private ITeacherDao teacherDao;
	@Resource
	private IClassDao classDao;
	@Resource
	private ITeacher_classDao teacherClassDao;
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IStudent_classDao studentClassDao;
	//for getCorrectStudentList function run correctly
	@Resource
	private IStudent_studyDao student_studyDao;
	@Override
	public Map<String, Object> createClass(String teacherId, String className, String schoolId, String grade, String headPic) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, Object> classInfo = new HashMap<String, Object>();
		
		try{
			String SchoolIdOfTeacher = teacherDao.getSchoolId(teacherId);
			if(SchoolIdOfTeacher != null){
				String classId = UUID.randomUUID().toString();
				newClass.setClassId(classId);
				newClass.setSchoolId(schoolId);
				newClass.setClassName(className);
				newClass.setCreateTeacherId(teacherId);
				newClass.setCreateTime(new Date());
				newClass.setGrade(grade);
				newClass.setTeacherNumber(1);
				String picPath = null;
				if(headPic != null){
					String filePath = this.getClass().getClassLoader().getResource("../../").getPath();
					picPath = Base64Analysis.analysisPic(classId, filePath, headPic);
					newClass.setPicPath(picPath);
				}else{
					picPath = "123.jpg";
					newClass.setPicPath("123.jpg");
				}
				newClass.setStudentNumber(0);
				
				classDao.insertClass(newClass);
				teacherClassDao.insertRelationship(teacherId, classId);
				
				classInfo.put("classId",classId);
				classInfo.put("picPath", GlobalVar.picPath+picPath);
				
			}else{
				status = "-1";
			}
		}catch(Exception e){
			status = "-2";
			e.printStackTrace();
		}
		result.put("status", status);
		result.put("result", classInfo);
		
		return result;
	}

	@Override
	public Map<String, Object> inviteStudent(String teacherId, String classId, String studentTelnumber) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			boolean isRelationshipExist = teacherClassDao.isRealtionshipExist(teacherId, classId);
			if(isRelationshipExist){
				Student student = studentDao.getStudentByTel(studentTelnumber);
				if(student != null && student.getState().equals("1")){
					String studentId = student.getId();
					isRelationshipExist = studentClassDao.isRelationshipExist(studentId, classId);
					if(!isRelationshipExist){
						studentClassDao.insertRelationship(studentId, classId);
						int studentNumber = classDao.getStuNumber(classId);
						classDao.updateStudentNumber(classId, studentNumber+1);
						String classIdListStr = student.getClassIdList();
						if(classIdListStr != null && classIdListStr.length()>0){
							String[] classIdList = classIdListStr.split(",");
							boolean flag = false;
							for(int i = 0; i<classIdList.length; i++){
								if(classIdList[i].equals(classId)){
									flag = true;
									break;
								}
							}
							if(!flag){
								classIdListStr += ","+classId;
							}
						}else{
							classIdListStr = classId;
						}
						student.setClassIdList(classIdListStr);
						studentDao.updateStudent(student);
					}else{
						status = "2";
					}
				}else{
					status = "-3";
				}
			}else{
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);

		return result;
	}

	@Override
	public Map<String, Object> inviteTeacher(String teacherId, String classId, String teacherTelnumber) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			boolean isRelationshipExist = teacherClassDao.isRealtionshipExist(teacherId, classId);
			if(isRelationshipExist){
				Teacher teacher = teacherDao.getTeacherByTel(teacherTelnumber);
				if(teacher != null && teacher.getState().equals("1")){
					isRelationshipExist = teacherClassDao.isRealtionshipExist(teacher.getId(), classId);
					if(!isRelationshipExist){
						teacherClassDao.insertRelationship(teacher.getId(), classId);
						int teacherNumber = classDao.getTeacherNumber(classId);
						classDao.updateTeacherNumber(classId, teacherNumber+1);
					}else{
						status = "2";
					}
				}else{
					status = "-3";
				}
			}else{
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);

		return result;
	}

	@Override
	public Map<String, Object> deleteClass(String classId, String teacherId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		try{
			String createTeacherId = classDao.getCreateTeacherId(classId);
			if(createTeacherId.equals(teacherId)){
				teacherClassDao.deleteRelationshipByClassId(classId);
				studentClassDao.deleteRelationshipByClassId(classId);
			}else{
				status = "-1";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);

		return result;
	}

	@Override
	public Map<String, Object> removeStudent(String teacherId, String studentId, String classId) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			String createTeacherId = classDao.getCreateTeacherId(classId);
			if(createTeacherId.equals(teacherId)){
				studentClassDao.deleteRelationship(studentId, classId);
				int studentNumber = classDao.getStuNumber(classId);
				classDao.updateStudentNumber(classId, studentNumber-1);
				//for getCorrectStudentList function run correctly
				student_studyDao.removeStudent(studentId,teacherId);
			}else{
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace(); 
			status = "-2";
			
		}
		
		result.put("status", status);
		
		return result;
	}

	@Override
	public Map<String, Object> removeTeacher(String headTeacherId, String classId, String teacherId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			String createTeacherId = classDao.getCreateTeacherId(classId);
			if(createTeacherId.equals(headTeacherId)){
				if(!headTeacherId.equals(teacherId)){
					teacherClassDao.deleteRelationship(teacherId, classId);
					int teacherNumber = classDao.getTeacherNumber(classId);
					classDao.updateTeacherNumber(classId, teacherNumber-1);
				}else{
					status = "-3";
				}
			}else{
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);
		
		return result;
	}

	@Override
	public Map<String, Object> getClasses(String teacherId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			List<Map<String, Object>> classInfoList = teacherClassDao.getClassInfoListByTeacherId(teacherId);
			for(Map<String, Object> classInfo : classInfoList){
				String classId = (String)classInfo.get("classId");
				List<Map<String, Object>> studentList = studentClassDao.getStudentInfoListByClassId(classId);
				List<Map<String, Object>> teacherList = teacherClassDao.getTeacherInfoListByClassId(classId);
				classInfo.put("studentList", studentList);
				classInfo.put("teacherList", teacherList);
			}
			result.put("result", classInfoList);
		}catch(Exception e){
			e.printStackTrace();
			status = "-2"; 
		}
		
		result.put("status", status);
		
		return result;
	}

	@Override
	public Map<String, Object> quitClass(String teacherId, String classId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			boolean isRelationshipExist = teacherClassDao.isRealtionshipExist(teacherId, classId);
			if(isRelationshipExist){
				String headTeacherId = classDao.getCreateTeacherId(classId);
				if(!headTeacherId.equals(teacherId)){
					teacherClassDao.deleteRelationship(teacherId, classId);
					int teacherNumber = classDao.getTeacherNumber(classId);
					classDao.updateTeacherNumber(classId, teacherNumber-1);
				}else{
					status = "-1";
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);
		return result;
	}

	

}
