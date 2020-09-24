package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationContainerFragment
 * @Description:
 * @CreateDate: 2019/10/25 9:29
 */
public class EvaluationContainerFragment extends BaseFragment implements EvaluationListFragment.OnBackPressedClickListener, EvaluationFragment.OnExaminationClickListener {
    private Fragment fragmentAll;
    private EvaluationFragment evaluationFragment;
    private EvaluationListFragment evaluationListFragment;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_container;
    }
    private static EvaluationContainerFragment containerFragment;

    public static EvaluationContainerFragment get() {
        return containerFragment != null ? containerFragment : null;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        containerFragment = this;
        fragmentAll = getChildFragmentManager().findFragmentById(R.id.fragment_container);
        showEvaluationFragment();
    }

    public void showEvaluationFragment() {
        if (!(fragmentAll instanceof EvaluationFragment)) {
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            //如果所有的fragment都不为空的话，把所有的fragment都进行隐藏。最开始进入应用程序，fragment为空时，此方法不执行
            if(evaluationListFragment != null){
                fragmentTransaction.setCustomAnimations(0,R.anim.slide_right_out);
                fragmentTransaction.hide(evaluationListFragment);
            }
            if(evaluationFragment == null){
                evaluationFragment = new EvaluationFragment();
                evaluationFragment.setOnClickListener(this);
                fragmentTransaction.add(R.id.fragment_container, evaluationFragment);
            }else {
                fragmentTransaction.setCustomAnimations(R.anim.slide_left_in,0);
                fragmentTransaction.show(evaluationFragment);
            }

            backFragmentInterface.onSelectedFragment(evaluationFragment);
            fragmentTransaction.commit();
        }
    }

    public void showEvaluationListFragment() {
        if (!(fragmentAll instanceof EvaluationListFragment)) {
            fragmentTransaction = getChildFragmentManager().beginTransaction();
            if(evaluationFragment != null){
                fragmentTransaction.setCustomAnimations(0,R.anim.slide_left_out);
                fragmentTransaction.hide(evaluationFragment);
            }
            fragmentTransaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_right_out);
            if(evaluationListFragment == null){
                evaluationListFragment = new EvaluationListFragment();
                evaluationListFragment.setOnBackPressedClickListener(this);
                fragmentTransaction.add(R.id.fragment_container, evaluationListFragment);
            }else {
                fragmentTransaction.show(evaluationListFragment);
            }
            fragmentTransaction.commit();
        }
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
        if(evaluationListFragment == null){
            return false;
        }else{
            onBackPressedEvaluationListFragment();
            return true;
        }
    }

    @Override
    public void onBackPressedEvaluationListFragment() {
        showEvaluationFragment();
        if(evaluationListFragment != null){
            //evaluationListFragment.onDestroy();
            //fragmentTransaction.remove(evaluationListFragment);
            evaluationListFragment = null;
        }
    }

    @Override
    public void onExaminationClick() {
        showEvaluationListFragment();
    }
}
