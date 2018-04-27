package org.scut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestion_paperDao {
	
	List<Map<String, Object>> getQuestionIds(@Param("paperId")String paperId);
	public int assignHomework(@Param("paperId")String paperId,@Param("questionId")String questionId,@Param("point")int point);
}
