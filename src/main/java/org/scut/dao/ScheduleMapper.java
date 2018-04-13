package org.scut.dao;

import org.scut.model.Schedule;

public interface ScheduleMapper {
    int deleteByPrimaryKey(String scheduleId);

    int insert(Schedule record);

    int insertSelective(Schedule record);

    Schedule selectByPrimaryKey(String scheduleId);

    int updateByPrimaryKeySelective(Schedule record);

    int updateByPrimaryKey(Schedule record);
}