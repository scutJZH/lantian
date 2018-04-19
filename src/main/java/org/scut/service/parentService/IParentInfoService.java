package org.scut.service.parentService;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.scut.model.Parent;
import org.springframework.web.multipart.MultipartFile;

public interface IParentInfoService {
	public Map<String, Object> getParentInfo(String parentId);

	public Map<String, Object> modifyParentInfo(String parentId, List<MultipartFile> filesList, String nickname,
			String birthdayStr, String sex);
}
