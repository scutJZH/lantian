package org.scut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IParent_studentDao {

	public boolean isExist(@Param("parentId")String parentId, @Param("studentId")String studentId);
	
	public void insertRelationship(@Param("parentId")String parentId, @Param("studentId")String studentId);
	
	public List<String> getChildrenIdsList(@Param("parentId")String parentId);
	
	public void removeChild(@Param("parentId")String parentId, @Param("studentId")String studentId);
}
