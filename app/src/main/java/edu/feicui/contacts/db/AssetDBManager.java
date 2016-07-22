package edu.feicui.contacts.db;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

//������������Assets�ļ�����db���ݵĹ�����
public class AssetDBManager {
	/*
	 * ���Ʊ���assets�е�db�ļ���ָ��Ŀ¼��
	 * @param context
	 * @param path
	 * 	Assets��db�ļ�·��
	 * @param toFile
	 *  Ŀ��Ŀ¼
	 *  @throws IOException
	 */
	public static void copyAssetsFileToFile(Context context,String path,File toFile) throws IOException{
		InputStream inStream=null;
	//  ������( ������ȡ��ǰ��Ŀ�� Assets  �ڵ� db )
		BufferedInputStream buffInputStream=null;
	//  �����( ��������ȡ���� db  ��Ϣд��ָ��Ŀ¼�ļ� toFile  ��ȥ)
		BufferedOutputStream buffOutputStream=null;
		inStream=context.getAssets().open(path);
		buffInputStream=new BufferedInputStream(inStream);
		buffOutputStream=new BufferedOutputStream(new FileOutputStream(toFile, false));
		//IO����
		int len=0;
		byte[] buff=new byte[2*1024];
		while((len=buffInputStream.read(buff))!=-1){
			buffOutputStream.write(buff, 0, len);
		}
		buffOutputStream.flush();
		//IO�ر�
		buffOutputStream.close();
		buffInputStream.close();
		inStream.close();
	}
}
