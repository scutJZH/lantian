package org.scut.dao;


import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
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
    
    public int getStudentNumber(@Param("classId")String classId);
    
    public int addStudent(@Param("classId")String classId,@Param("recentStudentNumber")int recentStudentNumber);
    public int addClass(@Param("schoolName")String schoolName,@Param("teacherId")String teacherId,
    					@Param("grade")String grade,@Param("classId")String classId,@Param("className")String className,
    					@Param("createTime")String createTime,@Param("studentNumber")String studentNumber);
    public HashMap<String,Object> judgeClassExist(@Param("classId")String classId);
    public int addClass(String schoolName,String teacherId,int grade,String classId,String className,String createTime,int studentNumber);
	
}