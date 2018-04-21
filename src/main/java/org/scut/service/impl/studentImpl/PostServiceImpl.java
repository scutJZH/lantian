package org.scut.service.impl.studentImpl;

import java.util.List;

import javax.annotation.Resource;


import org.scut.dao.IPostDao;
import org.scut.dao.IStudentDao;
import org.scut.model.Post;
import org.scut.model.Student;
import org.scut.service.studentService.IPostService;
import org.springframework.stereotype.Service;


@Service
public class PostServiceImpl implements IPostService {
@Resource
private IPostDao IPostDao;
@Resource
private IStudentDao IStudentDao;
	
	

	@SuppressWarnings("finally")
	public int addpost(Post post) {
		int result=1;
		try{
			IPostDao.insertSelective(post);
			Student student=IStudentDao.getStudentById(post.getStudentId());
			post.setPicPath(student.getPicPath());
			
			
			
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
	public List<Post> getpostfuzzy(String keyWord){
		return IPostDao.getpostfuzzy(keyWord);
	}

	@Override
	public List<Post> gettotal() {
		List<Post> posts = IPostDao.getallpost();
		return posts;
	}
	

}
