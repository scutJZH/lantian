package org.scut.dao;

import org.scut.model.Post;
import org.springframework.stereotype.Repository;
@Repository
public interface IPostDao {
    int deleteByPrimaryKey(String postId);

    int insert(Post record);

    int insertSelective(Post record);

    Post selectByPrimaryKey(String postId);

    int updateByPrimaryKeySelective(Post record);

    int updateByPrimaryKey(Post record);

}