package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.MicroLessonPageEnetity;
import cn.fek12.evaluation.view.widget.RoundImageView;
import pl.droidsonroids.gif.GifImageView;


public class AlikeAdapter extends RecyclerView.Adapter<AlikeAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<MicroLessonPageEnetity.DataBean.RecordsBean> mList = new ArrayList();

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

    public void notifyChanged(List<MicroLessonPageEnetity.DataBean.RecordsBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 3;
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
        private TextView tvTime;
        private TextView tvPlayNumber;
        private TextView tvNumber;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = this.itemView.findViewById(R.id.tvName);
            tvPlayNumber = this.itemView.findViewById(R.id.tvPlayNumber);
            tvTime = this.itemView.findViewById(R.id.tvTime);
            tvNumber = this.itemView.findViewById(R.id.tvNumber);
            ivCover = this.itemView.findViewById(R.id.ivCover);
        }

        public void setData(final int position) {
        }
    }
}
