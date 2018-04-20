package org.scut.dao;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.scut.dao.IClass_paperDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IClass_paperDaoTest {

    @Resource
    private IClass_paperDao class_paperDao;

    @Test
    public void test2() throws Exception {
       String teacherId="201430613253";
       String classId="10001";
       //papar_id,paper_name,assign_time,submit_number
        List<HashMap<String,String>> test= class_paperDao.get(teacherId,classId);
        System.out.println(test.get(1).get("assign_teacher_id").toString()+" "+test.get(2).get("assign_teacher_id").toString());
    }
}
