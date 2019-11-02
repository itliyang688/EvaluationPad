package com.fek12.basic.utils.popwindowUtils;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fek12.basic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by :wjh
 * @package name ：com.zgjky.basic.utils.popwindowUtils
 * @describe :
 * @Date :2017/12/21 10:08
 */

public class PopwindowUtils extends PopupWindow {
    private View mainView;
    private int xOff = 0;
    private int yOff = 0;
    private List<PopLisetInfo> list = new ArrayList<>();
    private PopuwindowAdapter adapter;
    private PopCallBack callBack;

    /**
     * 头部右上角popupWindow
     *
     * @param context 来自哪里
     * @param width   宽度
     * @param height  高度
     */
    public PopwindowUtils(Context context, int width, int height) {
        super(context);
        //窗口布局
        mainView = LayoutInflater.from(context).inflate(R.layout.popwin_baseview, null);

        ListView listView = (ListView) mainView.findViewById(R.id.baseview_listview);
        listView.setFocusable(true);

        setContentView(mainView);

        adapter = new PopuwindowAdapter(context);
        listView.setAdapter(adapter);

        setTouchable(true);
        setOutsideTouchable(true);
        getContentView().setFocusableInTouchMode(true);
        getContentView().setFocusable(true);
        //设置宽度
        setWidth(width);
        //设置高度
        setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置显示隐藏动画
        setAnimationStyle(R.style.AnimTools);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    private int[] calculatePopWindowPos(View anchorView, View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = getScreenHeight(anchorView.getContext());
        final int screenWidth = getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * 获取屏幕高度(px)
     */
    public int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public void setXOff(int xOff) {
        this.xOff = xOff;
    }

    public void setYOff(int yOff) {
        this.yOff = yOff;
    }

    public void showPop(View view) {
        int windowPos[] = calculatePopWindowPos(view, mainView);
        windowPos[0] -= xOff;
        windowPos[1] -= yOff;
        this.showAtLocation(view, Gravity.TOP | Gravity.START, windowPos[0], windowPos[1]);
    }

    public class PopuwindowAdapter extends BaseAdapter {
        private Context context;

        public PopuwindowAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            PopHelper helper = null;
            if (convertView == null) {
                helper = new PopHelper();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_popupwindow, null);
                helper.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.item_popwin_re);
                helper.imageView = (ImageView) convertView.findViewById(R.id.item_popwin_img);
                helper.textView = (TextView) convertView.findViewById(R.id.item_popwin_text);
                convertView.setTag(helper);
            } else {
                helper = (PopHelper) convertView.getTag();
            }

            if (position == list.size() - 1) {
                helper.imageView.setVisibility(View.GONE);
            } else {
                helper.imageView.setVisibility(View.VISIBLE);
            }

            helper.textView.setText(list.get(position).getInfo());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.state(list.get(position).isFlag());
                    PopwindowUtils.this.dismiss();
                }
            });
            return convertView;
        }

        class PopHelper {
            RelativeLayout relativeLayout;
            TextView textView;
            ImageView imageView;
        }
    }

    public void setList(List<PopLisetInfo> list) {
        this.list.clear();
        this.list.addAll(list);
        adapter.notifyDataSetChanged();
    }

    public interface PopCallBack {
        void state(boolean flag);
    }

    public void setCallBack(PopCallBack callBack) {
        this.callBack = callBack;
    }
}
