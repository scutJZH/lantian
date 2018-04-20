package org.scut.service.impl.parentImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.dao.IClass_paperDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_paperDao;
import org.scut.service.parentService.IChildInfoService;
import org.springframework.stereotype.Service;

@Service("childInfoService")
public class ChildInfoServiceImpl implements IChildInfoService{
	
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IStudent_paperDao studentPaperDao;
	@Resource
	private IClass_paperDao classPaperDao;

	@Override
	public Map<String, Object> getChildHomework(String studentId, String subjectId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		List<Map<String, Object>> homeworkInfoList = new ArrayList<Map<String, Object>>();
		try{
			
		String classId = studentDao.getClassIdById(studentId);
		
		if(classId != null){
//			List<String> paperIdsList = classPaperDao
			
		}else{
			status = "-1";
		}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		result.put("status", status);
		result.put("result", homeworkInfoList);
		
		return result;
	}

	

}
