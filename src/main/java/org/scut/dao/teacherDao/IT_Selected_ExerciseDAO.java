package org.scut.dao.teacherDao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.*;
import java.util.*;
public interface IT_Selected_ExerciseDAO {
	//public boolean insert(@Param("t_Selected_ExerciseVO")ArrayList<T_Selected_ExerciseVO> t_Selected_ExerciseVO);
	public boolean insert(@Param("t_Selected_ExerciseVO")ArrayList<T_Selected_ExerciseVO> t_Selected_ExerciseVO);

}
