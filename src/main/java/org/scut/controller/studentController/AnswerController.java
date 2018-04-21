package org.scut.controller.studentController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.controller.studentController.Json;
import org.scut.model.Answerbean;
import org.scut.service.studentService.IAnserService;
import org.scut.service.studentService.IPostService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;



@Controller
public class AnswerController {
	@Resource
	private IAnserService anserService;
	@Resource
	private IPostService postService;
	
	
	@RequestMapping(value="/student/answer/like")
	@ResponseBody
	public Map<String, Object>  like(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String answerId=(String)map.get("answerId");
		String userId=(String)map.get("userId");

		int result=anserService.likeanswer(answerId,userId);
		if (result>0) {
			return Json.getJson();
		}
		else {
			return Json.getJson(-1,"");
		}
		
	}
	
	@RequestMapping("/student/answer/cancel")
	@ResponseBody
	public Map<String, Object> cancel(HttpServletRequest request, HttpServletResponse response) throws  IOException{
		
		Map<String, Object> map =ParamsTransport.getParams(request);
		String answerId=(String)map.get("answerId");
		String userId=(String)map.get("userId");
		int result=anserService.cancel(answerId, userId);
		if (result>0) {
			return Json.getJson();
		}
		else {
			return Json.getJson(-1,"");
		}
	
	
	

	}
}
