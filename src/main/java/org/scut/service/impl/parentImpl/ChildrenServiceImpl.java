package org.scut.service.impl.parentImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.IParentDao;
import org.scut.dao.IParent_studentDao;
import org.scut.dao.IStudentDao;
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

	/**
	 * status:
	 */
	@Override
	public Map<String, Object> addChild(String parentId, String telnumber) {

		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		Map<String, Object> child = null;
		Map<String, Object> childInfo = new HashMap<String, Object>();

		try {
			child = studentDao.getchildInfoByTel(telnumber);
			if (child != null && child.get("state").equals("1")) {
				String childId = (String) child.get("studentId");
				boolean relationship = parentStudentDao.isExist(parentId, childId);
				if (relationship) {
					status = "2";
				} else {
					parentStudentDao.insertRelationship(parentId, childId);
					child.remove("state");
					child.put("picPath", GlobalVar.picPath+child.get("picPath"));
					childInfo.putAll(child);
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
		Map<String, Object> childrenInfo = new HashMap<String, Object>();

		try {
			List<String> childrenIdsList = parentStudentDao.getChildrenIdsList(parentId);
			if (childrenIdsList != null) {
				for (String childId : childrenIdsList) {
					Map<String, Object> childInfo = studentDao.getchildInfoById(childId);
					childInfo.remove("state");
					childrenInfo.putAll(childInfo);
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
