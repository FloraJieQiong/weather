package com.jieqiong.coolweather.widget;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jieqiong.coolweather.util.LogUtil;

public class Chart extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = "Chart";
	static int gapX;
	  static int right;
	  private static float[] tempHeigh = { 31.0F, 33.0F, 34.0F, 33.0F};//13.0F, 8.0F, 20.0F, 15.0F, 10.0F, 12.0F   31.0, 33.0, 34.0, 33.0
	  private static float[] tempLow = { 22.0F, 22.0F, 22.0F, 23.0F };//6.0F, 3.0F, 10.0F, 5.0F, 2.0F, 6.0F  22.0, 22.0, 22.0, 23.0
	  public static int width;
	  private Context c;
	  private int currentX;
	  private boolean isRunning = true;
	  private Bitmap[] lowBmps;
	  private Paint mLinePaint1;
	  //private Paint mLinePaint2;
	  private Paint mPointPaint;
	  private Paint mTextPaint;
	  private Paint mTimePaint;
	 // private Paint mTitlePaint;
	  private int oldX;
	  private float radius = 8.0F;
	  private SurfaceHolder sfh;
	  private List<String> timeDate;
	  private List<String> timeWeak;
	  private Bitmap[] topBmps;
	  private int[] x = new int[4];

	// public static Canvas localCanvas = sfh.lockCanvas(new Rect(oldX, 0, 180 +
	// oldX, 180));

	public Chart(Context paramContext) {
		super(paramContext);
		this.c = paramContext;
	}

	public Chart(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
		this.c = paramContext;
		setZOrderOnTop(true);
		this.sfh = getHolder();
		this.sfh.addCallback(this);
		this.sfh.setFormat(PixelFormat.TRANSLUCENT);// 设置半透明
		init();
	}

	  private void clearCanvas()
	  {
	    Canvas localCanvas = this.sfh.lockCanvas();
	    localCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
	    this.sfh.unlockCanvasAndPost(localCanvas);
	  }
	  
	  float lowestY = 0.0F;
	  int lowestIndex = 0;
	  private void drawHighTemp(float deltaY, int bottom, Canvas paramCanvas, Paint paramPaint1, Paint paramPaint2, float paramFloat2, float tempHeighMin)
	  {//deltaY, 180, 23, localCanvas, localPaint1, localPaint2, 140.0F / (getMax(tempHeigh) - tempHeighMin), tempHeighMin
	    setWidth(width);
	    float f1 = 0.0F;
	    int i = 0;
	    
	    for (int j = 0;j < this.timeWeak.size() ; j++)
	    {
	        float f2 = bottom + paramFloat2 * (tempHeigh[j] - tempHeighMin);
	        if (f1 > f2){
	          f1 = f2;
	        }
	        if(lowestY < f2){
	        	lowestY = f2;
	        	lowestIndex = j;
	        }
	        LogUtil.v(TAG, "lowestY = "+lowestY+"lowestIndex = " +lowestIndex);
	        paramCanvas.drawCircle(this.x[i], f2, this.radius, paramPaint1);
	        
	        if (i != -1 + tempHeigh.length)
	        {
	          float f3 = bottom + paramFloat2 * (tempHeigh[(i + 1)] - tempHeighMin);
	          paramCanvas.drawLine(this.x[i], f2, this.x[(i + 1)], f3, paramPaint2);
	        }
	        LogUtil.v("WeatherChart", "drawHighTemp f2 - deltaY / 2.0F = "+(f2 - deltaY / 2.0F)+" , i = "+i+" x[i] = "+this.x[i]);
	        paramCanvas.drawText(tempHeigh[i] + "°", this.x[i], f2 - deltaY / 2.0F, this.mTextPaint);
	        paramCanvas.drawBitmap(this.topBmps[i], this.x[i] - this.topBmps[i].getWidth() / 2, f2 - deltaY - this.topBmps[i].getWidth(), null);
	        i++;
	        
	      
	      paramCanvas.drawText((String)this.timeWeak.get(j)/*+" "+timeDate.get(j)*/, this.x[j], f1 + deltaY / 2, this.mTimePaint);
	    }
	  }

	  private void drawLowTemp(float deltaY, Canvas paramCanvas, Paint paramPaint1, Paint paramPaint2, float spacePX,/* float tempMin,*/int bottom)
	  {//deltaY, localCanvas, localPaint1, localPaint2, 140.0F / (getMax(tempHeigh) - tempHeighMin
		  LogUtil.v("WeatherChart","drawLowTemp() deltaY = "+deltaY+" , spacePX = "+spacePX+" , bottom = "+bottom);
		  //deltaY = 34.09424 , paramFloat2 = -11.666667 , paramFloat3 = 20.0 , paramInt1 = 180
	    setWidth(width);
	    
	    float baseLine = lowestY + 80;
	    float cy = 0.0F;
	    float dy = 0.0F;
	    float highestY = 0.0F;
	    
	    
	    for(int j = 0; j < tempLow.length; j++){
	    	if(j != lowestIndex){
	    		cy = baseLine + (tempLow[j] - tempLow[lowestIndex]) * spacePX;
	    	}else {
				cy = baseLine;
			}
	    	
	    	if(highestY < cy){
	    		highestY = cy;
	    	}
	    	
	    	paramCanvas.drawCircle(x[j], cy, radius, mPointPaint);
	    	if(j != tempLow.length - 1){
	    		if(j == lowestIndex - 1){
	    			dy = baseLine;
	    		}
	    		dy = baseLine + (tempLow[j + 1] - tempLow[lowestIndex]) * spacePX;
	    		LogUtil.v(TAG, "dy = "+ dy + " j = "+j);
	    		paramCanvas.drawLine(x[j], cy, x[j + 1], dy, mLinePaint1);
	    	}
	    	LogUtil.v(TAG, "drawLowTemp() cy + deltaY = "+(cy + deltaY)+" , j = "+j+" , x[j] = "+x[j]); 
	    	paramCanvas.drawText(tempLow[j]+"°", x[j], cy + deltaY, mTextPaint);
	    	paramCanvas.drawBitmap(lowBmps[j], x[j] - lowBmps[j].getWidth() /2, cy + deltaY *3 /2,null);
	    }
	    
	    for(int i = 0; i < timeDate.size(); i++){
	    	paramCanvas.drawText(timeDate.get(i), x[i], highestY + deltaY * 5 / 2 + lowBmps[i].getHeight(), mTimePaint);
	    }
	    
//	    float highestY = 0.0F;
//	    float f2 = 0.0F;
//	    bottom = 195;
	    /*f2 = bottom+ spacePX * (tempLow[lowestIndex] - tempMin);
	    float delta = f2 - lowestY - 70;
	    for (int j = 0; j < this.timeDate.size(); j++)
	    {
	    	//int paramInt1 = 180;
	        f2 = bottom+ spacePX * (tempLow[j] - tempMin);
	        LogUtil.v("WeatherChart", "f2 = "+f2);
	        if (highestY < f2){
	        	highestY = f2;
	        }
	        f2 = f2 - delta;
	        paramCanvas.drawCircle(this.x[j], f2, this.radius, paramPaint1);
	        if (j != -1 + tempLow.length)
	        {
	          float f3 = bottom + spacePX * (tempLow[(j + 1)] - tempMin) - delta;
	          LogUtil.v("WeatherChart", "f3 = "+f3);
	          paramCanvas.drawLine(this.x[j], f2, this.x[(j + 1)], f3, paramPaint2);
	        }
	        LogUtil.v("WeatherChart", "drawLowTemp f2 + deltaY = "+(f2 + deltaY)+" , i = "+j+" x[i] = "+this.x[j]);
	        
	        paramCanvas.drawText(tempLow[j] + "°", this.x[j], f2 + deltaY, this.mTextPaint);
	        paramCanvas.drawBitmap(this.lowBmps[j], this.x[j] - this.lowBmps[j].getWidth() / 2, f2 + deltaY + this.lowBmps[j].getWidth() / 2, null);
	      
//	      paramCanvas.drawText((String)this.timeDate.get(j), this.x[j], highestY +  mTextPaint.getTextSize() *2+ this.lowBmps[j].getHeight() - delta, this.mTimePaint);
	    }*/
	  }

	private float getMax(float[] paramArrayOfFloat) {
		float f = paramArrayOfFloat[0];
		for (int i = 1;; i++) {
			if (i >= paramArrayOfFloat.length)
				return f;
			if (f < paramArrayOfFloat[i])
				f = paramArrayOfFloat[i];
		}
	}

	private float getMin(float[] paramArrayOfFloat) {
		float f = paramArrayOfFloat[0];
		for (int i = 1; i < paramArrayOfFloat.length; i++) {

			if (f > paramArrayOfFloat[i])
				f = paramArrayOfFloat[i];
		}
		return f;
	}

	private void init() {
		this.topBmps = new Bitmap[4];
		this.lowBmps = new Bitmap[4];

		/*
		 * Paint localPaint = new Paint(); localPaint.setColor(Color.WHITE);
		 * localPaint.setAntiAlias(true); localPaint.setStrokeWidth(2.0F);
		 * localPaint.setStyle(Paint.Style.FILL);
		 */

		this.timeWeak = new ArrayList<String>();
		this.timeDate = new ArrayList<String>();

		this.mPointPaint = new Paint();
		this.mPointPaint.setAntiAlias(true);
		this.mPointPaint.setColor(Color.WHITE);

		this.mLinePaint1 = new Paint();
		this.mLinePaint1.setColor(Color.YELLOW);
		this.mLinePaint1.setAntiAlias(true);
		this.mLinePaint1.setStrokeWidth(4.0F);
		this.mLinePaint1.setStyle(Paint.Style.FILL);

		/*
		 * this.mLinePaint2 = new Paint(); this.mLinePaint2.setColor(-16776961);
		 * this.mLinePaint2.setAntiAlias(true);
		 * this.mLinePaint2.setStrokeWidth(4.0F);
		 * this.mLinePaint2.setStyle(Paint.Style.FILL);
		 */

		this.mTextPaint = new Paint();
		this.mTextPaint.setAntiAlias(true);
		this.mTextPaint.setColor(Color.WHITE);
		this.mTextPaint.setTextSize(25.0F);
		this.mTextPaint.setTextAlign(Paint.Align.CENTER);

		/*
		 * this.mTitlePaint = new Paint(); this.mTitlePaint.setAntiAlias(true);
		 * this.mTitlePaint.setColor(-1); this.mTitlePaint.setTextSize(30.0F);
		 * this.mTitlePaint.setTextAlign(Paint.Align.CENTER);
		 */

		this.mTimePaint = new Paint();
		this.mTimePaint.setAntiAlias(true);
		this.mTimePaint.setColor(Color.WHITE);
		this.mTimePaint.setTextSize(20.0F);
		this.mTimePaint.setTextAlign(Paint.Align.CENTER);
	}

	protected void drawChart() {
		while (true) {
			if (!this.isRunning)
				return;
			try {
				drawChart(this.currentX);
			} catch (Exception localException) {
				localException.printStackTrace();
			}
		}
	}

	  void drawChart(int paramInt)
	  {
	    Paint.FontMetrics localFontMetrics = this.mTextPaint.getFontMetrics();
	    float deltaY = localFontMetrics.bottom - localFontMetrics.top;
	    if (paramInt == 0)
	      this.oldX = 0;
	    
	    Canvas localCanvas = this.sfh.lockCanvas(new Rect(this.oldX, 0, paramInt + this.oldX, 180));
	    
	    Paint localPaint1 = new Paint();
	    localPaint1.setAntiAlias(true);
	    localPaint1.setColor(-1);
	    
	    Paint localPaint2 = new Paint();
	    localPaint2.setColor(-256);
	    localPaint2.setAntiAlias(true);
	    localPaint2.setStrokeWidth(2.0F);
	    localPaint2.setStyle(Paint.Style.FILL);
	    
	    float tempHeighMax = getMax(tempHeigh);
	    float spacePX = 300.0F / (getMin(tempLow) - tempHeighMax);
	    drawHighTemp(deltaY, 120, localCanvas, localPaint1, localPaint2, spacePX, tempHeighMax);
	    
	    drawLowTemp(deltaY, localCanvas, localPaint1, localPaint2, spacePX, /*getMin(tempLow),*/ 195);
	    
	    this.sfh.unlockCanvasAndPost(localCanvas);
	  }

			/*paramCanvas.drawText(tempLow[i] + "°", this.x[i], f2 + deltaY,
					this.mTextPaint);
			paramCanvas.drawBitmap(this.lowBmps[i],
					this.x[i] - this.lowBmps[i].getWidth() / 2, f2 + deltaY,
					null);
			i++;*/

			// paramCanvas.drawText((String)this.timeDate.get(j), this.x[j], f1
			// + 2.0F * paramFloat1 + this.lowBmps[j].getHeight(),
			// this.mTimePaint);
