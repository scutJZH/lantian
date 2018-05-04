package org.scut.util;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GlobalVar {

	public static ConcurrentMap<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	public static final String picPath = "/img/";
	
	public static final String titlePicPath = "/titles/";
	
	public static final String solutionPicPath = "/solutions/";
	
	public static final String questionPicPath="/question/";
}
