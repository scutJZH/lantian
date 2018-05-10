package org.scut.service.impl.studentImpl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.IMistakeDao;
import org.scut.model.Mistake;
import org.scut.model.Question;
import org.scut.service.studentService.IMistakeService;
import org.scut.util.Base64Analysis;
import org.springframework.stereotype.Service;


@Service
public class MistakeServiceImpl  implements IMistakeService {

@Resource IMistakeDao iMistakeDao;
	
	public List<Mistake> findbysubjectid(String subjectId ,String studentId) {
		List<Mistake> mistakes =iMistakeDao.findmistakebysub(subjectId ,studentId);
		return mistakes;
		
	}

	
	public List<Mistake> findbytype(String subjectId, String studentId, String questionType) {
		List<Mistake>mistakes=iMistakeDao.findmistakebytype(subjectId, studentId, questionType);
		return mistakes;
	}



	public List<Mistake> findbystudentId(String studentId) {
		List<Mistake>mistakes =iMistakeDao.findmistakebystu(studentId);
		return mistakes;
	}


	
	public int delete(String studentId, String questionId) {
		int status;
		try {
			iMistakeDao.delete(studentId, questionId);
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
			status=-2;
		}
		return status;
	}

	public int updatenote(String studentId, String questionId, String note) {
		int status;
		try {
			iMistakeDao.updatenote(studentId, questionId, note);
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
			status=-2;
		}
		return status;
	}


	
	public int insertquestion(Question question) {
		int status;
		try {
			iMistakeDao.insertquestion(question);
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
			status=-2;
		}
		return status;
	}


	
	public String inserttitle(String titleContent) {
		String titleId=UUID.randomUUID().toString();
		try {
			iMistakeDao.inserttitle(titleId, titleContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titleId;
	}


	
	public int insertmistake(String questionId, String studentId, String questionType, String note, String lastAnswer) {
		int status;
		try {
			
			Date createTime=new Date();
			iMistakeDao.insertmistake(studentId, questionId, note, questionType, lastAnswer, createTime);
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
			status=-2;
		}
		return status;
	}


	
	public int updateanswer(String studentId, String questionId, String newAnswer) {
		int status;
		try {
			Date changeTime=new Date();
			iMistakeDao.updateanswer(studentId, questionId, newAnswer,changeTime);
			status=1;
		} catch (Exception e) {
			e.printStackTrace();
			status=-2;
		}
		return status;
	}


	
	public String inserttitle2(String imgbase64, String filepath) {
		String titleId=UUID.randomUUID().toString();
		try {
			String picPath=Base64Analysis.analysisPic(UUID.randomUUID().toString(), filepath, imgbase64);
			iMistakeDao.inserttitle2(titleId, picPath);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		return titleId;
	}

}
