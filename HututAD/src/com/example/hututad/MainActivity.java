package com.example.hututad;

import java.util.Timer;
import java.util.TimerTask;

import android.Manifest;
import com.example.hututad.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	private Timer mTimer = new Timer();

	private CountdownCircleProgressBar mCircleProgressBar;

	public final static int OPEN_SCREEN_TIME = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// BuildPermission();
		setContentView(R.layout.activity_welcome);
		mCircleProgressBar = (CountdownCircleProgressBar) findViewById(R.id.skipBtn);
		mCircleProgressBar.setTimeMillis(OPEN_SCREEN_TIME);
		// mCircleProgressBar 播放动画
		mCircleProgressBar.play();
		// mCircleProgressBar 跳过按钮点击事件
		mCircleProgressBar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startMainActivity();
			}
		});
		// 使用计时器Task来等待5秒跳转
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				startMainActivity();
			}
		};
		mTimer.schedule(timerTask, OPEN_SCREEN_TIME);
	}

	private void startMainActivity() {
		startActivityForResult(new Intent(MainActivity.this, WelcomeActivity.class), 1);
		finish();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 注意：WelcomeActivity销毁时应该停止线程，防止内存泄漏！！！
		mCircleProgressBar.isRunning = false;
		// 注意：WelcomeActivity销毁时应该停止mTimer，防止内存泄漏！！！
		mTimer.cancel();
	}

	public void BuildPermission() {
		// Build.VERSION_CODES.M
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUR_DEVELOPMENT && ContextCompat.checkSelfPermission(this,
				Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
			// ContextCompat.checkSelfPermission
			ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_PHONE_STATE }, 123);
			// ActivityCompat.requestPermissions
		}
	}
}
