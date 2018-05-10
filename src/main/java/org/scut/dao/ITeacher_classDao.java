package org.scut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITeacher_classDao {
	  public List<Map<String,Object>> getClassList(@Param("teacherId")String teacherId);

	  public void insertRelationship(@Param("teacherId")String teacherId, @Param("classId")String classId);

	  public boolean isRealtionshipExist(@Param("teacherId")String teacherId, @Param("classId")String classId);

	  public List<Map<String, Object>> getClassIdAndNameByTeacherId(@Param("teacherId")String teacherId);

	  public void deleteRelationshipByClassId(@Param("classId")String classId);
	  
	  public void deleteRelationship(@Param("teacherId")String teacherId, @Param("classId")String classId);
	  
	  public List<Map<String, Object>> getClassInfoListByTeacherId(@Param("teacherId")String teacherId);
	  
	  public List<Map<String, Object>> getTeacherInfoListByClassId(@Param("classId")String classId);
}
