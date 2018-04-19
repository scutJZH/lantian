package org.scut.controller.teacherController;

import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.teacherService.ITeacherInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/teacher")
public class TeacherInfoController {

	@Resource
	private ITeacherInfoService teacherInfoService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getParentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String teacherId = (String)m.get("teacherId");
		
		Map<String, Object> result = teacherInfoService.getTeacherInfo(teacherId);
		
		return result;
	}

}
