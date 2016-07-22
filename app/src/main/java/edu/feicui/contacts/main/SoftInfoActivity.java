package edu.feicui.contacts.main;

import java.io.File;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import edu.feicui.contacts.R;

public class SoftInfoActivity extends Activity {
    private ListView mListView;

    int[] pics={R.drawable.setting_info_icon_version, R.drawable.setting_info_icon_cpu, R.drawable.setting_info_icon_camera, R.drawable.setting_info_icon_root, R.drawable.setting_info_icon_space};

    String[] name={"设备名称","CPU名称","手机分辨率","基带版本","内存"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_softinfo);
        mListView= (ListView) findViewById(R.id.listview);

        mListView.setAdapter(new adapter());

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    public  class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return name.length;
        }

        @Override
        public Object getItem(int i) {
            return name[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_softinfo_item,null);
            }
            ImageView image= (ImageView) view.findViewById(R.id.imageview);
            TextView textview1= (TextView) view.findViewById(R.id.textview1);
            TextView textview2= (TextView) view.findViewById(R.id.textview2);

            image.setImageResource(pics[i]);
            textview1.setText(name[i]);
            switch (i){
                case 0:
                    textview2.setText(Build.MODEL);
                    break;
                case 1:
                    textview2.setText(Build.CPU_ABI);
                    break;
                case 2:
                	WindowManager windowManager = getWindowManager();
                    Display display1 = windowManager.getDefaultDisplay();
                    int screenWidth = screenWidth = display1.getWidth();
                    int screenHeight = screenHeight = display1.getHeight();
                    textview2.setText((screenWidth+"***"+screenHeight));
                    break;
                case 3:
                    textview2.setText(Build.ID);
                    break;
                case 4:
                	 File path = Environment.getDataDirectory();
                     StatFs stat = new StatFs(path.getPath());
                     long blockSize = stat.getBlockSize();
                     long totalBlocks = stat.getBlockCount();
                     textview2.setText((blockSize*totalBlocks)+"");
                     break;
            }


            return view;
        }
    }
}
