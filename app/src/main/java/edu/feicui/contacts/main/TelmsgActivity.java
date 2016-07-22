package edu.feicui.contacts.main;

import java.io.IOException;

import edu.feicui.contacts.R;
import edu.feicui.contacts.adapter.TelclassAdapter;
import edu.feicui.contacts.db.AssetDBManager;
import edu.feicui.contacts.db.DBReader;
import edu.feicui.contacts.entity.TelclassInfo;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class TelmsgActivity extends Activity implements OnItemClickListener{
	/**
	 * 通讯大全页面
	 */
	//全局定义ListView控件
	private ListView listView;
	private TelclassAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_telmsg);
		//初始控件
		listView=(ListView) findViewById(R.id.listview);
		adapter=new TelclassAdapter(this);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	private void initAppDBFile(){
		if(!DBReader.isExistsTeldbFIle()){
			try {
				AssetDBManager.copyAssetsFileToFile(getApplicationContext(), "db/commonnum.db", DBReader.telFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initAppDBFile();
		adapter.clearDataToAdapter();
		adapter.addDataToAdapter(new TelclassInfo("本地电话",0));
		adapter.addDataToAdapter(DBReader.readTeldbClasslist());
		adapter.notifyDataSetChanged();
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub
		if (position==0) {
			Intent intent=new Intent();
			intent.setAction(Intent.ACTION_DIAL);
			startActivity(intent);
			return;
		}
		TelclassInfo classInfo=(TelclassInfo) adapter.getItem(position);
//		Intent intent=new Intent(this,TellistActivity.class);
//		intent.putExtra("idx", classInfo.idx);
//		startActivity(intent);
	}
}
