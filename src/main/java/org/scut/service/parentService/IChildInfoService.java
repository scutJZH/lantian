package org.scut.service.parentService;

import java.util.Map;

public interface IChildInfoService {
	public Map<String, Object> getChildHomework(String studentId, String subjectId);
}
