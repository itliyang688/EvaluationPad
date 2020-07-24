package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
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
import cn.fek12.evaluation.model.entity.RelevantVideoListEntity;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.utils.VideoUtils;
import cn.fek12.evaluation.view.widget.RoundImageView;


public class RelevantVideoAdapter extends RecyclerView.Adapter<RelevantVideoAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List<RelevantVideoListEntity.DataBean.RelatedVideoBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private Context context;

    public RelevantVideoAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<RelevantVideoListEntity.DataBean.RelatedVideoBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public RelevantVideoAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.relevant_video_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(RelevantVideoAdapter.EvaluationHolder holder, final int position) {
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
        private RoundImageView ivCover;
        private TextView tvTextbook;
        private TextView tvPlayNumber;


        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = this.itemView.findViewById(R.id.tvName);
            ivCover = this.itemView.findViewById(R.id.ivCover);
            tvSubject = this.itemView.findViewById(R.id.tvSubject);
            tvTextbook = this.itemView.findViewById(R.id.tvTextbook);
            tvPlayNumber = this.itemView.findViewById(R.id.tvPlayNumber);
        }

        public void setData(final int position) {
            tvName.setText(mList.get(position).getVideoName());
            tvSubject.setText(mList.get(position).getSubject());
            tvTextbook.setText(mList.get(position).getTextbookName());
            tvPlayNumber.setText(String.valueOf(mList.get(position).getPlayCount()));
            Glide.with(MyApplication.getApp()).load(mList.get(position).getImgUrl()).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivCover);
            /*ivCover.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        String path = FastDFSUtil.generateSourceUrl(mList.get(position).getAddressUrl());
                        Bitmap bitmap = VideoUtils.getInstance().getNetVideoBitmap(path);
                        if(bitmap != null){
                            ivCover.setImageBitmap(bitmap);
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });*/
        }
    }
}
