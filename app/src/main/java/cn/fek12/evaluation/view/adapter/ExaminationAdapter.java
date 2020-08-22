package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.MicroLessonPageEnetity;
import cn.fek12.evaluation.view.widget.RoundImageView;
import pl.droidsonroids.gif.GifImageView;


public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<ExaminationEntity.DataBean.RecordsBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }


    private Context context;

    public ExaminationAdapter(Context context) {
        this.context = context;
    }

    public void notifyChanged(List<ExaminationEntity.DataBean.RecordsBean> list,boolean isAdd) {
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
    public ExaminationAdapter.EvaluationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.examination_item, parent, false);
        return new EvaluationHolder(view);
    }

    @Override
    public void onBindViewHolder(ExaminationAdapter.EvaluationHolder holder, final int position) {
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
        private TextView tvData;
        private TextView tvSubject;
        private TextView tvState;
        private RoundImageView ivCover;
        private GifImageView gifView;
        private LinearLayout llClick0;
        private LinearLayout llClick1;
        private LinearLayout llClick2;
        private LinearLayout llClick3;
        private LinearLayout llClick4;
        private LinearLayout llClick5;

        public EvaluationHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            tvName = this.itemView.findViewById(R.id.tvName);
            tvSubject = this.itemView.findViewById(R.id.tvSubject);
            tvData = this.itemView.findViewById(R.id.tvData);
            tvState = this.itemView.findViewById(R.id.tvState);
            ivCover = this.itemView.findViewById(R.id.ivCover);
            gifView = this.itemView.findViewById(R.id.gifView);
            llClick0 = this.itemView.findViewById(R.id.llClick0);
            llClick1 = this.itemView.findViewById(R.id.llClick1);
            llClick2 = this.itemView.findViewById(R.id.llClick2);
            llClick3 = this.itemView.findViewById(R.id.llClick3);
            llClick4 = this.itemView.findViewById(R.id.llClick4);
            llClick5 = this.itemView.findViewById(R.id.llClick5);
        }
        int clickTaskStatus;
        public void setData(final int position) {
            tvName.setText(mList.get(position).getTitle());
            tvState.setText(mList.get(position).getBetweenDateStr());
            tvSubject.setText(mList.get(position).getSubjectName());
            tvData.setText("发布时间："+mList.get(position).getCreateDate());
            int taskStatus = mList.get(position).getTaskStatus();

            switch (taskStatus){
                case 0://去做作业
                    setViewVisibility(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                    llClick0.setOnClickListener(onClickListener);
                    break;
                case 1://补交作业
                    setViewVisibility(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                    llClick1.setOnClickListener(onClickListener);
                    break;
                case 2://等待审阅
                    setViewVisibility(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                    break;
                case 3://作业/考试完成报告生成
                    llClick2.setOnClickListener(onClickListener);
                    if(StringUtils.isEmpty(mList.get(position).getDrillId())){
                        setViewVisibility(View.GONE,View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE);
                    }else{
                        setViewVisibility(View.GONE,View.GONE,View.VISIBLE,View.VISIBLE,View.GONE,View.GONE);
                        llClick3.setOnClickListener(onClickListener);
                    }
                    break;
                case 4://考试未开始
                    setViewVisibility(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE,View.GONE);
                    break;
                case 5://马上开始
                    setViewVisibility(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    llClick5.setOnClickListener(onClickListener);
                    break;
            }
        }

        private View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.llClick0://去做作业

                        break;
                    case R.id.llClick1://补交作业

                        break;
                    case R.id.llClick2://查看结果
                        break;
                    case R.id.llClick3://强化训练
                        break;
                    case R.id.llClick5://马上考试
                        break;
                }
            }
        };

        private void setViewVisibility(int isShow0,int isShow1,int isShow2,int isShow3,int isShow4,int isShow5){
            llClick0.setVisibility(isShow0);
            llClick1.setVisibility(isShow1);
            llClick2.setVisibility(isShow2);
            llClick3.setVisibility(isShow3);
            llClick4.setVisibility(isShow4);
            llClick5.setVisibility(isShow5);
        }
    }
}
