package cn.fek12.evaluation.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.Map;

import ayalma.ir.expandablerecyclerview.ExpandableRecyclerView;
import cn.fek12.evaluation.R;

/**
 */
public class ExerciseExpandableAdapter extends ExpandableRecyclerView.Adapter<ExerciseExpandableAdapter.ChildViewHolder, ExpandableRecyclerView.SimpleGroupViewHolder, String, String> {
    private Map<Integer,Integer> mapData = new HashMap<>();
    public ExerciseExpandableAdapter(Map<Integer,Integer> map) {
        mapData = map;
    }

    public void notifyChanged(int group,int itemCount){
        mapData.put(group,itemCount);
    }

    @Override
    public int getGroupItemCount() {
        return mapData.size() - 1;
    }

    @Override
    public int getChildItemCount(int group) {
        return mapData.get(group);
    }

    @Override
    public String getGroupItem(int position) {
        return "12月1" + position;
    }

    @Override
    public String getChildItem(int group, int position)
    {
        return "group : "+ group + " item" + position;
    }

    @Override
    protected ExpandableRecyclerView.SimpleGroupViewHolder onCreateGroupViewHolder(ViewGroup parent) {
        return new ExpandableRecyclerView.SimpleGroupViewHolder(parent.getContext());
    }

    @Override
    protected ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.exercise_notes_item, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public int getChildItemViewType(int group, int position) {
        return 1;
    }

    @Override
    public void onBindGroupViewHolder(ExpandableRecyclerView.SimpleGroupViewHolder holder, int group) {
        super.onBindGroupViewHolder(holder, group);
        holder.setText(getGroupItem(group));
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int group, final int position) {
        super.onBindChildViewHolder(holder, group, position);
        //mapData.get(group)
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llEmpty;
        private LinearLayout llContain;
        private TextView tvSubject;
        private TextView tvChapter;
        private TextView tvTime;
        private TextView tvCorrectCount;
        private RelativeLayout tbAnalysis;
        private RelativeLayout tbPractice;

        public ChildViewHolder(View itemView) {
            super(itemView);
            llEmpty = itemView.findViewById(R.id.llEmpty);
            llContain = itemView.findViewById(R.id.llContain);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvChapter = itemView.findViewById(R.id.tvChapter);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCorrectCount = itemView.findViewById(R.id.tvCorrectCount);
            tbAnalysis = itemView.findViewById(R.id.tbAnalysis);
            tbPractice = itemView.findViewById(R.id.tbPractice);
        }
    }

}
