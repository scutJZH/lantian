package org.scut.service.impl.teacherImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.*;
import org.scut.service.teacherService.ITeacherCourseModuleService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import sun.misc.BASE64Decoder;  
import sun.misc.BASE64Encoder;  

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
	public HashMap<String,Object> deleteList(List<String> paperId) {
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try{
			this.class_paperDao.deleteList(paperId);
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
			HashMap<String,Object> questionTypeId=new HashMap<String,Object>();
			for(int i=0;i<r1.size();i++) {
			if(r1.get(i).get("optionA")!=null)
				questionTypeId.put("questionTypeId", "2");
			else questionTypeId.put("questionTypeId", "1");
			HashMap<String,Object> temp=r1.get(i);
			temp.putAll(questionTypeId);
			r1.set(i, temp);
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
			HashMap<String,Object> r2=this.classDao.getStudentNumber(classId);
			/**for unsubmittedNumber**/
			for(int i=0;i<r1.size();i++) {
				int unsubmittedNumber=(Integer.parseInt(String.valueOf(r2.get("studentNumber")))-(Integer)(r1.get(i).get("submitNumber")));
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
			List<LinkedHashMap<String,Object>> a= this.student_paperDao.getRankDetails(paperId);
			/**鎻掑叆鎺掑簭浣垮緱璇ョ粨鏋滄槸鎸夊垎鏁颁粠灏忓埌澶ф帓鐨�**/
			
	        /**鎻掑叆鎺掑簭浣垮緱璇ョ粨鏋滄槸鎸夊垎鏁颁粠灏忓埌澶ф帓鐨�**/
	       /**涓婇潰鐨勭粨鏋滄病鏈夋帓鍚嶏紝涓嬮潰缁欑粨鏋滃姞鍏ユ帓鍚嶆暟瀛梤ankNumber**/
	        int n2 = a.size();
	        for(int i = 0; i< n2; i++) {
	        	LinkedHashMap<String,Object> temp=a.get(i);
	        	temp.put("rankNumber", String.valueOf(i+1));
	        	a.set(i, temp);
	        }
	        result.put("result",a);
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
			if((Integer)(this.class_paperDao.getCorrectionList2(teacherId, paperId).get("submitNumber"))!=0) {
			boolean a=true;
			boolean b=false;
			for(int i=0;i<r1.size();i++) {
				if(((Integer)((r1.get(i)).get("score"))-(Integer)((r1.get(i)).get("choiceScore")))>0) {
					r1.get(i).put("correctedBox", a);
				}
				else {
					r1.get(i).put("correctedBox", b);
					}
			}
			result.put("result",r1);
			}
			else result.put("result",new ArrayList<HashMap<String,Object>>());
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
	public HashMap<String,Object> getSubjectiveOrObjectiveList(String teacherId,String questionType,String subjectId,int grade){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		try {
			List<HashMap<String,Object>> r1=this.questionDao.getSubjectiveList(subjectId,grade);
			List<HashMap<String,Object>> r2=this.questionDao.getObjectiveList(subjectId,grade);
			int str2=2;
			//should use String.equals(),not==
			if(Integer.parseInt(questionType) != str2) result.put("result",r1);
			else result.put("result",r2);
		}catch(Exception e) {
			e.printStackTrace();
			status = "-2";
			result.put("result",status);
		}
		result.put("status", status);
		return result;
	}
	/**15.鍒犻櫎涓�閬撻鐩�
	public int deleteQuestion(String teacherId,String questionnId) {
		return this.questionDao
	}
	**/
	  //base64 String to photo
    public int GenerateImage(String imgStr,String picPath)  
    {   //对字节数组字符串进行Base64解码并生成图片  
        BASE64Decoder decoder = new BASE64Decoder();  
        try
        {  byte[] b=null;
        	if(String.valueOf(imgStr.charAt(12))!="j") {
            //Base64解码  
            b = decoder.decodeBuffer(imgStr.substring(23));  //把字符串中的"data:image/jpeg;base64,"去掉
        	}
        	if(String.valueOf(imgStr.charAt(12))!="p"|String.valueOf(imgStr.charAt(12))!="b"|String.valueOf(imgStr.charAt(12))!="g") {
                //Base64解码  
                b = decoder.decodeBuffer(imgStr.substring(23));  //把字符串中的"data:image/jpeg;base64,"去掉
            	}
        	//else if(imgStr.charAt(12)=='p'||imgStr.charAt(12)=='g'||imgStr.charAt(12)=='b'||imgStr.charAt(12)=='i') {
                //Base64解码  
              //  b = decoder.decodeBuffer(imgStr.substring(22));  //把字符串中的"data:image/jpeg;base64,"去掉
            	//}
        	//else if(imgStr.indexOf('p')==12||imgStr.indexOf('b')==12||imgStr.indexOf('g')==12) {
                //Base64解码  
             //   b = decoder.decodeBuffer(imgStr.substring(22));  //把字符串中的"data:image/jpeg;base64,"去掉
            	//}
            	//else if(imgStr.charAt(12)=='p'||imgStr.charAt(12)=='g'||imgStr.charAt(12)=='b'||imgStr.charAt(12)=='i') {
                    //Base64解码  
                  //  b = decoder.decodeBuffer(imgStr.substring(22));  //把字符串中的"data:image/jpeg;base64,"去掉
                //	}
            for(int i=0;i<b.length;++i)  
            {  
                if(b[i]<0)  
                {//调整异常数据  
                    b[i]+=256;  
                }
            }
            OutputStream fos = new FileOutputStream(picPath);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            bos.write(b); 
            bos.flush();  
            fos.close();
            bos.close();  
            return 1;  
        }   
        catch (Exception e)   
        {  
            return -5;  //means trnsform fail
        }  
    }
	//17createSubjective completed
	@Override
	public HashMap<String,Object> createSubjective(String teacherId,String picSubjective,
			String picPath,
			String picAnswer,String answer,String subjectId,int grade,String picId1,String picId2) {
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> base64file=new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmap1=new HashMap<String, Object>();
		HashMap<String, Object> hashmap2=new HashMap<String, Object>();
		hashmap1.put("imgStr", picSubjective);
		hashmap1.put("picPath", picPath);
		hashmap2.put("imgStr", picAnswer);
		hashmap2.put("picPath", answer);
		base64file.add(hashmap1);
		base64file.add(hashmap2);
		result.put("picPath",picPath);
		result.put("picPath2",picAnswer);
		try{
			String questionId=UUID.randomUUID().toString();
			String titleId=UUID.randomUUID().toString();
			for(HashMap<String, Object> subfile:base64file) {
				GenerateImage(String.valueOf(subfile.get("imgStr")),String.valueOf(subfile.get("picPath")));
			}
			this.titleDao.createSubjective(titleId,GlobalVar.questionPicPath+picId1+".jpg");
			this.questionDao.createSubjective(questionId,titleId,subjectId,grade,GlobalVar.questionPicPath+picId2+".jpg");
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
			String answer,String picA,String picB,String picC,String picD,String picPathPicture
			,String opaPicPath,String opbPicPath,String opcPicPath,String opdPicPath,String picPath,String titleContent,
			String picId1,String picId2,String picId3,String picId4,String picId5){
		String status = "1";
		HashMap<String, Object> result = new HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> base64file=new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> hashmap1=new HashMap<String, Object>();
		HashMap<String, Object> hashmap2=new HashMap<String, Object>();
		HashMap<String, Object> hashmap3=new HashMap<String, Object>();
		HashMap<String, Object> hashmap4=new HashMap<String, Object>();
		HashMap<String, Object> hashmap5=new HashMap<String, Object>();
		if(picA!=null) {
		hashmap1.put("imgStr", picA);
		hashmap1.put("picPath", opaPicPath);
		base64file.add(hashmap1);
		}
		if(picB!=null) {
		hashmap2.put("imgStr", picB);
		hashmap2.put("picPath", opbPicPath);
		base64file.add(hashmap2);
		}
		if(picC!=null) {
		hashmap3.put("imgStr", picC);
		hashmap3.put("picPath", opcPicPath);
		base64file.add(hashmap3);
		}
		if(picD!=null) {
		hashmap4.put("imgStr", picD);
		hashmap4.put("picPath", opdPicPath);
		base64file.add(hashmap4);
		}
		if(picPathPicture!=null) {
		hashmap5.put("imgStr", picPathPicture);
		hashmap5.put("picPath", picPath);
		base64file.add(hashmap5);
		}
		try{
			String questionId=UUID.randomUUID().toString();
			String titleId=UUID.randomUUID().toString();
			if(base64file!=null)
			for(HashMap<String, Object> subfile:base64file) {
				GenerateImage(subfile.get("imgStr").toString(),subfile.get("picPath").toString());
			}
			if(picPathPicture!=null)
			this.titleDao.createObjective(titleId,titleContent,GlobalVar.questionPicPath+picId5+".jpg");
			if(picPathPicture==null)
			this.titleDao.createObjective(titleId,titleContent,null);
			if(picA!=null|picB!=null|picC!=null|picD!=null|picPathPicture!=null)
			this.questionDao.createObjective(questionId, titleId, subjectId, grade, optionA, optionB, optionC, optionD, answer, GlobalVar.questionPicPath+picId1+".jpg", GlobalVar.questionPicPath+picId2+".jpg", GlobalVar.questionPicPath+picId3+".jpg", GlobalVar.questionPicPath+picId4+".jpg");
			if(picA==null)
			this.questionDao.createObjective(questionId, titleId, subjectId, grade, optionA, optionB, optionC, optionD, answer, null, null,null, null);
			
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
			if(((String)r1.get("optionA"))==null) {r1.put("questionType", 1);
			r1.put("answer", r1.get("answer").toString());}
			else if(((String)r1.get("optionA")).length()>0) {r1.put("questionType", 2);
			r1.put("answer", r1.get("answer").toString().toUpperCase());}
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

}
