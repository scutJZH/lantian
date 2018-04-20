package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPptDao {
	//注意Object可能要改
	public List<HashMap<String,Object>> getPptList(@Param("teacherId")String teacherId);
}
