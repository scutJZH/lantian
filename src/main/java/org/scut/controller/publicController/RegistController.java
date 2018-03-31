package org.scut.controller.publicController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.model.Parent;
import org.scut.model.Student;
import org.scut.model.Teacher;
import org.scut.service.parentService.IParentService;
import org.scut.service.publicService.IVerificateService;
import org.scut.service.studentService.IStudentService;
import org.scut.service.teacherService.ITeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
public class RegistController {
	
	@Resource
	private IVerificateService verificateService;
	@Resource
	private IStudentService studentService;
	@Resource
	private IParentService parentService;
	@Resource
	private ITeacherService teacherService;
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * 
	 * status 为0代表账号已经被注册，为1代表注册成功，-1代表注册发生错误
	 */
	@RequestMapping("/regist.do")
	public void regist(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        //读取json数据
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = "";
		while((input=br.readLine())!=null){
			sb.append(input);
		}
		
//		System.out.println(sb);
		
		//把json转为map
		Gson gson = new Gson();
		Map<String, Object> m = gson.fromJson(sb.toString(), Map.class);
		
//		System.out.println(m);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String id = "";
		String nickname = "";
		String password = "";
		int status = -1;
		String token = "";
		
		String telnumber = (String)m.get("telnumber");
		String userType = (String)m.get("userType");
		boolean isUserExist = this.verificateService.verificateTelnumber(telnumber, userType);
		if(isUserExist){
			status = 0;
		}else{
			
			id = UUID.randomUUID().toString();
			token = UUID.randomUUID().toString();
			
			nickname = (String)m.get("nickname");
			password = (String)m.get("password");
			try{
				switch(userType){
				case "1":
					if(!this.studentService.inputStudent(id, telnumber, nickname, password, token)){
						throw new Exception();
					}
					break;
				case "2":
					if(!this.teacherService.inputTeacher(id, telnumber, nickname, password, token)){
						throw new Exception();
					}
				case "3":
					if(!this.parentService.inputParent(id, telnumber, nickname, password, token)){
						throw new Exception();
					}
					break;
				default:
					throw new Exception();
				}
				status = 1;
			}catch(Exception e){
				status = -1;
			}
		
		}
		
		result.put("userid", id);
		result.put("token", token);
		result.put("isSuccess", status);
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		out.flush();
		out.close();
		
	}

}
