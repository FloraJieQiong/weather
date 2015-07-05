package com.jieqiong.coolweather.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jieqiong.coolweather.R;
import com.jieqiong.coolweather.domain.WeatherModel2;
import com.jieqiong.coolweather.util.Constants;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class GridViewAdapter extends BaseAdapter {

	protected static final int BITMAP = 0;
	private List<WeatherModel2> weather_data;
	private LayoutInflater inflater;
	private ViewHolder viewHolder;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case BITMAP:
				viewHolder.weather_img.setImageBitmap((Bitmap) msg.obj);
				break;

			default:
				break;
			}
		};
	};

	public GridViewAdapter(List<WeatherModel2> weather_data, Context context) {
		this.weather_data = weather_data;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		if (null != weather_data) {
			return weather_data.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int position) {
		return weather_data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {

			convertView = inflater.inflate(R.layout.grid_view_item, null);

			viewHolder = new ViewHolder();
			viewHolder.week = (TextView) convertView.findViewById(R.id.week);
			viewHolder.weather_img = (ImageView) convertView
					.findViewById(R.id.weather_img);
			viewHolder.temperature = (TextView) convertView
					.findViewById(R.id.temperature);
			viewHolder.climate = (TextView) convertView
					.findViewById(R.id.climate);
			viewHolder.wind = (TextView) convertView.findViewById(R.id.wind);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		// TODO
		String date = weather_data.get(position).date;
		if (position == 0) {
			date = "今天/" + date.substring(0, 3).trim();
			String[] arrayOfString3 = Constants.weather.date.split("-");
			viewHolder.week.setText(weather_data.get(position).date.substring(0, 2)+" "+arrayOfString3[1] + "/" + arrayOfString3[2]);
		}else{
			viewHolder.week.setText(weather_data.get(position).date);
		}
		
		

		this.viewHolder.weather_img
				.setTag(((WeatherModel2) Constants.weather_data.get(position)).dayPictureUrl);
		if ((this.viewHolder.weather_img.getTag() != null)
				&& (this.viewHolder.weather_img.getTag()
						.equals(((WeatherModel2) Constants.weather_data
								.get(position)).dayPictureUrl)))
			Constants.imageLoader
					.displayImage(((WeatherModel2) Constants.weather_data
							.get(position)).dayPictureUrl,
							this.viewHolder.weather_img, Constants.options,
							new SimpleImageLoadingListener() {
								public void onLoadingComplete(
										String paramAnonymousString,
										View paramAnonymousView,
										Bitmap paramAnonymousBitmap) {
								}

								public void onLoadingFailed(
										String paramAnonymousString,
										View paramAnonymousView,
										FailReason paramAnonymousFailReason) {
								}

								public void onLoadingStarted(
										String paramAnonymousString,
										View paramAnonymousView) {
								}
							}, new ImageLoadingProgressListener() {
								public void onProgressUpdate(
										String paramAnonymousString,
										View paramAnonymousView,
										int paramAnonymousInt1,
										int paramAnonymousInt2) {
								}
							});
		viewHolder.temperature.setText(weather_data.get(position).temperature);
		viewHolder.climate.setText(weather_data.get(position).weather);
		viewHolder.wind.setText(weather_data.get(position).wind);

		return convertView;
	}

}

class ViewHolder {
	public TextView week;
	public ImageView weather_img;
	public TextView temperature;
	public TextView climate;
	public TextView wind;
}
