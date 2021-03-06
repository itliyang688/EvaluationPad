package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
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
import cn.fek12.evaluation.impl.IPrepareExamination;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.model.entity.SubjectModel;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.PrepareExaminationPresenter;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.adapter.PrepareExaminationSubjectSection;
import cn.fek12.evaluation.view.adapter.PrimarySchoolVideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: EvaluationDetailsActivity
 * @Description:
 * @CreateDate: 2019/10/30 13:14
 */
public class PrepareExaminationActivity extends BaseActivity<PrepareExaminationPresenter> implements IPrepareExamination.View, PrimarySchoolVideoAdapter.OnItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    private SectionedRecyclerViewAdapter leftAdapter;
    private TreeDataEntity treeDataEntity;
    private String checkId = null;
    private TreeNode selectNode;
    private PrimarySchoolVideoAdapter videoAdapter;

    private List<SubjectModel.DataBean> subjectList;
    private List<MicrolessonVideoEntity.DataBean.RecordsBean> mList;

    private int currentPage = 1;
    private String pageSize = "20";
    private boolean isLoadMore = false;
    private String paperType;//1.备考 2.赢在中考
    private String subjectId = "";

    @Override
    public int setLayoutResource() {
        return R.layout.prepare_examination_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        setEmptyTitle();
        paperType = intent.getStringExtra("paperType");

        initLeftRecycler();
        //initTreeView();

        /**请求学科*/
        mPresenter.querySubjectList(this,paperType);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new PrimarySchoolVideoAdapter(PrepareExaminationActivity.this);
        videoAdapter.setOnItemClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(videoAdapter);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        //refreshLayout.setEnableLoadmore(false);
        //refreshLayout.setEnableRefresh(false);
    }

    private void initTree(){
        /**请求知识树*/
        mPresenter.initTreeData(PrepareExaminationActivity.this,subjectId);
    }

    private void initData(){
        loadView.showLoading();
        mPresenter.queryPaperList(this,subjectId,checkId,MyApplication.getMyApp().getUserId(),String.valueOf(currentPage),pageSize);
    }
    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryPaperList(PrepareExaminationActivity.this,subjectId,checkId,MyApplication.getMyApp().getUserId(),String.valueOf(currentPage),pageSize);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryPaperList(PrepareExaminationActivity.this,subjectId,checkId,MyApplication.getMyApp().getUserId(),String.valueOf(currentPage),pageSize);
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
                                    TreeNode file4 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBean.getName(), String.valueOf(childsBean.getId()), String.valueOf(childsBean.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(),AutoTreeChildItemHolder.ORIGINAL));
                                    node3.addChild(file4);
                                    nodeListenerAndUpdate(file4);
                                    defaultCheck(file4, String.valueOf(childsBean.getId()));
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanX.getName(), String.valueOf(childsBeanX.getId()), String.valueOf(childsBeanX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(),AutoTreeChildItemHolder.ORIGINAL));
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
                /*if (!id.equals(checkId)) {
                    if(selectNode != null){
                        isFocusTreeNode(selectNode,false);
                    }
                    checkId = id;
                    selectNode = node;
                    *//**请求数据*//*
                    isLoadMore = false;
                    currentPage = 1;
                    initData();
                }*/

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
                initData();
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

        leftAdapter.addSection("subject", new PrepareExaminationSubjectSection(subjectList, subjectId, new PrepareExaminationSubjectSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                subjectId = String.valueOf(subjectList.get(pos).getId());
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

               /* *请求知识树*/
                checkId = null;
                //mPresenter.initTreeData(PrepareExaminationActivity.this,paperType,gradeId,semesterId,subjectId,textbookId,MyApplication.getMyApplication().getUserId());
                /**请求页面数据*/
                isLoadMore = false;
                currentPage = 1;
                initTree();
                initData();
            }
        }));

        leftAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    protected PrepareExaminationPresenter onInitLogicImpl() {
        return new PrepareExaminationPresenter(this, getContext());
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
    public void loadVideoListSuc(MicrolessonVideoEntity entry) {
        loadView.showContent();
        if(isLoadMore){
            mList.addAll(entry.getData().getRecords());
        }else{
            mList = entry.getData().getRecords();
        }
        if(entry.getData().getPages() == 0){
            loadView.showEmpty();
            return;
        }
        if(entry.getData().getPages() > currentPage){
            refreshLayout.setEnableLoadmore(true);
        }else{
            refreshLayout.setEnableLoadmore(false);
        }

        if(mList != null && mList.size() > 0){
            videoAdapter.notifyChanged(mList,isLoadMore);
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadSubjectSuc(SubjectModel entry) {
        if(entry != null && entry.getData() != null && entry.getData().size() > 0){
            subjectList = entry.getData();
            subjectId = String.valueOf(subjectList.get(0).getId());
            initLabelAdapter();
            /**请求页面数据*/
            initData();
            initTree();
        }
    }


    @Override
    public void loadFail(String msg) {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadTreeEmpty() {
        layout.removeAllViews();
    }

    @Override
    public void loadDictionaryEmpty() {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadVideoListEmpty() {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void onItemClick(int position) {
        /*String path = "";
        try {
            path = FastDFSUtil.generateSourceUrl(mList.get(position).getVideoUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        Intent intent = new Intent(this,MicrolessonVideoPlayActivity.class);
        intent.putExtra("pathUrl",mList.get(position).getVideoUrl());
        intent.putExtra("videoName",mList.get(position).getVideoName());
        intent.putExtra("videoId",mList.get(position).getVideoId());
        intent.putExtra("imgUrl",mList.get(position).getImgUrl());
        intent.putExtra("playScheduleTime",mList.get(position).getPlayScheduleTime());
        intent.putExtra("isCollection",mList.get(position).getIsCollection());
        startActivity(intent);
    }
}
