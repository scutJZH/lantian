package org.scut.dao;

import org.scut.model.Paper;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaperDao {
    int deleteByPrimaryKey(String paperId);

    int insert(Paper record);

    int insertSelective(Paper record);

    Paper selectByPrimaryKey(String paperId);

    int updateByPrimaryKeySelective(Paper record);

    int updateByPrimaryKey(Paper record);
}