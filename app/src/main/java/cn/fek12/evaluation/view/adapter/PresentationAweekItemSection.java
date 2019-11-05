package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.AWeekEntity;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: DictionaryChildSection
 * @Description:
 * @CreateDate: 2019/10/25 15:04
 */
public class PresentationAweekItemSection extends Section {
    private Context mContext;
    private List<AWeekEntity.DataBean.WeekAndDayBean> mList = new ArrayList<>();
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    private String mHeader;
    public PresentationAweekItemSection(List<AWeekEntity.DataBean.WeekAndDayBean> list,Context context,String header, PresentationAweekItemSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.presentation_item)
                .headerResourceId(R.layout.presentation_header)
                .build());
        mContext = context;
        mList = list;
        mHeader = header;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateList(List<AWeekEntity.DataBean.WeekAndDayBean> list){
        mList = list;
    }
    @Override
    public int getContentItemsTotal() {
        if(mList == null){
            return 0;
        }else{
            return mList.size();
        }
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
        itemHolder.tvName.setText(mList.get(position).getName());
        itemHolder.tvSubject.setText(mList.get(position).getCourseName());
        Glide.with(mContext)
                .load(mList.get(position).getImageUrl())
                //.load("http://2016.fek12.cn/resources/admin_cp/img/home/banner1.jpg")
                .placeholder(R.mipmap.presentation_empty_bg)
                .error(R.mipmap.presentation_empty_bg)
                .into(itemHolder.ivSubject);
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        if(mList == null || mList.size() == 0){
            holderHolder.tvHeader.setVisibility(View.GONE);
            return;
        }
        holderHolder.tvHeader.setText(mHeader);
        if(mHeader.equals("近三天")){
            holderHolder.tvHeader.setTextColor(Color.parseColor("#6B400D"));
            holderHolder.ivIcon.setImageResource(R.mipmap.three_days_icon);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_three_days_header);
        }else if(mHeader.equals("一周内")){
            holderHolder.tvHeader.setTextColor(Color.parseColor("#188C6A"));
            holderHolder.ivIcon.setImageResource(R.mipmap.aweek_icon);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_aweek_header);
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
        private ImageView ivSubject;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivSubject = itemView.findViewById(R.id.ivSubject);
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
