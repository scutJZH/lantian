package org.scut.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudent_classDao {
	
	public List<String> getClassIdListByStudentId(@Param("studentId")String studentId);
	
	public void insertRelationship(@Param("studentId")String studentId, @Param("classId")String classId);

	public List<Map<String, Object>> getClassIdAndNameByStudentId(@Param("studentId")String studentId);

	public void deleteRelationshipByClassId(@Param("classId")String classId);
	
	public void deleteRelationship(@Param("studentId")String studentId, @Param("classId")String classId);

	public boolean isRelationshipExist(@Param("studentId")String studentId, @Param("classId")String classId);
	
	public List<Map<String, Object>> getStudentInfoListByClassId(@Param("classId")String classId);

	public List<Map<String, Object>> getClassInfoListByStudentId(@Param("studentId")String studentId);
}
