package com.jieqiong.coolweather.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.BitmapFactory;

import com.jieqiong.coolweather.util.inter.BitmapInter;

public class BitmapFromURL {
	/**
	 * 
	 * @param url
	 */
	public static void loadImagefromUrl(final String url, final BitmapInter bitmapInter) {
		
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
}
