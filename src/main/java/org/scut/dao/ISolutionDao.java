package org.scut.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISolutionDao {

	public List<Map<String, Object>> getUncheckedSolution(@Param("studyId")String paperId,@Param("studentId")String studentId);

	public void insertSolution(@Param("studentId")String studentId,@Param("studyId") String studyId,@Param("questionId") String questionId,@Param("solutionContent") String solutionContent, @Param("picPath")String picPath,  @Param("isRight")String isRight);

	public int submitCorrection(@Param("studentId")String studentId,@Param("paperId")String paperId,@Param("questionId")String questionId,@Param("point")int point,@Param("isright")String isright,@Param("content")String content);
	public ArrayList<HashMap<String,Object>> autoCorrectSelect();
	public int autoCorrectRight(@Param("studentId")String studentId,@Param("questionId")String questionId);
	public int autoCorrectFalse(@Param("studentId")String studentId,@Param("questionId")String questionId);

	public void correctSolution(String studentId, String studyId, String questionId, int point, String picId,
			String isright);
	
}
