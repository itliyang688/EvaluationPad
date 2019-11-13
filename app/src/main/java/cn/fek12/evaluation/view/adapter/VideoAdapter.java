package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.EvaluationListEntity;


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List mList = new ArrayList();

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

    public void notifyChanged(List list,boolean isAdd) {
        if(isAdd){
            mList.addAll(list);
        }else{
            mList = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 30;
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
            tvTime = this.itemView.findViewById(R.id.tvTime);
            tvPlayNumber = this.itemView.findViewById(R.id.tvPlayNumber);
        }

        public void setData(final int position) {

        }
    }
}