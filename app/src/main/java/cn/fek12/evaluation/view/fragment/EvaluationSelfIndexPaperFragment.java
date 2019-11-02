package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationSelfIndexPaperFragment
 * @Description:
 * @CreateDate: 2019/10/29 11:07
 */
public class EvaluationSelfIndexPaperFragment extends BaseFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_fragment_self;
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
