package org.scut.dao;

import org.scut.model.Title;
import org.springframework.stereotype.Repository;
@Repository
public interface ITitleDao {
    int deleteByPrimaryKey(String titleId);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(String titleId);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);
}