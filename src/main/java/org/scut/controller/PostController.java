package org.scut.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.scut.model.Answer;
import org.scut.model.Post;
import org.scut.service.IAnserService;
import org.scut.service.IPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {
	
	/**@Resource
	private IPostService postService;
	@Resource
	private IAnserService answerService;
	
	@RequestMapping(value="/student/post/publish.do")
	@ResponseBody
	public Object publish(@RequestParam(value="postTitle",required=false)String postTitle,
						@RequestParam(value="postContent",required=false)String postContent,
						@RequestParam(value="studentId",required=false)String studentId,
						@RequestParam(value="postSubject",required=false)String postSubject) {
//					      if (postTittle==null) {
//					    	  return Json.getJson(false,"帖子标题为空")；
//					    			  
//					      }，
						Post post= new Post();
						post.setPostTitle(postTitle);
						post.setStudentId(studentId);
						post.setSubjectId(postSubject);
						post.setPostContent(postContent);
						post.setCreateTime(new Date());
						int result= postService.addpost(post);
						
						if (result>0) {
							return Json.getJson();
						}
						else {
							return Json.getJson(false,"errormsg","");
						}
						
				
	}
	
	@RequestMapping(value="/student/post/answerPost.do")
	@ResponseBody
	public Object answerPost(@RequestParam(value="postId",required=false)String postId,
							@RequestParam(value="userId",required=false)String studentId,
							@RequestParam(value="answerContent",required=false)String answerContent){
		Answer answer=new Answer();
		answer.setPostId(postId);
		answer.setAnswerContent(answerContent);
		answer.setAnswerTime(new Date());
		answer.setStudentId(studentId);
		answer.setLikes(0);
		int result = answerService.addanswer(answer);
		if(result>0) {
			return Json.getJson();
		}
		else {
			return Json.getJson(false,"errormsg","");
		}
	}
	**/

}
