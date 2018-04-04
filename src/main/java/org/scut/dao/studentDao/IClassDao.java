package org.scut.dao.studentDao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClassDao {
	public String getGrade(@Param("id")String classId);
}
