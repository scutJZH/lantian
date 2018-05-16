package org.scut.controller.parentController;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.parentService.IChildInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/parent/children")
public class ChildInfoController {
	@Resource
	private IChildInfoService childInfoService;
	
	@RequestMapping("/homework")
	@ResponseBody
	public Map<String, Object> getChildHomework(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String studentId = (String)m.get("studentId");
		String subjectId = (String)m.get("subjectId");
		String classId = (String)m.get("classId");
		String parentId = (String)m.get("parentId");
		
		Map<String, Object> result = childInfoService.getChildStudy(studentId, parentId,classId, subjectId, "1");
		
		return result;
	}
	
	@RequestMapping("/exam")
	@ResponseBody
	public Map<String, Object> getChildExam(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);	
		
		String studentId = (String)m.get("studentId");
		String subjectId = (String)m.get("subjectId");
		String classId = (String)m.get("classId");
		String parentId = (String)m.get("parentId");
		
		Map<String, Object> result = childInfoService.getChildStudy(studentId, parentId, classId, subjectId, "2");
		
		return result;
	}
}
