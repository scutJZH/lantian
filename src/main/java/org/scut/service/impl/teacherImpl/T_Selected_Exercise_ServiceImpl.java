package org.scut.service.impl.teacherImpl;

import org.scut.service.teacherService.*;
import java.util.*;
import org.scut.dao.teacherDao.*;
import org.scut.model.T_Selected_ExerciseVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Selected_Exercise_Service")
public class T_Selected_Exercise_ServiceImpl implements IT_Selected_Exercise_Service{
	@Resource
	private IT_Selected_ExerciseDAO t_Selected_ExerciseDAO;
	public boolean insert(ArrayList<T_Selected_ExerciseVO> t_Selected_ExerciseVO) {
		return this.t_Selected_ExerciseDAO.insert(t_Selected_ExerciseVO);
	}

}
