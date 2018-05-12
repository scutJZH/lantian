package org.scut.controller.publicController;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.publicService.ISchoolService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SchoolController {
	
	@Resource
	private ISchoolService schoolService;

	@RequestMapping("/getschool")
	@ResponseBody
	public Map<String, Object> getSchoolByCity(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, Object> map = ParamsTransport.getParams(request);
		String city = (String)map.get("city");
		
		Map<String, Object> result = schoolService.getSchoolByCity(city);
		
		return result;
	}

}
