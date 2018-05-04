package org.scut.service.impl.teacherImpl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.scut.model.Class;
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
	private IStudyDao studyDao;
	@Resource
	private ITeacher_classDao teacher_classDao;
	@Resource
	private IClassDao classDao;
	@Resource
	private IQuestionDao questionDao;
	@Resource //鏉╂瑤閲滈崝鐔诲厴鏉╂ɑ鐥呭ù锟�
	private IPptDao pptDao;
	@Resource
	private IStudent_studyDao student_studyDao;
	@Resource
	private ISolutionDao solutionDao; 
	@Resource
	private ITitleDao titleDao;
	@Resource
	private Class newClass;
	public HashMap<String,Object> getClassListTwo(String teacherId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
		List<HashMap<String,Object>> r1=this.teacher_classDao.getClassList(teacherId);
		for(int i=0;i<r1.size();i++) {
			List<HashMap<String,Object>> r2=this.studentDao.getStudentByClassId(String.valueOf((r1.get(i).get("classId"))));
			r1.get(i).put("studentList", r2);
			}
		result.put("result",r1);
		}catch(Exception e) {
			e.printStackTrace();
			status="-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	
	public HashMap<String,Object> addStudent(String teacherId,String studentId,String classId) {
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			HashMap<String,Object> r1=studentDao.checkStudentExist(studentId);
			if(String.valueOf(r1.get("studentId"))==null)status="-3";//-3means student doesn't exist
			else {
				if (String.valueOf(r1.get("classId"))!="null") {
					status="-4";//-4means student already in other class
				}
				else {
					try {
						this.studentDao.addStudent(studentId,classId);
						int recentStudentNumber=Integer.parseInt(String.valueOf((classDao.getStudentNumber(classId).get("studentNumber"))))+1;
						this.classDao.addStudent(classId,recentStudentNumber);//add student_number
					}catch(Exception e) {
						e.printStackTrace();
						status="-2";
						result.put("result",status);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			status="-2";
			result.put("result",status);
		}
		result.put("status", status);
		result.put("result",status);
		return result;
	}
}
