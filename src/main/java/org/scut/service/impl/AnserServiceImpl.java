package org.scut.service.impl;

import java.util.Date;

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
	private IPostDao postMapper;
	@Resource
	private IAnswerDao answerMapper;
	@SuppressWarnings("finally")
	public int addanswer(Answer answer) {
		int result=1;
		try {
			result=answerMapper.insert(answer);
			if(result>0) {
				Integer postid=answer.getPostId();
				Post post=postMapper.findpostbypostid(postid);
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

}
