package org.scut.controller.parentController;

import java.io.IOException;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.parentService.IChildrenService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/parent")
public class ChildrenController {
	
	@Resource
	private IChildrenService childrenService;

	/**
	 * status:0用户未登录，-1孩子账号不存在，1成功，-2数据库发生错误，2已经存在此关系
	 */
	@RequestMapping("/children/addchild")
	@ResponseBody
	public Map<String, Object> addChild(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String childTelnumber = (String)m.get("studentTelnumber");
		String parentId = (String)m.get("parentId");

		
		Map<String, Object> result = childrenService.addChild(parentId, childTelnumber);
		
		return result;
		
	}
	
	
	@RequestMapping("/children/removechild")
	@ResponseBody
	public Map<String, Object> removeChild(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String parentId = (String)m.get("parentId");
		String childId = (String)m.get("studentId");
		
		Map<String, Object> result = childrenService.removeChild(parentId, childId);
		
		return result;
	}
	
	@RequestMapping("/children")
	@ResponseBody
	public Map<String, Object> getChildren(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String parentId = (String)m.get("parentId");
		
		Map<String, Object> result = childrenService.getChildren(parentId);
		
		return result; 
	}

}
