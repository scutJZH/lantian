package org.scut.service.studentService;

import java.util.List;

import org.scut.model.Mistake;
import org.scut.model.Question;

public interface IMistakeService {
	List<Mistake> findbysubjectid(String subjectId , String studentId);
	List<Mistake> findbytype(String subjectId,String studentId,String questionType);
	List<Mistake> findbystudentId(String studentId);
	int delete (String studentId,String questionId);
	int updatenote(String studentId,String questionId,String note);
	int insertquestion(Question question);
	String inserttitle(String titleContent);
	int insertmistake(String questionId,String studentId ,String questionType,String note,String lastAnswer);
	int updateanswer(String studentId,String questionId,String newAnswer);
	String inserttitle2(String imgbase64,String filepath);

}
