package org.scut.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacher_questionDao {
public int createQuestion(@Param("teacherId")String teacherId,@Param("questionId")String questionId);
public int deleteTitle(@Param("questionId")String questionId,@Param("teacherId")String teacherId);
}
