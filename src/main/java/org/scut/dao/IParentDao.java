package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Parent;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentDao {

	public Parent getParentByTel(@Param("telnumber")String telnumber);
	
	public void updateToken(@Param("parentId")String parentId, @Param("token")String token);
    
	public void insertParent(@Param("parent")Parent parent);
	
	public void insertUser(@Param("user")User user);
	
	public void updateUser(@Param("user")User user);
	
	public void updateParent(@Param("parent")Parent parent);
	
	public Parent getParentById(@Param("parentId")String parentId);
	
}
