package com.jieqiong.coolweather.util;

import android.app.Application;

public class MyApplication extends Application{
	  private static MyApplication instance;

	  public static MyApplication getInstance()
	  {
	    return instance;
	  }

	  public void onCreate()
	  {
	    super.onCreate();
	    instance = this;
	  }
}
