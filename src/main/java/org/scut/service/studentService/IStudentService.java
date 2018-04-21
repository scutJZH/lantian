package org.scut.service.studentService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.apache.coyote.Request;
import org.apache.ibatis.annotations.Param;
import org.scut.model.Student;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface IStudentService {
	Map<String, Object> getPaperList(String studentId, @Param("submit")String submit);

	Map<String, Object> getPaperQuestions(String paperId);

	Map<String, Object> getSchedules(String studentId);

	Map<String, Object> addSchedule(Map<String, Object> m) throws IOException;

	Map<String, Object> uploadSolutions(String studentId, String paperId, List<Map<String, Object>> sList, List<MultipartFile> files,MultipartHttpServletRequest request);
	
}
