package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_paperDao {
	public List<HashMap<String,String>> getRankDetails(@Param("paperId")String paperId);
	public List<HashMap<String,String>> getCorrectStudentList(@Param("teacherId")String teacherId,@Param("paperId")String paperId);

}
