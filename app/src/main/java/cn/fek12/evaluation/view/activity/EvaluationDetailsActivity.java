package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import java.util.ArrayList;
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
public class EvaluationDetailsActivity extends BaseActivity<EvaluationDetailsPresenter> implements EvaluationDetailsPresenter.View, EvaluationAdapter.OnItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.label)
    LinearLayout label;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    @BindView(R.id.titleName)
    TextView titleName;
    private SectionedRecyclerViewAdapter leftAdapter;
    private TreeDataEntity treeDataEntity;
    private String checkId;
    private TreeNode selectNode;
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
    private String pageSize = "30";
    private boolean isLoadMore = false;
    private String ptype;
    private String userType;
    private String paperType;
    private List<EvaluationListEntity.DataBean.PapersBean> mList;

    @Override
    public int setLayoutResource() {
        return R.layout.evaluation_details_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        setEmptyTitle();
        titleName.setText("测卷");
        //setDefaultTitle(intent.getStringExtra("titleName"));
        checkId = intent.getStringExtra("checkId");
        gradeId = intent.getStringExtra("gradeId");
        subjectId = intent.getStringExtra("subjectId");
        semesterId = intent.getStringExtra("semesterId");
        textbookId = intent.getStringExtra("textbookId");
        ptype = intent.getStringExtra("ptype");
        userType = intent.getStringExtra("userType");
        paperType = intent.getStringExtra("paperType");
        treeDataEntity = new Gson().fromJson(intent.getStringExtra("mTreeDataJson"), TreeDataEntity.class);
        ContainListEntity containListEntity = new Gson().fromJson(intent.getStringExtra("containListEntityJson"), ContainListEntity.class);
        gradeList = containListEntity.getGradeList();
        subjectList = containListEntity.getSubjectList();
        textBookList = containListEntity.getTextBookList();
        semesterList = containListEntity.getSemesterList();

        tagPos = gradeList.size() + subjectList.size();

        initLeftRecycler();
        initLabelAdapter();
        //initTreeView();

        /**请求知识树*/
        checkId = null;
        mPresenter.initTreeData(EvaluationDetailsActivity.this,paperType,gradeId,semesterId,subjectId,textbookId,MyApplication.getMyApp().getUserId());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        evaluationAdapter = new EvaluationAdapter(EvaluationDetailsActivity.this,EvaluationAdapter.EVALUATION);
        evaluationAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(evaluationAdapter);

        //refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        loadView.showLoading();
        mPresenter.queryPaperList(this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);
        }
    };

    private void initLeftRecycler() {
        /*if(gradeList != null && gradeList.size() > 0){
            tagPos = gradeList.size();
        }*/
        leftAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 12);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                /*if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 12;
                }else if (tagPos != 0 && position == tagPos + 1) {
                    return 12;
                } else if (tagPos != 0 && position == tagPos + 3) {
                    return 12;
                } else {
                    return 1;
                }*/
                if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 12;
                }else if (tagPos != 0 && position == tagPos + 2) {
                    return 12;
                }else {
                    return 1;
                }
            }
        });
        leftRecycler.setLayoutManager(manager);
        leftRecycler.setAdapter(leftAdapter);
    }

    private void initTreeView() {
        //创建根节点
        TreeNode root = TreeNode.root();
        /**第一级*/
        List<TreeDataEntity.DataBean> node1List = treeDataEntity.getData();
        for (TreeDataEntity.DataBean dataBean : node1List) {
            /**第二级*/
            List<TreeDataEntity.DataBean.ChildsBeanXX> node2List = dataBean.getChilds();
            if (node2List != null && node2List.size() > 0) {
                /**添加一级跟文件夹*/
                TreeNode node1 = new TreeNode(new TreeParentItemHolder.IconTreeItem(dataBean.getName())).setViewHolder(new TreeParentItemHolder(getContext(),TreeParentItemHolder.ORIGINAL));
                for (TreeDataEntity.DataBean.ChildsBeanXX childsBeanXX : node2List) {
                    List<TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX> node3List = childsBeanXX.getChilds();
                    if (node3List != null && node3List.size() > 0) {
                        TreeNode node2 = new TreeNode(new TreeParentItemHolder.IconTreeItem(childsBeanXX.getName())).setViewHolder(new TreeParentItemHolder(getContext(),TreeParentItemHolder.ORIGINAL));
                        node1.addChild(node2);
                        /**第三级*/
                        for (TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX childsBeanX : node3List) {
                            List<TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX.ChildsBean> node4List = childsBeanX.getChilds();
                            if (node4List != null && node4List.size() > 0) {
                                TreeNode node3 = new TreeNode(new TreeParentItemHolder.IconTreeItem(childsBeanX.getName())).setViewHolder(new TreeParentItemHolder(getContext(),TreeParentItemHolder.ORIGINAL));
                                node2.addChild(node3);
                                /**第四级*/
                                for (TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX.ChildsBean childsBean : node4List) {
                                    TreeNode file4 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBean.getName(), String.valueOf(childsBean.getId()), String.valueOf(childsBean.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(),TreeParentItemHolder.ORIGINAL));
                                    node3.addChild(file4);
                                    nodeListenerAndUpdate(file4);
                                    defaultCheck(file4, String.valueOf(childsBean.getId()));
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanX.getName(), String.valueOf(childsBeanX.getId()), String.valueOf(childsBeanX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(),TreeParentItemHolder.ORIGINAL));
                                node2.addChild(file3);
                                nodeListenerAndUpdate(file3);
                                defaultCheck(file3, String.valueOf(childsBeanX.getId()));
                            }
                        }
                    } else {
                        TreeNode file2 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanXX.getName(), String.valueOf(childsBeanXX.getId()), String.valueOf(childsBeanXX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(),AutoTreeChildItemHolder.ORIGINAL));
                        node1.addChild(file2);
                        nodeListenerAndUpdate(file2);
                        defaultCheck(file2, String.valueOf(childsBeanXX.getId()));
                    }
                }
                root.addChild(node1);
                node1.setExpanded(true);
            } else {
                /**添加一级跟文件*/
                TreeNode file1 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(dataBean.getName(), String.valueOf(dataBean.getId()), String.valueOf(0))).setViewHolder(new AutoTreeChildItemHolder(getContext(),AutoTreeChildItemHolder.ORIGINAL));
                root.addChild(file1);
                nodeListenerAndUpdate(file1);
                defaultCheck(file1, String.valueOf(dataBean.getId()));
            }
        }

        //创建树形视图
        AndroidTreeView tView = new AndroidTreeView(this, root);
        //设置树形视图开启默认动画
        tView.setDefaultAnimation(true);
        //设置树形视图默认的样式
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom, true);
        //设置树形视图默认的ViewHolder
        tView.setDefaultViewHolder(TreeParentItemHolder.class);
        //将树形视图添加到layout中
        layout.addView(tView.getView());

    }

    private void defaultCheck(TreeNode fileNode, String id) {
        if (checkId != null && checkId.equals(id)) {
            selectNode = fileNode;
            TreeNode treeNode3 = selectNode.getParent();
            if (treeNode3 != null) {
                treeNode3.setExpanded(true);
                TreeNode treeNode2 = treeNode3.getParent();
                if (treeNode2 != null) {
                    treeNode2.setExpanded(true);
                    TreeNode treeNode1 = treeNode2.getParent();
                    if (treeNode1 != null) {
                        treeNode1.setExpanded(true);
                    }
                }
            }
            selectNode.setExpanded(true);
        }
    }

    private void nodeListenerAndUpdate(TreeNode fileNode) {
        fileNode.setClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
                AutoTreeChildItemHolder.IconTreeItem treeItem = (AutoTreeChildItemHolder.IconTreeItem) value;
                String id = treeItem.id;
                String parentId = treeItem.parentId;
                String name = treeItem.text;
                isFocusTreeNode(node,true);

                if (selectNode != null) {
                    isFocusTreeNode(selectNode, false);
                }
                if (!id.equals(checkId)) {
                    checkId = id;
                    selectNode = node;
                }else{
                    checkId = "";
                    selectNode = null;
                }
                /**请求数据*/
                isLoadMore = false;
                currentPage = 1;
                mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);

                /*if (!id.equals(checkId)) {
                    if(selectNode != null){
                        isFocusTreeNode(selectNode,false);
                    }

                    checkId = id;
                    selectNode = node;
                    *//**请求数据*//*
                    isLoadMore = false;
                    currentPage = 1;
                    loadView.showLoading();
                    mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);
                }*/
            }
        });
    }

    private void isFocusTreeNode(TreeNode treeNode,boolean isFocus){
        AutoTreeChildItemHolder selectHolder = (AutoTreeChildItemHolder) treeNode.getViewHolder();
        selectHolder.isCheck = true;
        if(isFocus){
            selectHolder.arrowView.setImageResource(R.mipmap.check_icon);
            selectHolder.tvValue.setTextColor(Color.parseColor("#FEAE2D"));
        }else{
            selectHolder.arrowView.setImageResource(R.mipmap.file_icon);
            selectHolder.tvValue.setTextColor(Color.parseColor("#333333"));
        }
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

        List<TextbookChildEntity> tagList = new ArrayList<>();
        for(SubjectEntity.DataBean dataBeans : subjectList){
            TextbookChildEntity childEntity = new TextbookChildEntity();
            childEntity.setId(dataBeans.getId());
            childEntity.setName(dataBeans.getName());
            tagList.add(childEntity);
        }
        /*leftAdapter.addSection("subject", new EvaluationDetailsTagSection(getContext(), tagList, subjectId, new EvaluationDetailsTagSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                subjectId = String.valueOf(subjectList.get(pos).getId());
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

                mPresenter.queryTextBookList(getContext(),gradeId,subjectId);
            }
        }));*/

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
                /**请求知识树*/
                checkId = null;
                mPresenter.initTreeData(EvaluationDetailsActivity.this,paperType,gradeId,semesterId,subjectId,textbookId,MyApplication.getMyApp().getUserId());
                /**请求页面数据*/
                isLoadMore = false;
                currentPage = 1;
                loadView.showLoading();
                mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);
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
        layout.removeAllViews();
        treeDataEntity = entry;
        if(treeDataEntity != null && treeDataEntity.getData() != null && treeDataEntity.getData().size() > 0){
            initTreeView();
        }
    }

    @Override
    public void loadPaperListSuc(EvaluationListEntity entry) {
        EvaluationListEntity.DataBean.PageInfoBean pageInfoBean = entry.getData().getPage_info();
        if(isLoadMore){
            mList.addAll(entry.getData().getPapers());
        }else{
            mList = entry.getData().getPapers();
        }
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
        if(mList != null && mList.size() > 0){
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
            /**请求知识树*/
            checkId = null;
            mPresenter.initTreeData(EvaluationDetailsActivity.this,paperType,gradeId,semesterId,subjectId,textbookId,MyApplication.getMyApp().getUserId());
            /**请求页面数据*/
            isLoadMore = false;
            currentPage = 1;
            loadView.showLoading();
            mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);

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

        /**请求知识树*/
        checkId = null;
        mPresenter.initTreeData(EvaluationDetailsActivity.this,paperType,gradeId,semesterId,subjectId,textbookId,MyApplication.getMyApp().getUserId());
        /**请求页面数据*/
        isLoadMore = false;
        currentPage = 1;
        loadView.showLoading();
        mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);

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
        /**请求知识树*/
        checkId = null;
        mPresenter.initTreeData(EvaluationDetailsActivity.this,paperType,gradeId,semesterId,subjectId,textbookId,MyApplication.getMyApp().getUserId());
        /**请求页面数据*/
        isLoadMore = false;
        currentPage = 1;
        loadView.showLoading();
        mPresenter.queryPaperList(EvaluationDetailsActivity.this,gradeId,subjectId,textbookId,semesterId,ptype,MyApplication.getMyApp().getUserId(),userType,String.valueOf(currentPage),pageSize,checkId,null);

    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    public void loadTreeEmpty() {
        layout.removeAllViews();
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

    @Override
    public void onItemClick(int position) {
        /**跳转页面答题*/
        Intent intent = new Intent(getContext(), AnswerWebViewActivity.class);
        intent.putExtra("isanswered",mList.get(position).getIsanswered());
        intent.putExtra("paperId",mList.get(position).getId());
        intent.putExtra("titleName",mList.get(position).getName());
        startActivity(intent);
    }
}
