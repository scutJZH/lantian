package org.scut.service.impl.teacherImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.scut.dao.*;
import org.scut.service.teacherService.IClassManageModuleService;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Service;
@Service(value="classManageModuleService")
public class ClassManageModuleServiceImpl implements IClassManageModuleService{
	@Resource
	private ITeacherCourseModuleService teacherCourseModuleService;
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IClass_paperDao class_paperDao;
	@Resource
	private ITeacher_classDao teacher_classDao;
	@Resource
	private IClassDao classDao;
	@Resource
	private IQuestionDao questionDao;
	@Resource //杩欎釜鍔熻兘杩樻病娴�
	private IPptDao pptDao;
	@Resource
	private IStudent_paperDao student_paperDao;
	@Resource
	private ISolutionDao solutionDao; 
	@Resource
	private ITitleDao titleDao;
	public HashMap<String,Object> getClassListTwo(String teacherId){
		List<HashMap<String,Object>> r1=this.teacherCourseModuleService.getClassList(teacherId);
		for(int i=0;i<r1.size();i++) {
			List<HashMap<String,Object>> r2=this.studentDao.getStudentByClassId(String.valueOf((r1.get(i).get("classId"))));
			r1.get(i).put("studentList", r2);
			}
		return r1;
	}
	
	public int addStudent(String teacherId,String studentId,String classId) {
		HashMap<String,Object> r1=studentDao.checkStudentExist(studentId);
		
		if(String.valueOf(r1.get("studentId"))==null)return -1;//-1means student doesn't exist
		else {
			if (String.valueOf(r1.get("classId"))!=null) {
				return -2;//-2means student already in other class
			}
			else {
				int r2=studentDao.addStudent(studentId,classId);
				int recentStudentNumber=classDao.getStudentNumber(classId)+1;
				int r3=classDao.addStudent(classId,recentStudentNumber);//add student_number
				if((r2==r3)&&(r2==1))return 1;
				else return 0;//0means insert error
				}
		}
	}
	public HashMap<String,Object> addClass(String schoolName,String teacherId,int grade,String classId,String className) {
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		
		//get current time
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String createTime=date.format(new Date());
		int studentNumber=0;
		try {
			HashMap<String,Object> r1=this.classDao.judgeClassExist(classId);
			if(r1==null) {
				try {
				this.classDao.addClass(schoolName,teacherId,grade,classId,className,createTime,studentNumber);
				this.teacher_classDao.addClass(teacherId,classId);
				}catch(Exception e) {
					e.printStackTrace();
					status = "-2";
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			status = "-2";
		}
		result.put("status", status);
		result.put("result",status);
		return result;
	}
}
