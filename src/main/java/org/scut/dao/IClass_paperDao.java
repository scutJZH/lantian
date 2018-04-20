package org.scut.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import org.springframework.context.annotation.ComponentScan;  
import org.springframework.context.annotation.Configuration;  

@Configuration  
@ComponentScan 
@Repository
public interface IClass_paperDao {
	//注意Object可能要改
	public ArrayList<HashMap<String,Object>> selectList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public int deleteList(List<String> paperId);
	public List<HashMap<String,String>> getCorrectionList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public List<HashMap<String,String>> get(@Param("teacherId")String teacherId,@Param("classId")String classId);

	/**
	 * @author jzh
	 * @param classId
	 * @param subjectId
	 * @param studyType
	 * @return paperIdsList
	 */
	public List<String> getChildPaperIds(@Param("classId")String classId,
			@Param("subjectId")String subjectId, @Param("studyType")String studyType);
}
