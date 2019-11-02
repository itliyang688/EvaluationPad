package com.fek12.basic.view.titleView;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fek12.basic.R;
import com.fek12.basic.utils.resources.ResUtils;
import com.fek12.basic.utils.window.WindowUtils;

import java.lang.reflect.Field;

/**
 * @author by : liyang
 * @Date :2017/11/15 11:30
 */
public class BaseTitleView extends FrameLayout {
    private ViewGroup mRootView;
    //左侧布局容器
    private LinearLayout mLeftTitleGroup;
    //中间标题
    private TextView mMiddleTitle;
    //右侧布局容器
    private LinearLayout mRightTitleGroup;

    BaseTitleView(Context context) {
        super(context);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        View.inflate(getContext(), R.layout.base_title_view, this);
        mRootView = (ViewGroup) findViewById(R.id.base_activity_title);
        mLeftTitleGroup = (LinearLayout) findViewById(R.id.ll_left_title_group);
        mMiddleTitle = (TextView) findViewById(R.id.tv_middle_title);
        mRightTitleGroup = (LinearLayout) findViewById(R.id.ll_right_title_group);

        ViewGroup.LayoutParams params = mRootView.getLayoutParams();
        ViewGroup.MarginLayoutParams marginParams = null;
        //获取view的margin设置参数
        if (params instanceof ViewGroup.MarginLayoutParams) {
            marginParams = (ViewGroup.MarginLayoutParams) params;
        } else {
            //不存在时创建一个新的参数
            marginParams = new ViewGroup.MarginLayoutParams(params);
        }
        int top = getStatusBarHeight(getContext());//获取状态栏高度
        //设置margin
        marginParams.setMargins(0, top, 0, 0);
        mRootView.setLayoutParams(marginParams);
    }

