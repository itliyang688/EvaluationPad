package cn.fek12.evaluation.view.PopupWindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.widget.MultipleStatusView;


public class MenuPopupWindow extends PopupWindow {
	private View mMenuView;
	private MultipleStatusView multipleStatusView;

	@SuppressLint("InflateParams")
	public MenuPopupWindow(Context context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.menu_dialog_activity, null);
		multipleStatusView = mMenuView.findViewById(R.id.load_view);

		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.PopupAnimation);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x80000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnClickListener(clickListener);
		multipleStatusView.setOnClickListener(clickListener);

	}

	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			if(view.getId() != R.id.load_view){
				dismiss();
			}
		}
	};

}
