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
import cn.fek12.evaluation.model.entity.PlanVideoEntity;


public class PlanVideoAdapter extends RecyclerView.Adapter<PlanVideoAdapter.EvaluationHolder> {
    private OnPlanItemClickListener mOnPlanItemClickListener = null;

    private List<PlanVideoEntity.DataBean> mList = new ArrayList();

    public interface OnPlanItemClickListener {
        void onPlanItemClick(int position);
    }

    public void setOnPlanItemClickListener(OnPlanItemClickListener onPlanItemClickListener) {
        this.mOnPlanItemClickListener = onPlanItemClickListener;
    }


    private Context context;

    public PlanVideoAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<PlanVideoEntity.DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public PlanVideoAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.plan_video_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(PlanVideoAdapter.EvaluationHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnPlanItemClickListener != null){
                    mOnPlanItemClickListener.onPlanItemClick(position);
                }
            }
        });
        holder.setData(position);
    }

    class EvaluationHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tvName;
        private ImageView ivCover;
        private TextView tvTime;
        private TextView tvPlayNumber;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = this.itemView.findViewById(R.id.tvName);
            ivCover = this.itemView.findViewById(R.id.ivCover);
            tvTime = this.itemView.findViewById(R.id.tvTime);
            tvPlayNumber = this.itemView.findViewById(R.id.tvPlayNumber);
        }

        public void setData(final int position) {
            tvName.setText(mList.get(position).getVideoName());
            tvTime.setText(mList.get(position).getCreateDate());
            tvPlayNumber.setText(String.valueOf(mList.get(position).getPalyCount()));
            String imgUrl = mList.get(position).getImgUrl();
            Glide.with(MyApplication.getApp()).load(imgUrl).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivCover);
        }
    }
}
