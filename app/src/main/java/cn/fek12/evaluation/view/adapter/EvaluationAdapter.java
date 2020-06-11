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
import cn.fek12.evaluation.model.entity.EvaluationListEntity;


public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.EvaluationHolder> {
    public static final int AUTONOMY_EVALUATION = 0;
    public static final int EVALUATION = 1;
    private int pageType;
    private OnItemClickListener mOnItemClickListener = null;

    private List<EvaluationListEntity.DataBean.PapersBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public EvaluationAdapter(Context context,int pageType) {
        this.context = context;
        this.pageType = pageType;
    }

    public void notifyChanged(List<EvaluationListEntity.DataBean.PapersBean> list,boolean isAdd) {
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
    public EvaluationAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.evaluation_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(EvaluationAdapter.EvaluationHolder holder, final int position) {
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
        private TextView title;
        private TextView time;
        private TextView tvState;


        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = this.itemView.findViewById(R.id.title);
            time = this.itemView.findViewById(R.id.time);
            tvState = this.itemView.findViewById(R.id.tvState);
        }

        public void setData(final int position) {
            title.setText(mList.get(position).getName());
            time.setText(mList.get(position).getCreateDate());
            if(pageType == AUTONOMY_EVALUATION){
                tvState.setVisibility(View.VISIBLE);
                if(mList.get(position).getIsanswered() == 0){
                    tvState.setText("未完成");
                    tvState.setTextColor(context.getResources().getColor(R.color.color_FDAD2D));
                }else{
                    tvState.setText("已完成");
                    tvState.setTextColor(context.getResources().getColor(R.color.color_06BB85));
                }
            }
        }
    }
}
