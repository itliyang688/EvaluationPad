package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: TestChartRecordFragment
 * @Description:
 * @CreateDate: 2019/11/24 17:52
 */
public class TestChartRecordFragment extends BaseFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.test_chart_record_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

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
