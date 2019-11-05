package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页记录
 * @CreateDate: 2019/10/23 15:12
 */
public class RecordFragment extends BaseFragment{
    @Override
    protected int getLayoutResource() {
        return R.layout.record_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("记录",false);
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
