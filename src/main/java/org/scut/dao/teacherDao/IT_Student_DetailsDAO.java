package org.scut.dao.teacherDao;

import java.util.*;
import org.scut.model.*;
public interface IT_Student_DetailsDAO {
	public ArrayList<T_Student_DetailsVO> getClassList(String teacher_id);
	public T_Student_DetailsVO queryStudent(String teacher_id,int class_id,String student_id);
}
