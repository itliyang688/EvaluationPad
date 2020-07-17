package cn.fek12.evaluation.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fek12.basic.utils.toast.ToastUtils;

import org.zakariya.stickyheaders.SectioningAdapter;

import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.CollectionListEntity;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.activity.FullScreenVideoPlayActivity;
import cn.fek12.evaluation.view.activity.MicrolessonVideoPlayActivity;
import cn.fek12.evaluation.view.widget.RoundImageView;

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
    private int count = 0;
    private OnItemClickListener mOnItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(String videoId);
        void onVideoPlay(CollectionListEntity.DataBean.VideoBean videoBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    CollectionListEntity.DataBean mDataBean = new CollectionListEntity.DataBean();
    public MicroLessonRecordAdapter(int pageType,Context context){
        mPageType = pageType;
        mContext = context;
    }

    public void notifyChanged(CollectionListEntity.DataBean dataBean) {
        count = 3;
        mDataBean = dataBean;
        notifyAllSectionsDataSetChanged();
    }

    @Override
    public int getNumberOfSections() {
        return count;
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        if(sectionIndex == 0){
            if(mDataBean.getAweek() == null){
                return 0;
            }
            return mDataBean.getAweek().size();
        }else if(sectionIndex == 1){
            if(mDataBean.getAmonth() == null){
                return 0;
            }
            return mDataBean.getAmonth().size();
        }else if(sectionIndex == 2){
            if(mDataBean.getEarlier() == null){
                return 0;
            }
            return mDataBean.getEarlier().size();
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
        List<CollectionListEntity.DataBean.VideoBean> videoList = null;
        if(sectionIndex == 0){
            videoList = mDataBean.getAweek();
        }else if(sectionIndex == 1){
            videoList = mDataBean.getAmonth();
        }else if(sectionIndex == 2){
            videoList = mDataBean.getEarlier();
        }
        if(videoList != null){
            ivh.tvName.setText(videoList.get(itemIndex).getVideoName());
            if(!TextUtils.isEmpty(videoList.get(itemIndex).getVideoContent())){
                ivh.tvDescribe.setVisibility(View.VISIBLE);
                ivh.tvDescribe.setText("视频简介："+videoList.get(itemIndex).getVideoContent());
            }else{
                ivh.tvDescribe.setVisibility(View.INVISIBLE);
            }
            ivh.tvSemester.setText(videoList.get(itemIndex).getTextbookName());
            ivh.tvSubject.setText(videoList.get(itemIndex).getGradeName()+" "+videoList.get(itemIndex).getSubjcetName());
            String percentage = videoList.get(itemIndex).getPlayBack();
            if(TextUtils.isEmpty(percentage)){
                percentage = "0";
            }
            ivh.tvPlayCount.setText("已观看"+percentage+"%");
            Glide.with(MyApplication.getApp()).load(videoList.get(itemIndex).getImgUrl()).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivh.ivCover);
        }
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
        TextView tvSubject;
        TextView tvSemester;
        TextView tvDescribe;
        TextView tvName;
        RoundImageView ivCover;
        RelativeLayout rootView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView.findViewById(R.id.rootView);
            tvPlayCount = itemView.findViewById(R.id.tvPlayCount);
            ivCollection = itemView.findViewById(R.id.ivCollection);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvSemester = itemView.findViewById(R.id.tvSemester);
            tvName = itemView.findViewById(R.id.tvName);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvDescribe = itemView.findViewById(R.id.tvDescribe);
            ivCollection.setOnClickListener(this);
            rootView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            final int section = MicroLessonRecordAdapter.this.getSectionForAdapterPosition(adapterPosition);
            final int item = MicroLessonRecordAdapter.this.getPositionOfItemInSection(section, adapterPosition);
            List<CollectionListEntity.DataBean.VideoBean> videoList = null;
            if(section == 0){
                videoList = mDataBean.getAweek();
            }else if(section == 1){
                videoList = mDataBean.getAmonth();
            }else if(section == 2){
                videoList = mDataBean.getEarlier();
            }
            switch (v.getId()){
                case R.id.rootView:
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onVideoPlay(videoList.get(item));
                    }
                    break;
                case R.id.ivCollection:
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemClick(String.valueOf(videoList.get(item).getVideoId()));
                    }
                    break;
            }
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
