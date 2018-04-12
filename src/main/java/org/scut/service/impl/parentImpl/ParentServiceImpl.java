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
import org.scut.dao.studentDao.IHomeworkDao;
import org.scut.dao.studentDao.IStudentDao;
import org.scut.model.Homework;
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
	@Resource
	private IHomeworkDao homeworkDao;


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
				String classId = studentDao.getClassIdFromJoinClass(childrenId);
				childrenInfo.put("grade", classDao.getGrade(classId));
				childrenList.add(childrenInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return childrenList;

	}

	/**
	 * status:0用户未登录，-1孩子账号不存在，1成功，-2数据库发生错误，2已经存在此关系
	 */
	@Override
	public Map<String, Object> addChild(String parentId, String childTelnumber) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, String> childInfo = null;
		String studentId = null;
		String nickname = null;
		String grade = "";
		//若查不到数据库会返回null
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

	/**
	 * -2代表数据库错误，0代表未登录，1代表成功
	 */
	@Override
	public Map<String, String> removeChild(String parentId, String childId) {
		
		Map<String, String> result = new HashMap<String, String>();
		
		String status = "1";
		try{
			parentDao.removeChild(parentId, childId);
		}catch(Exception e){
			e.printStackTrace();
			status = "-1";
		}
		result.put("status", status);
		return result;
	}

	/**
	 * status:1
	 */
	@Override
	public Map<String, Object> getChildHomework(String studentId, String subjectId) {
		String status = "1";
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Map<String, String>> homeworkList = null;
		
		List<Map<String, String>> homeworkInfoList = new LinkedList<Map<String, String>>();
		try{
			homeworkList = studentDao.getHomeworkList(studentId);
			if(homeworkList != null){
				for(Map<String, String> homework:homeworkList){
					Map<String, String> homeworkTitleAndTime = homeworkDao.getHomeworkTitleAndCreateTime(homework.get("homework_id"), subjectId);
					if(homeworkTitleAndTime != null){
						Map<String, String> homeworkInfo = new HashMap<String, String>();
						homeworkInfo.putAll(homeworkTitleAndTime);
						homeworkInfo.putAll(homework);
						homeworkInfoList.add(homeworkInfo);
					}
				}
			}
			result.put("result", homeworkInfoList);
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
			result.put("result", "");
		}
		result.put("status", status);
		return result;
	}

}
