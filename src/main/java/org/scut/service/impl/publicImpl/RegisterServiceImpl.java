package org.scut.service.impl.publicImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.dao.teacherDao.ITeacherDao;
import org.scut.model.Student;
import org.scut.model.TokenMap;
import org.scut.model.User;
import org.scut.service.publicService.IRegisterService;
import org.springframework.stereotype.Service;

@Service("registService")
public class RegisterServiceImpl implements IRegisterService {

	@Resource
	private IStudentDao studentDao;
	@Resource
	private IParentDao parentDao;
	@Resource
	private ITeacherDao teacherDao;

	@Override
	public Map<String, Object> regist(String userType, String telnumber, String nickname, String password) {

		User user = null;

		String status = "1";

		try {
			user = this.getUserByType(userType, telnumber);

			if (user != null) {
				if (user.getState().equals("1")) {
					status = "-1";
				}else{
					/*
					 * 生成新的验证码，更新进数据库
					 */
				}
			} else {
				String id = UUID.randomUUID().toString();
				String verifyCode = "000000";//调用生成验证码的短信接口
				user.setId(id);
				user.setPhone(telnumber);
				user.setNickname(nickname);
				user.setPassword(password);
				user.setState("0");
				user.setVerifyCode(verifyCode);
				this.inputUserByType(userType, user);
			}
		} catch (Exception e) {
			status = "-2";
			e.printStackTrace();
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		
		return result;

	}

	
	@Override
	public Map<String, Object> verify(String userType, String telnumber, String nickname, String password,
			String verifyCode) {
		
		User user = null;
		String status = "1";
		Map<String, Object> userInfo = new HashMap<String, Object>();
		
		try{
			user = this.getUserByType(userType, telnumber);
			if(user!=null){
				if(user.getVerifyCode() != null){
					if(user.getVerifyCode().equals(verifyCode)){
						
						String token = UUID.randomUUID().toString();
						
						user.setState("1");
						user.setNickname(nickname);
						user.setPassword(password);
						user.setToken(token);
						this.inputUserByType(userType, user);
						userInfo.put("id", user.getId());
						userInfo.put("nickname", nickname);
						userInfo.put("pic_path", user.getPic_path());
						userInfo.put("token", token);
						
						TokenMap.tokenMap.put(user.getId(), token);
						
						
					}
				}else{
					status = "-3";
				}
			}else{
				status = "-2";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", status);
		result.put("result",userInfo);
		
		
		return result;
	}
	
	private User getUserByType(String userType, String telnumber) throws Exception{
		
		User user = null;
		switch (userType) {
		case "1":
			user = studentDao.getStudentById(telnumber);
			break;
		case "2":
			user = teacherDao.getTeacherByTel(telnumber);
			break;
		case "3":
			user = parentDao.getParentByTel(telnumber);
		}
		
		return user;
	}
	
	private void inputUserByType(String userType, User user) throws Exception{
		switch(userType){
		case "1":
			studentDao.inputStudent(user);
			break;
		case "2":
			teacherDao.inputTeacher(user);
			break;
		case "3":
			parentDao.inputParent(user);
			break;
		}
	}

}
