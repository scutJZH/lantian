package org.scut.service.publicService;

import java.util.Map;

public interface ILogService {
	
	public Map<String, Object> login(String userType, String telnumber, String pwd);
	
	public Map<String, Object> logout(String id, String token);
	
}
