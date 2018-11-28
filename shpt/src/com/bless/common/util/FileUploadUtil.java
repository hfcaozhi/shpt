package com.bless.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;


public class FileUploadUtil {
	//上传职工的图片
	public static String  memberFileUpload(File file,String fileName){
		
		String destFileName=null;
		//判断上传的文件是否存在
		if(file.exists()&&null!=fileName)
		{
			//获取保存文件的路径
			String memberPath = PropertiesUtil.getInstance().getMemberPic();
			//获取文件的后缀名
			String fileExtension = fileName.split("\\.")[1];
			//要保存的文件名
			 destFileName = UUID.randomUUID().toString()+"."+fileExtension;
			//保存文件的路径
			String filePath = memberPath+"/"+destFileName;
			//输入和输出流
			InputStream inputStream = null;
			OutputStream outputStream = null;
			try
			{
				//保存的文件
				File destFile = new File(filePath);
				outputStream = new FileOutputStream(destFile);
				inputStream = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len=inputStream.read(buffer))>0){
					outputStream.write(buffer,0,len);
				}
				
			}catch(Exception e)
			{
				destFileName = null;
				System.out.println("文件上传失败!");
	            e.printStackTrace();
			}
			finally{
				try {
					inputStream.close();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		return destFileName;
	}
	//删除图片的内容
	public static boolean delMemberFile(String name){
		boolean flag = false;
		if(null!=name){
			String fileName = PropertiesUtil.getInstance().getMemberPic()+"/"+name;
			File file = new File(fileName);
			if(file.exists()){
				flag = file.delete();
			}
		}
		return flag;
	}
}
