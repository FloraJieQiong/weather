package com.jieqiong.coolweather.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.jieqiong.coolweather.domain.Weather;
import com.jieqiong.coolweather.util.inter.WeatherInter;

public class WeatherUtil {
	
	private static String TAG = "WeatherUtil";

	public static void getWeather(final String city, final WeatherInter weatherInter)
	  {
		new Thread(new Runnable() {
			
			@Override
			public void run() {

				Weather weather = null;
			    try
			    {
			    
			      InputStream localInputStream = new URL("http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=lZ4nfQsByGRcXFs3DOAn2bct").openConnection().getInputStream();
			      byte[] arrayOfByte = new byte[1024];
			      StringBuffer localStringBuffer = new StringBuffer();
			      for (;;)
			      {
			        int i = localInputStream.read(arrayOfByte);
			        if (i == -1)
			        {
			          LogUtil.i(TAG , localStringBuffer.toString());
			          weather = (Weather)new Gson().fromJson(localStringBuffer.toString(), Weather.class);
			          LogUtil.i(TAG, weather.toString());
			          weatherInter.getWeather(weather);
			        }else{
			        	localStringBuffer.append(new String(arrayOfByte, 0, i, "UTF-8"));
			        }
			        
			      }
			     
			    }
			    catch (MalformedURLException localMalformedURLException)
			    {
			      localMalformedURLException.printStackTrace();
			    }
			    catch (IOException localIOException)
			    {
			      localIOException.printStackTrace();
			    }
			}
		}).start();
	  }
}
