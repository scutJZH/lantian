package org.scut.service.impl.parentImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.scut.dao.IParent_studentDao;
import org.scut.dao.IStudentDao;
import org.scut.dao.IStudent_studyDao;
import org.scut.service.parentService.IChildInfoService;
import org.scut.util.ParamsTransport;
import org.springframework.stereotype.Service;

@Service("childInfoService")
public class ChildInfoServiceImpl implements IChildInfoService {

	@Resource
	private IStudentDao studentDao;
	@Resource
	private IStudent_studyDao studentStudyDao;
	@Resource
	private IParent_studentDao parentStudentDao;

	@Override
	public Map<String, Object> getChildStudy(String studentId, String parentId, String classId, String subjectId, String studyType) {

		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		try {
			boolean isRelationshipExist = parentStudentDao.isExist(parentId, studentId);
			if (isRelationshipExist) {
				List<Map<String, Object>> childHomeworkInfoList = studentStudyDao.getChildStudyInfoList(studentId, classId, subjectId, studyType);
				if(childHomeworkInfoList != null && childHomeworkInfoList.size()>0){
					for(Map<String, Object> childHomeworkInfo : childHomeworkInfoList){
						String rank = null;
						String rankJson = (String)childHomeworkInfo.get("rank");
						List<Map<String, Object>> rankMapList = ParamsTransport.stringToList(rankJson);
						if(rankMapList != null && rankMapList.size() > 0){
							for(Map<String, Object> rankMap : rankMapList){
								String studentIdInRank = (String)rankMap.get("studentId");
								if(studentIdInRank.equals(studentId)){
									rank = (String)rankMap.get("rankNumber");
									break;
								}
							}
							childHomeworkInfo.put("rank", rank);
						}else{
							childHomeworkInfo.put("rank", null);
						}
					}
				}
				result.put("result", childHomeworkInfoList);
			}else{
				status = "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "-2";
		}
		result.put("status", status);

		return result;
	}

	

}
