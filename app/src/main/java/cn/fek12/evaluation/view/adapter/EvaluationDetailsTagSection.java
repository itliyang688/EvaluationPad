package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: DictionaryChildSection
 * @Description:
 * @CreateDate: 2019/10/25 15:04
 */
public class EvaluationDetailsTagSection extends Section {
    private Context mContext;
    private String mCheckId;
    private OnSelectItmeListener mOnSelectItmeListener = null;
    private boolean isUpdate = false;

    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    private int selectPosition = 0;
    private List<TextbookChildEntity> mList = new ArrayList<>();
    public EvaluationDetailsTagSection(Context context, List<TextbookChildEntity> list, String checkId, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_list_tag)
                .headerResourceId(R.layout.evaluation_list_header_dictionary)
                .build());
        mContext = context;
        mList.clear();
        mList = list;
        mCheckId = checkId;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateList(List<TextbookChildEntity> list){
        mList = list;
        selectPosition = 0;
        isUpdate = true;
    }
    @Override
    public int getContentItemsTotal() {
        if(mList == null){
            return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(View view) {
        return new MyItemViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyItemViewHolder itemHolder = (MyItemViewHolder) holder;
        itemHolder.llContain.removeAllViews();
        for(int i = 0; i < mList.size(); i++){
            View viewItem = View.inflate(mContext, R.layout.evaluationv_tag_item,null);
            TextView textView = viewItem.findViewById(R.id.title);
            textView.setText(mList.get(i).getName());
            if(isUpdate){
                textView.setSelected(selectPosition >= 0 && selectPosition == i);
            }else{
                if(String.valueOf(mList.get(i).getId()).equals(mCheckId)){
                    textView.setSelected(true);
                }else{
                    textView.setSelected(false);
                }
            }

            int finalI = i;
            viewItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnSelectItmeListener != null){

                        String childId = String.valueOf(mList.get(finalI).getId());
                        /*if (!childId.equals(mCheckId)) {
                            mCheckId = childId;
                            selectPosition = finalI;
                            mOnSelectItmeListener.onSelectItme(finalI);
                        }*/

                        if (finalI != selectPosition) {
                            selectPosition = finalI;
                            mCheckId = childId;
                            mOnSelectItmeListener.onSelectItme(finalI);
                        }
                    }
                }
            });
            itemHolder.llContain.addView(viewItem);
        }
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        holderHolder.tvHeader.setVisibility(View.GONE);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvHeader;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.header);
        }
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private HorizontalScrollView scrollView;
        private LinearLayout llContain;
        private LinearLayout ll1;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            scrollView =  itemView.findViewById(R.id.scrollView);
            llContain =  itemView.findViewById(R.id.llContain);
            scrollView =  itemView.findViewById(R.id.scrollView);
        }
    }
}
