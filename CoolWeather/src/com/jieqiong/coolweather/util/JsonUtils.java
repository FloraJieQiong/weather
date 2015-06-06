package com.jieqiong.coolweather.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JsonUtils {
	
	private static Gson gson = new Gson();
	  
	  public static <T> T jsonToObject(String paramString, TypeToken<T> paramTypeToken)
	    throws JsonSyntaxException
	  {
	    Type localType = paramTypeToken.getType();
	    try
	    {
	      T localObject = gson.fromJson(paramString, localType);
	      return localObject;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return null;
	  }
	  
	  public static String objectToJson(Object paramObject)
	  {
	    try
	    {
	      String str = gson.toJson(paramObject);
	      return str;
	    }
	    catch (Exception localException)
	    {
	      localException.printStackTrace();
	    }
	    return "";
	  }
	  
	  public static <T> T parseObjectFromInputStreamByGson(InputStream paramInputStream, Type paramType)
	    throws IllegalStateException, IOException
	  {
	    JsonReader localJsonReader = new JsonReader(new InputStreamReader(paramInputStream));
	    return gson.fromJson(localJsonReader, paramType);
	  }
}
