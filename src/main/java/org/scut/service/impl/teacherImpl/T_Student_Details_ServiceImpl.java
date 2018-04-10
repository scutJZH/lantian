package org.scut.service.impl.teacherImpl;

import java.util.ArrayList;
import org.scut.service.teacherService.*;
import org.scut.dao.teacherDao.*;
import org.scut.model.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
@Service("t_Student_Details_Service")
public class T_Student_Details_ServiceImpl implements IT_Student_Details_Service{
	@Resource
	private IT_Student_DetailsDAO t_Student_DetailsDAO;
	public ArrayList<T_Student_DetailsVO> getClassList(String teacher_id){
		return this.t_Student_DetailsDAO.getClassList(teacher_id);
	}
	public T_Student_DetailsVO queryStudent(String teacher_id,int class_id,String student_id) {
		return this.t_Student_DetailsDAO.queryStudent(teacher_id, class_id, student_id);
	}

}
