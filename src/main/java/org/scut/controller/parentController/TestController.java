package org.scut.controller.parentController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/fileupload")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException{
		InputStream is = request.getInputStream();
		File file = new File("D:\\","a");
		FileOutputStream fos = new FileOutputStream(file);
		try{
			int ch = 0;
		
			while((ch = is.read())!=-1){
				fos.write(ch);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			fos.close();
			is.close();
		}
		
		
	}
	
	@RequestMapping("formdata")
	public void formdataTest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		request.getParameter("img");
	}
	
	@RequestMapping("writecookie")
	public void cookieWrite(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		System.out.println("·ÃÎÊwcookie");
		Cookie cookie1 = new Cookie("jzh","123");
		Cookie cookie2 = new Cookie("wwp","321");
		Cookie cookie3 = new Cookie("shuige","141");
		cookie1.setPath("/lantian");
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
	}
	@RequestMapping("readcookie")
public void cookieRead(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		System.out.println("·ÃÎÊrcookie");
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie:cookies){
				System.out.println(cookie.getName());
			}
		}
		
		
	}

}
