package com.jieqiong.coolweather.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class MyGridView extends GridView {
	  private boolean haveScrollbar = true;

	  public MyGridView(Context paramContext)
	  {
	    super(paramContext);
	  }

	  public MyGridView(Context paramContext, AttributeSet paramAttributeSet)
	  {
	    super(paramContext, paramAttributeSet);
	  }

	  public MyGridView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
	  {
	    super(paramContext, paramAttributeSet, paramInt);
	  }

	  /**  
	     * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true  
	     *   
	     * @param haveScrollbars  
	     */  
	    public void setHaveScrollbar(boolean haveScrollbar) {  
	        this.haveScrollbar = haveScrollbar;  
	    }  
	  
	    @Override  
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	        if (haveScrollbar == false) {  
	            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);  
	            super.onMeasure(widthMeasureSpec, expandSpec);  
	        } else {  
	            super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
	        }  
	    }  
}
