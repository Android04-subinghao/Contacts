package edu.feicui.contacts.adapter;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.contacts.R;
import edu.feicui.contacts.db.DBReader;
import edu.feicui.contacts.entity.TelclassInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TelclassAdapter extends BaseAdapter{

	private LayoutInflater layouInflater;
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return adapterDatas.size();
	}

	@Override
	public TelclassInfo getItem(int arg0) {
		// TODO Auto-generated method stub
		return adapterDatas.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if (arg1==null) {
			arg1=layouInflater.inflate(R.layout.inflate_telmgr_listitem, null);
		}
		TextView tv_text=(TextView) arg1.findViewById(R.id.textview);
		tv_text.setText(getItem(arg0).name);
		return arg1;
	}
	private ArrayList<TelclassInfo> adapterDatas=new ArrayList<TelclassInfo>();
	public void addDataToAdapter(TelclassInfo e){
		if (e!=null) {
			adapterDatas.add(e);
		}
	}
	public void addDataToAdapter(List<TelclassInfo> e){
		if (e!=null) {
			adapterDatas.addAll(e);
		}
	}
	public ArrayList<TelclassInfo> getDataFromAdapter(){
		return adapterDatas;
	}
	public void clearDataToAdapter(){
		adapterDatas.clear();
	}
	public TelclassAdapter(Context context){
		layouInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	}
}
