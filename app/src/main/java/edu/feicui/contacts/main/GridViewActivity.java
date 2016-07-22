package edu.feicui.contacts.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import edu.feicui.contacts.R;

public class GridViewActivity extends Activity {
    private GridView mGridView;

    int[] pics = {R.drawable.icon_filemgr, R.drawable.icon_rocket, R.drawable.icon_phonemgr,
            R.drawable.icon_sdclean, R.drawable.icon_softmgr, R.drawable.icon_telmgr};
    String[] title = {"文件清理", "手机加速", "手机检测", "垃圾清理", "软件管理", "通讯大全"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        mGridView= (GridView) findViewById(R.id.gridview);
        mGridView.setAdapter(new adapter());
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==5){
                	Intent intent=new Intent(GridViewActivity.this,TelmsgActivity.class);
                	startActivity(intent);
                }
                if(i==4){
                	Intent intent=new Intent(GridViewActivity.this,ShowPhone.class);
                	startActivity(intent);
                }
                if(i==2){
                	Intent intent=new Intent(GridViewActivity.this,SoftInfoActivity.class);
                	startActivity(intent);
                }
            }
        });
    }
    public class adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return title.length;
        }

        @Override
        public Object getItem(int i) {
            return title[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view==null){
                view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_gridview_item,null);
            }
            ImageView iv= (ImageView) view.findViewById(R.id.itemImage);
            TextView tv= (TextView) view.findViewById(R.id.ItemText);
            iv.setImageResource(pics[i]);
            tv.setText(title[i]);
            return view;
        }
    }
}
