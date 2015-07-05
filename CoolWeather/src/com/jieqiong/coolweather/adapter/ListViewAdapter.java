package com.jieqiong.coolweather.adapter;

import java.util.ArrayList;

import android.R.integer;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jieqiong.coolweather.R;
import com.jieqiong.coolweather.domain.WeatherModel1;
import com.jieqiong.coolweather.util.Constants;

public class ListViewAdapter extends BaseAdapter {

	private ArrayList<WeatherModel1> index;
	private LayoutInflater inflater;
	private ViewHolder viewHolder;
	
	public ListViewAdapter(ArrayList<WeatherModel1> paramArrayList, Context paramContext)
	  {
	    this.index = paramArrayList;
	    this.inflater = LayoutInflater.from(paramContext);
	  }
	
	@Override
	public int getCount() {
		if(index != null){
			return index.size();
		}else {
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		if(null != index){
			index.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder mHolder = null;
		
		if(null == convertView){
			convertView = inflater.inflate(R.layout.listview_life_item, null);
			
			mHolder = new ViewHolder();
			mHolder.iv_life_icon = (ImageView) convertView.findViewById(R.id.iv_life_icon);
			mHolder.tv_life_title = (TextView) convertView.findViewById(R.id.tv_life_title);
			mHolder.tv_life_des = (TextView) convertView.findViewById(R.id.tv_life_des);
			mHolder.tv_life_zs = (TextView) convertView.findViewById(R.id.tv_life_zs);
			mHolder.iv_life_arrow = (ImageView) convertView.findViewById(R.id.iv_life_arrow);
			mHolder.list_item_ll = (LinearLayout) convertView.findViewById(R.id.list_item_ll);
			
			convertView.setTag(mHolder);
		}else {
			mHolder = (ViewHolder) convertView.getTag();
		}
		
		switch (position) {
		case 0:
			mHolder.iv_life_icon.setImageResource(R.drawable.ic_life_info_chuanyi);
			break;
		case 1:
			mHolder.iv_life_icon.setImageResource(R.drawable.ic_life_info_xiche);
			break;
		case 2:
			mHolder.iv_life_icon.setImageResource(R.drawable.ic_life_info_item_lvyou);
			break;
		case 3:
			mHolder.iv_life_icon.setImageResource(R.drawable.ic_life_info_ganmao);
			break;
		case 4:
			mHolder.iv_life_icon.setImageResource(R.drawable.ic_life_info_yundong);
			break;
		case 5:
			mHolder.iv_life_icon.setImageResource(R.drawable.ic_life_info_ziwaixian);
			break;
		default:
			break;
		}
		
		mHolder.tv_life_title.setText(Constants.index.get(position).title);
		mHolder.tv_life_zs.setText(Constants.index.get(position).zs);
		mHolder.tv_life_des.setText(Constants.index.get(position).des);
		
		
		
		return convertView;
	}
	
	class ViewHolder{
		public ImageView iv_life_icon;
		public TextView tv_life_title;
		public TextView tv_life_zs;
		public TextView tv_life_des;
		public ImageView iv_life_arrow;
		public LinearLayout list_item_ll;
	}

}
