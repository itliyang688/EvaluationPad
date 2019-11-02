package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;
import com.google.gson.Gson;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.presenter.EvaluationPresenter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页测评页面
 * @CreateDate: 2019/10/23 15:12
 */
public class PresentationFragment extends BaseFragment<EvaluationPresenter> implements IEvaluation.View {
    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("报告",false);
    }

    @Override
    protected void onLoadDataRemote() {
    }

    @Override
    protected EvaluationPresenter onInitLogicImpl() {
        return new EvaluationPresenter(this,getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void loginSuc(HomeEvaluationDeta entry) {

    }

    @Override
    public void loginFail(String msg) {
    }

    @Override
    public void showBaner() {

    }
}
