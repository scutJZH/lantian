package org.scut.service.impl.teacherImpl;

import org.scut.service.teacherService.*;
import java.util.*;
import org.scut.dao.teacherDao.*;
import org.scut.model.T_Homework_DetailsVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Homework_Details_Service")
public class T_Homework_Details_ServiceImpl implements IT_Homework_Details_Service{
	@Resource
	private IT_Homework_DetailsDAO t_Homework_DetailsDAO;
	public ArrayList<T_Homework_DetailsVO> get_Homework_List(String teacher_id) {
		return this.t_Homework_DetailsDAO.get_Homework_List(teacher_id);
	}
	public boolean delete_Homework_List(String teacher_id) {
		return t_Homework_DetailsDAO.delete_Homework_List(teacher_id);
	}
	public boolean insert(String teacher_id,int selected_class,int selected_subject) {
		return t_Homework_DetailsDAO.insert(teacher_id, selected_class, selected_subject);
	}
	//获取最新的数据HomeworkId便于给插入数据selected_exercise表功能使用
	public int getLatestHomeworkId() {
		return t_Homework_DetailsDAO.getLatestHomeworkId();
	}
	/*public ArrayList<T_Homework_DetailsVO> get_Assignments(String teacher_id) {}
	public boolean assign_Homework(T_Homework_DetailsVO table_Homework_ManageVO){}
*/
}
