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
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ISynchroVideoTree;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.SynchroVideoTreePresenter;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.adapter.SynchroVideoChildSection;
import cn.fek12.evaluation.view.adapter.SynchroVideoParentSection;
import cn.fek12.evaluation.view.adapter.SynchroVideoSubjectSection;
import cn.fek12.evaluation.view.adapter.SynchroVideoTagSection;
import cn.fek12.evaluation.view.adapter.VideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: PrimarySchoolSynchroVideoActivity
 * @Description:
 * @CreateDate: 2020/6/29 13:14
 */
public class PrimarySchoolSynchroVideoActivity extends BaseActivity<SynchroVideoTreePresenter> implements ISynchroVideoTree.View, VideoAdapter.OnItemClickListener {
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
    private VideoAdapter videoAdapter;
    private int tagPos;
    private String gradeId;
    private String subjectId;
    private String semesterId;
    private String textbookId;

    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;
    private List<VideoMoreListEntity.DataBean> mList;

    private int currentPage = 1;
    private boolean isLoadMore = false;
    private String paperType = "SWEETOWN";

    @Override
    public int setLayoutResource() {
        return R.layout.primary_school_synchro_video;
    }

    @Override
    protected void onInitView() {
        initLeftRecycler();
        initLabelAdapter();

        mPresenter.queryGradeDictionaryList(getContext());
        /**请求知识树*/
        checkId = null;

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(PrimarySchoolSynchroVideoActivity.this);
        videoAdapter.setOnItemClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(videoAdapter);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(false);
        initData();
    }

