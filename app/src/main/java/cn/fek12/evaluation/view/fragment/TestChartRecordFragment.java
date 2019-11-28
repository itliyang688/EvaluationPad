package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;
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
public class TestChartRecordFragment extends BaseFragment {
    @BindView(R.id.pieChartView)
    PieChartView pieChartView;
    @BindView(R.id.barChartView)
    BarChartView barChartView;
    @BindView(R.id.areaChartView)
    AreaChartView areaChartView;

    @Override
    protected int getLayoutResource() {
        return R.layout.test_chart_record_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        barChartView.chartDataSet();
        pieChartView.chartDataSet();
        areaChartView.chartDataSet();
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    protected BasePresenter onInitLogicImpl() {
        return null;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
