package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: EvaluationPaperSection
 * @Description:
 * @CreateDate: 2019/10/28 16:22
 */
public class RecommendEvaluationSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;
    private Context mContext;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    List<HomeEvaluationDeta.DataBean.RecommendPaperBean> mList = new ArrayList<>();
    public RecommendEvaluationSection(Context context,List<HomeEvaluationDeta.DataBean.RecommendPaperBean> list, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.recommend_itme)
                .headerResourceId(R.layout.recommend_evaluation_header)
                .build());
        mContext = context;
        mList = list;
        mOnSelectItmeListener = onSelectItmeListener;
    }

    @Override
    public int getContentItemsTotal() {
        return mList.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
        itemHolder.tvName.setText(mList.get(position).getName());
        itemHolder.tvData.setText(mList.get(position).getCreateDate());
        itemHolder.tvSubject.setText(mList.get(position).getCourseName());
        Glide.with(mContext)
                .load(mList.get(position).getImageUrl())
                .placeholder(R.mipmap.presentation_empty_bg)
                .error(R.mipmap.presentation_empty_bg)
                .into(itemHolder.ivSubject);
        itemHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItmeListener != null){
                    mOnSelectItmeListener.onSelectItme(position);
                }
            }
        });
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {

    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvSubject;
        private TextView tvData;
        private ImageView ivSubject;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvData = itemView.findViewById(R.id.tvData);
            ivSubject = itemView.findViewById(R.id.ivSubject);
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
        }
    }
}
