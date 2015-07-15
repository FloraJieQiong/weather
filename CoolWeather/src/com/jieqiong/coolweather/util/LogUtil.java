package com.jieqiong.coolweather.util;

import android.util.Log;
/**
 * 
 * @author jieqiong
 *
 */
public class LogUtil {

	public static final int VERBOSE = 1;
	public static final int DEBUG = 2;
	public static final int INFO = 3;
	public static final int WARN = 4;
	public static final int ERROR = 5;
	public static final int NOTHING = 6;
	
	public static final int LEVEL = WARN;
	
	/**
	 * Send a {@link #VERBOSE} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void v(String tag, String msg){
		if(LEVEL <= VERBOSE){
			Log.v(tag, msg);
		}
	}
	
	/**
	 * Send a {@link #VERBOSE} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 * @param tr An exception to log
	 */
	public static void v(String tag, String msg, Throwable tr) {
		if(LEVEL <= VERBOSE){
			Log.v(tag, msg, tr);
		}
	}
	
	/**
	 * Send a {@link #DEBUG} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 */
	public static void d(String tag, String msg){
		if(LEVEL <= DEBUG){
			Log.d(tag, msg);
		}
	}
	
	/**
	 * Send a {@link #DEBUG} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 * @param tr An exception to log
	 */
	public static void d(String tag, String msg, Throwable tr) {
		if(LEVEL <= DEBUG){
			Log.d(tag, msg, tr);
		}
	}
	
	/**
	 * Send a {@link #INFO} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 */
	public static void i(String tag, String msg){
		if(LEVEL <= INFO){
			Log.i(tag, msg);
		}
	}
	
	/**
	 * Send a {@link #INFO} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 * @param tr An exception to log
	 */
	public static void i(String tag, String msg, Throwable tr) {
		if(LEVEL <= INFO){
			Log.i(tag, msg, tr);
		}
	}
	
	/**
	 * Send a {@link #WARN} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 */
	public static void w(String tag, String msg){
		if(LEVEL <= WARN){
			Log.w(tag, msg);
		}
	}
	
	/**
	 * Send a {@link #WARN} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 * @param tr An exception to log
	 */
	public static void w(String tag, String msg, Throwable tr) {
		if(LEVEL <= WARN){
			Log.w(tag, msg, tr);
		}
	}
	
	/**
	 * Send a {@link #ERROR} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 */
	public static void e(String tag, String msg){
		if(LEVEL <= ERROR){
			Log.e(tag, msg);
		}
	}
	
	/**
	 * Send a {@link #ERROR} log message.
	 * @param tag Used to identify the source of a log message.  It usually identifies
     *        the class or activity where the log call occurs.
	 * @param msg The message you would like logged
	 * @param tr An exception to log
	 */
	public static void e(String tag, String msg, Throwable tr) {
		if(LEVEL <= ERROR){
			Log.e(tag, msg, tr);
		}
	}
}
