package org.scut.service.teacherService;

import java.util.ArrayList;

import org.scut.model.T_ClassVO;

public interface IT_Class_Service {
	public ArrayList<T_ClassVO> get_Class(String teacher_id);

}
