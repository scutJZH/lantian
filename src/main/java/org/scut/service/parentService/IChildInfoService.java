package org.scut.service.parentService;

import java.util.Map;

public interface IChildInfoService {
	public Map<String, Object> getChildStudy(String studentId, String parentId, String classId, String subjectId, String studyType);

}
