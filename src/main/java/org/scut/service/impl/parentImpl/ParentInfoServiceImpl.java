package org.scut.service.impl.parentImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.scut.dao.IParentDao;
import org.scut.model.Parent;
import org.scut.service.parentService.IParentInfoService;
import org.scut.util.GlobalVar;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("parentInfoService")
public class ParentInfoServiceImpl implements IParentInfoService{
	
	@Resource
	private IParentDao parentDao;

	@Override
	public Map<String, Object> getParentInfo(String parentId) {
		Map<String, Object> result = new HashMap<String, Object>();

		String status = "1";
		Map<String, Object> userInfo = new HashMap<String, Object>();
		try{
			Parent parent = parentDao.getParentById(parentId);
			if (parent != null) {
				userInfo.put("telnumber", parent.getPhone());
				userInfo.put("createTime", parent.getCreateTime());
				userInfo.put("picPath", GlobalVar.picPath+parent.getPicPath());
				userInfo.put("birthday", parent.getBirthday());
				userInfo.put("nickname", parent.getNickname());
				userInfo.put("sex", parent.getSex());
			} else {
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		result.put("result", userInfo);
		result.put("status", status);
		return result;
	}

	@Override
	public Map<String, Object> modifyParentInfo(String parentId, List<MultipartFile> filesList, String nickname,
			String birthdayStr, String sex) {
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		return null;
	}

	

}
