package org.scut.service.publicService;

import java.util.Map;

public interface ILoginService {
	public Map<String,String> login(String userType, String telnumber, String pwd, String token);
}
