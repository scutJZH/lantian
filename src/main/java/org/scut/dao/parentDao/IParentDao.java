package org.scut.dao.parentDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Parent;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentDao {

	public String verificateTelnumber(String telnumber);
	
	public void inputParent(Parent parent);
	
	public Map<String,String> queryIdAndPwdAndToken(String telnumber);
	
	public void updateToken(@Param("id")String id, @Param("token")String token);
	
	public List<String> getChildrenIdList(@Param("id")String parentId);
	
	public void addChild(@Param("relationshipId")String relationshipId, @Param("parentId")String parentId, @Param("studentId")String studentId);
	
	public void removeChild(@Param("parentId")String parentId, @Param("childId")String childId);
}
