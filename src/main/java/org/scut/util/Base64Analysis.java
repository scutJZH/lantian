package org.scut.util;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.springframework.util.Base64Utils;

public class Base64Analysis {

	public static String analysisPic(String filename,String filePath, String base64Str) throws Exception{
		String tempFileName = "";
		
		if(base64Str == null){
            throw new Exception("�ϴ�ʧ�ܣ��ϴ�ͼƬ����Ϊ��");
        } else{
        	String dataPrix;
        	String data;
            String [] d = base64Str.split("base64,");
            if(d != null && d.length == 2){
                dataPrix = d[0];
                data = d[1];
            }else{
                throw new Exception("�ϴ�ʧ�ܣ����ݲ��Ϸ�");
            }String suffix = "";
            if("data:image/jpeg;".equalsIgnoreCase(dataPrix)){//data:image/jpeg;base64,base64�����jpegͼƬ����
                suffix = ".jpg";
            } else if("data:image/x-icon;".equalsIgnoreCase(dataPrix)){//data:image/x-icon;base64,base64�����iconͼƬ����
                suffix = ".ico";
            } else if("data:image/gif;".equalsIgnoreCase(dataPrix)){//data:image/gif;base64,base64�����gifͼƬ����
                suffix = ".gif";
            } else if("data:image/png;".equalsIgnoreCase(dataPrix)){//data:image/png;base64,base64�����pngͼƬ����
                suffix = ".png";
            }else{
                throw new Exception("�ϴ�ͼƬ��ʽ���Ϸ�");
            }
            tempFileName =filename + suffix;
          
          //��ΪBASE64Decoder��jar���⣬�˴�ʹ��spring����ṩ�Ĺ��߰�
            byte[] bs = Base64Utils.decodeFromString(data);
                //ʹ��apache�ṩ�Ĺ����������
            	File imgFile = new File(tempFileName,filename);
                FileUtils.writeByteArrayToFile(imgFile, bs);  
        }
		return tempFileName;
	}
	
}
