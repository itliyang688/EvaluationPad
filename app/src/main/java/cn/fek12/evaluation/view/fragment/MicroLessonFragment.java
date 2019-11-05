package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonFragment
 * @Description:
 * @CreateDate: 2019/11/4 16:12
 */
public class MicroLessonFragment extends BaseFragment {
    @Override
    protected int getLayoutResource() {
        return R.layout.microlesson_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("微课",false);
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
