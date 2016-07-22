package edu.feicui.contacts.main;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import edu.feicui.contacts.R;
import edu.feicui.contacts.adapter.MyPagerAdapter;

import java.util.ArrayList;



public class ViewPagerActivity extends Activity implements View.OnClickListener{
    private ViewPager mViewPager;
    ArrayList<View> mList;
    Button mButton;
    int[] pics={R.drawable.adware_style_applist, R.drawable.adware_style_banner, R.drawable.adware_style_creditswall};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences=getSharedPreferences("is_first_open",MODE_PRIVATE);
        Boolean b=preferences.getBoolean("is_first_open",true);
        if (!b){
            Intent intent=new Intent(ViewPagerActivity.this,LogoActivity.class);
            startActivity(intent);
            finish();
        }else {
            setContentView(R.layout.activity_view_pager);
            mViewPager = (ViewPager) findViewById(R.id.vp_pager);
            mButton = (Button) findViewById(R.id.button);
            mButton.setOnClickListener(this);
            mList = new ArrayList<View>();
            for (int i = 0; i < pics.length; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(pics[i]);
                mList.add(imageView);
            }
            MyPagerAdapter adapter = new MyPagerAdapter(mList);
            mViewPager.setAdapter(adapter);
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    if (position == 2) {
                        mButton.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        }
    }

    @SuppressLint("NewApi")
	@Override
    public void onClick(View view) {
        SharedPreferences preferences=getSharedPreferences("is_first_open",MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("is_first_open",false);
        editor.apply();
        Intent intent=new Intent(ViewPagerActivity.this,LogoActivity.class);
        startActivity(intent);
        finish();
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @SuppressLint("NewApi")
		public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
