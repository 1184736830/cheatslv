package com.example.hututad;

import com.aliyun.da.render.AdClient;

public class App extends Application {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		System.out.println("********Application********");
		Log.i("Application", "-------------------------");
		// init 第二个参数为特定 channelID （必填）需额外找运营申请
		AdClient.getInstance().init(this.getApplicationContext(), "529X");
		// setDeviceInfo 第一个参数为定制的 vendor name （需要申请分配） , 第二个参数为自定义设备 ID （需
		// 要申请分配），第三个参数为设备类型， Android 手机就是 PHONE
		AdClient.getInstance().setDeviceInfo("qianhaiyilian", "qianhaiyilian_bid", "PHONE");
		// 开屏广告初始化代码，若不接入开屏广告可以跳过
		Map<String, String> adActivities = new HashMap<String, String>();
		// adActivities.put(LoadAdActivity.class.getSimpleName(), "#4b4b4b");
		// 将广告页面之前启动的 splash 页做类似设置（若有）
		// adActivities.put(SplashActivity.class.getSimpleName(),
		// AdClient.SPLASH_CODE);
		// setOpenInfo 第一个参数为开屏的 slotid （必填） 需找运营申请
		AdClient.getInstance().setOpenInfo("VM_WAWAJI", adActivities);
		registerActivityLifecycleCallbacks(AdClient.getInstance().lifecycleCallback);
	}

}
