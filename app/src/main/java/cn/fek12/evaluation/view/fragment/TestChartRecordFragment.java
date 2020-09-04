package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fek12.basic.base.BaseFragment;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.model.entity.RecordInfoEntity;
import cn.fek12.evaluation.model.entity.TestRecordEntity;
import cn.fek12.evaluation.presenter.TestCharRecordPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.PopupWindow.SubjectAllPopupWindow;
import cn.fek12.evaluation.view.activity.CommonWebViewActivity;
import cn.fek12.evaluation.view.activity.ExerciseNotesActivity;
import cn.fek12.evaluation.view.activity.RankingListActivity;
import cn.fek12.evaluation.view.widget.AreaChartView;
import cn.fek12.evaluation.view.widget.BarChartView;
import cn.fek12.evaluation.view.widget.PieChartView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: TestChartRecordFragment
 * @Description:
 * @CreateDate: 2019/11/24 17:52
 */
public class TestChartRecordFragment extends BaseFragment<TestCharRecordPresenter> implements TestCharRecordPresenter.View {
    @BindView(R.id.pieChartView)
    PieChartView pieChartView;
    @BindView(R.id.barChartView)
    BarChartView barChartView;
    @BindView(R.id.areaChartView)
    AreaChartView areaChartView;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.llRanking)
    LinearLayout llRanking;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.tvCorrectCount)
    TextView tvCorrectCount;
    @BindView(R.id.tvTotal)
    TextView tvTotal;
    @BindView(R.id.tvTimeDuration)
    TextView tvTimeDuration;
    @BindView(R.id.tvSubject)
    TextView tvSubject;
    @BindView(R.id.llTestRecord)
    LinearLayout llTestRecord;
    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    @BindView(R.id.llContainSubject)
    LinearLayout llContainSubject;
    @BindView(R.id.tvEmptyList)
    TextView tvEmptyList;
    private SubjectAllPopupWindow subjectPopupWindow;
    private String mSubjectId = "0";

    @Override
    protected int getLayoutResource() {
        return R.layout.test_chart_record_fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && pieChartView != null) {
            mPresenter.requestData(getContext(), mSubjectId, MyApplication.getMyApp().getUserId());
        }
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onLoadDataRemote() {
        mPresenter.requestData(getContext(), mSubjectId, MyApplication.getMyApp().getUserId());
    }

    @Override
    protected TestCharRecordPresenter onInitLogicImpl() {
        return new TestCharRecordPresenter(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @OnClick({R.id.llRanking, R.id.llSubject, R.id.llRecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llRecord:
                Intent intent = new Intent(getContext(), ExerciseNotesActivity.class);
                intent.putExtra("structId", mSubjectId);
                startActivity(intent);
                break;
            case R.id.llRanking:
                startActivity(new Intent(getContext(), RankingListActivity.class));
                break;
            case R.id.llSubject:
                subjectPopupWindow = new SubjectAllPopupWindow(getContext(), new SubjectAllPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        tvSubject.setText(subjectName);
                        mSubjectId = subjectId;
                        mPresenter.requestData(getContext(), mSubjectId, MyApplication.getMyApp().getUserId());
                    }
                },0);
                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow.setImageResource(R.mipmap.rise_icon);
                subjectPopupWindow.showAsDropDown(llContainSubject, -90, 0);
                //subjectPopupWindow.showAtLocation(llContainSubject, Gravity.CENTER,0,0);
                subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivArrow.setImageResource(R.mipmap.lower_icon);
                    }
                });
                break;
        }
    }

    @Override
    public void loadSuc(TestRecordEntity entity) {
        TestRecordEntity.DataBean.CorrectRateVoBean correctRateVoBean = entity.getData().getCorrectRateVo();
        if (entity.getData() != null && correctRateVoBean != null && !TextUtils.isEmpty(String.valueOf(correctRateVoBean.getRate()))
                && !TextUtils.isEmpty(String.valueOf(correctRateVoBean.getTotalCount())) && correctRateVoBean.getTotalCount() > 0) {
            tvEmpty.setVisibility(View.INVISIBLE);
            pieChartView.chartDataSet(correctRateVoBean.getRate());
            pieChartView.refreshChart();
            tvCorrectCount.setText(Html.fromHtml("答对题数 : <font color='#878AE7'>"+ correctRateVoBean.getRightTotalCount() +"</font>" + " 道题"));
            tvTotal.setText(Html.fromHtml("总答题数 : <font color='#4FC5EA'>"+ correctRateVoBean.getTotalCount() +"</font>" + " 道题"));
            tvTimeDuration.setText(Html.fromHtml("总练习时长 : <font color='#F86846'>"+ correctRateVoBean.getTotalTime() +"</font>" + " 分钟"));
        } else {
            tvEmpty.setVisibility(View.VISIBLE);
            tvCorrectCount.setText("答对题数：0 道题");
            tvTotal.setText("总答题数：0 道题");
            tvTimeDuration.setText("总练习时长：0 分");
        }

        /**近四周答题数*/
        List<Double> dataSeriesA = new LinkedList<Double>();
        List<String> chartLabels = new LinkedList<String>();
        String answerStr = entity.getData().getAnswers();
        String answers[] = answerStr.split(";");
        for (String answer : answers) {
            String data[] = answer.split(":");
            chartLabels.add(data[0]);
            dataSeriesA.add(Double.parseDouble(data[1]));
        }
        chartLabels.set(0, "本周");
        barChartView.chartDataSet(dataSeriesA, chartLabels);
        barChartView.invalidate();

        /**近四周正确率*/
        List<Double> dataSeries3 = new LinkedList<Double>();
        LinkedList<String> mLabels = new LinkedList<String>();
        String scopeCorrectStr = entity.getData().getScopeCorrectRate();
        String scopeCorrects[] = scopeCorrectStr.split(";");
        for (String scopeCorrect : scopeCorrects) {
            String data[] = scopeCorrect.split(":");
            mLabels.add(data[0]);
            dataSeries3.add(Double.parseDouble(data[1]));
        }
        mLabels.set(0, "本周");
        areaChartView.chartDataSet(dataSeries3, mLabels);
        areaChartView.invalidate();

        /**li练习记录*/
        String json = new Gson().toJson(entity.getData().getMore());
        if (entity.getData().getMore() != null && entity.getData().getMore().size() > 0) {
            tvEmptyList.setVisibility(View.GONE);
            llTestRecord.removeAllViews();

            String timeTag = "";
            for (int i = 0; i < entity.getData().getMore().size(); i++) {
                RecordInfoEntity bean = entity.getData().getMore().get(i);
                if (!bean.getModifyData().equals(timeTag)) {
                    timeTag = bean.getModifyData();
                    View viewGroup = LayoutInflater.from(mContext).inflate(R.layout.record_group_item, null);
                    TextView tvGroupName = viewGroup.findViewById(R.id.tvGroupName);
                    tvGroupName.setText(timeTag);
                    llTestRecord.addView(viewGroup);
                }

                View viewItem = LayoutInflater.from(mContext).inflate(R.layout.exercise_notes_item2, null);
                TextView tvSubject = viewItem.findViewById(R.id.tvSubject);
                TextView tvChapter = viewItem.findViewById(R.id.tvChapter);
                TextView tvTime = viewItem.findViewById(R.id.tvTime);
                TextView tvCorrectCount = viewItem.findViewById(R.id.tvCorrectCount);
                RelativeLayout tbAnalysis = viewItem.findViewById(R.id.tbAnalysis);
                RelativeLayout tbPractice = viewItem.findViewById(R.id.tbPractice);

                tvSubject.setText(bean.getSubject() + "  " + bean.getGrade() + "  " + bean.getTextBook());
                tvChapter.setText(bean.getKnowledgePoint());
                tvTime.setText("用时：" + bean.getTime() + "分钟");
                tvCorrectCount.setText("答对数：" + bean.getRightAmount() + "/" + bean.getCount());
                /**查看解析*/
                tbAnalysis.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = Configs.SMALL + "userId=" + MyApplication.getMyApp().getUserId() + "&subjectCategoryId=" + bean.getSubjectCategoryId() + "&practiceId=" + bean.getPracticeId();
                        Intent intent = new Intent(getContext(), CommonWebViewActivity.class);
                        intent.putExtra("webUrl", url);
                        startActivity(intent);
                    }
                });
                /**继续练习*/
                tbPractice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = Configs.SMALLWORK + "userId=" + MyApplication.getMyApp().getUserId() + "&subjectCategoryId=" + bean.getSubjectCategoryId();
                        Intent intent = new Intent(getContext(), CommonWebViewActivity.class);
                        intent.putExtra("webUrl", url);
                        startActivity(intent);
                    }
                });
                llTestRecord.addView(viewItem);
            }
        }else{
            tvEmptyList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadEmpty() {

    }
}
