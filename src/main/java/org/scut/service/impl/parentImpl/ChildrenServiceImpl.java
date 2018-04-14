package org.scut.service.impl.parentImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.IParentDao;
import org.scut.dao.IStudentDao;
import org.scut.service.parentService.IChildrenService;
import org.springframework.stereotype.Service;

@Service("childrenService")
public class ChildrenServiceImpl implements IChildrenService{
	
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IParentDao parentDao;

	/**
	 * status:
	 */
	@Override
	public Map<String, Object> addChild(String parentId, String childTelnumber) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, String> childInfo = null;
		String studentId = null;
		String nickname = null;
		String grade = "";

		try{
			childInfo = studentDao.getStudentIdAndName(childTelnumber);
			if(childInfo!=null){
				studentId = childInfo.get("student_id");
				nickname = childInfo.get("nickname");
				String classId = studentDao.getClassIdFromJoinClass(studentId);
				if(classId != null){
					grade = classDao.getGrade(classId);
				}
				List<String> childrenList = parentDao.getChildrenIdList(parentId);
				if(childrenList!=null&&childrenList.contains(studentId)){
					status = "2";
				}else{
					String relationshipId = UUID.randomUUID().toString();
					parentDao.addChild(relationshipId, parentId, studentId);
				}				
				childInfo = new HashMap<String, String>();
				childInfo.put("studentId", studentId);
				childInfo.put("nickname", nickname);
				childInfo.put("grade", grade);
				result.put("result", childInfo);
			}else{
				status = "-1";
				result.put("result", "");
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
			result.put("result", "");
		}
		result.put("status", status);
		
		return result;
	}

	@Override
	public Map<String, Object> removeChild(String parentId, String childId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getChildrenIdList(String parentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, String>> getChildren(String parentId) {
		// TODO Auto-generated method stub
		return null;
	}

}
