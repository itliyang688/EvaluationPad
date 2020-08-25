package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ayalma.ir.expandablerecyclerview.TrainExpandableRecyclerView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.model.entity.ExrGroupListEntity;
import cn.fek12.evaluation.model.entity.RecordInfoEntity;
import cn.fek12.evaluation.model.entity.TrainEntity;
import cn.fek12.evaluation.model.entity.TrainExrGroupListEntity;
import cn.fek12.evaluation.model.entity.TrainListEntity;
import cn.fek12.evaluation.view.activity.CommonWebViewActivity;

/**
 */
public class TrainExerciseExpandableAdapter extends TrainExpandableRecyclerView.Adapter<TrainExerciseExpandableAdapter.ChildViewHolder, TrainExpandableRecyclerView.SimpleGroupViewHolder, String, String> {
    private List<TrainExrGroupListEntity> mList = new ArrayList<>();
    private String excitation = "";
    private Context mContext;
    public TrainExerciseExpandableAdapter(List<TrainExrGroupListEntity> list, Context mContext) {
        this.mList = list;
        this.mContext = mContext;
    }

    public void notifyChanged(int group, List<TrainEntity> itemCount,String excitation){
        mList.get(group).setData(itemCount);
        this.excitation = excitation;
    }

    @Override
    public int getGroupItemCount() {
        return mList.size() - 1;
    }

    @Override
    public int getChildItemCount(int group) {
        if(mList.get(group) != null && mList.get(group).getData() != null && mList.get(group).getData().size() > 0){
            return mList.get(group).getData().size();
        }
        return 1;
    }

    @Override
    public String getGroupItem(int position) {
        return mList.get(position).getGroup();
    }

    @Override
    public String getChildItem(int group, int position){
        return "";
    }

    @Override
    protected TrainExpandableRecyclerView.SimpleGroupViewHolder onCreateGroupViewHolder(ViewGroup parent) {
        return new TrainExpandableRecyclerView.SimpleGroupViewHolder(parent.getContext());
    }

    @Override
    protected ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.train_exercise_notes_item, parent, false);
        return new ChildViewHolder(view);
    }

    @Override
    public int getChildItemViewType(int group, int position) {
        return 1;
    }

    @Override
    public void onBindGroupViewHolder(TrainExpandableRecyclerView.SimpleGroupViewHolder holder, int group) {
        super.onBindGroupViewHolder(holder, group);
        String groups[] = getGroupItem(group).split("\\.");
        String groupName = groups[1]+"月" + groups[2] + "日";
        holder.setText(groupName);
    }

    @Override
    public void onBindChildViewHolder(ChildViewHolder holder, int group, final int position) {
        super.onBindChildViewHolder(holder, group, position);
        //mapData.get(group)
        List<TrainEntity> list = mList.get(group).getData();
        if(list != null && list.size() > 0){
            holder.llEmpty.setVisibility(View.GONE);
            holder.llContain.setVisibility(View.VISIBLE);
            holder.tvSubject.setText(list.get(position).getSubject()+"  强化训练");
            holder.tvChapter.setText(list.get(position).getDrillName());
            holder.tvTime.setText("用时："+list.get(position).getDateStr());
            holder.tvCorrectCount.setText("答对数："+list.get(position).getRightAmount()+"/"+list.get(position).getCount());
            /**查看解析*/
            holder.tbAnalysis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*String url = Configs.SMALL + "userId=" + MyApplication.getMyApp().getUserId() + "&subjectCategoryId=" + list.get(position).getSubjectCategoryId() + "&practiceId=" + list.get(position).getPracticeId();
                    Intent intent = new Intent(mContext, CommonWebViewActivity.class);
                    intent.putExtra("webUrl",url);
                    mContext.startActivity(intent);*/
                }
            });
        }else{
            holder.llEmpty.setVisibility(View.VISIBLE);
            holder.llContain.setVisibility(View.GONE);
            holder.tvEncourage.setText(excitation);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout llEmpty;
        private LinearLayout llContain;
        private TextView tvSubject;
        private TextView tvChapter;
        private TextView tvTime;
        private TextView tvCorrectCount;
        private TextView tvEncourage;
        private RelativeLayout tbAnalysis;

        public ChildViewHolder(View itemView) {
            super(itemView);
            llEmpty = itemView.findViewById(R.id.llEmpty);
            llContain = itemView.findViewById(R.id.llContain);
            tvSubject = itemView.findViewById(R.id.tvSubject);
            tvChapter = itemView.findViewById(R.id.tvChapter);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvCorrectCount = itemView.findViewById(R.id.tvCorrectCount);
            tbAnalysis = itemView.findViewById(R.id.tbAnalysis);
            tvEncourage = itemView.findViewById(R.id.tvEncourage);
        }
    }

}
