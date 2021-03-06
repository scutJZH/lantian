package org.scut.controller.parentController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.scut.service.parentService.IParentInfoService;
import org.scut.util.GlobalVar;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
@RequestMapping("/parent")
public class ParentInfoController {
	
	@Resource
	private IParentInfoService parentInfoService;
	
	@RequestMapping("/mine")
	@ResponseBody
	public Map<String, Object> getParentInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String parentId = (String)m.get("parentId");
		
		Map<String, Object> result = parentInfoService.getParentInfo(parentId);
		
		return result;
	}
	
	@RequestMapping("/mine/modify")
	@ResponseBody
	public Map<String, Object> modifyInfo(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		Map<String, Object> result = null;
		
		String parentId = request.getParameter("parentId");
		List<MultipartFile> filesList = request.getFiles("img");
		String nickname = request.getParameter("nickname");
		String birthdayStr = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String filePath = request.getSession().getServletContext().getRealPath("/")+"img\\";
		
		if(filesList.size() > 1){
			String status = "-1";
			result = new HashMap<String, Object>();
			result.put("status", status);
			result.put("result", new HashMap<String, Object>());
		}else{
			result = parentInfoService.modifyParentInfo(parentId, filesList, nickname, birthdayStr, sex, filePath);
		}
		
		return result;
		*/
		Map<String, Object> m = ParamsTransport.getParams(request);
		 String parentId = (String)m.get("parentId");
		 String imgBase64 = (String)m.get("img");
		 String nickname = (String)m.get("nickname");
		 String birthdayStr = (String)m.get("birthday");
		 String sex = (String)m.get("sex");
		 String filePath =this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.picPath;
		 Map<String, Object> result = parentInfoService.modifyParentInfo(parentId, imgBase64, nickname, birthdayStr, sex, filePath);
		 return result;
	}

}
