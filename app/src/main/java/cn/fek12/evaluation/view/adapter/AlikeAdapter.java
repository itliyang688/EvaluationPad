package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.text.Html;
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
import cn.fek12.evaluation.model.entity.PromoteRecommedVideoEntity;
import cn.fek12.evaluation.view.widget.RoundImageView;


public class AlikeAdapter extends RecyclerView.Adapter<AlikeAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<PromoteRecommedVideoEntity.DataBean.RecordsBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public AlikeAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<PromoteRecommedVideoEntity.DataBean.RecordsBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public AlikeAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.learn_together_video_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(AlikeAdapter.EvaluationHolder holder, final int position) {
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
        private RoundImageView ivCover;
        private TextView tvName;
        //private TextView tvTime;
        //private TextView tvPlayNumber;
        private TextView tvLearned;
        private TextView tvNumber;
        private TextView tvCollection;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = this.itemView.findViewById(R.id.tvName);
            //tvPlayNumber = this.itemView.findViewById(R.id.tvPlayNumber);
            //tvTime = this.itemView.findViewById(R.id.tvTime);
            tvNumber = this.itemView.findViewById(R.id.tvNumber);
            tvCollection = this.itemView.findViewById(R.id.tvCollection);
            tvLearned = this.itemView.findViewById(R.id.tvLearned);
            ivCover = this.itemView.findViewById(R.id.ivCover);
        }

        public void setData(final int position) {
            tvCollection.setText("收藏："+mList.get(position).getIsCollection());
            tvLearned.setText("已学："+mList.get(position).getPlayCount());
            tvName.setText(mList.get(position).getVideoName());
            //tvPlayNumber.setText(String.valueOf(mList.get(position).getPlayCount()));
            //tvTime.setText(mList.get(position).getCreateDate());
            tvNumber.setText(Html.fromHtml("本周本校 <font color='#F28504'>"+ mList.get(position).getClassMateCount() +"</font>" + " 位学生在学"));
            Glide.with(MyApplication.getApp()).load(mList.get(position).getImgUrl()).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivCover);
        }
    }
}
