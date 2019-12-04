package cn.fek12.evaluation.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;


public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.RankingHolder> {
    private OnItemClickListener mOnItemClickListener = null;
    private int type;

    private List mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public RankingListAdapter(Context context,int type) {
        this.context = context;
        this.type = type;
    }

    public void notifyChanged(List list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RankingListAdapter.RankingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ranking_list_item, parent, false);
        return new RankingHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(RankingListAdapter.RankingHolder holder, final int position) {
        holder.ivChampion.setVisibility(View.VISIBLE);
        if(position == 0){
            holder.ivChampion.setImageResource(R.mipmap.champion_1);
            holder.rlRanking.setBackgroundResource(R.drawable.circular_bg_shape1);
        }else if(position == 1){
            holder.ivChampion.setImageResource(R.mipmap.champion_2);
            holder.rlRanking.setBackgroundResource(R.drawable.circular_bg_shape2);
        }else if(position == 2){
            holder.ivChampion.setImageResource(R.mipmap.champion_3);
            holder.rlRanking.setBackgroundResource(R.drawable.circular_bg_shape3);
        }else{
            holder.rlRanking.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.ivChampion.setVisibility(View.INVISIBLE);
        }
        holder.setData(position);
    }

    class RankingHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tvDescribe;
        private TextView tvCount;
        private ImageView ivChampion;
        private RelativeLayout rlRanking;



        public RankingHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvDescribe = itemView.findViewById(R.id.tvDescribe);
            tvCount = itemView.findViewById(R.id.tvCount);
            ivChampion = itemView.findViewById(R.id.ivChampion);
            rlRanking = itemView.findViewById(R.id.rlRanking);
        }

        public void setData(final int position) {
            if(type == 0){
                tvDescribe.setText("本周正确率");
                tvCount.setText("80%");
            }else{
                tvDescribe.setText("本周答题数");
                tvCount.setText("4000");
            }
        }
    }
}
