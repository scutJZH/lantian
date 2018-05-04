package org.scut.service.impl.studentImpl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.scut.dao.IAnswerDao;
import org.scut.dao.ILikeDao;
import org.scut.dao.IPostDao;
import org.scut.model.Answer;
import org.scut.model.Answerbean;
import org.scut.model.Post;
import org.scut.service.studentService.IAnserService;
import org.springframework.stereotype.Service;

@Service
public class AnserServiceImpl implements IAnserService{
	@Resource

	private IPostDao iPostDao;
	@Resource
	private IAnswerDao iAnswerDao;
	@Resource
	private ILikeDao iLkedao;


	@SuppressWarnings("finally")
	public int addanswer(Answer answer) {
		int result=1;
		
		try {
			    iAnswerDao.insert(answer);
				
			
		} catch (Exception e ){
			// TODO: handle exception
			result=0;
			e.printStackTrace();
			
		}finally {
			String postId=answer.getPostId();
			Post post=iPostDao.findpostbyid(postId);
					int num = post.getAnswerNumber() + 1;
					post.setAnswerNumber(num);
					post.setChangeTime(new Date());
					iPostDao.updateByPrimaryKeySelective(post);
			return result;
		}
		
	}
	public Answer findanswerbyid (String answerId) {
		return iAnswerDao.findanswerbyid(answerId);
	}
	@SuppressWarnings("finally")
	public int  likeanswer(String answerId,String userId) {
		int result=1;
		try {
			
			Answer answer=iAnswerDao.findanswerbyid(answerId);
			answer.setLikes(answer.getLikes()+1);
			iAnswerDao.updateByPrimaryKeySelective(answer);
			
			iLkedao.addlike(userId, answerId);
			
			result=1;
		} catch (Exception e) {
			result=0;
			e.printStackTrace();
		}finally {
			return result;
		}
	}
	@Override
	public List<Answer> findanswerbypostid(String postId) {
		// TODO 自动生成的方法存根
		
		return iAnswerDao.findanswerbypostid(postId);
	}
	@Override
	public List<Answerbean> findanswerbeanbypostid(String postId) {
		// TODO 自动生成的方法存根
		return iAnswerDao.findAnswerBeanByPostId(postId);
	}
	@Override
	public int cancel(String answerId, String userId) {
		// TODO 自动生成的方法存根
		int result=1;
		try {
			Answer answer=iAnswerDao.findanswerbyid(answerId);
			answer.setLikes(answer.getLikes()-1);
			iAnswerDao.updateByPrimaryKeySelective(answer);
			iLkedao.delete(userId, answerId);
		} catch (Exception e) {
			// TODO: handle exception
			result=0;
			e.printStackTrace();
		}
		return result;
	}
	
	

}
