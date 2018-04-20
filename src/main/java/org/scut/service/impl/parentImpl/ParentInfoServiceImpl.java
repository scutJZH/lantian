package org.scut.service.impl.parentImpl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
				userInfo.put("createTime", String.valueOf(parent.getCreateTime().getTime()));
				userInfo.put("picPath", GlobalVar.picPath+parent.getPicPath());
				String birthdayStamp = null;
				if(parent.getBirthday() != null){
					long timeStamp = parent.getBirthday().getTime();
					birthdayStamp = String.valueOf(timeStamp);
				}
				userInfo.put("birthday", birthdayStamp);
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
			String birthdayStr, String sex, String filePath) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		String status = "1";
		Map<String, Object> parentInfo = new HashMap<String, Object>();
		
		try{
			Parent parent = parentDao.getParentById(parentId);
			if(parent != null){
				String picPath = parent.getPicPath();
				if(filesList!=null && filesList.size() > 0){
					for(MultipartFile file:filesList){
						String imgOriginalName = file.getOriginalFilename();
						String extensionName = imgOriginalName.substring(imgOriginalName.lastIndexOf("."));
						picPath = UUID.randomUUID().toString()+extensionName;
						File newfile = new File(filePath+picPath);
						FileOutputStream fos = new FileOutputStream(newfile);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						bos.write(file.getBytes());
						fos.close();
						bos.close();
						parent.setPicPath(picPath);
					}
				}
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date birthday = formatter.parse(birthdayStr);
				parent.setBirthday(birthday);
				parent.setNickname(nickname);
				parent.setSex(sex);
				
				parentDao.updateParent(parent);
				
				parentInfo.put("picPath", "/img/"+picPath);
			}else{
				status = "-1";
			}
		}catch(Exception e){
			e.printStackTrace();
			status = "-2";
		}
		
		result.put("result", parentInfo);
		result.put("status", status);
		
		return result;
	}

	

}
