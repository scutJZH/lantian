package org.scut.dao;

import org.scut.model.student;

public interface studentMapper {
    int deleteByPrimaryKey(String studentId);

    int insert(student record);

    int insertSelective(student record);

    student selectByPrimaryKey(String studentId);

    int updateByPrimaryKeySelective(student record);

    int updateByPrimaryKey(student record);
}