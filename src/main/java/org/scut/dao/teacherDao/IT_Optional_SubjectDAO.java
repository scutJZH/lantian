package org.scut.dao.teacherDao;

import java.util.*;

import org.scut.model.T_Optional_SubjectVO;

public interface IT_Optional_SubjectDAO {
	public ArrayList<T_Optional_SubjectVO> get_Optional_Subject(String teacher_id);
}
