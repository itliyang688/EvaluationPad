package cn.fek12.evaluation.view.adapter;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;

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
public class DictionaryTagChildSection extends Section {
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(String textbookId);
    }
    private int selectPosition = 0;
    private String mHeader;
    private List<TextbookChildEntity> mList = new ArrayList<>();
    public DictionaryTagChildSection(List<TextbookChildEntity> list, String header, DictionaryTagChildSection.OnSelectItmeListener onSelectItmeListener) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.evaluation_tag_item_dictionary)
                .headerResourceId(R.layout.evaluation_list_header_dictionary)
                .build());
        mList.clear();
        mList = list;
        mHeader = header;
        mOnSelectItmeListener = onSelectItmeListener;
    }
    public void updateList(List<TextbookChildEntity> list){
        //mList.clear();
        mList = list;
        selectPosition = 0;
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
        ArrayList<Tag> tags = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            Tag tag = new Tag(mList.get(i).getName());
            tag.setId(String.valueOf(mList.get(i).getId()));
            tag.setRadius(10f);
            if(i == 0){
                selectPosition = i;
                tag.setTagTextColor(Color.parseColor("#FFFFFF"));
                tag.setLayoutColor(Color.parseColor("#FEAC2C"));
            }else{
                tag.setTagTextColor(Color.parseColor("#666666"));
                tag.setLayoutColor(Color.parseColor("#E3E3E3"));
            }
            tags.add(tag);
        }

        itemHolder.tagView.addTags(tags);
        itemHolder.tagView.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                if(mOnSelectItmeListener != null){
                    if (selectPosition != position) {
                        for(Tag tagItem : tags){
                            tagItem.setTagTextColor(Color.parseColor("#666666"));
                            tagItem.setLayoutColor(Color.parseColor("#E3E3E3"));
                        }
                        tag.setTagTextColor(Color.parseColor("#FFFFFF"));
                        tag.setLayoutColor(Color.parseColor("#FEAC2C"));
                        tags.set(position,tag);
                        itemHolder.tagView.addTags(tags);
                        selectPosition = position;
                        mOnSelectItmeListener.onSelectItme(tags.get(position).getId());
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
        private TagView tagView;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            tagView = itemView.findViewById(R.id.tag_group);
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
