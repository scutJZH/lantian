package org.scut.dao;


import java.util.List;
import java.util.Map;
import java.sql.Blob;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IStudyDao {

	
	public Map<String, Object> getStudyById(@Param("studyId")String studyId);

	public List<HashMap<String,Object>> selectList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public int deleteList(@Param("studyIdList")List<String> studyId);
	public List<HashMap<String,Object>> getCorrectionList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public List<HashMap<String,Object>> get(@Param("teacherId")String teacherId,@Param("classId")String classId);

	public int assignHomework(@Param("studyId")String studyId, @Param("paperId")String paperId,@Param("classId")String classId,@Param("assignTeacherId")String assignTeacherId,@Param("deadLine")String deadLine,@Param("submitNumber")int submitNumber,@Param("assignTime")String assignTime,@Param("paperType")String paperType,@Param("examTime")int examTime,@Param("studyName")String studyName);
	public HashMap<String,Object> getCorrectionList2(@Param("studyId")String studyId);


	public void updateSubmitNum(@Param("studyId")String studyId);

	public int getRankDetails(@Param("rank")String rank,@Param("studyId")String studyId);
	public Blob getBlobRank(@Param("studyId")String studyId);
	
	public Map<String, Object> getRankById(@Param("studyId")String studyId);
}