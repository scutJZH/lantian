package org.scut.dao;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.scut.dao.IClass_paperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IClass_paperDaoTest {

    @Resource
    private ITitleDao titleDao;

    @Test
    public void test2() throws Exception {
       String teacherId="201430613253";
       String classId="10001";
       String paperId="1001";
       String studentId="201830613253";
       String subjectId="5";
       String questionId="1";
       //papar_id,paper_name,assign_time,submit_number
       HashMap<String,Object> test= titleDao.getTitleContent(questionId);
       //int test= classDao.getStudentNumber(classId);System.out.println(test);
       System.out.println(test.get("titleContent")+" "+test.get("titleContent"));
    }
}
