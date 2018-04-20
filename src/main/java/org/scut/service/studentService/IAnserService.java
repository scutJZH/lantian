package org.scut.service.studentService;



import org.scut.model.Answer;

public interface IAnserService {
		int addanswer(Answer answer);
		Answer findanswerbyid(String answerId);
		int likeanswer(String answerId);
		java.util.List<Answer> findanswerbypostid (String postId);
}
