package org.scut.service.impl.publicImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.IParentDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.ITeacherDao;
import org.scut.model.User;
import org.scut.service.publicService.IRegisterService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;

@Service("registService")
public class RegisterServiceImpl implements IRegisterService {

	@Resource
	private IStudentDao studentDao;
	@Resource
	private IParentDao parentDao;
	@Resource
	private ITeacherDao teacherDao;
	@Resource
	private User user;

	@Override
	public Map<String, Object> sendVerifyCode(String userType, String telnumber) {

		
		Map<String, Object> result = new HashMap<String, Object>();
		String status = "1";

		try {
			user = this.getUserByType(userType, telnumber);

			if (user != null) {
				if (user.getState().equals("1")) {
					status = "-1";
				}else{
					/*
					 * 锟斤拷锟斤拷锟铰碉拷锟斤拷证锟诫，锟斤拷锟铰斤拷锟斤拷锟捷匡拷
					 */
				}
			} else {
				String id = UUID.randomUUID().toString();
				String verifyCode = "000000";//锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷证锟斤拷亩锟斤拷沤涌锟�
				user = new User();
				user.setId(id);
				user.setPhone(telnumber);
				user.setState("0");
				user.setVerifyCode(verifyCode);
				user.setPicPath("123.jpg");
				this.inputUserByType(userType, user);
			}
		} catch (Exception e) {
			status = "-2";
			e.printStackTrace();
		}
		
		
		result.put("status", status);
		
		return result;

	}

	
	@Override
	public Map<String, Object> verify(String userType, String telnumber, String nickname, String password,
			String verifyCode) {
		
		Map<String, Object> result = new HashMap<String, Object>();
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
						user.setCreateTime(new Date());
						this.updateUserByType(userType, user);
						userInfo.put("id", user.getId());
						userInfo.put("picPath", GlobalVar.picPath+user.getPicPath());
						userInfo.put("token", token);
						
						GlobalVar.tokenMap.put(user.getId(), token);
						
						
					}
				}else{
					status = "-3";
				}
			}else{
				status = "-3";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		
		result.put("status", status);
		result.put("result",userInfo);
		
		
		return result;
	}
	
	private User getUserByType(String userType, String telnumber) throws Exception{
		
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
		
		return user;
	}
	
	private void inputUserByType(String userType, User user) throws Exception{
		switch(userType){
		case "1":
			studentDao.insertUser(user);
			break;
		case "2":
			teacherDao.insertUser(user);
			break;
		case "3":
			parentDao.insertUser(user);
			break;
		}
	}
	
	private void updateUserByType(String userType, User user) throws Exception{
		switch(userType){
		case "1":
			studentDao.updateUser(user);
			break;
		case "2":
			teacherDao.updateUser(user);
			break;
		case "3":
			parentDao.updateUser(user);
		}
	}

}
