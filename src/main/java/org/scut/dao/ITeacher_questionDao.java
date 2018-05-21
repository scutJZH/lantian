package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacher_questionDao {
public int createQuestion(@Param("teacherId")String teacherId,@Param("questionId")String questionId);
}
