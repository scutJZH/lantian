package org.scut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.scut.model.School;
import org.springframework.stereotype.Repository;

@Repository
public interface ISchoolDao {

	public String getSchoolNameById(@Param("schoolId")String schoolId);
	
	public List<School> getSchoolByCity(@Param("city")String city);
}
