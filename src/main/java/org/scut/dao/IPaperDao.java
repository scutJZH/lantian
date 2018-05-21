package org.scut.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
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
    int assignHomework(@Param("paperId")String paperId,@Param("paperName")String paperName,@Param("grade")String grade,@Param("subjectId")String subjectId,@Param("createTeacherId")String createTeacherId,@Param("createTime")String createTime,@Param("maxScore")int maxScore);
}