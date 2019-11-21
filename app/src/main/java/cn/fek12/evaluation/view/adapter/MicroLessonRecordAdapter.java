package cn.fek12.evaluation.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.zakariya.stickyheaders.SectioningAdapter;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: MicroLessonRecordAdapter
 * @Description:
 * @CreateDate: 2019/11/20 15:48
 */
public class MicroLessonRecordAdapter extends SectioningAdapter {
    private final int STUDY = 1;//学习记录
    private final int COLLECTION = 2;//收藏记录
    private int mPageType;
    private Context mContext;
    public MicroLessonRecordAdapter(int pageType,Context context){
        mPageType = pageType;
        mContext = context;
    }
    @Override
    public int getNumberOfSections() {
        return 3;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        return 10;
    }
    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.micro_lesson_record_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.micro_lesson_record_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        ItemViewHolder ivh = (ItemViewHolder) viewHolder;
        if(mPageType == STUDY){
            ivh.ivCollection.setVisibility(View.GONE);
            ivh.tvPlayCount.setTextColor(mContext.getResources().getColor(R.color.app_bg));
        }else if(mPageType == COLLECTION){
            ivh.ivCollection.setVisibility(View.VISIBLE);
            ivh.tvPlayCount.setTextColor(mContext.getResources().getColor(R.color.color_F8B311));
        }
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        HeaderViewHolder hvh = (HeaderViewHolder) viewHolder;
        if(sectionIndex == 0){
            hvh.header.setText("近一周");
        }else if(sectionIndex == 1){
            hvh.header.setText("近一月");
        }else if(sectionIndex == 2){
            hvh.header.setText("较早");
        }
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnClickListener {
        TextView tvPlayCount;
        ImageView ivCollection;
        public ItemViewHolder(View itemView) {
            super(itemView);
            tvPlayCount = itemView.findViewById(R.id.tvPlayCount);
            ivCollection = itemView.findViewById(R.id.ivCollection);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            final int section = MicroLessonRecordAdapter.this.getSectionForAdapterPosition(adapterPosition);
            final int item = MicroLessonRecordAdapter.this.getPositionOfItemInSection(section, adapterPosition);
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder implements View.OnClickListener {

        TextView header;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            header = itemView.findViewById(R.id.header);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
