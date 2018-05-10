package org.scut.service.impl.parentImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.scut.dao.IClassDao;
import org.scut.dao.IParent_studentDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_classDao;
import org.scut.model.Student;
import org.scut.service.parentService.IChildrenService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;

@Service("childrenService")
public class ChildrenServiceImpl implements IChildrenService {

	@Resource
	private IStudentDao studentDao;
	@Resource
	private IParent_studentDao parentStudentDao;
	@Resource
	private IStudent_classDao studentClassDao;
	@Resource
	private IClassDao classDao;

	/**
	 * status:
	 */
	@Override
	public Map<String, Object> addChild(String parentId, String telnumber) {

		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		Map<String, Object> childInfo = new HashMap<String, Object>();

		try {
			Student child = studentDao.getStudentByTel(telnumber);
			if (child != null && child.getState().equals("1")) {
				String childId = (String) child.getId();
				boolean relationship = parentStudentDao.isExist(parentId, childId);
				if (relationship) {
					status = "2";
				} else {
					parentStudentDao.insertRelationship(parentId, childId);
					
					String classIdListStr = child.getClassIdList();
					List<String> classIdList = Arrays.asList(classIdListStr.split(","));
					List<Map<String, Object>> classInfoList = new ArrayList<Map<String, Object>>();
					for(String classId : classIdList){
						Map<String, Object> classInfo = classDao.getClassNameAndPicAndSchoolById(classId);
						classInfoList.add(classInfo);
					}

					childInfo.put("studentId", child.getId());
					childInfo.put("nickname", child.getNickname());
					childInfo.put("classList", classInfoList);
					childInfo.put("picPath", GlobalVar.picPath+child.getPicPath());
				}
			} else {
				status = "-1";
			}

		} catch (Exception e) {
			e.printStackTrace();
			status = "-2";
		}
		result.put("status", status);
		result.put("result", childInfo);

		return result;
	}

	@Override
	public Map<String, Object> removeChild(String parentId, String childId) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		try{
			parentStudentDao.removeChild(parentId, childId);
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		result.put("status", status);
		
		return result;
	}

	@Override
	public Map<String, Object> getChildren(String parentId) {

		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		List<Map<String, Object>> childrenInfo = new ArrayList<Map<String, Object>>();

		try {
			List<String> childrenIdsList = parentStudentDao.getChildrenIdsList(parentId);
			if (childrenIdsList != null) {
				for (String childId : childrenIdsList) {
					Map<String, Object> childInfo = new HashMap<String, Object>();
					
					Student child = studentDao.getStudentById(childId);
					String classIdListStr = child.getClassIdList();
					List<String> classIdList = Arrays.asList(classIdListStr.split(","));
					List<Map<String, Object>> classInfoList = new ArrayList<Map<String, Object>>();
					for(String classId : classIdList){
						Map<String, Object> classInfo = classDao.getClassNameAndPicAndSchoolById(classId);
						classInfoList.add(classInfo);
					}
					childInfo.put("studentId", child.getId());
					childInfo.put("nickname", child.getNickname());
					childInfo.put("classList", classInfoList);
					childInfo.put("picPath", GlobalVar.picPath+child.getPicPath());
					
					childrenInfo.add(childInfo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "-2";
		}

		result.put("status", status);
		result.put("result", childrenInfo);

		return result;
	}
}
