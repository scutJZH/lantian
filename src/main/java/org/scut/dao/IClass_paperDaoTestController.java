package org.scut.dao;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.scut.controller.teacherController.TeacherCourseModule;
import org.scut.dao.IClass_paperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
 
/**
 * 集成Web环境方式 springmvc mock测试
 *
 * @author admin
 *
 * 2017年11月23日 上午11:12:43
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath*:spring-*.xml" })
public class IClass_paperDaoTestController extends AbstractJUnit4SpringContextTests {
 
  @Autowired
  public WebApplicationContext wac;
 
  public MockMvc mockMvc;
 
  public MockHttpSession session;
 
  @Before
  public void before() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
  }
 
  @Test
  public void testGetSequence() {
    try {
      MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/getClassList"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
      int status = mvcResult.getResponse().getStatus();
      System.out.println("请求状态码：" + status);
      String result = mvcResult.getResponse().getContentAsString();
      System.out.println("接口返回结果：" + result);
      JSONObject resultObj = JSON.parseObject(result);
      // 判断接口返回json中success字段是否为true
      if(resultObj.getBooleanValue("success")) System.out.println("true");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
 
}