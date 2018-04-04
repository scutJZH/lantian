package org.scut.service.publicService;

import java.util.Map;

public interface ILoginService {
	public Map<String, Object> login(String userType, String telnumber, String pwd, String token);
}
