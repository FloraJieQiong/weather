package com.jieqiong.coolweather.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {
	public static byte[] read(InputStream paramInputStream) throws Exception {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayOfByte = new byte[1024];
		while (true) {
			int i = paramInputStream.read(arrayOfByte);
			if (i == -1) {
				paramInputStream.close();
				return localByteArrayOutputStream.toByteArray();
			}
			localByteArrayOutputStream.write(arrayOfByte, 0, i);
		}
	}
}
