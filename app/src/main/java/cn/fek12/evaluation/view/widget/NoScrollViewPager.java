package cn.fek12.evaluation.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * 不能左右划的ViewPager
 * 
 * @author Liyang
 * 
 */
public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}

	// 表示事件是否拦截, 返回false表示不拦截, 可以让嵌套在内部的viewpager响应左右划的事件
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		return false;
	}

	/**
	 * 重写onTouchEvent事件,什么都不用做
	 */
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		return false;
	}

	@Override
	public void setCurrentItem(int item) {
		//去除页面切换时的滑动翻页效果
		super.setCurrentItem(item, false);
	}
}
