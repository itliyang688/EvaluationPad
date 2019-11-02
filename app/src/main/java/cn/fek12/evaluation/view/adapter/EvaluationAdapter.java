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


public class EvaluationAdapter extends RecyclerView.Adapter<EvaluationAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public EvaluationAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List list) {
        this.mList.clear();
        this.mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 18;
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


        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = this.itemView.findViewById(R.id.title);
            time = this.itemView.findViewById(R.id.time);
        }

        public void setData(final int position) {
            title.setText("数学  |  小学数学人教版四年级上亿以内数的计数单位、数级、数位随堂测评");
            time.setText("2019-09-17");
        }
    }
}
