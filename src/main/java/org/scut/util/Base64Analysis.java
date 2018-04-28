package org.scut.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

public class Base64Analysis {

	public static String analysisPic(String filename,String filePath, String base64Str) throws Exception{
		String tempFileName = "";
		
		if(base64Str == null){
            throw new Exception("上传失败，上传图片数据为空");
        } else{
        	String dataPrix;
        	String data;
            String [] d = base64Str.split("base64,");
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else{
                throw new Exception("上传失败，数据不合法");
            }String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64编码的jpeg图片数据
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64编码的icon图片数据
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64编码的gif图片数据
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64编码的png图片数据
                suffix = ".png";
            }else{
                throw new Exception("上传图片格式不合法");
            }
            tempFileName =filename + suffix;
          
          //因为BASE64Decoder的jar问题，此处使用spring框架提供的工具包
            byte[] bs = Base64Utils.decodeFromString(data);
                //使用apache提供的工具类操作流
            	File imgFile = new File(tempFileName,filename);
                FileUtils.writeByteArrayToFile(imgFile, bs);  
        }
		return tempFileName;
	}
	
}
