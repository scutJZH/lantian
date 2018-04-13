package org.scut.service.publicService;

import java.util.Map;

public interface IRegisterService {

	public Map<String, Object> regist(String userType, String telnumber, String nickname, String password);
	
	public Map<String, Object> verify(String userType, String telnumber, String nickname, String password, String verifyCode);
	
}
