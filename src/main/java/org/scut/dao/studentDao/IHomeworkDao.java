package org.scut.dao.studentDao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IHomeworkDao {
	public Map<String, String> getHomeworkTitleAndCreateTime(@Param("homeworkId")String homeworkId, @Param("subjectId")String subjectId);
}
