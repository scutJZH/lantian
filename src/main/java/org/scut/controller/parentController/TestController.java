package org.scut.controller.parentController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.util.Base64Analysis;
import org.scut.util.GlobalVar;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@RequestMapping("/formdata")
	public void formdataTest(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		try{
			//图片用form表单上传
			//获取前端key为“img”的图片列表
		List<MultipartFile> files = request.getFiles("img");
		//获取前端key为“id”的text类型参数
//		String id = request.getParameter("id");
		for(MultipartFile file :files){
			if(!file.isEmpty()){
				//创建图片文件，request.getSession().getServletContext().getRealPath("/")为项目根目录，
				//img为根目录下的img文件夹，file.getOriginalFilename()为上传文件的名字（取得这个名字后，用正则表达式读取到后缀名，
				//用uuid生成文件名，以避免文件名重复，数据库存uuid+后缀名）
				File img = new File(request.getSession().getServletContext().getRealPath("/")+"img\\"+file.getOriginalFilename());
				//建立文件输入流
				FileOutputStream fos = new FileOutputStream(img);
				//建立输入流
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				//对输入流进行输入
	            bos.write(file.getBytes());
	            bos.close();
	            fos.close();
			}
		}}catch(Exception e){e.printStackTrace();}
		
	}
	
	@RequestMapping("/imglistsize")
	public void imgsizeTest(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException{
		System.out.println("test/imgsize");
		List<MultipartFile> files = request.getFiles("img");
		System.out.println(files.size());
	}
	
	
	@RequestMapping("/writecookie")
	public void cookieWrite(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		
		System.out.println("访问wcookie");
		Cookie cookie1 = new Cookie("jzh","123");
		Cookie cookie2 = new Cookie("wwp","321");
		Cookie cookie3 = new Cookie("shuige","141");
		cookie1.setPath("/lantian");
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		response.addCookie(cookie3);
	}
	@RequestMapping("/readcookie")
public void cookieRead(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		System.out.println("访问rcookie");
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie:cookies){
				System.out.println(cookie.getName());
			}
		}
		
		
	}
	
	@RequestMapping("/pathtest1")
	public void pathTest1(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String path1 = request.getSession().getServletContext().getRealPath("/");
		String path2 = this.getClass().getClassLoader().getResource("/").getPath();
		String path3 = this.getClass().getClassLoader().getResource("../../").getPath();
		String path4 = this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.picPath;
		System.out.println(path1);
		System.out.println(path2);
		System.out.println(path3);
		System.out.println(path4);
//		Map<String, Object> m = ParamsTransport.getParams(request);
//		
//		String img = (String)m.get("img");
//		if(img != null){
//			String imgPath1 = Base64Analysis.analysisPic("1", path1, img)+"\\img";
////			String imgPath2 = Base64Analysis.analysisPic("1", path2, img);
//			System.out.println(imgPath1);
//		}
	}
	
	@RequestMapping("/pathtest2")
	public void pathTest2(HttpServletRequest request, HttpServletResponse response) throws Exception{
//		String path1 = request.getSession().getServletContext().getRealPath("/");
		String path2 = this.getClass().getClassLoader().getResource("../../").getPath()+GlobalVar.picPath;
		System.out.println(path2);
		Map<String, Object> m = ParamsTransport.getParams(request);
		try{
		String img = (String)m.get("img");
		if(img != null){
//			String imgPath1 = Base64Analysis.analysisPic("1", path1, img);
			String imgPath2 = Base64Analysis.analysisPic("1", path2, img);
			System.out.println(imgPath2);
		}}catch(Exception e){
			e.printStackTrace();
		}
	}

}
