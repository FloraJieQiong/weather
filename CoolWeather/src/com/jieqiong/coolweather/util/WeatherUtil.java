package com.jieqiong.coolweather.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jieqiong.coolweather.domain.Weather;

public class WeatherUtil {

	private static String TAG = "WeatherUtil";
	private static SharedPreferences sp;

	public static Weather getWeather(final String city) {
		Weather localWeather = null;
		try {
			HttpURLConnection localHttpURLConnection = (HttpURLConnection) new URL(
					"http://api.map.baidu.com/telematics/v3/weather?location="
							+ city
							+ "&output=json&ak=lZ4nfQsByGRcXFs3DOAn2bct")
					.openConnection();
			localHttpURLConnection.setConnectTimeout(5000);
			localHttpURLConnection.setRequestMethod("GET");
			if (localHttpURLConnection.getResponseCode() == 200) {
				String str = new String(StreamTool.read(localHttpURLConnection
						.getInputStream()));
				LogUtil.i(TAG, str);
				Gson localGson = new Gson();
				localWeather = (Weather) localGson.fromJson(str, Weather.class);
				LogUtil.i(TAG, localWeather.toString());
//				Constants.setWeather(localWeather);
				if (sp == null)
					sp = MyApplication.getInstance().getSharedPreferences(
							"weather", 0);
				SharedPreferences.Editor localEditor = sp.edit();
				localEditor
						.putString("weather", localGson.toJson(localWeather));
				localEditor.commit();
//				Constants.dataReady = true;
				LogUtil.i(TAG, "getWeather2");
			}
//			return localWeather;
		} catch (MalformedURLException localMalformedURLException) {
				localMalformedURLException.printStackTrace();
		} catch (IOException localIOException) {
				localIOException.printStackTrace();
		} catch (JsonSyntaxException localJsonSyntaxException) {
				getWeatherFromSp(MyApplication.getInstance());
		} catch (Exception localException) {
				localException.printStackTrace();
		}
		return localWeather;
	}

	public static void getWeatherFromSp(Context paramContext) {
		sp = paramContext.getSharedPreferences("weather", 0);
	    if (sp != null)
	      Constants.setWeather((Weather)new Gson().fromJson(sp.getString("weather", ""), Weather.class));
	}
}
