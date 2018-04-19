package org.scut.controller.studentController;

import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.studentService.IStudentInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/student")
public class StudentInfoController {
	@Resource
	private IStudentInfoService studentInfoService;

	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getStudentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String studentId = (String)m.get("studentId");
		
		Map<String, Object> result = studentInfoService.getStudentInfo(studentId);
		
		return result;
	}

}
