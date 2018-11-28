package com.bless.common.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static PropertiesUtil propertiesUtil;
	private static Properties properties;
	
	private PropertiesUtil(){
		properties = new Properties();
		try{
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("fileUpload.properties"));
		}catch (IOException e) {
			System.out.println("加载fileUpload.properties配置文件出现错误！！");
			e.printStackTrace();
		}
	}
	public  synchronized static  PropertiesUtil getInstance(){
		if(null==propertiesUtil){
			propertiesUtil = new PropertiesUtil();
		}
		return propertiesUtil;
	}
	//获取属性
	public static String getMemberPic(){
		return properties.getProperty("member_img");
	}
	
}
