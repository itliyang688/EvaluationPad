package cn.fek12.evaluation.view.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: EvaluationPaperSection
 * @Description:
 * @CreateDate: 2019/10/28 16:22
 */
public class EvaluationPaperSection extends Section {
    private int mTypePage;
    private int TYPE_HOT = 1;
    private int TYPE_UPDATE = 2;
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
        void onMore();
    }
    List<AssessmentIndexPaperResp.DataBean.HotAndUpdateBean> mList = new ArrayList<>();
    public EvaluationPaperSection(List<AssessmentIndexPaperResp.DataBean.HotAndUpdateBean> list,int typePage,OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_list_item_paper)
                .headerResourceId(R.layout.evaluation_list_header_paper)
                .build());
        mList = list;
        mTypePage = typePage;
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
        if(mTypePage == TYPE_HOT){
            if(position >= 0 && position <= 2){
                itemHolder.time.setTextColor(Color.parseColor("#F41F09"));
                itemHolder.title.setTextColor(Color.parseColor("#F41F09"));
                itemHolder.image.setImageResource(R.drawable.ic_paper_item_hot);
            }else{
                itemHolder.time.setTextColor(Color.parseColor("#333333"));
                itemHolder.title.setTextColor(Color.parseColor("#333333"));
                itemHolder.image.setImageResource(0);
            }
        }else if(mTypePage == TYPE_UPDATE){
            if(position == 0){
                itemHolder.time.setTextColor(Color.parseColor("#F67106"));
                itemHolder.title.setTextColor(Color.parseColor("#F67106"));
                itemHolder.image.setImageResource(R.drawable.ic_paper_top_one);
            }else if(position == 1){
                itemHolder.time.setTextColor(Color.parseColor("#FEAC2C"));
                itemHolder.title.setTextColor(Color.parseColor("#FEAC2C"));
                itemHolder.image.setImageResource(R.drawable.ic_paper_top_two);
            }else if(position == 2){
                itemHolder.time.setTextColor(Color.parseColor("#FDCC49"));
                itemHolder.title.setTextColor(Color.parseColor("#FDCC49"));
                itemHolder.image.setImageResource(R.drawable.ic_paper_top_three);
            }else{
                itemHolder.time.setTextColor(Color.parseColor("#333333"));
                itemHolder.title.setTextColor(Color.parseColor("#333333"));
                itemHolder.image.setImageResource(0);
            }
        }

        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
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
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        if(mList.size() == 0){
            holderHolder.rootView.setVisibility(View.GONE);
            return;
        }
        holderHolder.rootView.setVisibility(View.VISIBLE);
        if (mTypePage == TYPE_HOT) {
            holderHolder.header.setText("热门测评");
            holderHolder.header.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_paper_header_hot,
                    0,
                    0,
                    0
            );
        }else{
            holderHolder.header.setText("最近更新");
            holderHolder.header.setCompoundDrawablesWithIntrinsicBounds(
                    R.drawable.ic_paper_header_update,
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
        private TextView title;
        private TextView time;
        private ImageView image;
        private LinearLayout rootView;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            image = itemView.findViewById(R.id.image);
            rootView = itemView.findViewById(R.id.rootView);
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private TextView header;
        private TextView tvMore;
        private FrameLayout rootView;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
            tvMore = itemView.findViewById(R.id.tvMore);
            rootView = itemView.findViewById(R.id.rootView);
        }
    }
}
