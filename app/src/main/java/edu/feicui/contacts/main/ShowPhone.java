package edu.feicui.contacts.main;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import edu.feicui.contacts.R;

public class ShowPhone extends Activity {
	ListView mlv;
	ArrayList<PackageInfo> mlist;
	ArrayList<Drawable> mlist1;
	ArrayList<CharSequence> mlist2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showphone);
		mlv=(ListView) findViewById(R.id.listview);
		mlist=new ArrayList<PackageInfo>();
		mlist1=new ArrayList<Drawable>();
		mlist2=new ArrayList<CharSequence>();
		
		PackageManager packageManager=this.getPackageManager();
		List<PackageInfo> infolist=packageManager.getInstalledPackages(0);
		
		for (int i = 0; i < infolist.size(); i++) {
			PackageInfo packageinfo=infolist.get(i);
			Drawable drawable=packageinfo.applicationInfo.loadIcon(packageManager);
			CharSequence charSequence = packageinfo.applicationInfo.loadLabel(packageManager);
			
			ApplicationInfo info=packageinfo.applicationInfo;
			
			if((info.flags & ApplicationInfo.FLAG_SYSTEM)<=0){
				mlist.add(packageinfo);
				mlist1.add(drawable);
				mlist2.add(charSequence);
			}
		}
		mlv.setAdapter(new adapter());
		mlv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Intent.ACTION_DELETE);
				intent.setData(Uri.parse("package:"+mlist.get(arg2).packageName));
				startActivity(intent);
			}
		});
		
		
		
	}

	class adapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mlist.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return mlist.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int arg0, View view, ViewGroup arg2) {
			// TODO Auto-generated method stub
			if(view==null){
				view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_showphone_item, null);
			}
			ImageView miv=(ImageView) view.findViewById(R.id.iv);
			TextView mtv=(TextView) view.findViewById(R.id.tv);
			TextView mtv1=(TextView) view.findViewById(R.id.tv1);
			TextView mtv2=(TextView) view.findViewById(R.id.tv2);
			
			miv.setImageDrawable(mlist1.get(arg0));
			mtv1.setText(mlist2.get(arg0));
			mtv.setText(mlist.get(arg0).versionName);
			mtv2.setText(mlist.get(arg0).packageName);
			return view;
		}
		
	}
	

}
