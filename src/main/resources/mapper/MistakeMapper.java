package org.scut.dao;

import org.scut.model.Mistake;

public interface MistakeMapper {
    int deleteByPrimaryKey(Integer mistakeId);

    int insert(Mistake record);

    int insertSelective(Mistake record);

    Mistake selectByPrimaryKey(Integer mistakeId);

    int updateByPrimaryKeySelective(Mistake record);

    int updateByPrimaryKey(Mistake record);
}