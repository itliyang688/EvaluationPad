package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.MicroLessonPageEnetity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<MicroLessonPageEnetity.DataBean.RecordsBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<MicroLessonPageEnetity.DataBean.RecordsBean> list, boolean isAdd) {
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
    public VideoAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoAdapter.EvaluationHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
        holder.setData(position);
    }

    class EvaluationHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tvName;
        private TextView tvSubject;
        private TextView tvGrade;
        private ImageView ivCover;
        private TextView tvTime;
        private TextView tvPlayNumber;
        private LinearLayout rootView;


        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            rootView = this.itemView.findViewById(R.id.rootView);
            tvName = this.itemView.findViewById(R.id.tvName);
            ivCover = this.itemView.findViewById(R.id.ivCover);
            tvSubject = this.itemView.findViewById(R.id.tvSubject);
            tvGrade = this.itemView.findViewById(R.id.tvGrade);
            tvTime = this.itemView.findViewById(R.id.tvTime);
            tvPlayNumber = this.itemView.findViewById(R.id.tvPlayNumber);
        }

        public void setData(final int position) {
            tvName.setText(mList.get(position).getVideoName());
            tvSubject.setText(mList.get(position).getTextbookName());
            tvGrade.setText(mList.get(position).getSubjcetName());
            tvTime.setText(mList.get(position).getCreateDate());
            tvPlayNumber.setText(String.valueOf(mList.get(position).getPlayCount()));
            String imgUrl = mList.get(position).getImgUrl();
            Glide.with(MyApplication.getApp()).load(imgUrl).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivCover);
        }
    }
}
