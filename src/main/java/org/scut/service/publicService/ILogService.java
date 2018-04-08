package org.scut.service.publicService;

import java.util.Map;

public interface ILogService {
	
	public Map<String, Object> login(String userType, String telnumber, String pwd, String token);
	
	public Map<String, String> logout(String userType, String id, String token);
	
}
