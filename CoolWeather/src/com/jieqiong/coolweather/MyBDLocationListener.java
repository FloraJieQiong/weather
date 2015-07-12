package com.jieqiong.coolweather;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.jieqiong.coolweather.util.Constants;

public class MyBDLocationListener implements BDLocationListener {

	@Override
	public void onReceiveLocation(BDLocation location) {
		Constants.currentCity = location.getCity();
	}

}
