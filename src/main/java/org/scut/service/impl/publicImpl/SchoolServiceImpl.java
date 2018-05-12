package org.scut.service.impl.publicImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.dao.ISchoolDao;
import org.scut.model.School;
import org.scut.service.publicService.ISchoolService;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements ISchoolService{
	@Resource
	private ISchoolDao schoolDao;

	@Override
	public Map<String, Object> getSchoolByCity(String city) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		List<School> schoolList = new ArrayList<School>();
		
		try{
			schoolList = schoolDao.getSchoolByCity(city);
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		result.put("status", status);
		result.put("result", schoolList);
		
		return result;
	}

	

}
