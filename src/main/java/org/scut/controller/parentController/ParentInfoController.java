package org.scut.controller.parentController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.parentService.IParentService;
import org.scut.service.publicService.IGetMyInfoService;
import org.scut.service.studentService.IStudentService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/parent")
public class ParentInfoController {
	
	@Resource
	private IGetMyInfoService getMyInfoService;
	
	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getParentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String parentId = (String)m.get("parentId");
		
		Map<String, Object> result = getMyInfoService.getMyInfo("3", parentId);
		
		return result;
	}

}
