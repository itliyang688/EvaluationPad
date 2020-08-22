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

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ILearningSituation;
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.model.entity.ExaminationEntity;
import cn.fek12.evaluation.model.entity.PlanVideoEntity;
import cn.fek12.evaluation.presenter.LearningSituationPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.DialogUtils;
import cn.fek12.evaluation.utils.DisUtil;
import cn.fek12.evaluation.utils.LoadViewUtils;
import cn.fek12.evaluation.view.PopupWindow.SubjectAllPopupWindow;
import cn.fek12.evaluation.view.activity.MainActivity;
import cn.fek12.evaluation.view.activity.MoreTasKSetActivity;
import cn.fek12.evaluation.view.activity.PlanForTheWeekActivity;
import cn.fek12.evaluation.view.adapter.AlikeAdapter;
import cn.fek12.evaluation.view.adapter.ExaminationAdapter;
import cn.fek12.evaluation.view.adapter.PlanVideoAdapter;

public class LearningSituationFragment extends BaseFragment<LearningSituationPresenter> implements ILearningSituation.View,PlanVideoAdapter.OnPlanItemClickListener {
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

    private SubjectAllPopupWindow subjectPopupWindow;
    private List<PlanVideoEntity.DataBean> planList;
    private List<ExaminationEntity.DataBean.RecordsBean> recordList;

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
        alikeRecycler.setLayoutManager(new GridLayoutManager(getContext(), 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        alikeRecycler.setAdapter(alikeAdapter);

    }

    @Override
    protected void onLoadDataRemote() {
        DialogUtils.showDialog(LoadViewUtils.getLoadingView(getContext()));
        mPresenter.queryTaskPage(getContext(),MyApplication.getMyApp().getUserId(),"1","3");
        mPresenter.querWeekStudyPlanVideoList(getContext(), MyApplication.getMyApp().getUserId(), subjectId);
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
                subjectPopupWindow = new SubjectAllPopupWindow(getContext(), new SubjectAllPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        tvSubject.setText(subjectName);
                        subjectId = subjectId.equals("0") ? "" : subjectId;
                        /**请求本周计划*/
                        mPresenter.querWeekStudyPlanVideoList(getContext(), MyApplication.getMyApp().getUserId(), subjectId);
                    }
                });

                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow.setImageResource(R.mipmap.rise_icon);
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
    public void onPlanItemClick(int position) {
        Intent intent = new Intent(getContext(), PlanForTheWeekActivity.class);
        getContext().startActivity(intent);
    }
}
