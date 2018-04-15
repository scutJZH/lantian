package org.scut.dao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Post;

public interface IPostDao {
    int deleteByPrimaryKey(String postId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

	Post findpostbyid(@Param("postId")String postId);
}