package org.scut.dao.studentDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Student;
import org.scut.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentDao {
	
	public void inputStudent(Student student);
	
	public String verificateTelnumber(String telnumber);
	
	public Map<String,String> queryIdAndPwdAndToken(String telnumber);
	
	public void updateToken(@Param("id")String id, @Param("token")String token);
	
}
