package org.scut.dao.teacherDao;

import java.util.*;
import org.scut.model.*;
import org.apache.ibatis.annotations.Param;
public interface IT_Optional_ExerciseDAO {
	public ArrayList<T_Optional_ExerciseVO> get_Optional_Exercise(@Param("optional_Subject")HashMap<String,Integer> optional_subject);
}
