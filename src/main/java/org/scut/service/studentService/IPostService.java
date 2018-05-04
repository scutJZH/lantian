package org.scut.service.studentService;

import java.util.List;

import org.scut.model.Post;

public interface IPostService {
	int addpost(Post post);
	Post findpostbyid(String postId);
	List<Post> getpostfuzzy(String keyWord);
	List<Post> gettotal();


}
