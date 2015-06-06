package com.jieqiong.coolweather.domain;

import java.util.List;

public class WeatherModel {
	
	public String currentCity;
	  public List<WeatherModel1> index;
	  public String pm25;
	  public List<WeatherModel2> weather_data;
	  
	  public String toString()
	  {
	    return "WeatherModel11 [currentCity=" + this.currentCity + ", pm25=" + this.pm25 + ", index=" + this.index + ", weather_data=" + this.weather_data + "]";
	  }
}
