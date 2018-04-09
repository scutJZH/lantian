package org.scut.dao.teacherDao;

import java.util.*;
import org.scut.model.*;
public interface IT_ClassDAO {
	public ArrayList<T_ClassVO> get_Class(String teacher_id);
	public boolean addClass(String teacher_id,int class_id,int grade,int class_number);
	
}
