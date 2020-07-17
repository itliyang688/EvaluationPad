package cn.fek12.evaluation.view.adapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.utils.FastDFSUtil;
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
    public VideoItemSection(int typePage,List<MicroLessonEnetity.DataBean.VideoBean> list, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.video_item)
                .headerResourceId(R.layout.evaluation_list_header_paper)
                .build());
        mTypePage = typePage;
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
        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItmeListener != null){
                    mOnSelectItmeListener.onSelectItme(position);
                }
            }
        });
        itemHolder.tvName.setText(mList.get(position).getVideoName());
        itemHolder.tvPlayNumber.setText(String.valueOf(mList.get(position).getPlayCount()));
        itemHolder.tvSubject.setText(mList.get(position).getTextbookName());
        itemHolder.tvGrade.setText(mList.get(position).getSubjcetName());
        itemHolder.tvTime.setText(mList.get(position).getCreateDate());
        //String imgUrl = "http://192.168.0.46/group1/M00/00/02/wKgALl324MmAa3AuAAAnk16E_l4634.jpg?token=bbb9e3960a6d1882a11ee26815e0dd17&ts=1576460493";
        String imgUrl = mList.get(position).getImgUrl();
        Glide.with(MyApplication.getApp()).load(imgUrl).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(itemHolder.ivCover);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        if(mList == null || mList.size() == 0){
            holderHolder.rootView.setVisibility(View.GONE);
            return;
        }
        //holderHolder.rootView.setVisibility(View.VISIBLE);
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
        private TextView tvGrade;
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
            tvGrade = itemView.findViewById(R.id.tvGrade);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPlayNumber = itemView.findViewById(R.id.tvPlayNumber);
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header;
        private TextView tvMore;
        private View view;
        private FrameLayout rootView;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            header = itemView.findViewById(R.id.header);
            rootView = itemView.findViewById(R.id.rootView);
            tvMore = itemView.findViewById(R.id.tvMore);
        }
    }
}
