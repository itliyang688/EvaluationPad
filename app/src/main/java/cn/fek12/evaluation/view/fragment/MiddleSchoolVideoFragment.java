package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.fek12.basic.base.BaseFragment;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMicorLesson;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.presenter.MicroLessonPresenter;
import cn.fek12.evaluation.view.activity.MicroLessonTreeActivity;
import cn.fek12.evaluation.view.activity.PrepareExaminationActivity;
import cn.fek12.evaluation.view.adapter.DictionaryChildSection;
import cn.fek12.evaluation.view.adapter.DictionaryParentSection;
import cn.fek12.evaluation.view.adapter.DictionarySubjectSection;
import cn.fek12.evaluation.view.adapter.DictionaryTagChildSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import cn.fek12.evaluation.view.widget.NoScrollViewPager;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonFragment
 * @Description:
 * @CreateDate: 2019/11/4 16:12
 */
public class MiddleSchoolVideoFragment extends BaseFragment<MicroLessonPresenter> implements IMicorLesson.View {
    @BindView(R.id.left_recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    @BindView(R.id.mTabLayout)
    SegmentTabLayout mTabLayout;
    private SectionedRecyclerViewAdapter leftAdapter;
    private String gradeId;
    private String subjectId;
    private String semesterId = "";
    private String textbookId;
    private int tagPos;
    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;
    private MyPagerAdapter adapter;
    private String[] mTitles = {"全部", "同步", "备考", "赢在中考"};
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.microlesson_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        initLeftRecycler();
        segmentTabLayout();
    }

