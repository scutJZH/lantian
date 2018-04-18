package org.scut.service.impl.parentImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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

	@Override
	public Map<String, Object> getChildHomework(String studentId, String subjectId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		List<Map> homeworkInfoList = new ArrayList<Map>();
		try{
		String classId = studentDao.getClassIdById(studentId);
		
		if(classId != null){
			homeworkInfoList = studentPaperDao.getHomeworkInfo(studentId, classId, subjectId, "1");
			
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
