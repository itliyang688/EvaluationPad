package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fek12.basic.base.BaseFragment;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ITestCharRecord;
import cn.fek12.evaluation.model.entity.TestRecordEntity;
import cn.fek12.evaluation.presenter.TestCharRecordPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.PopupWindow.SubjectPopupWindow;
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
    private SubjectPopupWindow subjectPopupWindow;
    private String mSubjectId = "0";

    @Override
    protected int getLayoutResource() {
        return R.layout.test_chart_record_fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && pieChartView != null) {
            mPresenter.requestData(getContext(), mSubjectId, MyApplication.getMyApplication().getUserId());
        }
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onLoadDataRemote() {
        mPresenter.requestData(getContext(), mSubjectId, MyApplication.getMyApplication().getUserId());
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
                startActivity(new Intent(getContext(), ExerciseNotesActivity.class));
                break;
            case R.id.llRanking:
                startActivity(new Intent(getContext(), RankingListActivity.class));
                break;
            case R.id.llSubject:
                subjectPopupWindow = new SubjectPopupWindow(getContext(), new SubjectPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        tvSubject.setText(subjectName);
                        mSubjectId = subjectId;
                        mPresenter.requestData(getContext(), mSubjectId, MyApplication.getMyApplication().getUserId());
                    }
                });
                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                subjectPopupWindow.showAsDropDown(llSubject, -155, 0);
                break;
        }
    }

    @Override
    public void loadSuc(TestRecordEntity entity) {
        TestRecordEntity.DataBean.CorrectRateVoBean correctRateVoBean = entity.getData().getCorrectRateVo();
        if (correctRateVoBean != null && !TextUtils.isEmpty(String.valueOf(correctRateVoBean.getRate()))
                && !TextUtils.isEmpty(String.valueOf(correctRateVoBean.getTotalCount())) && correctRateVoBean.getTotalCount() > 0) {
            tvEmpty.setVisibility(View.INVISIBLE);
            pieChartView.chartDataSet(correctRateVoBean.getRate());
            pieChartView.invalidate();
            tvCorrectCount.setText("答对题数：" + correctRateVoBean.getRightTotalCount() + " 道题");
            tvTotal.setText("总答题数：" + correctRateVoBean.getTotalCount() + " 道题");
            tvTimeDuration.setText("总练习时长：" + correctRateVoBean.getTotalTime());
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

    }

    @Override
    public void loadEmpty() {

    }
}
