package cn.fek12.evaluation.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.utils.EmptyViewHolder;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: DictionaryParentSection1
 * @Description:
 * @CreateDate: 2019/10/25 14:16
 */
public class DictionaryParentSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;

    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    List<DictionaryListResp.DataBean> mList = new ArrayList<>();
    public DictionaryParentSection(List<DictionaryListResp.DataBean> list, DictionaryParentSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_list_item_dictionary)
                .headerResourceId(R.layout.evaluation_list_header_dictionary)
                .build());
        mList.clear();
        mList = list;
       mOnSelectItmeListener = onSelectItmeListener;
    }
    private int selectPosition = 0;

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
