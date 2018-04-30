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
    
    public List<HashMap<String,Object>> getQuestionList(String subjectId,int grade);

    
    public List<HashMap<String,Object>> getSubjectiveList(String subjectId,int grade);
    
    public List<HashMap<String,Object>> getObjectiveList(String subjectId,int grade);
    public int createObjective(String questionId,String titleId,String subjectId,int grade,String optionA,String optionB,String optionC,String optionD,
			String answer,String opaPicPath,String opbPicPath,String opcPicPath,String opdPicPath);
    public Map<String,Object> checkTitle(String question);
    public int createSubjective(String questionId,String titleId,String subjectId,int grade,String answer);
}