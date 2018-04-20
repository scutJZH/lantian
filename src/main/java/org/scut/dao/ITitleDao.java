package org.scut.dao;


import java.util.Map;
import java.util.HashMap;

import java.util.UUID;
import org.apache.ibatis.annotations.Param;
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

	Map<String, Object> getTitle(@Param("titleId")String string);

    public HashMap<String,Object> getTitleContent(@Param("questionId")String questionId);

    public int createObjective(@Param("titleId")UUID titleId,@Param("titleContent")String titleContent);
}