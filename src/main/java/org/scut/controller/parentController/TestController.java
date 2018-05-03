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
			//ͼƬ��form���ϴ�
			//��ȡǰ��keyΪ��img����ͼƬ�б�
		List<MultipartFile> files = request.getFiles("img");
		//��ȡǰ��keyΪ��id����text���Ͳ���
//		String id = request.getParameter("id");
		for(MultipartFile file :files){
			if(!file.isEmpty()){
				//����ͼƬ�ļ���request.getSession().getServletContext().getRealPath("/")Ϊ��Ŀ��Ŀ¼��
				//imgΪ��Ŀ¼�µ�img�ļ��У�file.getOriginalFilename()Ϊ�ϴ��ļ������֣�ȡ��������ֺ���������ʽ��ȡ����׺����
				//��uuid�����ļ������Ա����ļ����ظ������ݿ��uuid+��׺����
				File img = new File(request.getSession().getServletContext().getRealPath("/")+"img\\"+file.getOriginalFilename());
				//�����ļ�������
				FileOutputStream fos = new FileOutputStream(img);
				//����������
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				//����������������
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
		
		
		System.out.println("����wcookie");
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
		
		System.out.println("����rcookie");
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
