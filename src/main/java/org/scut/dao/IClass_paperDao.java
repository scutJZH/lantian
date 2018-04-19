package org.scut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClass_paperDao {
	
	List<Map<String, Object>> getClassPaperByCId(@Param("classId")String classId);

}
