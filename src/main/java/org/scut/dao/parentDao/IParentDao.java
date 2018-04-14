package org.scut.dao.parentDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Parent;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentDao {
	
	public void inputParent(Parent parent);
	
	public void inputParent(User user);
	
	public Parent getParentByTel(@Param("telnumber")String telnumber);
	
	public String getStateByTel(@Param("telnumber")String telnumber);
}
