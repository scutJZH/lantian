package org.scut.service.parentService;

import java.util.List;
import java.util.Map;

public interface IChildrenService {
	
	public Map<String, Object> addChild(String parentId, String childTelnumber);
	
	public Map<String, Object> removeChild(String parentId, String childId);
	
	public List<String> getChildrenIdList(String parentId);
	
	public List<Map<String, String>> getChildren(String parentId);
}
