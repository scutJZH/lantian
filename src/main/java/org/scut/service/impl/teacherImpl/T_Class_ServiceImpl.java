package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import org.scut.service.teacherService.*;
import org.scut.dao.teacherDao.*;
import org.scut.model.T_ClassVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Class_Service")
public class T_Class_ServiceImpl implements IT_Class_Service{
	@Resource
	private IT_ClassDAO t_ClassDAO;
	public ArrayList<T_ClassVO> get_Class(String teacher_id){
		return this.t_ClassDAO.get_Class(teacher_id);
	}
	public boolean addClass(String teacher_id,int class_id,int grade,int class_number) {
		return this.t_ClassDAO.addClass(teacher_id, class_id, grade, class_number);
	}

}
