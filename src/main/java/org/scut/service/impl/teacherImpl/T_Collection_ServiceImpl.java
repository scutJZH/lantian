package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import org.scut.service.teacherService.*;
import org.scut.dao.teacherDao.*;
//import org.scut.model.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Collection_Service")
public class T_Collection_ServiceImpl implements IT_Collection_Service{
	@Resource
	private IT_CollectionDAO t_Collection_Service;
	public ArrayList<Integer> getCollectionList(String teacher_id){
		return this.t_Collection_Service.getCollectionList(teacher_id);
	}
}
