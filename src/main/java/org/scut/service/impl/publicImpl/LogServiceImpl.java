package org.scut.service.impl.publicImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.scut.dao.IParentDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.ITeacherDao;
import org.scut.model.User;
import org.scut.service.publicService.ILogService;
import org.scut.util.GlobalVar;
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
	public Map<String, Object> login(String userType, String telnumber, String password) {

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, Object> userInfo = new HashMap<String, Object>();
		String status = "1";
		User user = null;
		try {
			switch (userType) {
			case "1":
				user = studentDao.getStudentByTel(telnumber);
				break;
			case "2":
				user = teacherDao.getTeacherByTel(telnumber);
				break;
			case "3":
				user = parentDao.getParentByTel(telnumber);
			}
			if (user != null && user.getState().equals("1")) {
				if (password.equals(user.getPassword())) {
					String token = UUID.randomUUID().toString();
					this.updateToken(userType, user.getId(), token);
					GlobalVar.tokenMap.put(user.getId(), token);
					user.setToken(token);
					userInfo.put("id", user.getId());
					userInfo.put("nickname", user.getNickname());
					userInfo.put("picPath", GlobalVar.picPath+user.getPicPath());
					userInfo.put("token", token);
				} else {
					status = "0";
				}
			} else {
				status = "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "-2";
		}

		result.put("status", status);
		result.put("result", userInfo);

		return result;

	}

	public void updateToken(String userType, String id, String token) throws Exception {
		switch (userType) {
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
	}

	@Override
	public Map<String, Object> logout(String id, String token) {

		Map<String, Object> result = new HashMap<String, Object>();
		String status = "1";

		System.out.println(GlobalVar.tokenMap);

		GlobalVar.tokenMap.remove(id, token);
		System.out.println(GlobalVar.tokenMap);
		result.put("status", status);
		return result;
	}

	@Override
	public Map<String, Object> modifyPassword(String userType, String telnumber, String newPassword,
			String verifyCode) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			User user = null;
			switch(userType){
			case "1":
				user = studentDao.getStudentByTel(telnumber);
				break;
			case "2":
				user = teacherDao.getTeacherByTel(telnumber);
				break;
			case "3":
				user = parentDao.getParentByTel(telnumber);
				break;
			}
			if(user != null && user.getState().equals("1")){
				if(user.getVerifyCode() != null){
					if(user.getVerifyCode().equals(verifyCode)){
						user.setPassword(newPassword);
						switch(userType){
						case "1":
							studentDao.updateUser(user);
							break;
						case "2":
							teacherDao.updateUser(user);
							break;
						case "3":
							parentDao.updateUser(user);
							break;
						}
					}else{
						status = "-1";
					}
				}else{
					status = "-3";
				}
			}else{
				status = "-4";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);
		return result;
	}

	@Override
	public Map<String, Object> sendVerifyCode(String userType, String telnumber) {

		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		
		try{
			/*
			 * send verify to phone,and set reserved time in db
			 */
			String verifyCode = "111111";
			User user = null;
			switch(userType){
			case "1":
				user = studentDao.getStudentByTel(telnumber);
				break;
			case "2":
				teacherDao.getTeacherByTel(telnumber);
				break;
			case "3":
				parentDao.getParentByTel(telnumber);
				break;
			}
			
			if(user != null && user.getState().equals("1")){
				user.setVerifyCode(verifyCode);
				switch(userType){
				case "1":
					studentDao.updateUser(user);
					break;
				case "2":
					teacherDao.updateUser(user);
					break;
				case "3":
					parentDao.updateUser(user);
					break;
				}
			}else{
				status = "-1";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("status", status);
		
		return result;
	}
	

}
