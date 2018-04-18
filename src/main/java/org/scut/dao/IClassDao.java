package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.scut.model.Class;
import org.springframework.stereotype.Repository;
@Repository
public interface IClassDao {
    int deleteByPrimaryKey(String classId);

    int insert(Class record);

    int insertSelective(Class record);

    Class selectByPrimaryKey(String classId);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
    
    public int getStudentNumber(String classId);
}