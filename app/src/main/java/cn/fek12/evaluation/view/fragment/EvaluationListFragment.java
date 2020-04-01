package cn.fek12.evaluation.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.base.BaseFragment;
import com.google.gson.Gson;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IEvaluationList;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.presenter.EvaluationListPresenter;
import cn.fek12.evaluation.view.activity.EvaluationDetailsActivity;
import cn.fek12.evaluation.view.activity.EvaluationListActivity;
import cn.fek12.evaluation.view.activity.TreeViewDialogActivity;
import cn.fek12.evaluation.view.adapter.DictionaryChildSection;
import cn.fek12.evaluation.view.adapter.DictionaryParentSection;
import cn.fek12.evaluation.view.adapter.DictionarySubjectSection;
import cn.fek12.evaluation.view.adapter.DictionaryTagChildSection;
import cn.fek12.evaluation.view.widget.CustomViewPager;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import cn.fek12.evaluation.view.widget.NoScrollViewPager;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationListFragment
 * @Description:
 * @CreateDate: 2019/10/25 10:13
 */
public class EvaluationListFragment extends BaseFragment<EvaluationListPresenter> implements IEvaluationList.View {
    @BindView(R.id.left_recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.magicIndicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private OnBackPressedClickListener mOnBackPressedClickListener;
    private SectionedRecyclerViewAdapter leftAdapter;
    private String gradeId;
    private String subjectId;
    private String subjectName;
    private String semesterId = "";
    private String textbookId;
    private List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter adapter;
    private List<PaperTypeListResp.DataBean> mTitleData;
    private int tagPos;
    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;
    private boolean isLoadPaerType = false;

    public interface OnBackPressedClickListener {
        void onBackPressedEvaluationListFragment();
    }

    public void setOnBackPressedClickListener(OnBackPressedClickListener onBackPressedClickListener) {
        this.mOnBackPressedClickListener = onBackPressedClickListener;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_fragment_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("测评", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnBackPressedClickListener != null) {
                    mOnBackPressedClickListener.onBackPressedEvaluationListFragment();
                }
            }
        });

        initLeftRecycler();
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /**随堂测，单元测，专项测，复习测页面请求不传semesterId，教材item默认不选中*/
                //mViewPager.setCurrentItem(0);
                //emptyCheck();
                updateContent(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mTitleData != null && mViewPager.getCurrentItem() != mTitleData.size() - 1){
            mViewPager.setCurrentItem(0);
        }

        emptyCheck();
        updateContent(mViewPager.getCurrentItem());

    }

    private void emptyCheck(){
        DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
        if (mViewPager != null && mTitleData != null && mTitleData.size() > 0) {
            int position = mViewPager.getCurrentItem();

            if (mTitleData.get(position).getType() == 1 || mTitleData.get(position).getType() == 3) {
                semesterUpdate(semesterSection);
                semesterSection.updateSelect(-1);
                semesterId = null;
            } else {
                semesterUpdate(semesterSection);
            }
        }else{
            semesterUpdate(semesterSection);
        }
        leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
    }

    private void semesterUpdate(DictionaryChildSection semesterSection){
        if (semesterList != null && semesterList.size() > 0) {
            semesterId = String.valueOf(semesterList.get(0).getId());
            semesterSection.updateList(semesterList);
        }else{
            semesterId = null;
            semesterSection.updateList(null);
        }
    }


    private void initLeftRecycler() {
        leftAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 3;
                } else if (position == tagPos + 3) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        leftRecycler.setLayoutManager(manager);
        leftRecycler.setAdapter(leftAdapter);

        leftAdapter.addSection("parent", new DictionaryParentSection(gradeList, new DictionaryParentSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                gradeId = String.valueOf(gradeList.get(pos).getId());
                leftAdapter.getAdapterForSection("parent").notifyAllItemsChanged("payloads");
                isLoadPaerType = true;
                /**请求二三四级数据*/
                mPresenter.querySubjectList(getContext(),gradeId);
            }
        }));

        leftAdapter.addSection("subject", new DictionarySubjectSection(subjectList, "学科", new DictionarySubjectSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                subjectId = String.valueOf(subjectList.get(pos).getId());
                subjectName = subjectList.get(pos).getName();
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

                mPresenter.queryTextBookList(getContext(),gradeId,subjectId);
            }
        }));

        leftAdapter.addSection("textbook", new DictionaryTagChildSection(textBookList, "版本", new DictionaryTagChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(String Id) {
                textbookId = Id;

                /**点击版本去查询教材*/
                mPresenter.querySemesterList(getContext(),gradeId,subjectId,textbookId);
            }
        }));

        leftAdapter.addSection("semester", new DictionaryChildSection(semesterList, "教材", new DictionaryChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
                semesterId = String.valueOf(semesterList.get(pos).getId());

                int currentItme = mViewPager.getCurrentItem();
                if (mTitleData.get(currentItme).getType() == 1) {
                    startActivityIntent(mTitleData.get(currentItme).getName(), mTitleData.get(currentItme).getValue(), mTitleData.get(currentItme).getId(),TreeViewDialogActivity.class);
                }else if(mTitleData.get(currentItme).getType() == 3){
                    /**直接进入详情页面*/
                    startActivityIntent(mTitleData.get(currentItme).getName(), mTitleData.get(currentItme).getValue(), mTitleData.get(currentItme).getId(), EvaluationListActivity.class);
                }else{
                    /**请求右侧页面数据*/
                    updateContent(mViewPager.getCurrentItem());
                }
            }
        }));
    }

    @Override
    protected void onLoadDataRemote() {
        loadView.showLoading();
        /**执行完转场动画后请求，减少卡顿*/
        loadView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.queryGradeDictionaryList(getContext());
            }
        },300);
    }

    @Override
    protected EvaluationListPresenter onInitLogicImpl() {
        return new EvaluationListPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        if (mOnBackPressedClickListener != null) {
            mOnBackPressedClickListener.onBackPressedEvaluationListFragment();
        }
        return true;
    }

    @Override
    public void loadGradeDictionarySuc(GradeDictionaryListEntity entry) {
        gradeList = entry.getData();
        if(gradeList != null && gradeList.size() > 0){
            //loadView.showContent();
            gradeId = String.valueOf(gradeList.get(0).getId());
            DictionaryParentSection parentSection = (DictionaryParentSection) leftAdapter.getSection("parent");
            parentSection.updateList(gradeList);

            leftAdapter.notifyDataSetChanged();

            /**请求二三四级数据*/
            mPresenter.querySubjectList(getContext(),gradeId);
        }else{
            loadView.showEmpty();
        }
    }

    @Override
    public void loadSubjectSuc(SubjectEntity entry) {
        DictionarySubjectSection subjectSection = (DictionarySubjectSection) leftAdapter.getSection("subject");
        DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
        DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
        subjectList = entry.getData();
        if(subjectList != null && subjectList.size() > 0){
            tagPos = gradeList.size() + subjectList.size();
            subjectId = String.valueOf(subjectList.get(0).getId());
            subjectName = subjectList.get(0).getName();

            subjectSection.updateList(subjectList);

            textBookList = subjectList.get(0).getTextbook();

            if(textBookList != null && textBookList.size() > 0){
                textbookId = String.valueOf(textBookList.get(0).getId());
                tagChildSection.updateList(textBookList);

                semesterList = textBookList.get(0).getSemester();
                emptyCheck();

            }else{
                textbookId = null;
                tagChildSection.updateList(null);

                semesterId = null;
                semesterSection.updateList(null);
            }

            leftAdapter.notifyDataSetChanged();
        }else{
            subjectId = null;
            subjectName = null;
            subjectSection.updateList(null);
            textbookId = null;
            tagChildSection.updateList(null);
            semesterId = null;
            semesterSection.updateList(null);
        }

        loadView.showContent();
        if(isLoadPaerType){
            /**请求右侧页面数据*/
            updateContent(mViewPager.getCurrentItem());
        }else{
            /**请求右侧title*/
            mPresenter.getPaperTypeList(getContext());
        }
    }

    @Override
    public void loadTextBookSuc(TextbookEntity entry) {
        textBookList = entry.getData();
        DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
        DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
        if(textBookList != null && textBookList.size() > 0){
            loadView.showContent();
            textbookId = String.valueOf(textBookList.get(0).getId());


            tagChildSection.updateList(textBookList);
            semesterList = textBookList.get(0).getSemester();

            emptyCheck();
            leftAdapter.notifyDataSetChanged();
        }else{
            semesterId = null;
            semesterSection.updateList(null);

            textbookId = null;
            tagChildSection.updateList(null);
        }
        /**请求右侧页面数据*/
        updateContent(mViewPager.getCurrentItem());
    }

    @Override
    public void loadSemesterSuc(SemesterEntity entry) {
        semesterList = entry.getData();
        emptyCheck();
        /**请求右侧页面数据*/
        updateContent(mViewPager.getCurrentItem());
    }

    private void startActivityIntent(String titleName, String value,String ptypeId, Class clazz) {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setGradeList(gradeList);
        containListEntity.setSemesterList(semesterList);
        containListEntity.setSubjectList(subjectList);
        containListEntity.setTextBookList(textBookList);
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("titleName", titleName);
        intent.putExtra("paperType", value);
        intent.putExtra("ptype", ptypeId);
        intent.putExtra("userType", "1");//类型 测评1 自主测2
        intent.putExtra("typePage", "2");//类型 微课进入1 测评进x入2
        intent.putExtra("containListEntityJson", new Gson().toJson(containListEntity));
        getActivity().startActivity(intent);
    }

    private void startContainActivityIntent(String titleName, String value,String ptypeId,Class clazz) {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setGradeList(gradeList);
        containListEntity.setSemesterList(semesterList);
        containListEntity.setSubjectList(subjectList);
        containListEntity.setTextBookList(textBookList);
        Intent intent = new Intent(getContext(),clazz);
        intent.putExtra("checkId","");
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("titleName",titleName);
        intent.putExtra("ptype",ptypeId);
        intent.putExtra("paperType", value);
        intent.putExtra("typePos", 0);
        intent.putExtra("userType", "1");//类型 测评1 自主测2
        intent.putExtra("containListEntityJson",new Gson().toJson(containListEntity));
        //intent.putExtra("mTreeDataJson",new Gson().toJson(mEntry));
        getActivity().startActivity(intent);
    }

    @Override
    public void loadPaperTypeSuc(PaperTypeListResp entry) {
        //loadView.showContent();
        if (entry != null && entry.getData() != null && entry.getData().size() > 0) {
            mTitleData = entry.getData();
            PaperTypeListResp.DataBean dataBean = new PaperTypeListResp.DataBean();
            dataBean.setName("全部");
            dataBean.setType(0);
            mTitleData.add(0, dataBean);
            initMagicIndicator(mTitleData);
            for (int i = 0; i < mTitleData.size(); i++) {
                if (mTitleData.get(i).getType() == 1 || mTitleData.get(i).getType() == 3 || mTitleData.get(i).getType() == 0) {
                    fragments.add(new EvaluationIndexPaperFragment());
                } else {
                    fragments.add(new AutonomyEvaluationFragment());
                }
            }

            adapter = new MyPagerAdapter(getChildFragmentManager(), fragments);
            mViewPager.setAdapter(adapter);

            updateContent(mViewPager.getCurrentItem());
        }
    }

    private void updateContent(int pos) {
        if (adapter != null && mTitleData != null && mTitleData.size() > 0) {
            if(mTitleData.get(pos).getType() == 1){
                /**直接进入详情页面*/
                startContainActivityIntent(mTitleData.get(pos).getName(), mTitleData.get(pos).getValue(), mTitleData.get(pos).getId(), EvaluationDetailsActivity.class);
                return;
            }
            if(mTitleData.get(pos).getType() == 3){
                /**直接进入详情页面*/
                startActivityIntent(mTitleData.get(pos).getName(), mTitleData.get(pos).getValue(), mTitleData.get(pos).getId(), EvaluationListActivity.class);
                return;
            }
            BaseFragment baseFragment = (BaseFragment) adapter.getItem(mViewPager.getCurrentItem());
            if (baseFragment instanceof EvaluationIndexPaperFragment) {
                EvaluationIndexPaperFragment fragment = (EvaluationIndexPaperFragment) baseFragment;
                fragment.queryIndexPagerData(gradeId, semesterId, subjectId, textbookId, mTitleData.get(pos).getId(), MyApplication.getMyApplication().getUserId());
                fragment.setLists(gradeList,subjectList,textBookList,semesterList);
            }else if(baseFragment instanceof AutonomyEvaluationFragment){
                int currentItme = mViewPager.getCurrentItem();
                AutonomyEvaluationFragment fragment = (AutonomyEvaluationFragment) baseFragment;
                fragment.queryTreeData(gradeId,semesterId,subjectId,textbookId,mTitleData.get(currentItme).getValue(), MyApplication.getMyApplication().getUserId());
                fragment.setLists(gradeList,subjectList,textBookList,semesterList,mTitleData.get(currentItme).getId(),subjectName);
            }
        }

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> mFragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
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

    @Override
    public void loadFail(String msg) {
        //loadView.showEmpty();
    }

    @Override
    public void loadDictionaryEmpty() {
        loadView.showEmpty();
    }

    @Override
    public void loadPaperTypeEmpty() {
        loadView.showEmpty();
    }

    private void initMagicIndicator(List<PaperTypeListResp.DataBean> mDataList) {
        CommonNavigator commonNavigator = new CommonNavigator(getContext());
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index).getName());
                simplePagerTitleView.setNormalColor(Color.parseColor("#333333"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#FFFFFF"));
                simplePagerTitleView.setTextSize(12f);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(Color.parseColor("#06BB86"));
                indicator.setRoundRadius(20f);
                indicator.setVerticalPadding(2);
                return indicator;
            }
        });

        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
}
