package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import org.scut.service.teacherService.*;
import org.scut.dao.teacherDao.*;
//import org.scut.model.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Ppt_Service")
public class T_Ppt_ServiceImpl implements IT_Ppt_Service{
	@Resource
	private IT_PptDAO t_PptDAO;
	public ArrayList<Integer> getPptList(String teacher_id){
		return this.t_PptDAO.getPptList(teacher_id);
	}
}
