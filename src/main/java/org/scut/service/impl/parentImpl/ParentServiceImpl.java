package org.scut.service.impl.parentImpl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.model.Parent;
import org.scut.service.parentService.IParentService;
import org.springframework.stereotype.Service;

@Service("parentService")
public class ParentServiceImpl implements IParentService {

	@Resource
	private Parent parent;
	@Resource
	private IParentDao parentDao;
	
	@Override
	public boolean inputParent(String id, String telnumber, String nickname, String password, String token) {
		
		Date createTime = new Date();
		
		
		this.parent.setId(id);
		this.parent.setPhone(telnumber);
		this.parent.setNickname(nickname);
		this.parent.setPassword(password);
		this.parent.setCreateTime(createTime);
		this.parent.setToken(token);
		
		try{
			this.parentDao.inputParent(parent);
			return true;
			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Map<String, String> queryPwdAndToken(String telnumber) {
		return null;
	}


}
