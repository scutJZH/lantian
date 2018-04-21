package org.scut.dao;


import java.util.List;

import org.scut.model.Answer;
import org.scut.model.Answerbean;


public interface IAnswerDao {
    int deleteByPrimaryKey(String answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(String answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
    Answer findanswerbyid (String answerId);
    List<Answer> findanswerbypostid(String postId);

    List<Answerbean> findAnswerBeanByPostId(String postId);
}