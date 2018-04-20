package org.scut.service.impl.studentImpl;

import javax.annotation.Resource;


import org.scut.dao.IPostDao;
import org.scut.model.Post;
import org.scut.service.studentService.IPostService;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements IPostService {
	@Resource

	private IPostDao IPostDao;
	
	@Resource
	private IPostDao postMapper;

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
