package org.scut.service.studentService;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;


public interface IStudentInfoService {
	public Map<String, Object> getStudentInfo(String studentId);

	public Map<String, Object> modifyStudentInfo(String studentId, List<MultipartFile> filesList, String nickname, String birthdayStr, String sex, String schoolName, String filePath);
}
