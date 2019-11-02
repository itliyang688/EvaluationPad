package cn.fek12.evaluation.view.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: DictionaryChildSection
 * @Description:
 * @CreateDate: 2019/10/25 15:04
 */
public class PresentationItemSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    private int selectPosition = 0;
    private String mHeader;
    public PresentationItemSection(String header, PresentationItemSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.presentation_item)
                .headerResourceId(R.layout.presentation_header)
                .build());
        mHeader = header;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateList(){
        selectPosition = 0;
    }
    public void updateSelect(int selectPos){
        selectPosition = selectPos;
    }
    @Override
    public int getContentItemsTotal() {
        if(mHeader.equals("近三天")){
            return 3;
        }else if(mHeader.equals("一周内")){
            return 6;
        }else{
            return 22;
        }
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
        holderHolder.tvHeader.setText(mHeader);
        if(mHeader.equals("近三天")){
            holderHolder.tvHeader.setTextColor(Color.parseColor("#6B400D"));
            holderHolder.ivIcon.setImageResource(R.mipmap.three_days_icon);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_three_days_header);
        }else if(mHeader.equals("一周内")){
            holderHolder.tvHeader.setTextColor(Color.parseColor("#188C6A"));
            holderHolder.ivIcon.setImageResource(R.mipmap.aweek_icon);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_aweek_header);
        }else{
            holderHolder.tvHeader.setTextColor(Color.parseColor("#753C09"));
            holderHolder.ivIcon.setImageResource(R.mipmap.earlier);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_earlier_header);
        }
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvSubject;
        private TextView tvTime;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvTime = itemView.findViewById(R.id.tvTime);
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHeader;
        private ImageView ivIcon;
        private RelativeLayout rlContain;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.header);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            rlContain = itemView.findViewById(R.id.rlContain);
        }
    }
}
