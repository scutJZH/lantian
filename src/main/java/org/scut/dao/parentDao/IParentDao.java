package org.scut.dao.parentDao;

import java.util.List;

import org.scut.model.Parent;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentDao {

	public String verificateTelnumber(String telnumber);
	
	public void inputParent(Parent parent);
	
	public List<String> queryPwdAndToken(String telnumber);
}
