package org.scut.dao.studentDao;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Practice;
import org.springframework.stereotype.Repository;

@Repository
public interface IPracticeDao {
	
	public Practice getPracticeByIdAndSubjectId(@Param("practiceId")String practiceId, @Param("subjectId")String subjectId);
}
