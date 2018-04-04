package org.scut.service.impl.publicImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.dao.teacherDao.ITeacherDao;
import org.scut.model.TokenMap;
import org.scut.service.parentService.IParentService;
import org.scut.service.publicService.IRegistService;
import org.scut.service.publicService.IVerificateService;
import org.scut.service.studentService.IStudentService;
import org.scut.service.teacherService.ITeacherService;
import org.springframework.stereotype.Service;

@Service("registService")
public class RegistServiceImpl implements IRegistService{
	
	@Resource
	private IVerificateService verificateService;
	@Resource
	private IStudentService studentService;
	@Resource
	private ITeacherService teacherService;
	@Resource
	private IParentService parentService;
	
	
	@Override
	public Map<String, String> regist(String telnumber, String nickname, String password, String userType) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		boolean isUserExist = this.verificateService.verificateTelnumber(telnumber, userType);
		
		String status = "-1";
		String token = "";
		String id = "";
		
		if(isUserExist){
			status = "0";
		}else{

			id = UUID.randomUUID().toString();
			token = UUID.randomUUID().toString();
			
			
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
				status = "1";
			}catch(Exception e){
				status = "-1";
			}
		
		}
		if(status.equals("1")){
			TokenMap.tokenMap.put(id, token);
//			System.out.println(TokenMap.tokenMap);
		}
		
		result.put("id", id);
		result.put("token", token);
		result.put("status", status);
		return result;
	}

}
