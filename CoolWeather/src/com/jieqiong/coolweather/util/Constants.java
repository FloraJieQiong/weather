package com.jieqiong.coolweather.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.graphics.Bitmap;

import com.jieqiong.coolweather.domain.Weather;
import com.jieqiong.coolweather.domain.WeatherModel;
import com.jieqiong.coolweather.domain.WeatherModel1;
import com.jieqiong.coolweather.domain.WeatherModel2;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class Constants {
	public static final int NET_ERROR = 3;
	  public static final int SET_IMAGE = 1;
	  public static final int UPDATE_UI = 2;
	  public static boolean dataReady = false;
	  public static String[] date;
	  public static ImageLoader imageLoader = ImageLoader.getInstance();
	  public static int[] imgLow;
	  public static int[] imgTop;
	  public static ArrayList<WeatherModel1> index;
	  public static List<Integer> lowPic;
	  public static List<Float> maxlist;
	  public static List<Float> minlist;
	  private static String name = "weather";
	  public static DisplayImageOptions options;
	  public static ArrayList<WeatherModel> results;
	  public static List<String> timeDate;
	  public static List<String> timeWeek;
	  public static List<Integer> topPic;
	  public static Weather weather = null;
	  public static ArrayList<WeatherModel2> weather_data;

	  static
	  {
	    options = new DisplayImageOptions.Builder().showImageOnLoading(2130837554).showImageForEmptyUri(2130837545).showImageOnFail(2130837546).cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565).build();
	    int[] arrayOfInt1 = new int[4];
	    arrayOfInt1[0] = 2;
	    arrayOfInt1[1] = 1;
	    arrayOfInt1[2] = 7;
	    arrayOfInt1[3] = 7;
	    imgTop = arrayOfInt1;
	    int[] arrayOfInt2 = new int[4];
	    arrayOfInt2[0] = 1;
	    arrayOfInt2[1] = 2;
	    arrayOfInt2[2] = 2;
	    arrayOfInt2[3] = 7;
	    imgLow = arrayOfInt2;
	    String[] arrayOfString = new String[4];
	    arrayOfString[0] = "4/1";
	    arrayOfString[1] = "4/2";
	    arrayOfString[2] = "4/3";
	    arrayOfString[3] = "4/4";
	    date = arrayOfString;
	  }

	  public static Weather getWeather()
	  {
	    return weather;
	  }

	  private static void setDate()
	  {
	    timeDate.clear();
	    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("MM/dd");
	    Calendar localCalendar = Calendar.getInstance();
	    timeDate.add(localSimpleDateFormat.format(localCalendar.getTime()));
	    for (int i = 1; ; i++)
	    {
	      if (i >= weather_data.size())
	        return;
	      localCalendar.add(5, 1);
	      String str = localSimpleDateFormat.format(localCalendar.getTime());
	      timeDate.add(str);
	    }
	  }

	  public static void setWeather(Weather paramWeather)
	  {
	    dataReady = false;
	    if (paramWeather == null)
	      return;
	    weather = paramWeather;
	    results = (ArrayList<WeatherModel>)paramWeather.results;
	    index = (ArrayList<WeatherModel1>)((WeatherModel)results.get(0)).index;
	    weather_data = (ArrayList<WeatherModel2>)(results.get(0)).weather_data;
	    topPic = new ArrayList<Integer>();
	    maxlist = new ArrayList<Float>();
	    minlist = new ArrayList<Float>();
	    topPic = new ArrayList<Integer>();
	    lowPic = new ArrayList<Integer>();
	    timeWeek = new ArrayList<String>();
	    timeDate = new ArrayList<String>();
	    int i = 0;
	    String[] arrayOfString;
	    while (i < weather_data.size())
	    {
	      arrayOfString = ((WeatherModel2)weather_data.get(i)).temperature.split("~");
	      if (arrayOfString.length == 2){
	    	  maxlist.add(Float.valueOf(arrayOfString[0].substring(0, 2)));
		      minlist.add(Float.valueOf(arrayOfString[1].substring(0, 2)));
	      }
	      
	      topPic.add(Integer.valueOf(imgTop[i]));
	      lowPic.add(Integer.valueOf(imgLow[i]));
	      timeWeek.add(((WeatherModel2)weather_data.get(i)).date);
	      setDate();
	      i++;
	      minlist.add(Float.valueOf(arrayOfString[0].substring(0, 2)));
	    }
	  }
}
