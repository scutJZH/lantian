package org.scut.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/test")
public class TestController {
	
	class User1{
		private String m;
		private String k;
		
		public String getM() {
			return m;
		}

		public void setM(String m) {
			this.m = m;
		}

		public String getK() {
			return k;
		}

		public void setK(String k) {
			this.k = k;
		}
		
		}
	
	@RequestMapping("/objcetJSON")
	public void test1(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String a = request.getParameter("a");
		String b = request.getParameter("b");
		
		System.out.println("a = "+a+"b="+b);
		
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson(); 
		
		
		
		User1 user = new User1();
		user.setK("hahdah");
		user.setM("dadasda");
		
		String json = gson.toJson(user);
		System.out.println(json);
		out.write(json);
		out.flush();
		out.close();
		
		
	}
	
	@RequestMapping("/JSONObject")
	public void test2(HttpServletRequest request, HttpServletResponse response) throws IOException{
		PrintWriter out = response.getWriter();
		JsonObject jobj = new JsonObject();
		jobj.addProperty("a", 1);
		jobj.addProperty("b", "is b");
		
		JsonObject jobj2 = new JsonObject();
		jobj2.addProperty("c", 3);
		jobj2.add("json", jobj);
		
		out.write(jobj2.toString());
		out.flush();
		out.close();
	}

	


}
