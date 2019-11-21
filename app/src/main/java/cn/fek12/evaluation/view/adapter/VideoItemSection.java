package cn.fek12.evaluation.view.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.utils.VideoUtils;
import cn.fek12.evaluation.view.widget.RoundImageView;
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
    private List<MicroLessonEnetity.DataBean.VideoBean> mList = new ArrayList<>();
    private int mTypePage;
    private int TYPE_HOT = 1;//热门
    private int TYPE_UPDATE = 2;//最近更新
    private int TYPE_RECOMMEND = 3;//推荐
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
        void onMore();
    }
    public VideoItemSection(int typePage, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.video_item)
                .headerResourceId(R.layout.evaluation_list_header_paper)
                .build());
        mTypePage = typePage;
        mOnSelectItmeListener = onSelectItmeListener;
    }

    public void updateList( List<MicroLessonEnetity.DataBean.VideoBean> list){
        mList = list;
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
        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItmeListener != null){
                    mOnSelectItmeListener.onSelectItme(position);
                }
            }
        });
        itemHolder.tvName.setText(mList.get(position).getVideoName());
        //itemHolder.tvSubject.setText(mList.get(position).get);
        itemHolder.tvTime.setText(mList.get(position).getVideoCreateTime());
        Bitmap bitmap = VideoUtils.getInstance().getNetVideoBitmap(mList.get(position).getAddressUrl());
        itemHolder.ivCover.setImageBitmap(bitmap);
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
        private RoundImageView ivCover;
        private TextView tvTime;
        private TextView tvPlayNumber;
        private LinearLayout rootView;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
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
