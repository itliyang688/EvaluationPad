package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.PresentationEntity;
import cn.fek12.evaluation.view.activity.ConqueredActivity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.adapter
 * @ClassName: MicroLessonRecordAdapter
 * @Description:
 * @CreateDate: 2019/11/20 15:48
 */
public class Presentation1Adapter extends SectioningAdapter {
    private final int PRESENTATION = 1;//报告
    private final int PROMOTE = 2;//提升
    private int mPageType;
    private Context mContext;
    private int count = 0;
    private List<PresentationEntity> mDaylist = new ArrayList<>();
    private List<PresentationEntity> mAweeklist = new ArrayList<>();
    private List<PresentationEntity> mEarlierList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public Presentation1Adapter(int pageType, Context context){
        mPageType = pageType;
        mContext = context;
    }

    public void notifyChanged(List<PresentationEntity> daylist, List<PresentationEntity> aweeklist,List<PresentationEntity> earlierList,boolean isAdd) {
        count = 3;
        if(isAdd){
            mEarlierList.addAll(earlierList);
        }else{
            if(daylist != null && daylist.size() > 0){
                mDaylist = daylist;
            }
            if(aweeklist != null && aweeklist.size() > 0){
                mAweeklist = aweeklist;
            }
            mEarlierList = earlierList;
        }
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public int getNumberOfSections() {
        return count;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        if(sectionIndex == 0){
            if(mDaylist == null){
                return 0;
            }
            return mDaylist.size();
        }else if(sectionIndex == 1){
            if(mAweeklist == null){
                return 0;
            }
            return mAweeklist.size();
        }else if(sectionIndex == 2){
            if(mEarlierList == null){
                return 0;
            }
            return mEarlierList.size();
        }
        return 0;
    }
    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return true;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.presentation_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public HeaderViewHolder onCreateHeaderViewHolder(ViewGroup parent, int headerType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.presentation_header, parent, false);
        return new HeaderViewHolder(v);
    }

    @Override
    public void onBindItemViewHolder(SectioningAdapter.ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemType) {
        ItemViewHolder itemHolder = (ItemViewHolder) viewHolder;
        List<PresentationEntity> mList = null;
        if(sectionIndex == 0){
            mList = mDaylist;
        }else if(sectionIndex == 1){
            mList = mAweeklist;
        }else if(sectionIndex == 2){
            mList = mEarlierList;
        }
        itemHolder.tvName.setText(mList.get(itemIndex).getName());
        itemHolder.tvSubject.setText(mList.get(itemIndex).getCourseName());
        if(mPageType == PROMOTE){//提升item
            itemHolder.tvTime.setText(mList.get(itemIndex).getPaperResultDate().split(" ")[0]);
            itemHolder.tvScore.setText(mList.get(itemIndex).getStudentScore()+"/"+mList.get(itemIndex).getScore());
        }else{
            itemHolder.tvTime.setText(mList.get(itemIndex).getPaperResultDate().split("\\.")[0]);
        }
        Glide.with(mContext)
                .load(mList.get(itemIndex).getImageUrl())
                //.load("http://2016.fek12.cn/resources/admin_cp/img/home/banner1.jpg")
                .placeholder(R.mipmap.empty_bg)
                .error(R.mipmap.empty_bg)
                .into(itemHolder.ivSubject);
        /*List<PresentationEntity> finalMList = mList;
        itemHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ConqueredActivity.class);
                intent.putExtra("paperResultId",String.valueOf(finalMList.get(itemIndex).getPaperResultId()));
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public void onBindHeaderViewHolder(SectioningAdapter.HeaderViewHolder viewHolder, int sectionIndex, int headerType) {
        HeaderViewHolder holderHolder = (HeaderViewHolder) viewHolder;
        if(sectionIndex == 0){
            holderHolder.tvHeader.setText("近三天");
            holderHolder.tvHeader.setTextColor(Color.parseColor("#6B400D"));
            holderHolder.ivIcon.setImageResource(R.mipmap.three_days_icon);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_three_days_header);
        }else if(sectionIndex == 1){
            holderHolder.tvHeader.setText("近一周");
            holderHolder.tvHeader.setTextColor(Color.parseColor("#188C6A"));
            holderHolder.ivIcon.setImageResource(R.mipmap.aweek_icon);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_aweek_header);
        }else if(sectionIndex == 2){
            holderHolder.tvHeader.setText("较早");
            holderHolder.tvHeader.setTextColor(Color.parseColor("#753C09"));
            holderHolder.ivIcon.setImageResource(R.mipmap.earlier);
            holderHolder.rlContain.setBackgroundResource(R.drawable.bg_earlier_header);
        }
    }

    public class ItemViewHolder extends SectioningAdapter.ItemViewHolder implements View.OnClickListener {
        private TextView tvName;
        private TextView tvSubject;
        private TextView tvTime;
        private TextView tvScore;
        private ImageView ivSubject;
        private LinearLayout rootView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvName = itemView.findViewById(R.id.tvName);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvTime = itemView.findViewById(R.id.tvTime);
            ivSubject = itemView.findViewById(R.id.ivSubject);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            final int section = Presentation1Adapter.this.getSectionForAdapterPosition(adapterPosition);
            final int item = Presentation1Adapter.this.getPositionOfItemInSection(section, adapterPosition);
            List<PresentationEntity> mList = null;
            if(section == 0){
                mList = mDaylist;
            }else if(section == 1){
                mList = mAweeklist;
            }else if(section == 2){
                mList = mEarlierList;
            }

            Intent intent = new Intent(mContext, ConqueredActivity.class);
            intent.putExtra("paperResultId",String.valueOf(mList.get(item).getPaperResultId()));
            mContext.startActivity(intent);
        }
    }

    public class HeaderViewHolder extends SectioningAdapter.HeaderViewHolder implements View.OnClickListener {

        private TextView tvHeader;
        private ImageView ivIcon;
        private RelativeLayout rlContain;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            tvHeader = itemView.findViewById(R.id.header);
            ivIcon = itemView.findViewById(R.id.ivIcon);
            rlContain = itemView.findViewById(R.id.rlContain);
        }

        @Override
        public void onClick(View view) {

        }
    }

}
