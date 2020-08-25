package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.SubjectModel;


public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<SubjectModel.DataBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(String subId);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context mContext;

    public SubjectAdapter(Context context) {
        this.mContext = context;
    }

    public void notifyChanged(List<SubjectModel.DataBean> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public SubjectAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.subject_byuser_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(SubjectAdapter.EvaluationHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick( mList.get(position).getSubId());
                    for(SubjectModel.DataBean dataBean : mList){
                        dataBean.setTagColor(0);
                    }
                    mList.get(position).setTagColor(1);
                    notifyDataSetChanged();
                }
            }
        });
        holder.setData(position);
    }

    class EvaluationHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView subjectName;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            subjectName = this.itemView.findViewById(R.id.subjectName);

        }

        public void setData(final int position) {
            if(mList.get(position).getTagColor() == 1){
                subjectName.setTextColor(mContext.getResources().getColor(R.color.color_F7A42B));
            }else{
                subjectName.setTextColor(mContext.getResources().getColor(R.color.color_333));
            }
            subjectName.setText(mList.get(position).getSubName());
        }
    }
}