    //获取状态栏高度
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 添加右侧文字按钮
     *
     * @return
     */
    public TextView addRightTextButton(String string, OnClickListener onClickListener) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getDimension(R.dimen.dp_13));
        textView.setPadding(getDimension(R.dimen.dp_15), getDimension(R.dimen.dp_15), getDimension(R.dimen.dp_15), getDimension(R.dimen.dp_15));
        textView.setTextColor(getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);
        textView.setText(string);
        textView.setOnClickListener(onClickListener);
        mRightTitleGroup.addView(textView);
        return textView;
    }

    /**
     * 添加右侧图片按钮
     *
     * @return
     */
    public ImageView addRightImgButton(@DrawableRes int id, OnClickListener onClickListener) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(getDimension(R.dimen.dp_60), getDimension(R.dimen.dp_34)));
        imageView.setPadding(getDimension(R.dimen.dp_6), getDimension(R.dimen.dp_6), getDimension(R.dimen.dp_6), getDimension(R.dimen.dp_6));
        imageView.setBackgroundResource(R.drawable.bg_common_btn);
        imageView.setImageResource(id);
        imageView.setOnClickListener(onClickListener);
        mRightTitleGroup.addView(imageView);
        return imageView;
    }

    /**
     * 添加左侧文字按钮
     * 注意: 右侧按钮容器添加了一个paddingRight
     * 如果有右侧按钮,使用时需要调一下padding值
     *
     * @return
     */
    @Deprecated
    public TextView addLeftTextButton(String string, OnClickListener onClickListener) {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getDimension(R.dimen.dp_13));
        textView.setPadding(getDimension(R.dimen.dp_15), getDimension(R.dimen.dp_15), getDimension(R.dimen.dp_15), getDimension(R.dimen.dp_15));
        textView.setTextColor(getColor(R.color.white));
        textView.setGravity(Gravity.CENTER);
        textView.setText(string);
        textView.setOnClickListener(onClickListener);
        mLeftTitleGroup.addView(textView);
        return textView;
    }

    /**
     * 添加左侧图片按钮
     * 注意: 右侧按钮容器添加了一个paddingRight
     * 如果有右侧按钮,使用时需要调一下padding值
     *
     * @return
     */
    @Deprecated
    public ImageView addLeftImgButton(@DrawableRes int id, OnClickListener onClickListener) {
        ImageView imageView = new ImageView(getContext());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(getDimension(R.dimen.dp_50), ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setPadding(getDimension(R.dimen.dp_17), getDimension(R.dimen.dp_17), getDimension(R.dimen.dp_17), getDimension(R.dimen.dp_17));
        imageView.setBackgroundResource(R.drawable.title_btn_color_selector);
        imageView.setImageResource(id);
        imageView.setOnClickListener(onClickListener);
        mLeftTitleGroup.addView(imageView);
        return imageView;
    }

    /**
     * 设置左侧标题,默认启用返回按钮
     *
     * @param title 中间标题名称
     */
    public TextView setLeftTitle(String title) {
        return setLeftTitle(title, new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
    }

    /**
     * 设置左侧标题,默认启用返回按钮
     *
     * @param title 中间标题名称
     */
    public TextView setLeftTitleAndLeftIcon(String title, int leftImg) {
        TextView textView = setLeftTitle(title, new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).finish();
            }
        });
        ImageView leftBack = (ImageView) findViewById(R.id.iv_left_back);
        leftBack.setImageResource(leftImg);
        return textView;
    }

    /**
     * 设置左侧标题,默认启用返回按钮
     *
     * @param title 中间标题名称
     */
    public TextView setLeftTitle(String title, OnClickListener onClickListener) {
        View leftBack = findViewById(R.id.iv_left_back);
        View leftDivisionLine = findViewById(R.id.v_left_division_line);
        TextView leftTitle = (TextView) findViewById(R.id.tv_left_title);
        leftBack.setVisibility(VISIBLE);
        leftTitle.setVisibility(VISIBLE);
        leftDivisionLine.setVisibility(VISIBLE);
        leftTitle.setText(title);
        leftBack.setOnClickListener(onClickListener);
        return leftTitle;
    }

    /**
     * 设置中间标题
     *
     * @param title 中间标题名称
     */
    public TextView setMiddleTitle(String title) {
        mMiddleTitle.setVisibility(VISIBLE);
        mMiddleTitle.setText(title);
        return mMiddleTitle;
    }

    /**
     * 设置中间标题
     *
     * @param title 中间标题名称
     * @param bool  是否显示左侧返回键
     * @return
     */
    public TextView setMiddleTitle(String title, boolean bool) {
        if (bool) {
            return setMiddleTitle(title, new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            });
        } else {
            return setMiddleTitle(title);
        }
    }

    /**
     * 设置中间标题
     *
     * @param title           中间标题名称
     * @param onClickListener 左侧返回按钮点击事件
     * @return
     */
    public TextView setMiddleTitle(String title, OnClickListener onClickListener) {
        mMiddleTitle.setVisibility(VISIBLE);
        mMiddleTitle.setText(title);
        View leftBack = findViewById(R.id.iv_left_back);
        leftBack.setVisibility(VISIBLE);
        leftBack.setOnClickListener(onClickListener);
        return mMiddleTitle;
    }

    /**
     * 左侧按钮使用自定义图片
     *
     * @return
     */
    public ImageView setDefaultBackButtonImage(@DrawableRes int resId) {
        ImageView leftBack = (ImageView) findViewById(R.id.iv_left_back);
        leftBack.setImageResource(resId);
        return leftBack;
    }


    /**
     * 使用自定义布局时,
     * BaseTitleView只负责宽高与背景颜色
     */
    public void useCustomLayout(View view) {
        //移除原有布局
        mRootView.removeAllViews();
        //使用自定义布局
        mRootView.addView(view);
    }

    /**
     * 添加状态栏高度
     *
     * @return
     */
    public BaseTitleView addStatusBarHeight() {
        mRootView.getLayoutParams().height += WindowUtils.getStatusHeight(getContext());
        mRootView.setPadding(0, WindowUtils.getStatusHeight(getContext()), 0, 0);
        return this;
    }

    public int getTitleHeight() {
        return mRootView.getHeight();
    }

    /**
     * 设置背景颜色
     */
    @Override
    public void setBackgroundColor(@ColorInt int color) {
        mRootView.setBackgroundColor(color);
    }

    public void titleIsShow(boolean isShow){
        mRootView.setVisibility(isShow? View.VISIBLE : View.GONE);
    }

    /**
     * 获取dimens
     */
    private int getDimension(@DimenRes int id) {
        return ResUtils.getDimen(id);
    }

    /**
     * 获取颜色
     */
    private int getColor(@ColorRes int id) {
        return ResUtils.getColor(id);
    }


}
