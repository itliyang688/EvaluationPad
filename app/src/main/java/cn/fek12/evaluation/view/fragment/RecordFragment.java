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
    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.rlTitle)
    RelativeLayout rlTitle;
    @BindView(R.id.btnCourseRecord)
    TextView btnCourseRecord;
    @BindView(R.id.btnTestRecord)
    TextView btnTestRecord;
    @BindView(R.id.llTopTitle)
    LinearLayout llTopTitle;
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
        titleName.setText("错题本");
        // 监听RadioGroup的选择事件
        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tabRecord:
                        rlTitle.setVisibility(View.VISIBLE);
                        llTopTitle.setVisibility(View.GONE);
                        titleName.setText("错题本");
                        enlargeAndreduction(tabRecord, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 0;
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.tabMicroLesson:
                        rlTitle.setVisibility(View.VISIBLE);
                        llTopTitle.setVisibility(View.GONE);
                        titleName.setText("微课学习");
                        enlargeAndreduction(tabMicroLesson, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 1;
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.tabFootprint:
                        llTopTitle.setVisibility(View.VISIBLE);
                        rlTitle.setVisibility(View.GONE);
                        enlargeAndreduction(tabFootprint, true);
                        enlargeAndreduction(radioButtonList.get(previousPos), false);
                        previousPos = 2;
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.tabCollection:
                        rlTitle.setVisibility(View.VISIBLE);
                        llTopTitle.setVisibility(View.GONE);
                        titleName.setText("我的收藏");
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

        btnCourseRecord.setOnClickListener(onClickListener);
        btnTestRecord.setOnClickListener(onClickListener);
        llSubject.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ConquerFootprintsFragment fragment;
            switch (view.getId()) {
                case R.id.llSubject:
                    List<ChildSectionEntity> list = new ArrayList<>();
                    for(int i = 0; i < 10; i ++){
                        if(i == 0){
                            ChildSectionEntity sectionEntity = new ChildSectionEntity();
                            sectionEntity.setName("全部");
                            list.add(sectionEntity);
                        }else{
                            ChildSectionEntity sectionEntity = new ChildSectionEntity();
                            sectionEntity.setName("数学"+i);
                            list.add(sectionEntity);
                        }
                    }
                    DialogUtils.subjectSelectDialog(getContext(),list, new DialogUtils.OnSelectSubjectItmeListener() {
                        @Override
                        public void onSelectItme(int pos) {
                            ToastUtils.popUpToast(list.get(pos).getName());
                        }
                    });

                    break;
                case R.id.btnCourseRecord:
                    fragment = (ConquerFootprintsFragment) adapter.getItem(mViewPager.getCurrentItem());
                    btnCourseRecord.setBackgroundResource(R.drawable.bg_present_item);
                    btnCourseRecord.setTextColor(getContext().getResources().getColor(R.color.white));
                    btnTestRecord.setTextColor(getContext().getResources().getColor(R.color.color_333));
                    btnTestRecord.setBackgroundResource(R.color.white);
                    fragment.selectTab(0);
                    break;
                case R.id.btnTestRecord:
                    fragment = (ConquerFootprintsFragment) adapter.getItem(mViewPager.getCurrentItem());
                    btnTestRecord.setBackgroundResource(R.drawable.bg_present_item);
                    btnTestRecord.setTextColor(getContext().getResources().getColor(R.color.white));
                    btnCourseRecord.setTextColor(getContext().getResources().getColor(R.color.color_333));
                    btnCourseRecord.setBackgroundResource(R.color.white);
                    fragment.selectTab(1);
                    break;
            }
        }
    };


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
