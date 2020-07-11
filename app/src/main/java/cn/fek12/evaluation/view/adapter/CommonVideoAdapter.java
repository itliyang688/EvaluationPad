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
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;


public class CommonVideoAdapter extends RecyclerView.Adapter<CommonVideoAdapter.ExerciseNotesHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<MicrolessonVideoEntity.DataBean.RecordsBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public CommonVideoAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<MicrolessonVideoEntity.DataBean.RecordsBean> list,boolean isAdd) {
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
    public CommonVideoAdapter.ExerciseNotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.common_video_item, parent, false);
        return new ExerciseNotesHolder(view);
    }

    @Override
    public void onBindViewHolder(CommonVideoAdapter.ExerciseNotesHolder holder, final int position) {
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

    class ExerciseNotesHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tvName;


        public ExerciseNotesHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = this.itemView.findViewById(R.id.tvName);
        }

        public void setData(final int position) {
            tvName.setText(mList.get(position).getVideoName());
        }
    }
}
