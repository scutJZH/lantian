package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeDao {
	int addlike(@Param("studentId")String studentId ,@Param("answerId")String answerId) ;
	int find(@Param("studentId")String studentId ,@Param("answerId")String answerId);
	int delete(@Param("studentId")String studentId ,@Param("answerId")String answerId);

}
