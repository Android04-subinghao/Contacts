package edu.feicui.contacts.db;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.contacts.entity.TelclassInfo;
import edu.feicui.contacts.entity.TelnumberInfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//���������ݿ��ļ��Ķ�ȡ����
public class DBReader {
	public static File telFile;
static{
	String dbFileDir="data/data/edu.feicui.contacts/";
	File fileDir=new File(dbFileDir);
	fileDir.mkdirs();
	telFile=new File(dbFileDir,"commonnum.db");
}
/*
 * ����Ƿ����ͨѶ��ȫDB�ļ�
 */
	public static boolean isExistsTeldbFIle(){
		//û��ͨѶ��ȫFile
		File toFile=DBReader.telFile;
		if (!toFile.exists()||toFile.length()<=0) {
			return false;
		}
		return true;
	}
	/*
	 * ��ȡTelFile������ݿ��ļ��е�classlist������ڵ�����
	 */
	public static ArrayList<TelclassInfo> readTeldbClasslist(){
		ArrayList<TelclassInfo> classListInfos=new ArrayList<TelclassInfo>();
		//��DB�ļ�
		SQLiteDatabase db=null;
		//ִ�в�ѯSQL���
		Cursor cursor=null;
		db=SQLiteDatabase.openOrCreateDatabase(telFile, null);
		cursor=db.rawQuery("select * from classlist", null);
		if (cursor.moveToFirst()) {
			do{
				String name=cursor.getString(cursor.getColumnIndex("name"));
				//idx Ϊ Ϊ classlist  ���е绰�� ID ������ idx 
				int idx=cursor.getInt(cursor.getColumnIndex("idx"));
				TelclassInfo classListInfo=new TelclassInfo(name,idx);
				classListInfos.add(classListInfo);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return classListInfos;
	}
	/**  ��ȡ telFile  ������ݿ��ļ��е� tableN  ������ڵ�����( �̼����ƺ͵绰����)
	* @throws Exception */
	public static ArrayList<TelnumberInfo> readTeldbTable(int idx){
		ArrayList<TelnumberInfo> numberInfos=new ArrayList<TelnumberInfo>();
		//idx  Ϊ classlist  ���е绰�� ID ������ idx 
		String sql="select * from table"+idx;
		SQLiteDatabase db=null;
		Cursor cursor=null;
		//��DB�ļ�
		db=SQLiteDatabase.openOrCreateDatabase(telFile, null);
		//ִ�в�ѯSQL���
		cursor=db.rawQuery(sql, null);
		if (cursor.moveToFirst()) {
			do{
				String name=cursor.getString(cursor.getColumnIndex("name"));
				String number=cursor.getString(cursor.getColumnIndex("number"));
				TelnumberInfo numberInfo=new TelnumberInfo(name,number);
				numberInfos.add(numberInfo);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return numberInfos;
	}
}