    private void initData() {
        loadView.showLoading();
        mPresenter.queryPaperList(this, gradeId, subjectId, textbookId, semesterId, String.valueOf(currentPage), checkId, checkId, String.valueOf(1));
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
        }
    };

    private void initLeftRecycler() {
        leftAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 10);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 10;
                } else if (position == tagPos + 2) {
                    return 10;
                } else {
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
                TreeNode node1 = new TreeNode(new TreeParentItemHolder.IconTreeItem(dataBean.getName())).setViewHolder(new TreeParentItemHolder(getContext(), TreeParentItemHolder.NOW));
                for (TreeDataEntity.DataBean.ChildsBeanXX childsBeanXX : node2List) {
                    List<TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX> node3List = childsBeanXX.getChilds();
                    if (node3List != null && node3List.size() > 0) {
                        TreeNode node2 = new TreeNode(new TreeParentItemHolder.IconTreeItem(childsBeanXX.getName())).setViewHolder(new TreeParentItemHolder(getContext(), TreeParentItemHolder.NOW));
                        node1.addChild(node2);
                        /**第三级*/
                        for (TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX childsBeanX : node3List) {
                            List<TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX.ChildsBean> node4List = childsBeanX.getChilds();
                            if (node4List != null && node4List.size() > 0) {
                                TreeNode node3 = new TreeNode(new TreeParentItemHolder.IconTreeItem(childsBeanX.getName())).setViewHolder(new TreeParentItemHolder(getContext(), TreeParentItemHolder.NOW));
                                node2.addChild(node3);
                                /**第四级*/
                                for (TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX.ChildsBean childsBean : node4List) {
                                    TreeNode file4 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBean.getName(), String.valueOf(childsBean.getId()), String.valueOf(childsBean.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(), AutoTreeChildItemHolder.NOW));
                                    node3.addChild(file4);
                                    nodeListenerAndUpdate(file4);
                                    defaultCheck(file4, String.valueOf(childsBean.getId()));
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanX.getName(), String.valueOf(childsBeanX.getId()), String.valueOf(childsBeanX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(), AutoTreeChildItemHolder.NOW));
                                node2.addChild(file3);
                                nodeListenerAndUpdate(file3);
                                defaultCheck(file3, String.valueOf(childsBeanX.getId()));
                            }
                        }
                    } else {
                        TreeNode file2 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanXX.getName(), String.valueOf(childsBeanXX.getId()), String.valueOf(childsBeanXX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext(), AutoTreeChildItemHolder.NOW));
                        node1.addChild(file2);
                        nodeListenerAndUpdate(file2);
                        defaultCheck(file2, String.valueOf(childsBeanXX.getId()));
                    }
                }
                root.addChild(node1);
                node1.setExpanded(true);
            } else {
                /**添加一级跟文件*/
                TreeNode file1 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(dataBean.getName(), String.valueOf(dataBean.getId()), String.valueOf(0))).setViewHolder(new AutoTreeChildItemHolder(getContext(), AutoTreeChildItemHolder.NOW));
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
                isFocusTreeNode(node, true);
                if (!id.equals(checkId)) {
                    if (selectNode != null) {
                        isFocusTreeNode(selectNode, false);
                    }
                    checkId = id;
                    selectNode = node;
                    /**请求数据*/
                    isLoadMore = false;
                    currentPage = 1;
                    initData();
                }
            }
        });
    }

    private void isFocusTreeNode(TreeNode treeNode, boolean isFocus) {
        AutoTreeChildItemHolder selectHolder = (AutoTreeChildItemHolder) treeNode.getViewHolder();
        selectHolder.isCheck = true;
        if (isFocus) {
            selectHolder.arrowView.setImageResource(R.mipmap.check_box_check);
            selectHolder.tvValue.setTextColor(Color.parseColor("#309BFF"));
        } else {
            selectHolder.arrowView.setImageResource(R.mipmap.check_box_normal);
            selectHolder.tvValue.setTextColor(Color.parseColor("#333333"));
        }
    }

    private void initLabelAdapter() {
        leftAdapter.addSection("parent", new SynchroVideoParentSection(gradeList, new SynchroVideoParentSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                gradeId = String.valueOf(gradeList.get(pos).getId());
                leftAdapter.getAdapterForSection("parent").notifyAllItemsChanged("payloads");

                /**请求二三四级数据*/
                mPresenter.querySubjectList(getContext(), gradeId);
            }
        }));

        leftAdapter.addSection("subject", new SynchroVideoSubjectSection(subjectList, subjectId, new SynchroVideoSubjectSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                subjectId = String.valueOf(subjectList.get(pos).getId());
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

                mPresenter.queryTextBookList(getContext(), gradeId, subjectId);
            }
        }));

        leftAdapter.addSection("textbook", new SynchroVideoTagSection(getContext(), textBookList, textbookId, new SynchroVideoTagSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("textbook").notifyAllItemsChanged("payloads");
                textbookId = String.valueOf(textBookList.get(pos).getId());

                /**点击版本去查询教材*/
                mPresenter.querySemesterList(getContext(), gradeId, subjectId, textbookId);

            }
        }));
        leftAdapter.addSection("semester", new SynchroVideoChildSection(semesterList, semesterId, new SynchroVideoChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
                semesterId = String.valueOf(semesterList.get(pos).getId());
                /**请求知识树*/
                checkId = null;
                mPresenter.initTreeData(PrimarySchoolSynchroVideoActivity.this, paperType, gradeId, semesterId, subjectId, textbookId, MyApplication.getMyApplication().getUserId());
                /**请求页面数据*/
                isLoadMore = false;
                currentPage = 1;
                initData();
            }
        }));
        leftAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    protected SynchroVideoTreePresenter onInitLogicImpl() {
        return new SynchroVideoTreePresenter(this, getContext());
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
        if (treeDataEntity != null && treeDataEntity.getData() != null && treeDataEntity.getData().size() > 0) {
            initTreeView();
        }
    }

    @Override
    public void loadVideoTreeListSuc(VideoMoreListEntity entry) {
        mList = entry.getData();
        if (mList != null && mList.size() > 0) {
            loadView.showContent();
            videoAdapter.notifyChanged(mList, false);
        } else {
            loadView.showEmpty();
        }
    }

    @Override
    public void loadGradeDictionarySuc(GradeDictionaryListEntity entry) {
        gradeList = entry.getData();
        if (gradeList != null && gradeList.size() > 0) {
            loadView.showContent();
            gradeId = String.valueOf(gradeList.get(0).getId());
            SynchroVideoParentSection parentSection = (SynchroVideoParentSection) leftAdapter.getSection("parent");
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
        subjectList = entry.getData();
        if (subjectList != null && subjectList.size() > 0) {
            loadView.showContent();
            tagPos = gradeList.size() + subjectList.size();
            subjectId = String.valueOf(subjectList.get(0).getId());

            SynchroVideoSubjectSection subjectSection = (SynchroVideoSubjectSection) leftAdapter.getSection("subject");
            subjectSection.updateList(subjectList);

            textBookList = subjectList.get(0).getTextbook();
            SynchroVideoTagSection tagChildSection = (SynchroVideoTagSection) leftAdapter.getSection("textbook");
            if (textBookList != null && textBookList.size() > 0) {
                textbookId = String.valueOf(textBookList.get(0).getId());
                tagChildSection.updateList(textBookList);

                semesterList = textBookList.get(0).getSemester();
                SynchroVideoChildSection semesterSection = (SynchroVideoChildSection) leftAdapter.getSection("semester");
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
            mPresenter.initTreeData(PrimarySchoolSynchroVideoActivity.this, paperType, gradeId, semesterId, subjectId, textbookId, MyApplication.getMyApplication().getUserId());
            /**请求页面数据*/
            isLoadMore = false;
            currentPage = 1;
            initData();

        }
    }

    @Override
    public void loadTextBookSuc(TextbookEntity entry) {
        textBookList = entry.getData();
        if (textBookList != null && textBookList.size() > 0) {
            loadView.showContent();
            textbookId = String.valueOf(textBookList.get(0).getId());

            SynchroVideoTagSection tagChildSection = (SynchroVideoTagSection) leftAdapter.getSection("textbook");
            tagChildSection.updateList(textBookList);

            semesterList = textBookList.get(0).getSemester();
            SynchroVideoChildSection semesterSection = (SynchroVideoChildSection) leftAdapter.getSection("semester");
            if (semesterList != null && semesterList.size() > 0) {
                semesterId = String.valueOf(semesterList.get(0).getId());
                semesterSection.updateList(semesterList);
            } else {
                semesterId = null;
                semesterSection.updateList(null);
            }
            leftAdapter.notifyDataSetChanged();
        }

        /**请求知识树*/
        checkId = null;
        mPresenter.initTreeData(PrimarySchoolSynchroVideoActivity.this, paperType, gradeId, semesterId, subjectId, textbookId, MyApplication.getMyApplication().getUserId());
        /**请求页面数据*/
        isLoadMore = false;
        currentPage = 1;
        initData();

    }

    @Override
    public void loadSemesterSuc(SemesterEntity entry) {
        semesterList = entry.getData();
        if (semesterList != null && semesterList.size() > 0) {
            semesterId = String.valueOf(semesterList.get(0).getId());
            SynchroVideoChildSection semesterSection = (SynchroVideoChildSection) leftAdapter.getSection("semester");
            semesterSection.updateList(semesterList);

            leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
        }
        /**请求知识树*/
        checkId = null;
        mPresenter.initTreeData(PrimarySchoolSynchroVideoActivity.this, paperType, gradeId, semesterId, subjectId, textbookId, MyApplication.getMyApplication().getUserId());
        /**请求页面数据*/
        isLoadMore = false;
        currentPage = 1;
        initData();

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
    public void loadVideoTreeListEmpty() {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void onItemClick(int position) {
        startSpecialVideo(position, FullScreenVideoPlayActivity.class);
    }

    private void startSpecialVideo(int pos, Class cla) {
        String path = "";
        try {
            path = FastDFSUtil.generateSourceUrl(mList.get(pos).getAddressUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getContext(), cla);
        intent.putExtra("pathUrl", path);
        intent.putExtra("videoName", mList.get(pos).getVideoName());
        intent.putExtra("chapter", mList.get(pos).getSpecialName());
        intent.putExtra("cacheKey", mList.get(pos).getCacheKey());
        intent.putExtra("structLayKey", mList.get(pos).getStructLayKey());
        intent.putExtra("videoType", mList.get(pos).getType());
        intent.putExtra("videoId", mList.get(pos).getVideoId());
        intent.putExtra("describe", mList.get(pos).getIntroduction());
        intent.putExtra("isCollection", mList.get(pos).getIsCollection());
        intent.putExtra("playScheduleTime", mList.get(pos).getPlayScheduleTime());
        startActivity(intent);
    }

    @OnClick(R.id.iv_left_back)
    public void onViewClicked() {
        PrimarySchoolSynchroVideoActivity.this.finish();
    }
}
