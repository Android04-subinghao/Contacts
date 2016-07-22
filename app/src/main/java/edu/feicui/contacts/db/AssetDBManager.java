package edu.feicui.contacts.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

//用来操作本地Assets文件夹内db数据的管理类
public class AssetDBManager {
	/*
	 * 复制本地assets中的db文件到指定目录中
	 * @param context
	 * @param path
	 * 	Assets内db文件路径
	 * @param toFile
	 *  目标目录
	 *  @throws IOException
	 */
	public static void copyAssetsFileToFile(Context context,String path,File toFile) throws IOException{
		InputStream inStream=null;
	//  输入流( 用来读取当前项目的 Assets  内的 db )
		BufferedInputStream buffInputStream=null;
	//  输出流( 用来将读取到的 db  信息写到指定目录文件 toFile  中去)
		BufferedOutputStream buffOutputStream=null;
		inStream=context.getAssets().open(path);
		buffInputStream=new BufferedInputStream(inStream);
		buffOutputStream=new BufferedOutputStream(new FileOutputStream(toFile, false));
		//IO操作
		int len=0;
		byte[] buff=new byte[2*1024];
		while((len=buffInputStream.read(buff))!=-1){
			buffOutputStream.write(buff, 0, len);
		}
		buffOutputStream.flush();
		//IO关闭
		buffOutputStream.close();
		buffInputStream.close();
		inStream.close();
	}
}
