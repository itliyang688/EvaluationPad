package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import com.fek12.basic.utils.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.utils.DialogUtils;
import cn.fek12.evaluation.view.widget.CustomViewPager;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页记录
 * @CreateDate: 2019/10/23 15:12
 */
public class RecordFragment extends BaseFragment {
    @BindView(R.id.tabRecord)
    RadioButton tabRecord;
    @BindView(R.id.tabCollection)
    RadioButton tabCollection;
    @BindView(R.id.tabFootprint)
    RadioButton tabFootprint;
    @BindView(R.id.tabMicroLesson)
    RadioButton tabMicroLesson;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.llLeft)
    LinearLayout llLeft;
    @BindView(R.id.view_pager)
    CustomViewPager mViewPager;
    private MyPagerAdapter adapter;
    private boolean mIsVisibleToUser;
    private int previousPos = 0;
    private List<RadioButton> radioButtonList = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.record_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("记录", false);
        initView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisibleToUser = isVisibleToUser;
        if (mIsVisibleToUser && adapter == null) {
            initAdapter();
        }
    }

    @Override
    protected void onLoadDataRemote() {

    }

    private void initAdapter() {
        adapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);
        rgGroup.check(R.id.tabRecord);// 默认勾选首页
        // 监听RadioGroup的选择事件
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tabRecord:
                        enlargeAndreduction(tabRecord, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 0;
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.tabMicroLesson:
                        enlargeAndreduction(tabMicroLesson, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 1;
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.tabFootprint:
                        enlargeAndreduction(tabFootprint, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 2;
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.tabCollection:
                        enlargeAndreduction(tabCollection, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 3;
                        mViewPager.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initView() {
        radioButtonList.add(tabRecord);
        radioButtonList.add(tabMicroLesson);
        radioButtonList.add(tabFootprint);
        radioButtonList.add(tabCollection);
        enlargeAndreduction(tabMicroLesson, false);
        enlargeAndreduction(tabFootprint, false);
        enlargeAndreduction(tabCollection, false);
        fragmentList.add(new TopicWrongRecordPageFragment());
        fragmentList.add(new MicroLessonRecordFragment(1));
        fragmentList.add(new ConquerFootprintsFragment());
        fragmentList.add(new MicroLessonRecordFragment(2));
    }

    private void enlargeAndreduction(RadioButton radioButton, boolean isEnlarge) {
        Animation animation = null;
        if (isEnlarge) {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_menu_enlarge);
        } else {
            animation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_menu_reduction);
        }
        animation.setFillAfter(true);
        radioButton.startAnimation(animation);
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
    protected BasePresenter onInitLogicImpl() {
        return null;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
