package cn.fek12.evaluation.view.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BaseActivity;
import com.flyco.tablayout.SlidingTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.fragment.ExerciseNotesFragment;
import cn.fek12.evaluation.view.widget.CustomViewPager;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: ExerciseNotesActivity
 * @Description:
 * @CreateDate: 2019/12/9 9:43
 */
public class ExerciseNotesActivity extends BaseActivity {

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private MyPagerAdapter mAdapter;
    private final String[] mTitles = {
            "近一周", "近一月", "2019.11"
            , "2019.10", "2019.09", "2019.08", "2019.07","2019.06", "2019.05", "2019.04"
            , "2019.03", "2019.02", "2019.01"
    };

    @Override
    public int setLayoutResource() {
        return R.layout.exercise_notes_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle();

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mAdapter);
        viewPager.setOffscreenPageLimit(mTitles.length);

        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    protected void onLoadData() {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return new ExerciseNotesFragment(position);
        }
    }
}
