package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.widget.NoScrollViewPager;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: ConquerFootprintsFragment
 * @Description:
 * @CreateDate: 2019/11/21 11:17
 */
public class ConquerFootprintsFragment extends BaseFragment {
    @BindView(R.id.view_pager)
    NoScrollViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<>();
    private MyPagerAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.conquer_footprints_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

        fragmentList.add(new CurriculumRecordFragment(1));
        fragmentList.add(new CurriculumRecordFragment(2));

    }

    public void selectTab(int pos){
        viewPager.setCurrentItem(pos, false);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && adapter == null) {
            initAdapter();
        }
    }

    private void initAdapter() {
        adapter = new MyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
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
        return false;
    }
}
