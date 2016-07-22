package edu.feicui.contacts.db;

import java.io.File;
import java.util.ArrayList;

import edu.feicui.contacts.entity.TelclassInfo;
import edu.feicui.contacts.entity.TelnumberInfo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

//用来做数据库文件的读取操作
public class DBReader {
	public static File telFile;
static{
	String dbFileDir="data/data/edu.feicui.contacts/";
	File fileDir=new File(dbFileDir);
	fileDir.mkdirs();
	telFile=new File(dbFileDir,"commonnum.db");
}
/*
 * 检测是否存在通讯大全DB文件
 */
	public static boolean isExistsTeldbFIle(){
		//没有通讯大全File
		File toFile=DBReader.telFile;
		if (!toFile.exists()||toFile.length()<=0) {
			return false;
		}
		return true;
	}
	/*
	 * 读取TelFile这个数据库文件中的classlist这个表内的数据
	 */
	public static ArrayList<TelclassInfo> readTeldbClasslist(){
		ArrayList<TelclassInfo> classListInfos=new ArrayList<TelclassInfo>();
		//打开DB文件
		SQLiteDatabase db=null;
		//执行查询SQL语句
		Cursor cursor=null;
		db=SQLiteDatabase.openOrCreateDatabase(telFile, null);
		cursor=db.rawQuery("select * from classlist", null);
		if (cursor.moveToFirst()) {
			do{
				String name=cursor.getString(cursor.getColumnIndex("name"));
				//idx 为 为 classlist  表中电话的 ID ，根据 idx 
				int idx=cursor.getInt(cursor.getColumnIndex("idx"));
				TelclassInfo classListInfo=new TelclassInfo(name,idx);
				classListInfos.add(classListInfo);
			}while(cursor.moveToNext());
		}
		cursor.close();
		db.close();
		return classListInfos;
	}
	/**  读取 telFile  这个数据库文件中的 tableN  这个表内的数据( 商家名称和电话号码)
	* @throws Exception */
	public static ArrayList<TelnumberInfo> readTeldbTable(int idx){
		ArrayList<TelnumberInfo> numberInfos=new ArrayList<TelnumberInfo>();
		//idx  为 classlist  表中电话的 ID ，根据 idx 
		String sql="select * from table"+idx;
		SQLiteDatabase db=null;
		Cursor cursor=null;
		//打开DB文件
		db=SQLiteDatabase.openOrCreateDatabase(telFile, null);
		//执行查询SQL语句
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
