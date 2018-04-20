package org.scut.service.studentService;

import org.scut.model.Post;

public interface IPostService {
	int addpost(Post post);
	Post findpostbyid(String postId);

}
