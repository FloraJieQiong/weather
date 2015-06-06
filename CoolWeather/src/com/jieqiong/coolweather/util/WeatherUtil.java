package com.jieqiong.coolweather.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.jieqiong.coolweather.domain.Weather;

public class WeatherUtil {
	
	private static String TAG = "WeatherUtil";

	public static void getWeather(String city)
	  {
	    try
	    {
	    	city = URLEncoder.encode(city, "utf-8");
	      InputStream localInputStream = new URL("http://api.map.baidu.com/telematics/v3/weather?location="+city+"&output=json&ak=lZ4nfQsByGRcXFs3DOAn2bct").openConnection().getInputStream();
	      byte[] arrayOfByte = new byte[1024];
	      StringBuffer localStringBuffer = new StringBuffer();
	      for (;;)
	      {
	        int i = localInputStream.read(arrayOfByte);
	        if (i == -1)
	        {
	          LogUtil.i(TAG , localStringBuffer.toString());
	          Weather localWeather = (Weather)new Gson().fromJson(localStringBuffer.toString(), Weather.class);
	          LogUtil.i(TAG, localWeather.toString());
	          return;
	        }
	        localStringBuffer.append(new String(arrayOfByte, 0, i, "UTF-8"));
	      }
	     
	    }
	    catch (MalformedURLException localMalformedURLException)
	    {
	      localMalformedURLException.printStackTrace();
	      return;
	    }
	    catch (IOException localIOException)
	    {
	      localIOException.printStackTrace();
	    }
	  }
}
