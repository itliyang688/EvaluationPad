package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.EvaluationListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.EvaluationDetailsPresenter;
import cn.fek12.evaluation.view.adapter.EvaluationAdapter;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsChildSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsParentSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsSubjectSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsTagSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: EvaluationDetailsActivity
 * @Description:
 * @CreateDate: 2019/10/30 13:14
 */
public class EvaluationListActivity extends BaseActivity<EvaluationDetailsPresenter> implements EvaluationDetailsPresenter.View {
    @BindView(R.id.recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    private SectionedRecyclerViewAdapter leftAdapter;
    private String checkId;
    private EvaluationAdapter evaluationAdapter;
    private int tagPos;
    private String gradeId;
    private String subjectId;
    private String semesterId;
    private String textbookId;

    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;

    private int currentPage = 1;
    private boolean isLoadMore = false;
    private String ptype;
    private String userType;
    private String paperListType;//试卷列表类型 1热门 2最近更新

    @Override
    public int setLayoutResource() {
        return R.layout.evaluation_list_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        setEmptyTitle();
        //setDefaultTitle(intent.getStringExtra("titleName"));
        checkId = intent.getStringExtra("checkId");
        gradeId = intent.getStringExtra("gradeId");
        subjectId = intent.getStringExtra("subjectId");
        semesterId = intent.getStringExtra("semesterId");
        textbookId = intent.getStringExtra("textbookId");
        ptype = intent.getStringExtra("ptype");
        userType = intent.getStringExtra("userType");
        paperListType = intent.getStringExtra("paperListType");
        ContainListEntity containListEntity = new Gson().fromJson(intent.getStringExtra("containListEntityJson"), ContainListEntity.class);
        gradeList = containListEntity.getGradeList();
        subjectList = containListEntity.getSubjectList();
        textBookList = containListEntity.getTextBookList();
        semesterList = containListEntity.getSemesterList();

        tagPos = gradeList.size() + subjectList.size();

        initLeftRecycler();
        initLabelAdapter();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        evaluationAdapter = new EvaluationAdapter(EvaluationListActivity.this);
        recyclerView.setAdapter(evaluationAdapter);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        loadView.showLoading();
        mPresenter.queryPaperList(this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryPaperList(EvaluationListActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryPaperList(EvaluationListActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);
        }
    };

    private void initLeftRecycler() {
        leftAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 12);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 12;
                } else if (position == tagPos + 2) {
                    return 12;
                } else {
                    return 1;
                }
            }
        });
        leftRecycler.setLayoutManager(manager);
        leftRecycler.setAdapter(leftAdapter);
    }

    private void initLabelAdapter() {
        leftAdapter.addSection("parent", new EvaluationDetailsParentSection(gradeList, gradeId, new EvaluationDetailsParentSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                gradeId = String.valueOf(gradeList.get(pos).getId());
                leftAdapter.getAdapterForSection("parent").notifyAllItemsChanged("payloads");

                /**请求二三四级数据*/
                mPresenter.querySubjectList(getContext(), gradeId);
            }
        }));

        leftAdapter.addSection("subject", new EvaluationDetailsSubjectSection(subjectList, subjectId, new EvaluationDetailsSubjectSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                subjectId = String.valueOf(subjectList.get(pos).getId());
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

                mPresenter.queryTextBookList(getContext(),gradeId,subjectId);
            }
        }));

        leftAdapter.addSection("textbook", new EvaluationDetailsTagSection(getContext(), textBookList, textbookId, new EvaluationDetailsTagSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("textbook").notifyAllItemsChanged("payloads");
                textbookId = String.valueOf(textBookList.get(pos).getId());

                /**点击版本去查询教材*/
                mPresenter.querySemesterList(getContext(),gradeId,subjectId,textbookId);

            }
        }));
        leftAdapter.addSection("semester", new EvaluationDetailsChildSection(semesterList, semesterId, new EvaluationDetailsChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
                semesterId = String.valueOf(semesterList.get(pos).getId());
               /**请求页面数据*/
                isLoadMore = false;
                currentPage = 1;
                loadView.showLoading();
                mPresenter.queryPaperList(EvaluationListActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);
            }
        }));
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    protected EvaluationDetailsPresenter onInitLogicImpl() {
        return new EvaluationDetailsPresenter(this, getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void loadTreeSuc(TreeDataEntity entry) {

    }

    @Override
    public void loadPaperListSuc(EvaluationListEntity entry) {
        EvaluationListEntity.DataBean.PageInfoBean pageInfoBean = entry.getData().getPage_info();
        List<EvaluationListEntity.DataBean.PapersBean> list = entry.getData().getPapers();
        if(pageInfoBean.getTotalCount() == 0){
            loadView.showEmpty();
            return;
        }
        loadView.showContent();
        if(pageInfoBean.getTotalPage() > currentPage){
            refreshLayout.setEnableLoadmore(true);
        }else{
            refreshLayout.setEnableLoadmore(false);
        }
        if(list != null && list.size() > 0){
            evaluationAdapter.notifyChanged(entry.getData().getPapers(),isLoadMore);
            if(!isLoadMore){
                recyclerView.smoothScrollToPosition(0);
            }
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadSubjectSuc(SubjectEntity entry) {
        subjectList = entry.getData();
        if (subjectList != null && subjectList.size() > 0) {
            loadView.showContent();
            tagPos = gradeList.size() + subjectList.size();
            subjectId = String.valueOf(subjectList.get(0).getId());

            EvaluationDetailsSubjectSection subjectSection = (EvaluationDetailsSubjectSection) leftAdapter.getSection("subject");
            subjectSection.updateList(subjectList);

            textBookList = subjectList.get(0).getTextbook();
            EvaluationDetailsTagSection tagChildSection = (EvaluationDetailsTagSection) leftAdapter.getSection("textbook");
            if (textBookList != null && textBookList.size() > 0) {
                textbookId = String.valueOf(textBookList.get(0).getId());
                tagChildSection.updateList(textBookList);

                semesterList = textBookList.get(0).getSemester();
                EvaluationDetailsChildSection semesterSection = (EvaluationDetailsChildSection) leftAdapter.getSection("semester");
                if (semesterList != null && semesterList.size() > 0) {
                    semesterId = String.valueOf(semesterList.get(0).getId());
                    semesterSection.updateList(semesterList);
                } else {
                    semesterId = null;
                    semesterSection.updateList(null);
                }

            } else {
                textbookId = null;
                tagChildSection.updateList(null);
            }

            leftAdapter.notifyDataSetChanged();
           /**请求页面数据*/
            isLoadMore = false;
            currentPage = 1;
            loadView.showLoading();
            mPresenter.queryPaperList(EvaluationListActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);

        }
    }

    @Override
    public void loadTextBookSuc(TextbookEntity entry) {
        textBookList = entry.getData();
        if(textBookList != null && textBookList.size() > 0){
            loadView.showContent();
            textbookId = String.valueOf(textBookList.get(0).getId());

            EvaluationDetailsTagSection tagChildSection = (EvaluationDetailsTagSection) leftAdapter.getSection("textbook");
            tagChildSection.updateList(textBookList);

            semesterList = textBookList.get(0).getSemester();
            EvaluationDetailsChildSection semesterSection = (EvaluationDetailsChildSection) leftAdapter.getSection("semester");
            if(semesterList != null && semesterList.size() > 0){
                semesterId = String.valueOf(semesterList.get(0).getId());
                semesterSection.updateList(semesterList);
            }else{
                semesterId = null;
                semesterSection.updateList(null);
            }
            leftAdapter.notifyDataSetChanged();
        }

       /**请求页面数据*/
        isLoadMore = false;
        currentPage = 1;
        loadView.showLoading();
        mPresenter.queryPaperList(EvaluationListActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);

    }

    @Override
    public void loadSemesterSuc(SemesterEntity entry) {
        semesterList = entry.getData();
        if(semesterList != null && semesterList.size() > 0){
            semesterId = String.valueOf(semesterList.get(0).getId());
            EvaluationDetailsChildSection semesterSection = (EvaluationDetailsChildSection) leftAdapter.getSection("semester");
            semesterSection.updateList(semesterList);

            leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
        }
        /**请求页面数据*/
        isLoadMore = false;
        currentPage = 1;
        loadView.showLoading();
        mPresenter.queryPaperList(EvaluationListActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApplication().getUserId(),userType,String.valueOf(currentPage),checkId,paperListType);

    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    public void loadTreeEmpty() {

    }

    @Override
    public void loadDictionaryEmpty() {

    }

    @Override
    public void loadPaperTypeEmpty() {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }
}
