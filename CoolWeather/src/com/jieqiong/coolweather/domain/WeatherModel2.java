package com.jieqiong.coolweather.domain;

public class WeatherModel2 {

	public String date;
	public String dayPictureUrl;
	public String nightPictureUrl;
	public String temperature;
	public String weather;
	public String wind;

	public String toString() {
		return "WeatherModel2 [date=" + this.date + ", dayPictureUrl="
				+ this.dayPictureUrl + ", nightPictureUrl="
				+ this.nightPictureUrl + ", weather=" + this.weather
				+ ", wind=" + this.wind + ", temperature=" + this.temperature
				+ "]";
	}
}
