package org.scut.dao;

import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao {
    User selectUser(long id);
    
    
    
}
