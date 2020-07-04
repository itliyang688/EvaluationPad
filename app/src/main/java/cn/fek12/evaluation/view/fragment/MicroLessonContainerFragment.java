package cn.fek12.evaluation.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.widget.NoScrollViewPager;

public class MicroLessonContainerFragment extends BaseFragment {
    public static MicroLessonContainerFragment microLessonContainerFragment;
    @BindView(R.id.viewPage)
    NoScrollViewPager viewPage;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivTitleIcon)
    ImageView ivTitleIcon;
    @BindView(R.id.rightButton)
    RelativeLayout rightButton;
    @BindView(R.id.tvSelect1)
    TextView tvSelect1;
    @BindView(R.id.tvSelect2)
    TextView tvSelect2;
    @BindView(R.id.llSelectFrame)
    LinearLayout llSelectFrame;
    @BindView(R.id.container)
    ConstraintLayout container;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int pageType = 0;
    private boolean isSelectOpend = false;

    @Override
    protected int getLayoutResource() {
        return R.layout.microlesson_container;
    }

    public static MicroLessonContainerFragment getFragment(){
        return microLessonContainerFragment == null? null : microLessonContainerFragment;
    }
    @Override
    protected void onInitView(Bundle savedInstanceState) {
        microLessonContainerFragment = this;
        mFragments.add(new PrimarySchoolVideoFragment());
        mFragments.add(new MiddleSchoolVideoFragment());
        viewPage.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        viewPage.setOffscreenPageLimit(2);
        viewPage.setCurrentItem(0);
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

    @OnClick({R.id.tvSelect1, R.id.tvSelect2, R.id.rightButton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSelect1:
                colseFrame();
                pageType = 0;
                viewPage.setCurrentItem(0);
                tvTitle.setText("小学");
                break;
            case R.id.tvSelect2:
                colseFrame();
                pageType = 1;
                viewPage.setCurrentItem(1);
                tvTitle.setText("初中");
                break;
            case R.id.rightButton:
                if (isSelectOpend) {
                    colseFrame();
                } else {
                    if (pageType == 0) {
                        tvSelect2.setTextColor(Color.parseColor("#333333"));
                        tvSelect1.setTextColor(Color.parseColor("#FEAC2C"));
                    } else {
                        tvSelect2.setTextColor(Color.parseColor("#FEAC2C"));
                        tvSelect1.setTextColor(Color.parseColor("#333333"));
                    }
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.push_in);
                    //设置动画结束之后是否保持动画的目标状态
                    animation.setFillAfter(true);
                    llSelectFrame.startAnimation(animation);
                    isSelectOpend = true;
                }
                break;
        }
    }

    public RelativeLayout getRightView(){
        return rightButton;
    }


    public void colseFrame() {
        if(!isSelectOpend){
            return;
        }
        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.push_out);
        //设置动画结束之后是否保持动画的目标状态
        animation.setFillAfter(false);
        llSelectFrame.startAnimation(animation);
        isSelectOpend = false;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }


        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}
