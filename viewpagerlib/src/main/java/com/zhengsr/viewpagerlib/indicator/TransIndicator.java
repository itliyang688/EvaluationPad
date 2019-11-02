package com.zhengsr.viewpagerlib.indicator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.ViewPager;

import com.zhengsr.viewpagerlib.R;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.type.TransType;


/**
 * Created by zhengshaorui on 2017/10/29.
 * csdn: http://blog.csdn.net/u011418943
 */
public class TransIndicator extends LinearLayout implements ViewPager.OnPageChangeListener {
    private static final String TAG = "zsr";



    /**
     * ui
     */
    private View mOpenView;
    private View mFirstView;
    private View mSecondView;

    /**
     * normal and logic
     */
    private Context mContext;
    private int mCount;
    private Path mPath;
    private Paint mPaint;
    private int[] mFirstPosition = new int[2];
    private int[] mSecondPositon = new int[2];
    /**
     * attrs
     */
    private int mTransWidht = 8;
    private int mTransHeight = 8;
    private int mMoveDistance = 0;
    private int mMoveSize;
    private int mLeftMargin;
    private int mDefaultcolor = 0xb0cccccc;
    private int mMovecolor = 0xffffff;
    private boolean mDismissOpen; //是否隐藏底部指示器，当“立即体验”的view 出现的时候。
    private TransType mTransType;
    private int mRoundRadius;

    public TransIndicator(Context context) {
        this(context,null);
    }

    public TransIndicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TransIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TransIndicator);
        mLeftMargin =  ta.getDimensionPixelSize(R.styleable.TransIndicator_trans_leftmargin,20);
        mTransWidht =  ta.getDimensionPixelSize(R.styleable.TransIndicator_trans_width,mTransWidht);
        mTransHeight =  ta.getDimensionPixelSize(R.styleable.TransIndicator_trans_height,mTransHeight);
        mDefaultcolor = ta.getColor(R.styleable.TransIndicator_trans_defaultcolor,
                mDefaultcolor);
        mMovecolor = ta.getColor(R.styleable.TransIndicator_trans_movecolor,
               mMovecolor);
        mDismissOpen = ta.getBoolean(R.styleable.TransIndicator_trans_dismiss_open,false);
        int type = ta.getInteger(R.styleable.TransIndicator_trans_type,0);
        if (type == 0){
            mTransType = TransType.TRANS_ROUND;
        }else{
            mTransType = TransType.TRANS_CIRCLE;
        }
        mRoundRadius = ta.getDimensionPixelSize(R.styleable.TransIndicator_trans_round_radius,7);
        ta.recycle();


        setGravity(Gravity.CENTER);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mMovecolor);
        setClipChildren(false);
        setClipToPadding(false);

    }



    public void addPagerData(PageBean bean, ViewPager viewPager){
        if (bean != null){
            mCount = bean.getParams().getDatas().size();
            if (mCount == 0){
                return;
            }

            mOpenView = bean.getParams().getOpenview();
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(mLeftMargin,0,0,0);
            GradientDrawable drawable = new GradientDrawable();
            if (mTransType == TransType.TRANS_ROUND) {
                drawable.setShape(GradientDrawable.RECTANGLE);
                drawable.setSize(mTransWidht, mTransHeight);
                drawable.setCornerRadius(mRoundRadius);
            }else{
                drawable.setShape(GradientDrawable.OVAL);
                drawable.setSize(mTransWidht *2, mTransHeight*2);
            }
            drawable.setColor(mDefaultcolor);

            for (int i = 0; i < mCount; i++) {
                ImageView imageView = new ImageView(mContext);
                imageView.setBackground(drawable);
                imageView.setLayoutParams(params);
                if (i == 0){

                    mFirstView = imageView;
                }
                if (i == 1){
                    mSecondView = imageView;
                }

                addView(imageView);
            }
            if (viewPager != null) {
                viewPager.addOnPageChangeListener(this);
            }
        }
    }


    /**
     * 显示最后一页的状态
     * @param position
     */
    private void showStartView(int position) {
        // 最后一页
        if (position == mCount - 1) {
            if (mOpenView != null) {
                mOpenView.setVisibility(VISIBLE);
                ObjectAnimator animator = ObjectAnimator.ofFloat(mOpenView,
                        "alpha", 0, 1);
                animator.setDuration(500);
                animator.setInterpolator(new AccelerateDecelerateInterpolator());
                animator.start();
                if (mDismissOpen){
                    setVisibility(View.GONE);
                }
            }
        } else {
            if (mOpenView != null) {

                mOpenView.setVisibility(GONE);

                if (mDismissOpen){
                    setVisibility(VISIBLE);
                }
            }
        }
    }

    /**
     * 重绘圆点的运动
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mMoveDistance,0);
        canvas.drawPath(mPath,mPaint);
        canvas.restore();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (position % mCount == (mCount - 1) && positionOffset > 0){
            mMoveDistance = 0;
        }else {
            mMoveDistance = (int) (positionOffset * mMoveSize + position % mCount * mMoveSize);
        }
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
        showStartView(position%mCount);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mFirstView == null){
            return;
        }
        if (mSecondView == null){
            return;
        }
        //第一个控件在屏幕的位置
        mFirstView.getLocationOnScreen(mFirstPosition);
        mSecondView.getLocationOnScreen(mSecondPositon);
        // 两个圆点之间要移动的距离
        mMoveSize = mSecondPositon[0] - mFirstPosition[0];
        //获取liearlayout 的位置，两个相减才是正确的位置
        int[] pos = new int[2];
        getLocationInWindow(pos);

        // 获取到第一个小圆点的坐标，并重绘
        int firstViewX = mFirstPosition[0] - pos[0];
        if (mTransType == TransType.TRANS_CIRCLE) {
            mPath.addCircle(firstViewX + mTransWidht, getHeight() / 2,
                    mTransWidht, Path.Direction.CW);
        }else{
            RectF rectF = new RectF(
                    firstViewX,
                    (getHeight() - mTransHeight)/2,
                    firstViewX+mTransWidht,
                    (getHeight()+mTransHeight)/2);
            mPath.addRoundRect(rectF,mRoundRadius,mRoundRadius,Path.Direction.CW);
        }

    }

    public TransIndicator transWidth(int size){
        mTransWidht = size;
        return this;
    }
    public TransIndicator transHeight(int size){
        mTransHeight = size;
        return this;
    }
    public TransIndicator defaultColor(int color){
        mDefaultcolor = color;
        return this;
    }
    public TransIndicator moveColor(int color){
        mMovecolor = color;
        return this;
    }
    public TransIndicator transMargin(int size){
        mLeftMargin = size;
        return this;
    }

    public TransIndicator rectradius(int size){
        mRoundRadius = size;
        return this;
    }

    public TransIndicator transType(TransType transType){
        mTransType = transType;
        return this;
    }

    public TransIndicator dismissWhenOpen(boolean dismiss){
        mDismissOpen = dismiss;
        return this;
    }


}
