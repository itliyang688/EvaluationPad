package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ILearningSituation;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.PlanVideoEntity;
import cn.fek12.evaluation.model.entity.PromoteRecommedVideoEntity;
import cn.fek12.evaluation.presenter.LearningSituationPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.DialogUtils;
import cn.fek12.evaluation.utils.DisUtil;
import cn.fek12.evaluation.utils.LoadViewUtils;
import cn.fek12.evaluation.view.PopupWindow.PlanVideoSubjectPopupWindow;
import cn.fek12.evaluation.view.PopupWindow.SubjectAllPopupWindow;
import cn.fek12.evaluation.view.activity.CommonVideoActivity;
import cn.fek12.evaluation.view.activity.MicrolessonVideoPlayActivity;
import cn.fek12.evaluation.view.activity.MoreTasKSetActivity;
import cn.fek12.evaluation.view.activity.PlanForTheWeekActivity;
import cn.fek12.evaluation.view.adapter.AlikeAdapter;
import cn.fek12.evaluation.view.adapter.ExaminationAdapter;
import cn.fek12.evaluation.view.adapter.PlanVideoAdapter;

public class LearningSituationFragment extends BaseFragment<LearningSituationPresenter> implements ILearningSituation.View,ExaminationAdapter.OnItemClickListener,PlanVideoAdapter.OnPlanItemClickListener,AlikeAdapter.OnItemClickListener {
    @BindView(R.id.examinationRecycler)
    RecyclerView examinationRecycler;
    @BindView(R.id.ivEmpty1)
    ImageView ivEmpty1;
    @BindView(R.id.planRecycler)
    RecyclerView planRecycler;
    @BindView(R.id.ivEmpty2)
    ImageView ivEmpty2;
    @BindView(R.id.rlMoreWork)
    RelativeLayout rlMoreWork;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.rlRefresh)
    RelativeLayout rlRefresh;
    @BindView(R.id.alikeRecycler)
    RecyclerView alikeRecycler;
    @BindView(R.id.rlContainSubject)
    RelativeLayout rlContainSubject;
    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    @BindView(R.id.tvSubject)
    TextView tvSubject;

    private ExaminationAdapter examinationAdapter;
    private PlanVideoAdapter planVideoAdapter;
    private AlikeAdapter alikeAdapter;
    private String subjectId;

    private PlanVideoSubjectPopupWindow subjectPopupWindow;
    private List<PlanVideoEntity.DataBean> planList;
    private List<ExaminationEntity.DataBean.RecordsBean> recordList;
    private List<PromoteRecommedVideoEntity.DataBean.RecordsBean> promoterVideoList;
    private int current = 1;

    @Override
    protected int getLayoutResource() {
        return R.layout.learning_situation_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        examinationAdapter = new ExaminationAdapter(getContext());
        examinationRecycler.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        examinationRecycler.setAdapter(examinationAdapter);

        planVideoAdapter = new PlanVideoAdapter(getContext());
        planVideoAdapter.setOnPlanItemClickListener(this);
        planRecycler.setLayoutManager(new GridLayoutManager(getContext(), 4){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        planRecycler.setAdapter(planVideoAdapter);

        alikeAdapter = new AlikeAdapter(getContext());
        alikeAdapter.setOnItemClickListener(this);
        alikeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        alikeRecycler.setAdapter(alikeAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.querWeekStudyPlanVideoList(getContext(), MyApplication.getMyApp().getUserId(), subjectId);
        mPresenter.getPromoteRecommedVideo(getContext(),MyApplication.getMyApp().getUserId(),String.valueOf(current),"3");
    }

    @Override
    protected void onLoadDataRemote() {
        //DialogUtils.showDialog(LoadViewUtils.getLoadingView(getContext()));
        mPresenter.queryTaskPage(getContext(),MyApplication.getMyApp().getUserId(),"1","3");
    }

    @Override
    protected LearningSituationPresenter onInitLogicImpl() {
        return new LearningSituationPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick({R.id.rlMoreWork, R.id.llSubject, R.id.rlRefresh})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlMoreWork:
                startActivity(new Intent(getContext(), MoreTasKSetActivity.class));
                break;
            case R.id.llSubject:
                subjectPopupWindow = new PlanVideoSubjectPopupWindow(getContext(), new PlanVideoSubjectPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        tvSubject.setText(subjectName);
                        subjectId = subjectId.equals("0") ? "" : subjectId;
                        /**请求本周计划*/
                        mPresenter.querWeekStudyPlanVideoList(getContext(), MyApplication.getMyApp().getUserId(), subjectId);
                    }
                });

                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow.setImageResource(R.mipmap.select_rise_icon);
                int tv_width = rlContainSubject.getWidth();//获取对应的控件view宽度px值
                int pop_width = DisUtil.dp2px(getContext(), 150);
                int width = (tv_width - pop_width) / 2;//获取x轴偏移量px
                subjectPopupWindow.showAsDropDown(rlContainSubject, width, 0);//设置x轴偏移量：注意单位为px
                subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivArrow.setImageResource(R.mipmap.select_lower_icon);
                    }
                });

                subjectPopupWindow.setFocusable(true);
                subjectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                break;
            case R.id.rlRefresh:
                current += 1;
                mPresenter.getPromoteRecommedVideo(getContext(),MyApplication.getMyApp().getUserId(),String.valueOf(current),"3");
                break;
        }
    }

    @Override
    public void loadPlanVideoSuc(PlanVideoEntity entry) {
        planList = entry.getData();
        planRecycler.setVisibility(View.VISIBLE);
        ivEmpty2.setVisibility(View.INVISIBLE);
        planVideoAdapter.notifyChanged(planList);
    }

    @Override
    public void loadPlanVideoEmpty() {
        ivEmpty2.setVisibility(View.VISIBLE);
        planRecycler.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadExaminationSuc(ExaminationEntity entry) {
        DialogUtils.removeDialog(getContext());
        recordList = entry.getData().getRecords();
        examinationRecycler.setVisibility(View.VISIBLE);
        ivEmpty1.setVisibility(View.INVISIBLE);
        examinationAdapter.notifyChanged(recordList,false);
    }

    @Override
    public void loadExaminationEmpty() {
        DialogUtils.removeDialog(getContext());
        ivEmpty1.setVisibility(View.VISIBLE);
        examinationRecycler.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadPromoteRecommedVideoSuc(PromoteRecommedVideoEntity entry) {
        promoterVideoList = entry.getData().getRecords();
        alikeAdapter.notifyChanged(promoterVideoList);
    }

    @Override
    public void loadPromoteRecommedVideoEmpty() {
    }

    @Override
    public void onPlanItemClick(int position) {
        Intent intent = new Intent(getContext(), PlanForTheWeekActivity.class);
        intent.putExtra("imgUrl",planList.get(position).getImgUrl());
        intent.putExtra("videoId",planList.get(position).getVideoId());
        intent.putExtra("videName",planList.get(position).getVideoName());
        intent.putExtra("videoUrl",planList.get(position).getVideoUrl());
        intent.putExtra("playScheduleTime",planList.get(position).getPlayScheduleTime());
        intent.putExtra("isCollection",planList.get(position).getIsCollection());
        getContext().startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), MicrolessonVideoPlayActivity.class);
        intent.putExtra("pathUrl",promoterVideoList.get(position).getVideoUrl());
        intent.putExtra("videoName",promoterVideoList.get(position).getVideoName());
        intent.putExtra("videoId",promoterVideoList.get(position).getVideoId());
        intent.putExtra("imgUrl",promoterVideoList.get(position).getImgUrl());
        intent.putExtra("playScheduleTime",promoterVideoList.get(position).getPlayScheduleTime());
        intent.putExtra("isCollection",promoterVideoList.get(position).getIsCollection());
        getContext().startActivity(intent);
    }

    @Override
    public void onItemButClick(int taskStatus, int position) {
        switch (taskStatus){
            case 0://去做作业
                ToastUtils.popUpToast("taskStatus"+taskStatus+"position"+position);
                break;
            case 1://补交作业

                break;
            case 2://查看结果

                break;
            case 3://强化训练

                break;

            case 5://马上开始
                break;
        }
    }
}
