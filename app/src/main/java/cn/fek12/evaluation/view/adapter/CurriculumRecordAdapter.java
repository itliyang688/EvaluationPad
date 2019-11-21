package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;


public class CurriculumRecordAdapter extends RecyclerView.Adapter<CurriculumRecordAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private Context context;

    public CurriculumRecordAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 30;
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
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
        holder.setData(position);
    }

    class EvaluationHolder extends RecyclerView.ViewHolder {

        private View itemView;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

        public void setData(final int position) {

        }
    }
}
