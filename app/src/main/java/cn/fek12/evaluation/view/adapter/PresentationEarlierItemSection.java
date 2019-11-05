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
import cn.fek12.evaluation.model.entity.EarlierEntity;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: DictionaryChildSection
 * @Description:
 * @CreateDate: 2019/10/25 15:04
 */
public class PresentationEarlierItemSection extends Section {
    private List<EarlierEntity.DataBean.PapersBean> mList = new ArrayList<>();
    private Context mContext;
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    private String mHeader;
    public PresentationEarlierItemSection(Context context,List<EarlierEntity.DataBean.PapersBean> list,String header, PresentationEarlierItemSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.presentation_item)
                .headerResourceId(R.layout.presentation_header)
                .build());
        mContext = context;
        mList = list;
        mHeader = header;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateAndAddList(List<EarlierEntity.DataBean.PapersBean> list,boolean isAdd){
        if(isAdd){
            mList.addAll(list);
        }else{
            mList = list;
        }
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
        holderHolder.tvHeader.setText(mHeader);
        holderHolder.tvHeader.setTextColor(Color.parseColor("#753C09"));
        holderHolder.ivIcon.setImageResource(R.mipmap.earlier);
        holderHolder.rlContain.setBackgroundResource(R.drawable.bg_earlier_header);
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
            ivSubject = itemView.findViewById(R.id.ivSubject);
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
