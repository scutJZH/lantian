package org.scut.dao;


import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  


@Repository
public interface IClass_paperDao {

	
	List<Map<String, Object>> getClassPaperByCId(@Param("classId")String classId);

	//注意Object可能要改
	public List<HashMap<String,Object>> selectList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public int deleteList(@Param("paperIdList")List<String> paperId);
	public List<HashMap<String,Object>> getCorrectionList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public List<HashMap<String,Object>> get(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public int assignHomework(@Param("paperId")String paperId,@Param("classId")String classId,@Param("assignTeacherId")String assignTeacherId,@Param("deadLine")String deadLine,@Param("submitNumber")int submitNumber,@Param("assignTime")String assignTime,@Param("paperType")String paperType,@Param("examTime")int examTime);
	public HashMap<String,Object> getCorrectionList2(@Param("teacherId")String teacherId,@Param("paperId")String paperId);
}
