package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacher_classDao {
	  public List<HashMap<String,Object>> getClassList(@Param("teacherId")String teacherId);
}
