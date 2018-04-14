package org.scut.dao;

import org.scut.model.Answer;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnswerDao {
    int deleteByPrimaryKey(String answerId);

    int insert(Answer record);

    int insertSelective(Answer record);

    Answer selectByPrimaryKey(String answerId);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}