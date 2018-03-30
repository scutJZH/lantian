package org.scut.service.parentService;

import java.util.Map;

import org.scut.model.Parent;

public interface IParentService {
	
	public boolean inputParent(String id, String telnumber, String nickname, String password, String token);
	
	public Map<String,String> queryPwdAndToken(String telnumber);
}
