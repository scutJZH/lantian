package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolutionDao {
	public List<HashMap<String,String>> getSolution(@Param("teacherId")String teacherId,@Param("paperId")String paperId,@Param("studentId")String studentId);
}
