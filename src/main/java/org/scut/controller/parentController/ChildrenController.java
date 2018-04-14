package org.scut.controller.parentController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.service.parentService.IChildrenService;
import org.scut.service.parentService.IParentService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

@Controller
@RequestMapping("/parent")
public class ChildrenController {
	
	@Resource
	private IChildrenService childrenService;

	/**
	 * status:0�û�δ��¼��-1�����˺Ų����ڣ�1�ɹ���-2���ݿⷢ������2�Ѿ����ڴ˹�ϵ
	 */
	@RequestMapping("/children/addchild")
	@ResponseBody
	public Map<String, Object> addChild(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> m = ParamsTransport.getParams(request);
		
		String childTelnumber = (String)m.get("telnumber");
		String parentId = (String)m.get("id");

		
		Map<String, Object> result = childrenService.addChild(parentId, childTelnumber);
		
		return result;
		
	}

}
