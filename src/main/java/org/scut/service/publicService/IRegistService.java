package org.scut.service.publicService;

import java.util.Map;

public interface IRegistService {

	public Map<String, Object> regist(String telnumber, String nickname, String password, String userType);
}
