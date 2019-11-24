package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.CurriculumEntity;


public class CurriculumRecordAdapter extends RecyclerView.Adapter<CurriculumRecordAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private List<CurriculumEntity.DataBean.VideosBean> mList = new ArrayList();

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

    public void notifyChanged(List<CurriculumEntity.DataBean.VideosBean> list,boolean isAdd) {
        if(isAdd){
            mList.addAll(list);
        }else{
            mList = list;
        }
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
                    mOnItemClickListener.onItemClick(position);
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
        private TextView tvSubject;
        private TextView tvDate;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = itemView.findViewById(R.id.tvName);
            tvDescribe = itemView.findViewById(R.id.tvDescribe);
            tvSemester = itemView.findViewById(R.id.tvSemester);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescribe.setVisibility(View.INVISIBLE);
        }

        public void setData(final int position) {
            tvName.setText(mList.get(position).getVideoName());
            tvSemester.setText(mList.get(position).getTextbook());
            tvDate.setText(mList.get(position).getDate());
            tvSubject.setText(mList.get(position).getGrade()+" "+mList.get(position).getSubject());

        }
    }
}
