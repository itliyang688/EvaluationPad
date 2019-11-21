package cn.fek12.evaluation.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.widget
 * @ClassName: ImageWebView
 * @Description:
 * @CreateDate: 2019/11/15 19:16
 */

public class NoRollWebView extends WebView {
    public NoRollWebView(Context context) {
        super(context);
    }

    public NoRollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoRollWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                int maxOverScrollY, boolean isTouchEvent) {
        return false;
    }

    /**
     * 使WebView不可滚动
     */
    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(0, 0);
    }
}