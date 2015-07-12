package com.jieqiong.coolweather.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.Time;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jieqiong.coolweather.R;
import com.jieqiong.coolweather.adapter.GridViewAdapter;
import com.jieqiong.coolweather.adapter.ListViewAdapter;
import com.jieqiong.coolweather.domain.WeatherModel;
import com.jieqiong.coolweather.domain.WeatherModel2;
import com.jieqiong.coolweather.util.Constants;
import com.jieqiong.coolweather.util.MyApplication;
import com.jieqiong.coolweather.widget.Chart;
import com.jieqiong.coolweather.widget.MyGridView;
import com.jieqiong.coolweather.widget.MyListView;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	static int width;
	private TextView city;
	private TextView climate;
	private MyGridView grid_view;
	int height;
	private LinearLayout llLayout;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			/*
			 * default: case 1: case 2: case 3: } while (true) {
			 * MainActivity.this.weather_img.setImageBitmap((Bitmap) msg.obj);
			 * MainActivity.this.updateUi(); Toast.makeText(MainActivity.this,
			 * "当前网络不可用，请检查网络连接", 0).show(); }
			 */
			}
		}
	};
	private ProgressBar pb_update;
	private TextView pm_data;
	private TextView temperature;
	private TextView time;
	private ImageView today_weather_img;
	private TextView week_today;
	private TextView wind;
	private MyListView list_view;
	private Chart chart;

	private void init() {
		this.city = ((TextView) findViewById(R.id.city));
		this.time = ((TextView) findViewById(R.id.time));
		this.pm_data = ((TextView) findViewById(R.id.pm_data));
		this.today_weather_img = ((ImageView) findViewById(R.id.today_weather_img));
		this.week_today = ((TextView) findViewById(R.id.week_today));
		this.temperature = ((TextView) findViewById(R.id.temperature));
		this.climate = ((TextView) findViewById(R.id.climate));
		this.wind = ((TextView) findViewById(R.id.wind));
		this.llLayout = ((LinearLayout) findViewById(R.id.ll));
		this.list_view = (MyListView) findViewById(R.id.list_view);
		chart = (Chart) findViewById(R.id.chart);

		this.pb_update = ((ProgressBar) findViewById(R.id.pb_update));

		Constants.imageLoader
				.init(ImageLoaderConfiguration.createDefault(this));
		this.pb_update.setOnClickListener(new View.OnClickListener() {
			public void onClick(View paramAnonymousView) {
			}
		});
	}

	private void updateUi() {
		if ((Constants.weather != null)
				&& (!Constants.weather.status.equals("success"))) {
			Toast.makeText(this, "获取数据失败", Toast.LENGTH_SHORT).show();
			return;
		}
		Time localTime = new Time();
		localTime.setToNow();
		int i = localTime.hour;
		if ((i >= 7) && (i <= 19)) {
			this.llLayout.setBackgroundResource(R.drawable.day);
			if (Constants.weather_data != null)
				Constants.imageLoader
						.displayImage(
								((WeatherModel2) Constants.weather_data.get(0)).dayPictureUrl,
								today_weather_img, Constants.options);
		} else {
			this.llLayout.setBackgroundResource(R.drawable.night);
			if (Constants.weather_data != null)
				Constants.imageLoader
						.displayImage(
								((WeatherModel2) Constants.weather_data.get(0)).nightPictureUrl,
								today_weather_img, Constants.options);
		}

		if (Constants.results != null) {
			String str = ((WeatherModel) Constants.results.get(0)).currentCity;
			this.city.setText(str);
		}
		if (Constants.weather != null) {
			String[] arrayOfString3 = Constants.weather.date.split("-");
			this.time.setText(arrayOfString3[1] + "/" + arrayOfString3[2]);
		}
		if (Constants.results != null)
			this.pm_data
					.setText(((WeatherModel) Constants.results.get(0)).pm25);
		if (Constants.weather_data == null)
			return;
		String[] arrayOfString1 = ((WeatherModel2) Constants.weather_data
				.get(0)).date.split(" ");
		this.week_today.setText(arrayOfString1[0]);
		String[] arrayOfString2 = arrayOfString1[2].split("：");
		this.temperature.setText(arrayOfString2[1].replace(")", ""));
		this.climate
				.setText(((WeatherModel2) Constants.weather_data.get(0)).weather);
		this.wind.setText(((WeatherModel2) Constants.weather_data.get(0)).wind);
		this.grid_view = ((MyGridView) findViewById(R.id.grid_view));
		this.grid_view.setHaveScrollbar(false);
		GridViewAdapter localGridViewAdapter = new GridViewAdapter(
				Constants.weather_data, this);
		this.grid_view.setAdapter(localGridViewAdapter);

		ListViewAdapter listViewAdapter = new ListViewAdapter(Constants.index,
				this);
		
		list_view.setAdapter(listViewAdapter);
		list_view.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView tv_life_des = (TextView) view
						.findViewById(R.id.tv_life_des);
				switch (tv_life_des.getVisibility()) {
				case View.GONE:
					tv_life_des.setVisibility(View.VISIBLE);
					break;
				case View.VISIBLE:
					tv_life_des.setVisibility(View.GONE);

				default:
					break;
				}

			}
		});

	}

	private void setChartData() {
		Chart.width = getWindowManager().getDefaultDisplay().getWidth();
		chart.setTime(Constants.timeWeek, Constants.timeDate);
		chart.setBitmap(Constants.topBitmaps, Constants.lowBitmaps);
		chart.setTemperature(Constants.maxlist, Constants.minlist);
	}

	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		requestWindowFeature(1);
		setContentView(R.layout.activity_main);
		MyApplication.getInstance().addActivity(this);
		// WeatherUtil.getWeatherFromSp(this);
		init();
		// setValue();
		// TODO
		setChartData();

	}

	protected void onResume() {
		super.onResume();
//		setChartData();
		updateUi();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// 后退键，退出apk
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			MyApplication.getInstance().exit();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}