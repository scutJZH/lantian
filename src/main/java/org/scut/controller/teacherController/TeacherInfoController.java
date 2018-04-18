package org.scut.controller.teacherController;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.publicService.IGetMyInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherInfoController {

	@Resource
	private IGetMyInfoService getMyInfoService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getParentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String parentId = (String)m.get("teacherId");
		
		Map<String, Object> result = getMyInfoService.getMyInfo("2", parentId);
		
		return result;
	}

}
