package cn.fek12.evaluation.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.widget
 * @ClassName: SlideScrollView
 * @Description:
 * @CreateDate: 2020/5/19 9:47
 */
public class SlideScrollView extends ScrollView {
    public SlideScrollView(Context context) {
        this(context,null);
    }
    public SlideScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }
    public SlideScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //关键点在这
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(ev);
    }
}
