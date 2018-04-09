package org.scut.service.impl.teacherImpl;

import org.scut.service.teacherService.*;
import java.util.*;
import org.scut.dao.teacherDao.*;
import org.scut.model.*;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("t_Optional_Subject")
public class T_Optional_Subject_ServiceImpl implements IT_Optional_Subject_Service{
	@Resource
	private IT_Optional_SubjectDAO t_Optional_SubjectDAO;
	public ArrayList<T_Optional_SubjectVO> get_Optional_Subject(String teacher_id){
		return this.t_Optional_SubjectDAO.get_Optional_Subject(teacher_id);
	}
}
