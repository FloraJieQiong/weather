package com.jieqiong.coolweather.domain;

import java.util.List;

public class Weather {
	
	public String date;
	  public int error;
	  public List<WeatherModel> results;
	  public String status;
	  
	  public String toString()
	  {
	    return "Weather [error=" + this.error + ", status=" + this.status + ", date=" + this.date + ", results=" + this.results + "]";
	  }
}
