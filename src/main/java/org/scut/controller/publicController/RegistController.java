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
	@Resource
	private Student student;
	@Resource
	private Parent parent;
	@Resource
	private Teacher teacher;
	
	
	@RequestMapping("/regist.do")
	public void ParentRegist(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = "";
		while((input=br.readLine())!=null){
			sb.append(input);
		}
		
//		System.out.println(sb);
		
		
		Gson gson = new Gson();
		Map<String, Object> m = gson.fromJson(sb.toString(), Map.class);
		
//		System.out.println(m);
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String id = "";
		String nickname = "";
		String password = "";
		int isSuccess = -1;
		String token = "";
		
		String telnumber = (String)m.get("telnumber");
		String userType = (String)m.get("userType");
		boolean isUserExist = this.verificateService.verificateTelnumber(telnumber, userType);
		if(isUserExist){
			isSuccess = 0;
		}else{
			id = UUID.randomUUID().toString();
			nickname = (String)m.get("nickname");
			password = (String)m.get("password");
			Date createTime = new Date();
			token = UUID.randomUUID().toString();
			try{
				if(userType.equals("1")){
					this.student.setId(id);
					this.student.setPhone(telnumber);
					this.student.setNickname(nickname);
					this.student.setPassword(password);
					this.student.setCreateTime(createTime);
					this.student.setToken(token);
					if(!this.studentService.inputStudent(student)){
						throw new Exception();
					}
				}else if(userType.equals("2")){
					this.teacher.setId(id);
					this.teacher.setPhone(telnumber);
					this.teacher.setNickname(nickname);
					this.teacher.setPassword(password);
					this.teacher.setCreateTime(createTime);
					this.teacher.setToken(token);
					if(!this.teacherService.inputTeacher(teacher)){
						throw new Exception();
					}
				}else if(userType.equals("3")){
					this.parent.setId(id);
					this.parent.setPhone(telnumber);
					this.parent.setNickname(nickname);
					this.parent.setPassword(password);
					this.parent.setCreateTime(createTime);
					this.parent.setToken(token);
					if(!this.parentService.inputParent(parent)){
						throw new Exception();
					}
				}else{
					throw new Exception();
				}
				isSuccess = 1;
			}catch(Exception e){
				isSuccess = -1;
			}
		
		}
		
		result.put("userid", id);
		result.put("token", token);
		result.put("isSuccess", isSuccess);
		
		PrintWriter out = response.getWriter();
		out.write(gson.toJson(result));
		out.flush();
		out.close();
	}

}
