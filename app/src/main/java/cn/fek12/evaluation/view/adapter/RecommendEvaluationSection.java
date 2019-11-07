package cn.fek12.evaluation.view.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    List<HomeEvaluationDeta.DataBean.RecommendPaperBean> mList = new ArrayList<>();
    public RecommendEvaluationSection(List<HomeEvaluationDeta.DataBean.RecommendPaperBean> list, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_list_item_paper)
                .headerResourceId(R.layout.recommend_evaluation_header)
                .build());
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
        itemHolder.title.setText(mList.get(position).getName());
        itemHolder.time.setText(mList.get(position).getCreateDate());
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        holderHolder.header.setText("热门测评");
        holderHolder.header.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_paper_header_hot,
                0,
                0,
                0
        );
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView time;
        private ImageView image;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.image);
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
