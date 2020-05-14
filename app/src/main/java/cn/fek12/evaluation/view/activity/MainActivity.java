package cn.fek12.evaluation.view.activity;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.fek12.basic.base.BackFragmentInterface;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.TabEntity;
import cn.fek12.evaluation.view.fragment.EvaluationContainerFragment;
import cn.fek12.evaluation.view.fragment.EvaluationFragment;
import cn.fek12.evaluation.view.fragment.MicroLessonFragment;
import cn.fek12.evaluation.view.fragment.PresentationNewsFragment;
import cn.fek12.evaluation.view.fragment.PromoteNewsFragment;
import cn.fek12.evaluation.view.fragment.RecordFragment;

public class MainActivity extends BaseActivity implements BackFragmentInterface {
    private BaseFragment baseFragment;
    private String[] mTitles = {"测评", "报告", "微课", "提升", "记录"};
    @BindView(R.id.viewPage)
    ViewPager viewPage;
    @BindView(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.ic_bottom_evaluation_normal, R.mipmap.ic_bottom_report_normal,
            R.mipmap.ic_bottom_micro_class_normal, R.mipmap.ic_bottom_advance_normal, R.mipmap.ic_bottom_record_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_bottom_evaluation_checked, R.mipmap.ic_bottom_report_checked,
            R.mipmap.ic_bottom_micro_class_checked, R.mipmap.ic_bottom_advance_checked, R.mipmap.ic_bottom_record_checked};
    private int previousPos = 0;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new EvaluationContainerFragment());
        mFragments.add(new PresentationNewsFragment());
        mFragments.add(new MicroLessonFragment());
        mFragments.add(new PromoteNewsFragment());
        mFragments.add(new RecordFragment());
        initCommonTabLayout();
        viewPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPage.setOffscreenPageLimit(5);

    }

    private EvaluationFragment evaluationFragment;
    private FragmentTransaction fragmentTransaction;

    public void showEvaluationFragment() {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //如果所有的fragment都不为空的话，把所有的fragment都进行隐藏。最开始进入应用程序，fragment为空时，此方法不执行

        if (evaluationFragment == null) {
            evaluationFragment = new EvaluationFragment();
            fragmentTransaction.add(R.id.fragment_container, evaluationFragment);
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.slide_left_in, 0);
            fragmentTransaction.show(evaluationFragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onLoadData() {
    }

    @Override
    public void onBackPressed() {
        //if判断里面就调用了来自Fragment的onBackPressed()
        //一样！！，如果onBackPressed是返回false，就会进入条件内进行默认的操作
        if (baseFragment == null || !baseFragment.onBackPressed()) {
            exit();
        }
    }


    private void initCommonTabLayout() {
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {

                }
            }
        });

        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
                //enlargeAndreduction(position,true);
                //enlargeAndreduction(previousPos,false);
                //previousPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPage.setCurrentItem(1);
        //enlargeAndreduction(previousPos,true);
    }

    private void enlargeAndreduction(int position,boolean isEnlarge){
        ImageView imageView = commonTabLayout.getIconView(position);
        Animation animation = null;
        if(isEnlarge){
            animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_enlarge);
        }else{
            animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_reduction);
        }
        animation.setFillAfter(true);
        imageView.startAnimation(animation);
    }


    @Override
    public void onSelectedFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
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
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void exit() {
        if(!isExit) {
           isExit = true;
            ToastUtils.popUpToast("在按一次退出程序");
            new Timer().schedule(new TimerTask() {
                 @Override
                public void run() {
                    isExit = false;
                                     }
             }, 2000);
                     } else {
                        finish();
                     }
             }

    private static boolean isExit = false;

    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
         }
        return false;
        }*/
}