package cn.fek12.evaluation.view.PopupWindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.model.entity.SourceEntity;
import cn.fek12.evaluation.model.entity.SubjectsEntity;
import cn.fek12.evaluation.model.entity.SubjectsObsoleteEntity;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.PopupWindow
 * @ClassName: SubjectPopupWindow
 * @Description:
 * @CreateDate: 2019/12/4 15:22
 */
public class SubjectAllPopupWindow extends PopupWindow {
    private Context mContext;
    private View mMenuView;
    private View viewEmpty;
    private LinearLayout llContain;
    private MultipleStatusView loadView;
    private OnSelectItmeListener mOnSelectItmeListener = null;
    private int requestType = 0;

    public interface OnSelectItmeListener {
        void onSelectItme(String subjectId, String subjectName);
    }

    public SubjectAllPopupWindow(Context context, OnSelectItmeListener onSelectItmeListener,int requestType){
        this.requestType = requestType;
        this.mContext = context;
        this.mOnSelectItmeListener = onSelectItmeListener;

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.subject_pupup_layout, null);
        llContain = mMenuView.findViewById(R.id.llContain);
        viewEmpty = mMenuView.findViewById(R.id.viewEmpty);
        loadView = mMenuView.findViewById(R.id.loadView);

        // 设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.PopupSubAnimation);
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0x00000000);
        // 设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        mMenuView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if(requestType == 0){
            querySubjectList();
        }else{
            querySourceList();
        }

    }

    public void viewEmptyHide(){
        viewEmpty.setVisibility(View.GONE);
    }

    public View getMenuView(){
        return mMenuView;
    }

    public void querySourceList() {
        ApiRetrofit.getInstance().getApiService().wrongTopicOrigin()
                .compose(RxHelper.observableIO2Main(mContext))
                .subscribe(new BaseObserver<SourceEntity>() {

                    @Override
                    public void onSuccess(SourceEntity entry) {
                        if(entry.getState().equals("0")){
                            llContain.removeAllViews();
                            SourceEntity.DataBean dataBean = new SourceEntity.DataBean();
                            dataBean.setOrigin("ALL");
                            dataBean.setName("全部");
                            List<SourceEntity.DataBean> mList = entry.getData();
                            mList.add(0,dataBean);

                            for(int i = 0; i < mList.size(); i++){
                                View viewItem = LayoutInflater.from(mContext).inflate(R.layout.subject_itme, null);
                                TextView tvName = viewItem.findViewById(R.id.tvName);
                                tvName.setText(mList.get(i).getName());
                                int finalI = i;
                                viewItem.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        tvName.setTextColor(mContext.getResources().getColor(R.color.white));
                                        if(mOnSelectItmeListener != null){
                                            mOnSelectItmeListener.onSelectItme(String.valueOf(mList.get(finalI).getOrigin()),mList.get(finalI).getName());
                                        }
                                        dismiss();
                                    }
                                });
                                llContain.addView(viewItem);
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {
                    }
                });
    }

    public void querySubjectList() {
        ApiRetrofit.getInstance().getApiService().getNocSubjectInfoList()
                .compose(RxHelper.observableIO2Main(mContext))
                .subscribe(new BaseObserver<SubjectsEntity>() {

                    @Override
                    public void onSuccess(SubjectsEntity entry) {
                        if(entry.getState().equals("0")){
                            llContain.removeAllViews();
                            SubjectsEntity.DataBean dataBean = new SubjectsEntity.DataBean();
                            dataBean.setSubId("0");
                            dataBean.setSubName("全部");
                            List<SubjectsEntity.DataBean> mList = entry.getData();
                            mList.add(0,dataBean);

                            for(int i = 0; i < mList.size(); i++){
                                View viewItem = LayoutInflater.from(mContext).inflate(R.layout.subject_itme, null);
                                TextView tvName = viewItem.findViewById(R.id.tvName);
                                tvName.setText(mList.get(i).getSubName());
                                int finalI = i;
                                viewItem.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        tvName.setTextColor(mContext.getResources().getColor(R.color.white));
                                        if(mOnSelectItmeListener != null){
                                            mOnSelectItmeListener.onSelectItme(String.valueOf(mList.get(finalI).getSubId()),mList.get(finalI).getSubName());
                                        }
                                        dismiss();
                                    }
                                });
                                llContain.addView(viewItem);
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {
                    }
                });
    }

}
