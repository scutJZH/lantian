package org.scut.service.teacherService;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ITeacherInfoService {
public Map<String, Object> getTeacherInfo(String teacherId);
	
	public Map<String, Object> modifyTeacherInfo(String teacherId, List<MultipartFile> filesList, String nickname, String birthdayStr, String sex, String name, String schoolName, String filePath);
}
