package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Parent;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentDao {

	public Parent getParentByTel(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("parentId")String id, @Param("token")String token);
    
	public void insertParent(Parent parent);
	
	public void insertParent(User user);
	
}
