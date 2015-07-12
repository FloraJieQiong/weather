package com.jieqiong.coolweather.activity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.jieqiong.coolweather.MyBDLocationListener;
import com.jieqiong.coolweather.R;
import com.jieqiong.coolweather.domain.Weather;
import com.jieqiong.coolweather.util.Constants;
import com.jieqiong.coolweather.util.LogUtil;
import com.jieqiong.coolweather.util.MyApplication;
import com.jieqiong.coolweather.util.NetworkUtil;
import com.jieqiong.coolweather.util.WeatherUtil;

public class SplashActivity extends Activity {
	protected static final String TAG = "SplashActivity";
	protected static final int CHANGE_ACTIVITY = 0;
	protected static final int NET_UNAVAILABLE = 1;
	public LocationClient mLocationClient = null;
	public MyBDLocationListener myListener = new MyBDLocationListener();
	
	@SuppressLint("HandlerLeak")
	public Handler mHandler = new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case CHANGE_ACTIVITY:
				Intent intent = new Intent(SplashActivity.this, MainActivity.class);
				startActivity(intent);
				break;
			case NET_UNAVAILABLE:
				Toast.makeText(SplashActivity.this, "网络不可用，请检查网络连接",
						Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		MyApplication.getInstance().addActivity(this); 
		
		mLocationClient = new LocationClient( this );
		mLocationClient.registerLocationListener( myListener );
		setLocationOption();
		mLocationClient.start();
		
//		WeatherUtil.getWeatherFromSp(this);

	}
	@Override
	protected void onDestroy() {
		mLocationClient.stop();
		super.onDestroy();
	}
	private void setLocationOption() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true); //打开gps
		option.setAddrType("all");
		mLocationClient.setLocOption(option);
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		setCityValue();
//		setWeatherValue();
		
	}
	
	private void setCityValue() {
		new AsyncTask<Void, Void, Void>(){

			@Override
			protected Void doInBackground(Void... arg0) {
				while (Constants.currentCity == null) {
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				return null;
			}
			
			protected void onPostExecute(Void result) {
				setWeatherValue();
			};
		}.execute();
			
		
		
	}

	long startTime = 0;
	long endTime = 0;
	private void setWeatherValue() {
		LogUtil.d(TAG, "setValue1");
		new AsyncTask<Void, Integer, Weather>() {
			
			protected void onPreExecute() {
				startTime = System.currentTimeMillis();
			};
			
			@Override
			protected Weather doInBackground(Void... arg0) {
				LogUtil.d(TAG, "setValue2");
				Weather weather = null;

				if (!NetworkUtil.isNetworkAvailable(SplashActivity.this)) {
					Message msg = Message.obtain();
					msg.what = NET_UNAVAILABLE;
					mHandler.sendMessage(msg);
					return weather;
				}

				try {
					//TODO 定位  D1W9VOtmqmiGu4PCx0Ez0c6c
					weather = WeatherUtil.getWeather(URLEncoder.encode(Constants.currentCity,
							"utf-8"));
					LogUtil.d(TAG+"###", weather.toString());
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				;

				return weather;
			}

			protected void onProgressUpdate(Integer... values) {
				// TODO
			};

			protected void onPostExecute(Weather result) {
				LogUtil.d(TAG, "setValue3");
				Constants.setWeather(result);
				while (true) {
					endTime = System.currentTimeMillis();
					long deltaTime = 2000 - (endTime - startTime);
					
					if(Constants.dataReady){
						
						if(deltaTime <= 0){
							Message msg = Message.obtain();
							msg.what = CHANGE_ACTIVITY;
							mHandler.sendMessage(msg);
							break;
						}
						
						try {
							Thread.sleep(deltaTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						
						
					}else {
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					
				}
			};

		}.execute();
	}
	
	
}
