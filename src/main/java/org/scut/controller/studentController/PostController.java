package org.scut.controller.studentController;




import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.model.Answer;
import org.scut.model.Answerbean;
import org.scut.model.Post;
import org.scut.service.studentService.IAnserService;
import org.scut.service.studentService.ILikeService;
import org.scut.service.studentService.IPostService;
import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.scut.util.ParamsTransport;
import org.springframework.cglib.util.ParallelSorter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



	

@Controller
public class PostController {
	
	@Resource
	private IPostService postService;
	@Resource
	private IAnserService answerService;
	@Resource
	private ILikeService ILikeService;
	
	@RequestMapping("/post/publish")
	@ResponseBody

	public Map<String, Object>  addpost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String postTitle=(String)map.get("postTitle");
		String postContent=(String)map.get("postContent");
		String studentId=(String)map.get("studentId");
		String postSubject=(String)map.get("postSubject");
		String imgbase64=(String)map.get("imgbase64");
		Post post= new Post();
		String filePath=this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.picPath;
		String picPath;
		int result;
		try {
			picPath=Base64Analysis.analysisPic(UUID.randomUUID().toString(), filePath, imgbase64);
			post.setPostPic(picPath);
			post.setPostId(UUID.randomUUID().toString());
			post.setPostTitle(postTitle);
			post.setStudentId(studentId);
			post.setSubjectId(postSubject);
			post.setPostContent(postContent);
			post.setCreateTime(new Date());
			result= postService.addpost(post);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			result=-2;
		}
		
		
		
		return Json.getJson(result,"");
	
	}
	
	@RequestMapping(value="/post/search")
	@ResponseBody
public Map<String, Object>  search(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String keyWord=(String)map.get("keyWord");
		if (keyWord==null||keyWord=="") {
			return Json.getJson(-1, "");
		}
		keyWord="%"+keyWord+"%";
		List<Post> posts= postService.getpostfuzzy(keyWord);
		
		
		return Json.getJson(1,posts);
		
	}
	@RequestMapping(value="/post/answerPost")
	@ResponseBody
public Map<String, Object>  answerpost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String postId=(String)map.get("postId");
		String answerContent=(String)map.get("answerContent");
		String studentId=(String)map.get("userId");

		Answer answer=new Answer();
		answer.setAnswerId(UUID.randomUUID().toString());
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
			return Json.getJson(-1,"");
		}
	}
	@RequestMapping(value="/post/atanswer")
	@ResponseBody
public Map<String, Object>  answerAnswer(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String postId=(String)map.get("postId");
		String studentId=(String)map.get("userId");
	
		String answerContent=(String)map.get("answerContent");
		
		Answer answer=new Answer();
		answer.setPostId(postId);
		answer.setAnswerId(UUID.randomUUID().toString());
		answer.setAnswerContent(answerContent);
		answer.setAnswerTime(new Date());
		answer.setStudentId(studentId);
		answer.setLikes(0);
		
		int result = answerService.addanswer(answer);
		if(result>0) {
			return Json.getJson();
		}
		else {
			return Json.getJson(-1,"");
		}
	}
	@RequestMapping(value="/post/getanswer")
	@ResponseBody
  public Map<String, Object>  getAnswerbean(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String postId=(String)map.get("postId");
		String userId=(String)map.get("userId");

		
		Map<String, Object> result= new HashMap<String,Object>();
		try {
			List<Answerbean> answerbeans =answerService.findanswerbeanbypostid(postId);
			for(int i = 0; i < answerbeans.size(); i++) {
				boolean b = ILikeService.islike( answerbeans.get(i).getAnswerId(),userId);
				answerbeans.get(i).setIslike(b);
				
				
			}
			
			result.put("status", 1);
			result.put("result", answerbeans);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----------------"+e.getMessage());
			result.put("status",-2);
		} finally {
			return result;
		}
		
	}
	@RequestMapping("/getpost")
	@ResponseBody
	public Map<String, Object> getpost(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String postId=(String)map.get("postId");
		Post post=postService.findpostbyid(postId);
		if (post==null) {
			return Json.getJson(-2,"");
		}
		return Json.getJson(1,post);
	
	}
	@RequestMapping("/post")
	@ResponseBody
	public Map<String, Object> gettotal(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		Integer count=Integer.valueOf((String) map.get("count"));
		List<Post> result=new ArrayList<>();
		List<Post> posts= postService.gettotal();
		int size=posts.size();
		if(size>count+10&size==count+10) {
			for(int i =count;i<count+10;i++) {
				posts.get(i).setPicPath("/img/"+posts.get(i).getPicPath());
				posts.get(i).setPostPic("/img/"+posts.get(i).getPostPic());
				result.add(posts.get(i));
			}
		}else if (size<count+10) {
			for(int j=count;j<size;j++) {
				result.add(posts.get(j));
			}
		}
		
		return Json.getJson(1,result);	
	
}
}
	

