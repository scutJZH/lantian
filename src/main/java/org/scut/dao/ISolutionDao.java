package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolutionDao {

	public List<HashMap<String, Object>> getSolution(@Param("teacherId")String teacherId,@Param("paperId")String paperId,@Param("studentId")String studentId);

	public void insertSolution(@Param("studentId")String studentId,@Param("paperId") String paperId,@Param("questionId") String questionId,@Param("solutionContent") String solutionContent);

	public void updateSolution(@Param("studentId")String studentId,@Param("paperId") String paperId,@Param("questionId") String questionId,@Param("solutionContent") String solutionContent);

}
