package org.scut.dao;

import org.scut.model.Schedule;
import org.springframework.stereotype.Repository;
@Repository
public interface IScheduleDao {
    int deleteByPrimaryKey(String scheduleId);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(String scheduleId);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);
}