package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.view.widget.StrokeTextView;


public class ConqueredAdapter extends RecyclerView.Adapter<ConqueredAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<ConqueredEntity.DataBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public ConqueredAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<ConqueredEntity.DataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public ConqueredAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.conquered_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(ConqueredAdapter.EvaluationHolder holder, final int position) {
        holder.btnConquered.setOnClickListener(new View.OnClickListener() {
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
        private ProgressBar progressBar;
        private ImageView btnConquered;
        private StrokeTextView tvScore;
        private StrokeTextView tvReason;
        private StrokeTextView tvName;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            progressBar = this.itemView.findViewById(R.id.progressBar);
            tvScore = this.itemView.findViewById(R.id.tvScore);
            tvReason = this.itemView.findViewById(R.id.tvReason);
            tvName = this.itemView.findViewById(R.id.tvName);
            btnConquered = this.itemView.findViewById(R.id.btnConquered);

        }

        public void setData(final int position) {
            progressBar.setProgress(mList.get(position).getStudyLevel());
            tvScore.setText(mList.get(position).getScore()+"分");
            tvName.setText(mList.get(position).getName());
            tvReason.setText(mList.get(position).getWeakReason());
        }
    }
}
