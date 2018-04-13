package org.scut.dao;

import org.scut.model.Practice;

public interface PracticeMapper {
    int deleteByPrimaryKey(Integer practiceId);

    int insert(Practice record);

    int insertSelective(Practice record);

    Practice selectByPrimaryKey(Integer practiceId);

    int updateByPrimaryKeySelective(Practice record);

    int updateByPrimaryKey(Practice record);
}