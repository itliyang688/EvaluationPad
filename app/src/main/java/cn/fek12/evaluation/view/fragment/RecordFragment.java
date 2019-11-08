package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;
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
    @BindView(R.id.tabFootprint)
    RadioButton tabFootprint;
    @BindView(R.id.tabCollection)
    RadioButton tabCollection;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.llLeft)
    LinearLayout llLeft;
    @BindView(R.id.view_pager)
    CustomViewPager mViewPager;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private MyPagerAdapter adapter;

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
    protected void onLoadDataRemote() {

    }

    private void initView() {
        adapter = new MyPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
        rgGroup.check(R.id.tabRecord);// 默认勾选首页
        // 监听RadioGroup的选择事件
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tabRecord:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.tabFootprint:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.tabCollection:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            return new TopicWrongRecordPageFragment();
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
