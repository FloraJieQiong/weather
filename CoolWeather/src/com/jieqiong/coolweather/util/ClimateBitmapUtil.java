package com.jieqiong.coolweather.util;

import android.media.audiofx.Equalizer;

import com.jieqiong.coolweather.R;

public class ClimateBitmapUtil {
	
	public static int getClimateBitmap(String weather, String dayOrNight) {
		
		if(weather.equals("晴")){
			switch (dayOrNight) {
			case "night":
				return R.drawable.sunny_night;
			default:
				return R.drawable.qing;
			}
		}else if (weather.equals("多云")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.duoyun_night;
			default:
				return R.drawable.duoyun;
			}
		}else if (weather.equals("阴")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.yin_night;

			default:
				return R.drawable.yin;
			}
		}else if ("阵雨".equals(weather)) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.zhenyu_night;
			default:
				return R.drawable.zhenyu;
			}
		}else if ("雷阵雨".equals(weather)) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.leizhenyu_night;
			default:
				return R.drawable.leizhenyu;
			}
		}else if (weather.contains("雷阵雨") && weather.contains("冰雹")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.leizhenyu_bingbao_night;
			default:
				return R.drawable.leizhenyu_bingbao;
			}
		}else if ("雨夹雪".equals(weather)) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.yujiaxue_night;
			default:
				return R.drawable.yujiaxue;
			}
		}else if (weather.contains("特大暴雨")) {
			/*----------雨 start 顺序不能变-------------*/
			switch (dayOrNight) {
			case "night":
				return R.drawable.tedabaoyu_night;
			default:
				return R.drawable.tedabaoyu;
			}
		}else if (weather.contains("大暴雨")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.dabaoyu_night;
			default:
				return R.drawable.baoyu_dabaoyu;
			}
		}else if (weather.contains("暴雨")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.baoyu_night;
			default:
				return R.drawable.baoyu;
			}
		}else if (weather.contains("大雨")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.dayu_night;
			default:
				return R.drawable.dayu;
			}
		}else if (weather.contains("中雨")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.zhongyu;
			default:
				return R.drawable.zhongyu_night;
			}
		}else if (weather.contains("雨")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.xiaoyu;
			default:
				return R.drawable.xiaoyu_night;
				/*----------雨 end 顺序不能变-------------*/
			}
		}else if ("阵雪".equals(weather)) {
			
			switch (dayOrNight) {
			case "night":
				return R.drawable.zhenxue_night;
			default:
				return R.drawable.zhenxue;
			}
		}else if (weather.contains("暴雪")) {
			/*----------雪 start 顺序不能变-------------*/
			switch (dayOrNight) {
			case "night":
				return R.drawable.baoxue_night;
			default:
				return R.drawable.baoxue;
			}
		}else if (weather.contains("大雪")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.daxue_night;
			default:
				return R.drawable.daxue;
			}
		}else if (weather.contains("中雪")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.zhongxue_night;
			default:
				return R.drawable.zhongxue;
			}
		}else if (weather.contains("雪")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.xiaoxue_night;
			default:
				return R.drawable.xiaoxue;
			}
			/*----------雪 end 顺序不能变-------------*/
		}else if ("雾".equals(weather)) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.wu_night;
			default:
				return R.drawable.wu;
			}
		}else if ("冻雨".equals(weather)) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.dongyu_night;
			default:
				return R.drawable.dongyu;
			}
		}else if (weather.contains("沙尘")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.shachenbao_night;
			default:
				return R.drawable.shachenbao;
			}
		}else if (weather.contains("扬沙")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.yangsha_night;
			default:
				return R.drawable.yangsha;
			}
		}else if (weather.contains("浮尘")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.fuchen_night;
			default:
				return R.drawable.fuchen;
			}
		}else if (weather.contains("霾")) {
			switch (dayOrNight) {
			case "night":
				return R.drawable.mai_night;
			default:
				return R.drawable.mai;
			}
		}else {
			return R.drawable.cloudy1;
		}
		
	}
}
