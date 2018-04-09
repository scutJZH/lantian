package org.scut.dao.teacherDao;
import org.scut.model.*;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface IT_Homework_DetailsDAO {
	public ArrayList<T_Homework_DetailsVO> get_Homework_List(String teacher_id);
	public boolean delete_Homework_List(String teacher_id);
	public boolean insert(String teacher_id,int selected_class,int selected_subject);
	public int getLatestHomeworkId();
	/*public HashMap get_Assignments(String teacher_id);
	public boolean assign_Homework(T_Homework_DetailsVO table_Homework_ManageVO);
*/
}