    @Override
    protected void onLoadDataRemote() {
        mPresenter.queryGradeDictionaryList(getContext(),"2");
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mTabLayout != null && mTabLayout.getCurrentTab() != 0){
            mViewPager.setCurrentItem(0);
            mTabLayout.setCurrentTab(0);
            updateContent();
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
                } else if (tagPos != 0 && position == tagPos + 3) {
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
                /**请求二三四级数据*/
                mPresenter.querySubjectList(getContext(), gradeId);
            }
        }));

        leftAdapter.addSection("subject", new DictionarySubjectSection(subjectList, "学科", new DictionarySubjectSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                subjectId = String.valueOf(subjectList.get(pos).getId());
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

                mPresenter.queryTextBookList(getContext(), gradeId, subjectId);
            }
        }));

        leftAdapter.addSection("textbook", new DictionaryTagChildSection(textBookList, "版本", new DictionaryTagChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(String Id) {
                textbookId = Id;

                /**点击版本去查询教材*/
                mPresenter.querySemesterList(getContext(), gradeId, subjectId, textbookId);
            }
        }));

        leftAdapter.addSection("semester", new DictionaryChildSection(semesterList, "教材", new DictionaryChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
                semesterId = String.valueOf(semesterList.get(pos).getId());
                int currentItme = mViewPager.getCurrentItem();
                if(currentItme == 0){
                    /**请求右侧页面数据*/
                    updateContent();
                }
            }
        }));
    }

    private void updateContent() {
        int currentItme = mViewPager.getCurrentItem();
        if(currentItme == 0){
            BaseFragment baseFragment = (BaseFragment) adapter.getItem(currentItme);
            if (baseFragment instanceof MicroLessonPageFragment) {
                MicroLessonPageFragment fragment = (MicroLessonPageFragment) baseFragment;
                fragment.queryIndexPagerData(gradeId, semesterId, subjectId, textbookId, MyApplication.getMyApp().getUserId());
                fragment.setLists(gradeList,subjectList,textBookList,semesterList);
            }
            return;
        }

        if(currentItme == 1){
            String value = "SYNCHRO";
            startContainActivityIntent("",value,currentItme, MicroLessonTreeActivity.class);
        }else if(currentItme == 2){
            startActivityIntent("1", PrepareExaminationActivity.class);
        }else if(currentItme == 3){
            startActivityIntent("2", PrepareExaminationActivity.class);
        }
    }


    private void startActivityIntent(String paperType,Class clazz) {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setSubjectList(subjectList);
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtra("paperType", paperType);
        getActivity().startActivity(intent);
    }

    private void startContainActivityIntent(String id,String value,int typePos,Class clazz) {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setGradeList(gradeList);
        containListEntity.setSemesterList(semesterList);
        containListEntity.setSubjectList(subjectList);
        containListEntity.setTextBookList(textBookList);
        Intent intent = new Intent(getContext(),clazz);
        intent.putExtra("checkId",id);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("paperType", value);
        intent.putExtra("typePos", typePos);
        intent.putExtra("containListEntityJson", new Gson().toJson(containListEntity));
        getActivity().startActivity(intent);
    }

    @Override
    protected MicroLessonPresenter onInitLogicImpl() {
        return new MicroLessonPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void loadGradeDictionarySuc(GradeDictionaryListEntity entry) {
        gradeList = entry.getData();
        if (gradeList != null && gradeList.size() > 0) {
            loadView.showContent();
            gradeId = String.valueOf(gradeList.get(0).getId());
            DictionaryParentSection parentSection = (DictionaryParentSection) leftAdapter.getSection("parent");
            parentSection.updateList(gradeList);

            leftAdapter.notifyDataSetChanged();

            /**请求二三四级数据*/
            mPresenter.querySubjectList(getContext(), gradeId);
        } else {
            loadView.showEmpty();
        }
    }

    @Override
    public void loadSubjectSuc(SubjectEntity entry) {
        DictionarySubjectSection subjectSection = (DictionarySubjectSection) leftAdapter.getSection("subject");
        DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
        DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
        subjectList = entry.getData();
        if (subjectList != null && subjectList.size() > 0) {
            loadView.showContent();
            tagPos = gradeList.size() + subjectList.size();
            subjectId = String.valueOf(subjectList.get(0).getId());

            subjectSection.updateList(subjectList);

            textBookList = subjectList.get(0).getTextbook();

            if (textBookList != null && textBookList.size() > 0) {
                textbookId = String.valueOf(textBookList.get(0).getId());
                tagChildSection.updateList(textBookList);

                semesterList = textBookList.get(0).getSemester();
                emptyCheck(semesterSection);

            } else {
                textbookId = null;
                tagChildSection.updateList(null);

                semesterId = null;
                semesterSection.updateList(null);
            }

            leftAdapter.notifyDataSetChanged();
        } else {
            subjectId = null;
            subjectSection.updateList(null);
            textbookId = null;
            tagChildSection.updateList(null);
            semesterId = null;
            semesterSection.updateList(null);
        }

        /**请求右侧页面数据*/
        updateContent();
    }

    @Override
    public void loadTextBookSuc(TextbookEntity entry) {
        textBookList = entry.getData();
        DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
        DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
        if (textBookList != null && textBookList.size() > 0) {
            loadView.showContent();
            textbookId = String.valueOf(textBookList.get(0).getId());


            tagChildSection.updateList(textBookList);
            semesterList = textBookList.get(0).getSemester();

            emptyCheck(semesterSection);
            leftAdapter.notifyDataSetChanged();
        } else {
            semesterId = null;
            semesterSection.updateList(null);

            textbookId = null;
            tagChildSection.updateList(null);
        }
        /**请求右侧页面数据*/
        updateContent();
    }

    @Override
    public void loadSemesterSuc(SemesterEntity entry) {
        semesterList = entry.getData();
        DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
        emptyCheck(semesterSection);
        /**请求右侧页面数据*/
        updateContent();
    }

    private void emptyCheck(DictionaryChildSection semesterSection){
        int position = mViewPager.getCurrentItem();
        semesterUpdate(semesterSection);
        if (position == 1 || position == 2) {
            semesterSection.updateSelect(-1);
            semesterId = null;
        }
        leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
    }

    private void semesterUpdate(DictionaryChildSection semesterSection) {
        if (semesterList != null && semesterList.size() > 0) {
            semesterId = String.valueOf(semesterList.get(0).getId());
            semesterSection.updateList(semesterList);
        } else {
            semesterId = null;
            semesterSection.updateList(null);
        }
    }

    private void segmentTabLayout() {
        for (int i = 0; i < mTitles.length; i++) {
            fragments.add(new MicroLessonPageFragment(i));
        }
        adapter = new MyPagerAdapter(getChildFragmentManager(),fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(4);

        mTabLayout.setTabData(mTitles);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                //mViewPager.setCurrentItem(position);
                if(position == 1){
                    //String titleName = currentItme==1? "同步视频":"专题视频";
                    //String value = position==1? "SWEETOWN":"SPECIAL";
                    String value = "SYNCHRO";
                    startContainActivityIntent("",value,position, MicroLessonTreeActivity.class);
                }else {
                    if(position == 2){
                        startActivityIntent("1", PrepareExaminationActivity.class);
                    }else if(position == 3){
                        startActivityIntent("2", PrepareExaminationActivity.class);
                    }
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
                updateContent();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }


    private class MyPagerAdapter extends FragmentPagerAdapter {
        List<Fragment> mFragments;
        public MyPagerAdapter(FragmentManager fm,List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
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
            return mFragments.get(position);
        }
    }

    @Override
    public void loadDictionaryEmpty() {
        loadView.showEmpty();
    }
}
