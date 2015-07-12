package com.jieqiong.coolweather.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class MyApplication extends Application {
	private List<Activity> activityList = new LinkedList<Activity>();
	private static MyApplication instance;

	public static MyApplication getInstance() {
		return instance;
	}

	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	// 添加Activity到容器中
	public void addActivity(Activity activity) {
		activityList.add(activity);
	}

	// 遍历所有Activity并finish
	public void exit() {
		for (Activity activity : activityList) {
			activity.finish();
		}
		// System.exit(0);
	}
}
