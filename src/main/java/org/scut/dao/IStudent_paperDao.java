package org.scut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_paperDao {
	
	List<Map<String, Object>> getStudentPaperBySId(@Param("studentId")String studentId,@Param("submit")String submit);

}
