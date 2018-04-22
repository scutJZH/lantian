package org.scut.service.impl.teacherImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.*;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	
	public HashMap<String,Object> selectList(String teacherId,String classId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1=this.class_paperDao.selectList(teacherId, classId);
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	@Override
	public HashMap<String,Object> deleteList(List<String> paperId) {
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			int r1=this.class_paperDao.deleteList(paperId);
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getClassList(String teacherId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1=this.teacher_classDao.getClassList(teacherId);
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getQuestionList(String sunjectId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1=this.questionDao.getQuestionList(sunjectId);
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getPptList(String teacherId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1=this.pptDao.getPptList(teacherId);
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getCorrectionList(String teacherId,String classId){
		
		
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1= this.class_paperDao.getCorrectionList(teacherId,classId);
			int r2=this.classDao.getStudentNumber(classId);
			/**for unsubmittedNumber**/
			for(int i=0;i<r1.size();i++) {
				int unsubmittedNumber=(r2-(Integer)(r1.get(i).get("submitNumber")));
				r1.get(i).put("unsubmittedNumber", Integer.toString(unsubmittedNumber));
			}
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getRankList(String teacherId,String classId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1=this.class_paperDao.getCorrectionList(teacherId, classId);
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getRankDetails(String paperId){
        String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
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
	        result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getCorrectStudentList(String teacherId,String paperId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
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
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	public HashMap<String,Object> getCorrectQuestionList(String teacherId,String paperId,String studentId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			List<HashMap<String,Object>> r1= this.solutionDao.getSolution(teacherId,paperId,studentId);
			for(int i=0;i<r1.size();i++) {
				/**杩欓噷闇�瑕佹敞鎰忎娇鐢ㄤ簡string鍙傛暟鐨�,
				 * 杩欓噷鎶妔olutionDao.getSolution鐨勬煡璇㈢粨鏋滃綋浣滀簡getTitleContent鐨勫弬鏁般��**/
				HashMap<String,Object> r2=this.titleDao.getTitleContent((String)(r1.get(i).get("questionId")));
				r1.get(i).putAll(r2);
			}
			result.put("result",r1);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
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
	//17createSubjective completed
	@Override
	public HashMap<String,Object> createSubjective(String teacherId,MultipartFile picSubjective,
			String picPath,
			MultipartFile picAnswer,String answer,String subjectId,int grade) {
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<MultipartFile> file=new ArrayList<MultipartFile>();
		file.add(picSubjective);
		file.add(picAnswer);
		try{
			String questionId=UUID.randomUUID().toString();
			String titleId=UUID.randomUUID().toString();
			for(MultipartFile subfile:file) {
			File newFileQuestion=new File(picPath);
			FileOutputStream fos=new FileOutputStream(newFileQuestion);
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			bos.write(subfile.getBytes());
			fos.close();
			bos.close();
			}
			this.questionDao.createSubjective(questionId,titleId,subjectId,grade,answer);
			this.titleDao.createSubjective(titleId,picPath);
			result.put("result",status);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	//16.create objective completed!
	public HashMap<String,Object> createObjective(String subjectId,int grade,String optionA,String optionB,String optionC,String optionD,
			String answer,MultipartFile picA,MultipartFile picB,MultipartFile picC,MultipartFile picD,MultipartFile picPathPicture
			,String opaPicPath,String opbPicPath,String opcPicPath,String opdPicPath,String picPath,String titleContent){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		List<MultipartFile> file=new ArrayList<MultipartFile>();
		file.add(picA);
		file.add(picB);
		file.add(picC);
		file.add(picD);
		file.add(picPathPicture);
		try{
			String questionId=UUID.randomUUID().toString();
			String titleId=UUID.randomUUID().toString();
			this.questionDao.createObjective(questionId,titleId,subjectId,grade,optionA,optionB,optionC,optionD,
			answer,opaPicPath,opbPicPath,opcPicPath,opdPicPath);
			for(MultipartFile subfile:file) {
			File newFileQuestion=new File(picPath);
			FileOutputStream fos=new FileOutputStream(newFileQuestion);
			BufferedOutputStream bos=new BufferedOutputStream(fos);
			bos.write(subfile.getBytes());
			fos.close();
			bos.close();
			}
			this.titleDao.createObjective(titleId,titleContent,picPath);
			result.put("result",status);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	//17.
	//18.checkTitle
	public HashMap<String,Object> checkTitle(String questionId){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			Map<String,Object> r1=this.questionDao.checkTitle(questionId);
			if(((String)r1.get("optionA"))==null) r1.put("questionType", 1);
			else if(((String)r1.get("optionA")).length()>0) r1.put("questionType", 2);
			result.put("result",status);
		}
		catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}

}
