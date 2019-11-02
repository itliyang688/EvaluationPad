package cn.fek12.evaluation.view.adapter;

import android.view.View;
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
public class DictionaryChildSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    private int selectPosition = 0;
    private String mHeader;
    private List<DictionaryListResp.DataBean.TabInfoBean.SubTabInfo> mList = new ArrayList<>();
    public DictionaryChildSection(List<DictionaryListResp.DataBean.TabInfoBean.SubTabInfo> list,String header,DictionaryChildSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_list_item_dictionary)
                .headerResourceId(R.layout.evaluation_list_header_dictionary)
                .build());
        mList.clear();
        mList = list;
        mHeader = header;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateList(List<DictionaryListResp.DataBean.TabInfoBean.SubTabInfo> list){
        //mList.clear();
        mList = list;
        selectPosition = 0;
    }
    public void updateSelect(int selectPos){
        selectPosition = selectPos;
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
        itemHolder.tvItem.setText(mList.get(position).getName());
        itemHolder.tvItem.setSelected(selectPosition >= 0 && selectPosition == position);
        itemHolder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItmeListener != null){
                    if (selectPosition != position) {
                        selectPosition = position;
                        mOnSelectItmeListener.onSelectItme(position);
                    }
                }
            }
        });
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder) {
        MyHeaderViewHolder holderHolder = (MyHeaderViewHolder) holder;
        holderHolder.tvHeader.setText(mHeader);
    }

    @Override
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new MyHeaderViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvItem;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.title);
        }
    }

    class MyHeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvHeader;
        public MyHeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = (TextView) itemView.findViewById(R.id.header);
        }
    }
}
