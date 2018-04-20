package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.scut.dao.*;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Service;

@Service(value="teacherCourseModuleService")
public class TeacherCourseModuleServiceImpl implements ITeacherCourseModuleService{
	@Resource
	private IClass_paperDao class_paperDao;
	@Resource
	private ITeacher_classDao teacher_classDao;
	@Resource
	private IClassDao classDao;
	@Resource
	private IQuestionDao questionDao;
	@Resource 
	private IPptDao pptDao;
	@Resource
	private IStudent_paperDao student_paperDao;
	@Resource
	private ISolutionDao solutionDao; 
	@Resource
	private ITitleDao titleDao;
	
	public ArrayList<HashMap<String,Object>> selectList(String teacherId,String classId){
		return class_paperDao.selectList(teacherId, classId);
	}
	@Override
	public int deleteList(List<String> paperId) {
		return class_paperDao.deleteList(paperId);
	}
	public List<HashMap<String,Object>> getClassList(String teacherId){
		return this.teacher_classDao.getClassList(teacherId);
	}
	public List<HashMap<String,Object>> getQuestionList(String sunjectId){
		return this.questionDao.getQuestionList(sunjectId);
	}
	public List<HashMap<String,Object>> getPptList(String teacherId){
		return this.pptDao.getPptList(teacherId);
	}
	public List<HashMap<String,String>> getCorrectionList(String teacherId,String classId){
		List<HashMap<String,String>> r1= this.class_paperDao.getCorrectionList(teacherId,classId);
		int r2=this.classDao.getStudentNumber(classId);
		for(int i=0;i<r1.size();i++) {
			int unsubmittedNumber=(r2-Integer.parseInt(r1.get(i).get("submitNumber")));
			r1.get(i).put("unsubmittedNumber", Integer.toString(unsubmittedNumber));
		}
		return r1;
	}
	public List<HashMap<String,String>> getRankList(String teacherId,String classId){
		return this.class_paperDao.getCorrectionList(teacherId, classId);
	}
	public List<HashMap<String,String>> getRankDetails(String paperId){
		List<HashMap<String,String>> r1= this.student_paperDao.getRankDetails(paperId);
		/**插入排序使得该结果是按分数从小到大排的**/
		int n = r1.size();
		HashMap<String,String>  temp;
        for(int i = 1; i< n; i++) {
            for(int j = i; j>0 && Integer.parseInt(r1.get(j-1).get("score"))> Integer.parseInt(r1.get(j).get("score")); j--) {
            	temp = r1.get(j);
            	r1.get(j).clear();
                r1.get(j).putAll(r1.get(j-1));
                r1.get(j-1).clear();
                r1.get(j-1).putAll(temp);
            }
        }
        /**插入排序使得该结果是按分数从小到大排的**/
       /**上面的结果没有排名，下面给结果加入排名数字rankNumber**/
        int n2 = r1.size();
        for(int i = 0; i< n2; i++) {
        	r1.get(i).put("rankNumber", String.valueOf(r1.size()-(i+1)));
        }
        
        return r1;
	}
	public List<HashMap<String,String>> getCorrectStudentList(String teacherId,String paperId){
		List<HashMap<String,String>> r1= this.student_paperDao.getCorrectStudentList(teacherId,paperId);
		/**通过判断总分是否为0来判断是否已批改**/
		for(int i=0;i<r1.size();i++) {
			if(Integer.parseInt(r1.get(i).get("totalScore"))>0) {
				r1.get(i).put("correctedBox", "true");
			}
			else {
				r1.get(i).put("correctedBox", "false");
				}
		}
		return r1;
	}
	public List<HashMap<String,String>> getCorrectQuestionList(String teacherId,String paperId,String studentId){
		List<HashMap<String,String>> r1= this.solutionDao.getSolution(teacherId,paperId,studentId);
		for(int i=0;i<r1.size();i++) {
			/**这里需要注意使用了string参数的,
			 * 这里把solutionDao.getSolution的查询结果当作了getTitleContent的参数。**/
			HashMap<String,String> r2=this.titleDao.getTitleContent(r1.get(i).get("questionId"));
			r1.get(i).putAll(r2);
		}
		return r1;
	}
}
