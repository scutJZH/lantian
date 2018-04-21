package org.scut.service.impl.studentImpl;

import javax.annotation.Resource;


import org.scut.dao.ILikeDao;
import org.scut.service.studentService.ILikeService;
import org.springframework.stereotype.Service;


@Service
public class LikeServiceImpl implements ILikeService {
	@Resource
	private ILikeDao ilikedao;

	@Override
	public boolean islike(String answerId, String studentId) {
		int i=ilikedao.find(studentId, answerId);
		if (i>0) {
			return true;
			
		}
		return false;
	}



}
