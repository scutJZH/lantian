package org.scut.service.parentService;

import java.util.List;
import java.util.Map;

import org.scut.model.Parent;

public interface IParentService {
	
	public boolean inputParent(String id, String telnumber, String nickname, String password, String token);
	
	public List<String> getChildrenIdList(String parentId);
	
	public List<Map<String, String>> getChildren(String parentId);
	
	public Map<String, Object> addChild(String parentId, String childTelnumber);
	
	public Map<String, String> removeChild(String parentId, String childId);
	
	public Map<String, Object> getChildHomework(String studentId, String subjectId);
	
}
