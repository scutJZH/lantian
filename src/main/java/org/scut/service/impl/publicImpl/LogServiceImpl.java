package org.scut.service.impl.publicImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.dao.teacherDao.ITeacherDao;
import org.scut.model.TokenMap;
import org.scut.service.publicService.ILogService;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl implements ILogService {

	@Resource
	private IParentDao parentDao;
	@Resource
	private IStudentDao studentDao;
	@Resource
	private ITeacherDao teacherDao;

	@Override
	public Map<String, Object> login(String userType, String telnumber, String pwd, String token) {

		Map<String, String> queryResult = null;
		Map<String, Object> result = new HashMap();
		Map<String, String> userInfo = new HashMap();
		String status = "-1";
		try {
			switch (userType) {
			case "1":
				queryResult = this.studentDao.getIdAndPwdAndToken(telnumber);
				break;
			case "2":
				queryResult = this.teacherDao.queryIdAndPwdAndToken(telnumber);
				break;
			case "3":
				queryResult = this.parentDao.queryIdAndPwdAndToken(telnumber);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "-3";
		}
//�ж��Ƿ��¼�ɹ���0�����Զ���¼ʧ�ܣ�-1���������������1�����¼�ɹ���-2�����˺Ų����ڣ�-3�������ݿ��ѯ��������
		
		String newToken = "";
		if (queryResult != null) {
			
			switch(userType){
			case "1":
				userInfo.put("id", queryResult.get("student_id"));
				break;
			case "2":
				userInfo.put("id", queryResult.get("teacher_id"));
				break;
			case "3":
				userInfo.put("id", queryResult.get("parent_id"));
				break;
			}
			
			if (!token.equals("") && token != null) {
				if (token.equals(queryResult.get("token"))) {
					status = "1";
				} else {
					if (!pwd.equals("")&& pwd != null) {
						if(pwd.equals(queryResult.get("password"))){
							status = "1";
							newToken = UUID.randomUUID().toString();
							if(this.updateToken(userType, userInfo.get("id"), newToken)){
								token = newToken;
							}
						}else{
							status = "-1";
						}
					}else{
						status = "0";
					}
				}
			} else {	
				if (pwd != "" && pwd != null) {
					if(pwd.equals(queryResult.get("password"))){
						status = "1";
						newToken = UUID.randomUUID().toString();
						if(this.updateToken(userType, userInfo.get("id"), newToken)){
							token = newToken;
						}
					}else{
						status = "-1";
					}
				}else{
					status = "0";
				}
			}
			
			userInfo.put("token", token);
				
		} else {
			userInfo.put("id", "");
			userInfo.put("token", "");
			status = "-2";
		}
		result.put("status", status);
		result.put("result", userInfo);

		return result;

	}
	
	public boolean updateToken(String userType, String id, String token){
		try{
			switch(userType){
			case "1":
				this.studentDao.updateToken(id, token);
				break;
			case "2":
				this.teacherDao.updateToken(id, token);
				break;
			case "3":
				this.parentDao.updateToken(id, token);
				break;
			}
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
			
		}
	}

	@Override
	public Map<String, String> logout(String userType, String id, String token) {
		
		Map<String, String> result = new HashMap<String, String>();	
		String status = "1";
		
//		System.out.println(TokenMap.tokenMap);
		
		if(TokenMap.tokenMap.containsKey(id)){
			if(TokenMap.tokenMap.get(id).equals(token)){
				TokenMap.tokenMap.remove(id, token);
				if(this.updateToken(userType, id, "")){
					status = "1";
				}else{
					status= "0";
				}
			}
		}
//		System.out.println(TokenMap.tokenMap);
		result.put("status", status);
		return result;
	}

}
