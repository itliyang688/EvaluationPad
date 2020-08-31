package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fek12.basic.utils.string.StringUtils;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.MicroLessonPageEnetity;
import cn.fek12.evaluation.view.widget.RoundImageView;
import pl.droidsonroids.gif.GifImageView;


public class ExaminationAdapter extends RecyclerView.Adapter<ExaminationAdapter.EvaluationHolder> {
    private OnItemClickListener mOnItemClickListener = null;

    private List<ExaminationEntity.DataBean.RecordsBean> mList = new ArrayList();

    public interface OnItemClickListener {
        void onItemButClick(int taskStatus,int position);
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
        holder.setData(position);
    }

    class EvaluationHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView tvName;
        private TextView tvData;
        private TextView tvSubject;
        private TextView tvState;
        private ImageView ivCover;
        private GifImageView gifView;
        private LinearLayout llClick0;
        private LinearLayout llClick1;
        private LinearLayout llClick2;
        private LinearLayout llClick3;
        private LinearLayout llClick4;
        private LinearLayout llClick5;
        private LinearLayout llClick6;

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
            llClick6 = this.itemView.findViewById(R.id.llClick6);
        }
        int clickTaskStatus;
        public void setData(final int position) {
            tvName.setText(mList.get(position).getTitle());
            tvState.setText(mList.get(position).getBetweenDateStr());
            tvSubject.setText(mList.get(position).getSubjectName());
            tvData.setText("发布时间："+mList.get(position).getCreateDate());
            Glide.with(MyApplication.getApp()).load(mList.get(position).getImgUrl()).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(ivCover);
            int taskStatus = mList.get(position).getTaskStatus();

            switch (taskStatus){
                case 0://去做作业
                    setViewVisibility(View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                    setOnClickListener(llClick0,0,position);
                    break;
                case 1://补交作业
                    setViewVisibility(View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE);
                    setOnClickListener(llClick1,1,position);
                    break;
                case 2://等待审阅
                    setViewVisibility(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE);
                    break;
                case 3://作业/考试完成报告生成
                    setOnClickListener(llClick2,2,position);
                    if(StringUtils.isEmpty(mList.get(position).getDrillId())){
                        setViewVisibility(View.GONE,View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE);
                    }else{
                        if(mList.get(position).getDrillStatus().equals("1")){
                            setOnClickListener(llClick3,4,position);
                        }else{
                            setOnClickListener(llClick3,3,position);
                        }
                        setViewVisibility(View.GONE,View.GONE,View.VISIBLE,View.VISIBLE,View.GONE,View.GONE,View.GONE);
                    }
                    break;
                case 4://考试未开始
                    setViewVisibility(View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE,View.GONE,View.GONE);
                    break;
                case 5://马上开始
                    setViewVisibility(View.GONE,View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE,View.GONE);
                    setOnClickListener(llClick5,5,position);
                    break;
            }
        }

        private void setOnClickListener(LinearLayout itemButView,int taskStatus,int position){
            itemButView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null){
                        mOnItemClickListener.onItemButClick(taskStatus,position);
                    }
                }
            });
        }

        private void setViewVisibility(int isShow0,int isShow1,int isShow2,int isShow3,int isShow4,int isShow5,int isShow6){
            llClick0.setVisibility(isShow0);
            llClick1.setVisibility(isShow1);
            llClick2.setVisibility(isShow2);
            llClick3.setVisibility(isShow3);
            llClick4.setVisibility(isShow4);
            llClick5.setVisibility(isShow5);
            llClick6.setVisibility(isShow6);
        }
    }
}
