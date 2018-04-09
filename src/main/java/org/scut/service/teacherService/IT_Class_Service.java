package org.scut.service.teacherService;

import java.util.ArrayList;

import org.scut.model.T_ClassVO;

public interface IT_Class_Service {
	public ArrayList<T_ClassVO> get_Class(String teacher_id);
	public boolean addClass(String teacher_id,int class_id,int grade,int class_number);
	
}
