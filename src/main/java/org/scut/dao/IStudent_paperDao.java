package org.scut.dao;

<<<<<<< HEAD
import java.util.List;
import java.util.Map;
=======
import java.util.HashMap;
import java.util.List;
>>>>>>> branch 'master' of https://github.com/scutJZH/lantian.git

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_paperDao {
<<<<<<< HEAD
	public List<Map> getHomeworkInfo(@Param("studentId") String studentId, @Param("classId") String classid,
			@Param("subjectId") String subjectId, @Param("paperType") String paperType);
=======
	public List<HashMap<String,String>> getRankDetails(@Param("paperId")String paperId);
	public List<HashMap<String,String>> getCorrectStudentList(@Param("teacherId")String teacherId,@Param("paperId")String paperId);

>>>>>>> branch 'master' of https://github.com/scutJZH/lantian.git
}
