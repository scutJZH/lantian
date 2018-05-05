package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISchoolDao {

	public String getSchoolNameById(@Param("schoolId")String schoolId);
	
}
