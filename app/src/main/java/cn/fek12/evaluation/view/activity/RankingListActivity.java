package cn.fek12.evaluation.view.activity;

import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BackFragmentInterface;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.fragment.RankingListFragment;
import cn.fek12.evaluation.view.widget.CustomViewPager;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: RankingListActivity
 * @Description:
 * @CreateDate: 2019/11/29 13:59
 */
public class RankingListActivity extends BaseActivity {
    @BindView(R.id.ivClickDoubt)
    ImageView ivClickDoubt;
    @BindView(R.id.rlDoubtAnswer)
    RelativeLayout rlDoubtAnswer;
    @BindView(R.id.rooView)
    RelativeLayout rooView;
    @BindView(R.id.tvExcellent)
    TextView tvExcellent;
    @BindView(R.id.rlExcellent)
    RelativeLayout rlExcellent;
    @BindView(R.id.tvDiligence)
    TextView tvDiligence;
    @BindView(R.id.rlDiligence)
    RelativeLayout rlDiligence;
    @BindView(R.id.view_pager)
    CustomViewPager viewPager;
    private MyPagerAdapter pagerAdapter;

    @Override
    public int setLayoutResource() {
        return R.layout.ranking_list_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle();
        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
    }

    @Override
    protected void onLoadData() {

    }

    private boolean isDoubtShow = false;
    @OnClick({R.id.ivClickDoubt, R.id.rooView,R.id.rlExcellent, R.id.rlDiligence})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClickDoubt:
                if(isDoubtShow){
                    isDoubtShow = false;
                    rlDoubtAnswer.setVisibility(View.GONE);
                }else{
                    rlDoubtAnswer.setVisibility(View.VISIBLE);
                    isDoubtShow = true;
                }
                break;
            case R.id.rooView:
                isDoubtShow = false;
                rlDoubtAnswer.setVisibility(View.GONE);
                break;
            case R.id.rlExcellent:
                viewPager.setCurrentItem(0);
                tvExcellent.setBackgroundResource(R.drawable.bg_present_item);
                tvExcellent.setTextColor(getResources().getColor(R.color.white));
                tvDiligence.setBackgroundResource(R.color.white);
                tvDiligence.setTextColor(getResources().getColor(R.color.color_333));
                break;
            case R.id.rlDiligence:
                viewPager.setCurrentItem(1);
                tvDiligence.setBackgroundResource(R.drawable.bg_present_item);
                tvDiligence.setTextColor(getResources().getColor(R.color.white));
                tvExcellent.setBackgroundResource(R.color.white);
                tvExcellent.setTextColor(getResources().getColor(R.color.color_333));
                break;
        }
    }

    private BaseFragment baseFragment;


    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            return new RankingListFragment(position);
        }
    }
}
