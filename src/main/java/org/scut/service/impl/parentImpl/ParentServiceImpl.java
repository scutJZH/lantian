package org.scut.service.impl.parentImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.scut.dao.parentDao.IParentDao;
import org.scut.dao.studentDao.IClassDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.model.Parent;
import org.scut.model.Student;
import org.scut.service.parentService.IParentService;
import org.springframework.stereotype.Service;

@Service("parentService")
public class ParentServiceImpl implements IParentService {

	@Resource
	private Parent parent;
	@Resource
	private IParentDao parentDao;
	@Resource
	private IStudentDao studentDao;
	@Resource
	private IClassDao classDao;

	@Override
	public boolean inputParent(String id, String telnumber, String nickname, String password, String token) {

		Date createTime = new Date();

		this.parent.setId(id);
		this.parent.setPhone(telnumber);
		this.parent.setNickname(nickname);
		this.parent.setPassword(password);
		this.parent.setCreateTime(createTime);
		this.parent.setToken(token);

		try {
			this.parentDao.inputParent(parent);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<String> getChildrenIdList(String parentId) {
		List<String> childrenIdList = parentDao.getChildrenIdList(parentId);
		return childrenIdList;
	}

	@Override
	public List<Map<String, String>> getChildren(String parentId) {

		List<String> childrenIdList = this.getChildrenIdList(parentId);

		List<Map<String, String>> childrenList = new LinkedList<>();
		
		try {
			for (String childrenId : childrenIdList) {
				Map<String, String> childrenInfo = new HashMap<String, String>();
				childrenInfo.put("id", childrenId);
				childrenInfo.put("name", studentDao.getName(childrenId));
				String classId = studentDao.getClassId(childrenId);
				childrenInfo.put("grade", classDao.getGrade(classId));
				childrenList.add(childrenInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return childrenList;

	}

	/**
	 * status:0用户未登录，-1孩子账号不存在，1成功
	 */
	@Override
	public Map<String, Object> addChild(String parentId, String childTelnumber) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> userInfo = new HashMap<String, String>();
		return null;
	}

}