//		}
//	}

	public void setBitmap(List<Bitmap> topBitmaps, List<Bitmap> lowBitmaps) {
		if (topBitmaps == null || lowBitmaps == null) {
			return;
		}

		for (int i = 0; i < topBitmaps.size(); i++) {
			this.topBmps[i] = topBitmaps.get(i);
			this.lowBmps[i] = lowBitmaps.get(i);
			// this.topBmps[i] = WeatherPic.getSmallPic(this.c,
			// ((Integer)paramList1.get(i)).intValue(), 0);
			// this.lowBmps[i] = WeatherPic.getSmallPic(this.c,
			// ((Integer)paramList2.get(i)).intValue(), 1);
		}

	}

	public void setTemperature(ArrayList<Float> tempHeigh,
			ArrayList<Float> tempLow) {
		if (tempHeigh == null || tempLow == null) {
			return;
		}
		LogUtil.v(TAG, "tempHeigh = " + tempHeigh + ", tempLow = " + tempLow);
		for (int i = 0; i < tempHeigh.size(); i++) {
			this.tempHeigh[i] = tempHeigh.get(i);
			this.tempLow[i] = tempLow.get(i);
		}
	}

	public void setTime(List<String> timeWeak, List<String> timeDate) {
		this.timeWeak = timeWeak;
		this.timeDate = timeDate;
		postInvalidate();
	}

	public void setWidth(int paramInt) {
		this.x[0] = (-15 + paramInt / 12) * 6 / 4;
		this.x[1] = (-15 + paramInt * 3 / 12) * 6 / 4;
		this.x[2] = (-15 + paramInt * 5 / 12) * 6 / 4;
		this.x[3] = (-15 + paramInt * 7 / 12) * 6 / 4;
		/*
		 * this.x[4] = (-15 + paramInt * 9 / 12); this.x[5] = (-15 + paramInt *
		 * 11 / 12);
		 */
	}

	@Override
	public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1,
			int paramInt2, int paramInt3) {
		LogUtil.v("系统信息", "surfaceChanged");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		LogUtil.v("系统消息", "surfaceCreated");
		this.isRunning = true;
		this.currentX = 0;
		clearCanvas();
		new Thread(new Runnable() {
			public void run() {
				drawChart();
			}
		}).start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder) {
		LogUtil.v("系统信息", "surfaceDestroyed");
		this.isRunning = false;
	}
}
