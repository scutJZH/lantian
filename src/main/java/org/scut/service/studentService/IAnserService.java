package org.scut.service.studentService;



import org.scut.model.Answer;
import org.scut.model.Answerbean;
import java.util.List;

public interface IAnserService {
		int addanswer(Answer answer);
		Answer findanswerbyid(String answerId);
		int likeanswer(String answerId,String userId);
		List<Answer> findanswerbypostid (String postId);
		List<Answerbean> findanswerbeanbypostid (String postId);
		int cancel(String answerId,String userId);
}
