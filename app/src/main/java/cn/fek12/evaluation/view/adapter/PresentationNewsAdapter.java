package cn.fek12.evaluation.view.adapter;

import android.graphics.Color;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.PresentationEntity;
import cn.fek12.evaluation.view.widget.FullSpanUtil;


public class PresentationNewsAdapter extends BaseMultiItemQuickAdapter<PresentationEntity, BaseViewHolder> {
    public final static int PRESENTATION = 1;//报告
    public final static int PROMOTE = 2;//提升
    private int pageType;
    private Presentation1Adapter.OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick();
    }

    public void setOnItemClickListener(Presentation1Adapter.OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public PresentationNewsAdapter(@Nullable List<PresentationEntity> data, int type) {
        super(data);
        this.pageType = type;
        addItemType(PresentationEntity.PICTURE_CONTENT, R.layout.presentation_item);
        addItemType(PresentationEntity.PICTURE_TITLE, R.layout.presentation_header);
    }

    public void notifyChanged(List<PresentationEntity> data,boolean isAdd) {
        if(isAdd){
            for(PresentationEntity model : data){
                this.addData(model);
            }
        }else{
            this.getData().clear();
            this.getData().addAll(data);
        }
        notifyDataSetChanged();
    }

    @Override
    protected void convert(BaseViewHolder helper, PresentationEntity item) {

        if (helper.getItemViewType() == PresentationEntity.PICTURE_CONTENT) {
            helper.setText(R.id.tvName, item.getName());
            helper.setText(R.id.tvSubject, item.getCourseName());
            if(pageType == PROMOTE){//提升item
                helper.setText(R.id.tvTime, item.getPaperResultDate().split(" ")[0]);
                helper.setText(R.id.tvScore, item.getStudentScore()+"/"+item.getScore());
            }else{
                helper.setText(R.id.tvTime, item.getPaperResultDate().split("\\.")[0]);
            }

            Glide.with(mContext)
                    .load(item.getImageUrl())
                    .placeholder(R.mipmap.empty_bg)
                    .error(R.mipmap.empty_bg)
                    .into((ImageView) helper.getView(R.id.ivSubject));
        } else if (helper.getItemViewType() == PresentationEntity.PICTURE_TITLE) {
            helper.addOnClickListener(R.id.rootView);
            if(item.getTitleType() == 1){
                helper.setText(R.id.header, "近三天");
                helper.setTextColor(R.id.header,Color.parseColor("#6B400D"));
                helper.setImageResource(R.id.ivIcon,R.mipmap.three_days_icon);
                helper.setBackgroundRes(R.id.rlContain,R.drawable.bg_three_days_header);
            }else if(item.getTitleType() == 2){
                helper.setText(R.id.header, "近一周");
                helper.setTextColor(R.id.header,Color.parseColor("#188C6A"));
                helper.setImageResource(R.id.ivIcon,R.mipmap.aweek_icon);
                helper.setBackgroundRes(R.id.rlContain,R.drawable.bg_aweek_header);
            }else if(item.getTitleType() == 3){
                helper.setText(R.id.header, "较早");
                helper.setTextColor(R.id.header,Color.parseColor("#753C09"));
                helper.setImageResource(R.id.ivIcon,R.mipmap.earlier);
                helper.setBackgroundRes(R.id.rlContain,R.drawable.bg_earlier_header);
            }

        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, PresentationEntity.PICTURE_TITLE);


    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, PresentationEntity.PICTURE_TITLE);
    }

}
