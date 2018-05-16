package org.scut.service.publicService;

import java.util.Map;

public interface ILogService {
	
	public Map<String, Object> login(String userType, String telnumber, String pwd);
	
	public Map<String, Object> logout(String id, String token);
	
	public Map<String, Object> modifyPassword(String userType, String telnumber, String newPassword, String verifyCode);
	
	public Map<String, Object> sendVerifyCode(String userType, String telnumber);
	
	
}
