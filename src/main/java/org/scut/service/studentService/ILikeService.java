package org.scut.service.studentService;

import org.springframework.stereotype.Component;

@Component
public interface ILikeService {
	boolean islike(String answerId,String studentId);
	

}
