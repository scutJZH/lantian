package org.scut.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IClass_paperDao {
	//注意Object可能要改
	public ArrayList<HashMap<String,Object>> selectList(@Param("teacherId")String teacherId,@Param("classId")String classId);
	public int deleteList(List<String> paperId);
	public List<HashMap<String,String>> getCorrectionList(@Param("teacherId")String teacherId,@Param("classId")String classId);
}
