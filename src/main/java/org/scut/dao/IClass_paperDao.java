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
	public int deleteList(List<String> paperId);
	public List<HashMap<String,Object>> getCorrectionList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public List<HashMap<String,Object>> get(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public int assignHomework(String paperId,String classId,String assignTeacherId,String deadLine,int submitNumber,String assignTime,String paperType,int examTime);

}
