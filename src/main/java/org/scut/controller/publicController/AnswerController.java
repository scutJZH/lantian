package org.scut.controller.publicController;

import javax.annotation.Resource;

import org.scut.controller.Json;
import org.scut.service.IAnserService;
import org.scut.service.IPostService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AnswerController {
	@Resource
	private IAnserService anserService;
	@Resource
	private IPostService postService;
	@RequestMapping(value="/student/answer/like.do")
	@ResponseBody
	public Object like(@RequestParam(value="answerId",required=false)String answerid) {
		int result=anserService.likeanswer(answerid);
		if (result>0) {
			return Json.getJson();
		}
		else {
			return Json.getJson(false,"errormsg","");
		}
		
	}

	
	

}
