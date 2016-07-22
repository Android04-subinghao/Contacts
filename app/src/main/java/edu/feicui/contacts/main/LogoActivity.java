package edu.feicui.contacts.main;

import edu.feicui.contacts.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LogoActivity extends Activity{
/*
 * 欢迎界面
 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);
		//初始控件及动画
		ImageView img_logo=(ImageView) findViewById(R.id.iv_logo);
		Animation animation=AnimationUtils.loadAnimation(this, R.anim.anim_logo);
		AnimationListener animationListener=new AnimationListener() {
			//动画开始
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			//动画重复
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			//动画结束
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(LogoActivity.this, GridViewActivity.class);
				startActivity(intent);
				finish();
			}
		};
		animation.setAnimationListener(animationListener);
		img_logo.startAnimation(animation);
	}
}
