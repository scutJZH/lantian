package org.scut.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
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

	List<Map<String, Object>> getSchedules(String studentId);

	List<Map<String, Object>> getSchedulesByDate(@Param("studentId") String studentId, @Param("year") int year, @Param("month") int month,@Param("day") int day);

	void addSchedule(@Param("scheduleId")String scheduleId,@Param("studentId")String studentId, @Param("startTime")String startTime, @Param("endTime")String endTime,@Param("scheduleContext")String scheduleContext,@Param("year")int year, @Param("month")int month, @Param("day")int day, 
			@Param("dayOfWeek")int dayOfWeek);
}