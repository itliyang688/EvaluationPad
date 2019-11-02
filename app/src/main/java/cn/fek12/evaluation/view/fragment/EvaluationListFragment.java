package cn.fek12.evaluation.view.fragment;

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

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
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
import cn.fek12.evaluation.impl.IEvaluationList;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.PaperTypeListResp;
import cn.fek12.evaluation.presenter.EvaluationListPresenter;
import cn.fek12.evaluation.view.activity.TreeViewDialogActivity;
import cn.fek12.evaluation.view.adapter.DictionaryChildSection;
import cn.fek12.evaluation.view.adapter.DictionaryParentSection;
import cn.fek12.evaluation.view.adapter.DictionaryTagChildSection;
import cn.fek12.evaluation.view.widget.CustomViewPager;
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
    CustomViewPager mViewPager;
    private OnBackPressedClickListener mOnBackPressedClickListener;
    private SectionedRecyclerViewAdapter leftAdapter;
    private String gradeId;
    private String subjectId;
    private String semesterId = "";
    private String textbookId;
    private int parentPos;
    private List<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter adapter;
    private List<PaperTypeListResp.DataBean> mTitleData;
    private DictionaryListResp mEntry;
    private int tagPos;

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
                DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
                if(mTitleData.get(position).getType() == 1 || mTitleData.get(position).getType() == 3){
                    semesterId = "";
                    semesterSection.updateSelect(-1);
                }else{
                    if (mEntry.getData() != null && mEntry.getData().size() > 0) {
                        semesterId = mEntry.getData().get(0).getTabInfo().getSemester().get(0).getId();
                        semesterSection.updateSelect(0);
                    }
                }
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
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
        if(mViewPager != null && mTitleData != null && mTitleData.size() > 0){
            int position = mViewPager.getCurrentItem();
            if(mTitleData.get(position).getType() == 1 || mTitleData.get(position).getType() == 3){
                DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
                semesterSection.updateSelect(-1);
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
            }
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
                }else if(position == tagPos + 3){
                    return 3;
                }else {
                    return 1;
                }
            }
        });
        leftRecycler.setLayoutManager(manager);
        leftRecycler.setAdapter(leftAdapter);
    }

    @Override
    protected void onLoadDataRemote() {
        mPresenter.queryEvaluationList(getContext());
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
    public void loadDictionarySuc(DictionaryListResp entry) {
        this.mEntry = entry;
        if (mEntry.getData() != null && mEntry.getData().size() > 0) {
            List<DictionaryListResp.DataBean> list = mEntry.getData();
            gradeId = String.valueOf(list.get(0).getId());
            leftAdapter.addSection("parent", new DictionaryParentSection(list, new DictionaryParentSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    parentPos = pos;
                    gradeId = String.valueOf(list.get(pos).getId());
                    DictionaryChildSection subjectSection = (DictionaryChildSection) leftAdapter.getSection("subject");
                    subjectId = list.get(pos).getTabInfo().getSubject().get(0).getId();
                    /**获取tagpos*/
                    tagPos = mEntry.getData().size() + list.get(pos).getTabInfo().getSubject().size();
                    /***/
                    subjectSection.updateList(list.get(pos).getTabInfo().getSubject());
                    DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
                    textbookId = list.get(pos).getTabInfo().getTextbook().get(0).getId();
                    tagChildSection.updateList(list.get(pos).getTabInfo().getTextbook());
                    DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
                    semesterId = list.get(pos).getTabInfo().getSemester().get(0).getId();
                    semesterSection.updateList(list.get(pos).getTabInfo().getSemester());
                    updateContent(mViewPager.getCurrentItem());
                    leftAdapter.notifyDataSetChanged();
                }
            }));
            tagPos = mEntry.getData().size() + list.get(0).getTabInfo().getSubject().size();
            subjectId = list.get(0).getTabInfo().getSubject().get(0).getId();
            leftAdapter.addSection("subject", new DictionaryChildSection(list.get(0).getTabInfo().getSubject(), "学科", new DictionaryChildSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    subjectId = list.get(parentPos).getTabInfo().getSubject().get(pos).getId();
                    leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");
                    updateContent(mViewPager.getCurrentItem());
                }
            }));
            textbookId = list.get(0).getTabInfo().getTextbook().get(0).getId();
            leftAdapter.addSection("textbook", new DictionaryTagChildSection(list.get(0).getTabInfo().getTextbook(), "版本", new DictionaryTagChildSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(String Id) {
                    textbookId = Id;
                    //leftAdapter.getAdapterForSection("textbook").notifyAllItemsChanged("payloads");
                    updateContent(mViewPager.getCurrentItem());
                }
            }));
            semesterId = list.get(0).getTabInfo().getSemester().get(0).getId();
            leftAdapter.addSection("semester", new DictionaryChildSection(list.get(0).getTabInfo().getSemester(), "教材", new DictionaryChildSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
                    semesterId = list.get(parentPos).getTabInfo().getSemester().get(pos).getId();
                    int currentItme = mViewPager.getCurrentItem();
                    if (mTitleData.get(currentItme).getType() == 1){
                        initDialogTree(mTitleData.get(currentItme).getName(),mTitleData.get(currentItme).getValue());
                    }
                    updateContent(currentItme);
                }
            }));
            leftAdapter.notifyDataSetChanged();
        }

        /**请求右侧title*/
        mPresenter.getPaperTypeList(getContext());
    }

    private void initDialogTree(String titleName,String value) {
        Intent intent = new Intent(getContext(), TreeViewDialogActivity.class);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("titleName",titleName);
        intent.putExtra("paperType",value);
        intent.putExtra("mEntryJson", new Gson().toJson(mEntry));
        getActivity().startActivity(intent);
    }

    @Override
    public void loadPaperTypeSuc(PaperTypeListResp entry) {
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
                    fragments.add(new EvaluationSelfIndexPaperFragment());
                }
            }

            adapter = new MyPagerAdapter(getChildFragmentManager(), fragments);
            mViewPager.setAdapter(adapter);

            updateContent(mViewPager.getCurrentItem());
        }
    }

    private void updateContent(int pos) {
        if (adapter != null) {
            BaseFragment baseFragment = (BaseFragment) adapter.getItem(mViewPager.getCurrentItem());
            if (baseFragment instanceof EvaluationIndexPaperFragment) {
                EvaluationIndexPaperFragment fragment = (EvaluationIndexPaperFragment) baseFragment;
                fragment.queryIndexPagerData(gradeId, semesterId, subjectId, textbookId, mTitleData.get(pos).getId(), "413");
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
