package org.scut.service.impl.teacherImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
	@Resource //杩欎釜鍔熻兘杩樻病娴�
	private IPptDao pptDao;
	@Resource
	private IStudent_paperDao student_paperDao;
	@Resource
	private ISolutionDao solutionDao; 
	@Resource
	private ITitleDao titleDao;
	
	public List<HashMap<String,Object>> selectList(String teacherId,String classId){
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
	public List<HashMap<String,Object>> getCorrectionList(String teacherId,String classId){
		List<HashMap<String,Object>> r1= this.class_paperDao.getCorrectionList(teacherId,classId);
		int r2=this.classDao.getStudentNumber(classId);
		/**for unsubmittedNumber**/
		for(int i=0;i<r1.size();i++) {
			int unsubmittedNumber=(r2-(Integer)(r1.get(i).get("submitNumber")));
			r1.get(i).put("unsubmittedNumber", Integer.toString(unsubmittedNumber));
		}
		return r1;
	}
	public List<HashMap<String,Object>> getRankList(String teacherId,String classId){
		return this.class_paperDao.getCorrectionList(teacherId, classId);
	}
	public List<HashMap<String,Object>> getRankDetails(String paperId){
		List<HashMap<String,Object>> r1= this.student_paperDao.getRankDetails(paperId);
		/**鎻掑叆鎺掑簭浣垮緱璇ョ粨鏋滄槸鎸夊垎鏁颁粠灏忓埌澶ф帓鐨�**/
		int n = r1.size();
		HashMap<String,Object>  temp;
        for(int i = 1; i< n; i++) {
            for(int j = i; j>0 && (Integer)(r1.get(j-1).get("score"))> (Integer)(r1.get(j).get("score")); j--) {
            	temp = r1.get(j);
            	r1.get(j).clear();
                r1.get(j).putAll(r1.get(j-1));
                r1.get(j-1).clear();
                r1.get(j-1).putAll(temp);
            }
        }
        /**鎻掑叆鎺掑簭浣垮緱璇ョ粨鏋滄槸鎸夊垎鏁颁粠灏忓埌澶ф帓鐨�**/
       /**涓婇潰鐨勭粨鏋滄病鏈夋帓鍚嶏紝涓嬮潰缁欑粨鏋滃姞鍏ユ帓鍚嶆暟瀛梤ankNumber**/
        int n2 = r1.size();
        for(int i = 0; i< n2; i++) {
        	r1.get(i).put("rankNumber", String.valueOf(r1.size()-(i+1)));
        }
        
        return r1;
	}
	public List<HashMap<String,Object>> getCorrectStudentList(String teacherId,String paperId){
		List<HashMap<String,Object>> r1= this.student_paperDao.getCorrectStudentList(teacherId,paperId);
		/**閫氳繃鍒ゆ柇鎬诲垎鏄惁涓�0鏉ュ垽鏂槸鍚﹀凡鎵规敼**/
		for(int i=0;i<r1.size();i++) {
			if((Integer)((r1.get(i)).get("score"))>0) {
				r1.get(i).put("correctedBox", "true");
			}
			else {
				r1.get(i).put("correctedBox", "false");
				}
		}
		return r1;
	}
	public List<HashMap<String,Object>> getCorrectQuestionList(String teacherId,String paperId,String studentId){
		List<HashMap<String,Object>> r1= this.solutionDao.getSolution(teacherId,paperId,studentId);
		for(int i=0;i<r1.size();i++) {
			/**杩欓噷闇�瑕佹敞鎰忎娇鐢ㄤ簡string鍙傛暟鐨�,
			 * 杩欓噷鎶妔olutionDao.getSolution鐨勬煡璇㈢粨鏋滃綋浣滀簡getTitleContent鐨勫弬鏁般��**/
			HashMap<String,Object> r2=this.titleDao.getTitleContent((String)(r1.get(i).get("questionId")));
			r1.get(i).putAll(r2);
		}
		return r1;
	}

	//14.鑾峰彇涓昏/瀹㈣棰樺垪琛�
/**
	public List<Map<String,Object>> getSubjectiveOrObjectiveList(String teacherId,String questionType){
		//涓嬮潰杩欎釜鍒嗗姛鑳藉彲鑳介渶瑕佺敤鍒皊ubjectId锛屼笉杩囨殏鏃跺墠绔病浼�
		if(questionType=="1")return questionDao.getSubjectiveList();
		if(questionType=="2")return questionDao.getObjectiveList();
	}*/
	/**15.鍒犻櫎涓�閬撻鐩�
	public int deleteQuestion(String teacherId,String questionnId) {
		return this.questionDao
	}
	**/

	//16.create objective completed!
	public int createObjective(String teacherId,
			String titleContent,
			String optioanA,String optionB,String optionC,String optionD,
			String answer) {
		//generate UUID
		UUID titleId=UUID.randomUUID();
		int r1=this.questionDao.createObjective(teacherId,
				titleContent,titleId,
				optioanA,optionB,optionC,optionD,
				answer);
		int r2=this.titleDao.createObjective(titleId,titleContent);
		if(r1==1&&r2==1)return 1;
		else return 0;
	}
	//17.
	//18.checkTitle
	public Map<String,Object> checkTitle(String questionId){
		Map<String,Object> r1=this.questionDao.checkTitle(questionId);
		if(((String)r1.get("optionA")).length()>0) r1.put("questionType", 2) ;
		else r1.put("questionType", 1);
		return r1;
	}
	@Override
	public int createSubjective(String teacherId, String pic) {
		// TODO Auto-generated method stub
		return 0;
	}

}
