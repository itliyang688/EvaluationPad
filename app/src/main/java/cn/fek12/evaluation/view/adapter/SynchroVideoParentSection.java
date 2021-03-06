package cn.fek12.evaluation.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: SynchroVideoParentSection
 * @Description:
 * @CreateDate: 2020/6/25 14:16
 */
public class SynchroVideoParentSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;
    private int selectPosition = 0;

    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    List<ChildSectionEntity> mList = new ArrayList<>();
    public SynchroVideoParentSection(List<ChildSectionEntity> list, SynchroVideoParentSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.tag_item_dictionary)
                //.headerResourceId(R.progress_bar_ct.evaluation_list_header_dictionary)
                .build());
        mList.clear();
        mList = list;
        mOnSelectItmeListener = onSelectItmeListener;
    }

    public void updateList(List<ChildSectionEntity> list){
        mList = list;
    }

    @Override
    public int getContentItemsTotal() {
        if(mList == null){
            return 0;
        }
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
    public RecyclerView.ViewHolder getHeaderViewHolder(View view) {
        return new EmptyViewHolder(view);
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvItem;

        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
