package org.scut.controller.publicController;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.scut.model.inter.Dog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("test")
public class TestController {
	
	@Resource
	public Dog dog; 

	@RequestMapping("beanextends")
	@ResponseBody
	public Dog beanExtendsTest(HttpServletRequest request, HttpServletResponse response) throws IOException{
		dog.setId("111");
		dog.setName("qinghuai");
		dog.setFood("xiangchang");
		return dog;
	}

}
