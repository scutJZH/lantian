package org.scut.service.impl;

import javax.annotation.Resource;
import javax.batch.api.chunk.ItemProcessor;

import org.scut.dao.IPostDao;
import org.scut.model.Post;
import org.scut.service.IPostService;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements IPostService {
	@Resource
	private IPostDao IPostDao;
	
	@SuppressWarnings("finally")
	public int addpost(Post post) {
		int result=1;
		try{
			result = IPostDao.insertSelective(post);
		}
		catch (Exception e) {
			// TODO: handle exception
			result=0;
			e.printStackTrace();
		}finally {
			return result;
		}
	}

	public Post findpostbyid(String postId) {
		
		return IPostDao.findpostbyid(postId);
	}
	

}
