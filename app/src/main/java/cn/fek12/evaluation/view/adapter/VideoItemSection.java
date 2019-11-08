package cn.fek12.evaluation.view.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: EvaluationPaperSection
 * @Description:
 * @CreateDate: 2019/10/28 16:22
 */
public class VideoItemSection extends Section {
    private int mTypePage;
    private int TYPE_HOT = 1;//热门
    private int TYPE_UPDATE = 2;//最近更新
    private int TYPE_RECOMMEND = 3;//推荐
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
        void onMore();
    }
    List mList = new ArrayList<>();
    public VideoItemSection(List list, int typePage, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.video_item)
                .headerResourceId(R.layout.evaluation_list_header_paper)
                .build());
        mList = list;
        mTypePage = typePage;
        mOnSelectItmeListener = onSelectItmeListener;
    }

    @Override
    public int getContentItemsTotal() {
        return 20;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        if (mTypePage == TYPE_HOT) {
            holderHolder.header.setText("热门视频");
            holderHolder.header.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_paper_header_hot,
                    0,
                    0,
                    0
            );
        }else if (mTypePage == TYPE_UPDATE) {
            holderHolder.header.setText("最近更新");
            holderHolder.header.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.update_icon,
                    0,
                    0,
                    0
            );
        }else{
            holderHolder.header.setText("为你推荐");
            holderHolder.header.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.save_icon,
                    0,
                    0,
                    0
            );
        }

        holderHolder.tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItmeListener != null){
                    mOnSelectItmeListener.onMore();
                }
            }
        });
    }


    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvSubject;
        private ImageView ivCover;
        private TextView tvTime;
        private TextView tvPlayNumber;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPlayNumber = itemView.findViewById(R.id.tvPlayNumber);
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header;
        private TextView tvMore;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            tvMore = itemView.findViewById(R.id.tvMore);
        }
    }
}
