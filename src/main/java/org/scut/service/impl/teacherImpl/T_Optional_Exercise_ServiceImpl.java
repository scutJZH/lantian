package org.scut.service.impl.teacherImpl;

import org.scut.service.teacherService.*;
import java.util.*;
import org.scut.dao.teacherDao.*;
import org.scut.model.T_Optional_ExerciseVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Optional_Exercise_Service")
public class T_Optional_Exercise_ServiceImpl implements IT_Optional_Exercise_Service{
	@Resource
	private IT_Optional_ExerciseDAO t_Optional_ExerciseDAO;
	public ArrayList<T_Optional_ExerciseVO> get_Optional_Exercise(HashMap<String,Integer> optional_subject){
		return this.t_Optional_ExerciseDAO.get_Optional_Exercise(optional_subject);
	}
}
