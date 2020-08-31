package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.CurriculumEntity;
import cn.fek12.evaluation.view.widget.RoundImageView;


public class CurriculumRecordAdapter extends RecyclerView.Adapter<CurriculumRecordAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List<CurriculumEntity.DataBean.VideosBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(CurriculumEntity.DataBean.VideosBean videosBean);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private Context context;

    public CurriculumRecordAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<CurriculumEntity.DataBean.VideosBean> list,boolean isAdd) {
        if(!isAdd){
            mList.clear();
        }
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public CurriculumRecordAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.curriculum_record_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(CurriculumRecordAdapter.EvaluationHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(mList.get(position));
                }
            }
        });
        holder.setData(position);
    }

    class EvaluationHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tvName;
        private TextView tvDescribe;
        private TextView tvSemester;
        private TextView tvPlayCount;
        private TextView tvSubject;
        private TextView tvDate;
        private RoundImageView ivCover;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = itemView.findViewById(R.id.tvName);
            tvDescribe = itemView.findViewById(R.id.tvDescribe);
            tvSemester = itemView.findViewById(R.id.tvSemester);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvPlayCount = itemView.findViewById(R.id.tvPlayCount);
            ivCover = itemView.findViewById(R.id.ivCover);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescribe.setVisibility(View.INVISIBLE);
        }

        public void setData(final int position) {
            if(!TextUtils.isEmpty(mList.get(position).getContent())){
                tvDescribe.setVisibility(View.VISIBLE);
                tvDescribe.setText("视频简介："+mList.get(position).getContent());
            }else{
                tvDescribe.setVisibility(View.INVISIBLE);
            }
            tvName.setText(mList.get(position).getVideoName());
            tvSemester.setText(mList.get(position).getTextbookName());
            tvDate.setText(mList.get(position).getDate());
            tvSubject.setText(mList.get(position).getGradeName()+" "+mList.get(position).getSubject());
            String percentage = mList.get(position).getPlayBack();
            if(TextUtils.isEmpty(percentage)){
                percentage = "0";
            }
            tvPlayCount.setText("已观看"+percentage+"%");
            Glide.with(MyApplication.getApp()).load(mList.get(position).getImgUrl()).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivCover);
        }
    }
}
