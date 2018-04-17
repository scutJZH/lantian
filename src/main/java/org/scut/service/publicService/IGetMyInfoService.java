package org.scut.service.publicService;

import java.util.Map;

public interface IGetMyInfoService {
	
	public Map<String, Object> getMyInfo(String userType, String id);
}
