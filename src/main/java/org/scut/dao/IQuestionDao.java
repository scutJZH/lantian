package org.scut.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
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
}