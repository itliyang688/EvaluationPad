package cn.fek12.evaluation.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;
import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: DictionaryChildSection
 * @Description:
 * @CreateDate: 2019/10/25 15:04
 */
public class PrepareExaminationSubjectSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;
    private String mCheckId;
    private int selectPosition = 0;
    private boolean isUpdate = false;
    public interface OnSelectItmeListener {
        void onSelectItme(int pos);
    }
    private List<SubjectModel.DataBean> mList = new ArrayList<>();
    public PrepareExaminationSubjectSection(List<SubjectModel.DataBean> list, String checkId, OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_list_item_dictionary)
                .headerResourceId(R.layout.evaluation_list_header_dictionary)
                .build());
        mList.clear();
        mList = list;
        mCheckId = checkId;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateList(List<SubjectModel.DataBean> list){
        mList = list;
        isUpdate = true;
        selectPosition = 0;
    }
    @Override
    public int getContentItemsTotal() {
        if(mList == null || mList.size() == 0){
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
        itemHolder.tvItem.setText(mList.get(position).getSubName());
        if(isUpdate){
            itemHolder.tvItem.setSelected(selectPosition >= 0 && selectPosition == position);
        }else{
            if(String.valueOf(mList.get(position).getId()).equals(mCheckId)){
                selectPosition = position;
                itemHolder.tvItem.setSelected(true);
            }else{
                itemHolder.tvItem.setSelected(false);
            }
        }

        itemHolder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItmeListener != null){

                    String childId = String.valueOf(mList.get(position).getId());
                    /*if (!childId.equals(mCheckId)) {
                        mCheckId = childId;
                        selectPosition = position;
                        mOnSelectItmeListener.onSelectItme(position);
                    }*/

                    if (position != selectPosition) {
                        selectPosition = position;
                        mCheckId = childId;
                        mOnSelectItmeListener.onSelectItme(position);
                    }
                }
            }
        });
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
        private final TextView tvItem;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tvItem = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
