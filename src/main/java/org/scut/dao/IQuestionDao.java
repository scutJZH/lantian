package org.scut.dao;


import java.util.Map;
import java.util.UUID;
import org.apache.ibatis.annotations.Param;
import java.util.HashMap;
import java.util.List;
import org.scut.model.Question;
import org.springframework.stereotype.Repository;
@Repository
public interface IQuestionDao {
    int deleteByPrimaryKey(String questionId);

    int insert(Question record);

    int insertSelective(Question record);

    Question selectByPrimaryKey(String questionId);

    int updateByPrimaryKeySelective(Question record);

    int updateByPrimaryKey(Question record);

    Map<String, Object> getQuestion(@Param("questionId")String questionId);

    public List<HashMap<String,Object>> getQuestionList(@Param("subjectId")String subjectId);

    public List<HashMap<String,Object>> getSubjectiveList(@Param("teacherId")String teacherId,@Param("subjectId")String subjectId,@Param("grade")int grade);
    
    public List<HashMap<String,Object>> getObjectiveList(@Param("teacherId")String teacherId,@Param("subjectId")String subjectId,@Param("grade")int grade);
    public int createObjective(@Param("questionId")String questionId,@Param("titleId")String titleId,@Param("subjectId")String subjectId,@Param("grade")int grade,@Param("optionA")String optionA,@Param("optionB")String optionB,@Param("optionC")String optionC,@Param("optionD")String optionD,
    		@Param("answer")String answer,@Param("opaPicPath")String opaPicPath,@Param("opbPicPath")String opbPicPath,@Param("opcPicPath")String opcPicPath,@Param("opdPicPath")String opdPicPath);
    public Map<String,Object> checkTitle(@Param("questionId")String questionId);
    public int createSubjective(@Param("questionId")String questionId,@Param("titleId")String titleId,@Param("subjectId")String subjectId,@Param("grade")int grade,@Param("answer")String answer);
    public HashMap<String,Object> autoCorrectSelect(@Param("questionId")String questionId);
}