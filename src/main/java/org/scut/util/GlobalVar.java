package org.scut.util;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GlobalVar {

	public static ConcurrentMap<String, String> tokenMap = new ConcurrentHashMap<String, String>();

	public static final String picPath = "/src/main/webapp/img/";
}