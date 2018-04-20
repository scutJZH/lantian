package org.scut.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    
    public List<HashMap<String,Object>> getQuestionList(String subjectId);
    
    public List<Map<String,Object>> getSubjectiveList(String subjectId,String d);
    
    public List<Map<String,Object>> getObjectiveList(String subjectId);
    
    public int createObjective(String teacherId,
			String titleContent,UUID titleId,
			String optioanA,String optionB,String optionC,String optionD,
			String answer);
    public Map<String,Object> checkTitle(String question);
}