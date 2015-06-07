package com.jieqiong.coolweather;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jieqiong.coolweather.domain.Weather;
import com.jieqiong.coolweather.domain.WeatherModel;
import com.jieqiong.coolweather.domain.WeatherModel1;
import com.jieqiong.coolweather.domain.WeatherModel2;
import com.jieqiong.coolweather.util.ClimateBitmapUtil;
import com.jieqiong.coolweather.util.NetworkUtil;
import com.jieqiong.coolweather.util.WeatherUtil;
import com.jieqiong.coolweather.util.inter.BitmapInter;
import com.jieqiong.coolweather.util.inter.WeatherInter;

public class MainActivity extends ActionBarActivity {

	protected static final int SET_VALUE = 1;
	protected static final int SET_IMAGE = 2;
	private TextView city;
	private TextView time;
	// private TextView humidity;
	private TextView pm_data;
	private ImageView weather_img;
	private TextView week_today;
	private TextView temperature;
	private TextView climate;
	private TextView wind;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case SET_VALUE:
				Weather weather = (Weather) msg.obj;

				ArrayList<WeatherModel> results = (ArrayList<WeatherModel>) weather.results;
				ArrayList<WeatherModel1> index = (ArrayList<WeatherModel1>) results
						.get(0).index;
				ArrayList<WeatherModel2> weather_data = (ArrayList<WeatherModel2>) results
						.get(0).weather_data;

				if (!weather.status.equals("success")) {
					Toast.makeText(MainActivity.this, "获取数据失败",
							Toast.LENGTH_SHORT).show();
				} else {
					
					Time t = new Time();
					t.setToNow();
					int mHour =t.hour;
					if (mHour >= 7 && mHour <= 19) {
						llLayout.setBackgroundResource(R.drawable.day_background);
						weather_img.setImageResource(ClimateBitmapUtil.getClimateBitmap(weather_data.get(0).weather,"day"));
						
					}else{
						llLayout.setBackgroundResource(R.drawable.night);
						weather_img.setImageResource(ClimateBitmapUtil.getClimateBitmap(weather_data.get(0).weather,"night"));
					}
					
					String city1 = results.get(0).currentCity;
					city.setText(city1);

					String[] date = weather.date.split("-");
					time.setText(date[1] + "/" + date[2]);

					pm_data.setText(results.get(0).pm25);

					
					String[] weather_data_date = weather_data.get(0).date.split(" ");
					week_today.setText(weather_data_date[0]);
					
					String[] temperatureStrings = weather_data_date[2].split("：");
					temperature.setText(temperatureStrings[1].replace(")", ""));
					climate.setText(weather_data.get(0).weather);
					wind.setText(weather_data.get(0).wind);
				}
			
				break;
			
			default:
				break;
			}
		};
	};

	private Handler mhandler = new Handler(){
		public void handleMessage(Message msg) {
		switch (msg.what) {
		case SET_IMAGE:
			Bitmap bitmap = (Bitmap) msg.obj;
			//weather_img.setImageBitmap(bitmap);
			break;

		default:
			break;
		}
		};
	};
	private LinearLayout llLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		init();
	}
	


	/**
	 * 
	 * @param url
	 */
	protected void loadImagefromUrl(final String url, final BitmapInter bitmapInter) {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					URL murl = new URL(url);
					bitmapInter.getBitmap(BitmapFactory.decodeStream(murl.openStream()));
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
		
	}

	private void init() {
		city = (TextView) findViewById(R.id.city);
		time = (TextView) findViewById(R.id.time);
		// humidity = (TextView) findViewById(R.id.humidity);
		pm_data = (TextView) findViewById(R.id.pm_data);
		weather_img = (ImageView) findViewById(R.id.weather_img);

		week_today = (TextView) findViewById(R.id.week_today);
		temperature = (TextView) findViewById(R.id.temperature);
		climate = (TextView) findViewById(R.id.climate);
		wind = (TextView) findViewById(R.id.wind);
		llLayout = (LinearLayout) findViewById(R.id.ll);
		setValue();
	}

	@Override
	protected void onResume() {
		super.onResume();
		//setValue();
	}

	private void setValue() {
		if (NetworkUtil.isNetworkAvailable(MainActivity.this)) {
			// TODO 定位，传入city
			try {
				WeatherUtil.getWeather(URLEncoder.encode("天津", "utf-8"),
						new WeatherInter() {

							@Override
							public void getWeather(Weather weather) {
								Message msg = Message.obtain();
								msg.what = SET_VALUE;
								msg.obj = weather;
								handler.sendMessage(msg);
							}
						});
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

		} else {
			Toast.makeText(MainActivity.this, "当前网络不可用，请检查网络连接",
					Toast.LENGTH_SHORT).show();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
