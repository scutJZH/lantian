package org.scut.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;


import org.scut.dao.IAnswerDao;
import org.scut.dao.IPostDao;
import org.scut.model.Answer;
import org.scut.model.Post;
import org.scut.service.IAnserService;
import org.springframework.stereotype.Service;

@Service
public class AnserServiceImpl implements IAnserService{
	@Resource
	private IPostDao iPostDao;
	@Resource
	private IAnswerDao IAnswerDao;
	@SuppressWarnings("finally")
	public int addanswer(Answer answer) {
		int result=1;
		try {
			result=IAnswerDao.insert(answer);
			if(result>0) {
				String postid=answer.getPostId();
				Post post=iPostDao.findpostbyid(postid);
				post.setAnswerNumber(post.getAnswerNumber()+1);
				post.setChangeTime(new Date());
			}
		} catch (Exception e ){
			// TODO: handle exception
			result=0;
			e.printStackTrace();
			
		}finally {
			return result;
		}
		
	}
	public Answer findanswerbyid (String answerId) {
		return IAnswerDao.findanswerbyid(answerId);
	}
	@SuppressWarnings("finally")
	public int  likeanswer(String answerId) {
		int result=1;
		try {
			Answer answer=IAnswerDao.findanswerbyid(answerId);
			answer.setLikes(answer.getLikes()+1);
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
		return IAnswerDao.findanswerbypostid(postId);
	}
	

}
