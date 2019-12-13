package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;


public class ExerciseNotesAdapter extends RecyclerView.Adapter<ExerciseNotesAdapter.ExerciseNotesHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public ExerciseNotesAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List list,boolean isAdd) {
        if(isAdd){
            mList.addAll(list);
        }else{
            mList = list;
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    @Override
    public ExerciseNotesAdapter.ExerciseNotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.exercise_notes_item, parent, false);
        return new ExerciseNotesHolder(view);
    }

    @Override
    public void onBindViewHolder(ExerciseNotesAdapter.ExerciseNotesHolder holder, final int position) {
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


        public ExerciseNotesHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;

        }

        public void setData(final int position) {

        }
    }
}
