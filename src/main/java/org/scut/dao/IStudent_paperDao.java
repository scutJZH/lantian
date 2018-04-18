package org.scut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_paperDao {
	public List<Map> getHomeworkInfo(@Param("studentId") String studentId, @Param("classId") String classid,
			@Param("subjectId") String subjectId, @Param("paperType") String paperType);
}
