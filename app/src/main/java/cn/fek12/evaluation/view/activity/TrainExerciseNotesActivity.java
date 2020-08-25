package cn.fek12.evaluation.view.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BaseActivity;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ITrainExerciseNoters;
import cn.fek12.evaluation.model.entity.YearMonthEntity;
import cn.fek12.evaluation.presenter.TrainExerciseNotesPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.fragment.TrainExerciseNotesFragment;
import cn.fek12.evaluation.view.widget.CustomViewPager;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: ExerciseNotesActivity
 * @Description:
 * @CreateDate: 2019/12/9 9:43
 */
public class TrainExerciseNotesActivity extends BaseActivity<TrainExerciseNotesPresenter> implements ITrainExerciseNoters.View {

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout slidingTabLayout;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.rlLeftBack)
    RelativeLayout rlLeftBack;
    private MyPagerAdapter mAdapter;
    private String structId;
   /* private final String[] mTitles = {
            "近一周", "近一月", "2019.11"
            , "2019.10", "2019.09", "2019.08", "2019.07","2019.06", "2019.05", "2019.04"
            , "2019.03", "2019.02", "2019.01"
    };*/

    private List<YearMonthEntity.DataBean> mList = new ArrayList<>();

    @Override
    public int setLayoutResource() {
        return R.layout.train_exercise_notes_activity;
    }

    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    protected void onInitView() {
        rootView.setPadding(0, AppUtils.getStatusBarHeight(TrainExerciseNotesActivity.this), 10, 0);
        structId = getIntent().getStringExtra("structId");
    }

    @Override
    protected void onLoadData() {
        loadView.showLoading();
        mPresenter.getYears(getContext(), MyApplication.getMyApp().getUserId());
    }

    @Override
    public void loadSuc(YearMonthEntity entity) {
        if (entity != null && entity.getData() != null && entity.getData().size() > 0) {
            loadView.showContent();
            mList = entity.getData();

            mAdapter = new MyPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(mAdapter);
            viewPager.setOffscreenPageLimit(mList.size());

            slidingTabLayout.setViewPager(viewPager);
        } else {
            loadView.showEmpty();
        }
    }

    @Override
    public void loadEmpty() {
        loadView.showEmpty();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.rlLeftBack)
    public void onViewClicked() {
        TrainExerciseNotesActivity.this.finish();
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getMonth();
        }

        @Override
        public Fragment getItem(int position) {
            return new TrainExerciseNotesFragment(mList.get(position).getDays(), structId);
        }
    }

    @Override
    protected TrainExerciseNotesPresenter onInitLogicImpl() {
        return new TrainExerciseNotesPresenter(this);
    }
}
