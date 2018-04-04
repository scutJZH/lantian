package org.scut.service.impl.publicImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.dao.teacherDao.ITeacherDao;
import org.scut.service.publicService.ILoginService;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements ILoginService {

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
				queryResult = this.studentDao.queryIdAndPwdAndToken(telnumber);
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
//判断是否登录成功，0代表自动登录失败，-1代表密码输入错误，1代表登录成功，-2代表账号不存在，-3代表数据库查询发生错误
		
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

}
