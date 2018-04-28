package org.scut.service.parentService;

import java.util.Map;

public interface IParentInfoService {
	public Map<String, Object> getParentInfo(String parentId);

	public Map<String, Object> modifyParentInfo(String parentId, String imgBase64, String nickname,
			String birthdayStr, String sex, String filePath);
}
