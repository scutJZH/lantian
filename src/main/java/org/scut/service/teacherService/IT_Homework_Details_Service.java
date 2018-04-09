package org.scut.service.teacherService;

import java.util.*;

import org.scut.model.T_Homework_DetailsVO;

public interface IT_Homework_Details_Service {
	public ArrayList<T_Homework_DetailsVO> get_Homework_List(String teacher_id);
	public boolean delete_Homework_List(String teacher_id);
	public boolean insert(String teacher_id,int selected_class,int selected_subject);
	public int getLatestHomeworkId();
	/*public ArrayList<T_Homework_DetailsVO> get_Assignments(String teacher_id);
	public boolean assign_Homework(T_Homework_DetailsVO table_Homework_ManageVO);
*/
}
