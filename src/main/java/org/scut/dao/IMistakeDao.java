package org.scut.dao;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.scut.model.Mistake;
import org.scut.model.Question;
import org.springframework.stereotype.Repository;

@Repository
public interface IMistakeDao {
	List<Mistake> findmistakebysub (@Param("subjectId")String subjectId,@Param("studentId")String studentId);
	List<Mistake> findmistakebytype(@Param("subjectId")String subjectId,@Param("studentId")String studentId,@Param("questionType")String questionType);
    List<Mistake> findmistakebystu(@Param("studentId")String studentId);
    int delete(@Param("studentId")String studentId,@Param("questionId")String questionId);
    int updatenote(@Param("studentId")String studentId,@Param("questionId")String questionId,@Param("note")String note);
    int insertquestion(Question question);
    int inserttitle(@Param("titleId")String titleId,@Param("titleContent")String titleContent);
    int insertmistake(@Param("studentId")String studentId,@Param("questionId")String questionId,@Param("note")String note,
    					@Param("questionType")String questionType,@Param("lastAnswer")String lastAnwer,@Param("createTime")Date createTime);
   int updateanswer(@Param("studentId")String studentId,@Param("questionId")String questionId,@Param("newAnswer")String newAnswer,@Param("changeTime")Date changeTime); 
   int inserttitle2(@Param("titleId")String titleId,@Param("picPath")String picPath);
    
}