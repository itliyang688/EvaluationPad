package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.base.BasePresenter;
import com.google.gson.Gson;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.TreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.view.adapter.EvaluationAdapter;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsChildSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsParentSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsTagSection;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: EvaluationDetailsActivity
 * @Description:
 * @CreateDate: 2019/10/30 13:14
 */
public class EvaluationDetailsActivity extends BaseActivity {
    @BindView(R.id.recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.label)
    LinearLayout label;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private DictionaryListResp mEntry;
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

    @Override
    public int setLayoutResource() {
        return R.layout.evaluation_details_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        setDefaultTitle(intent.getStringExtra("titleName"));
        checkId = intent.getStringExtra("checkId");
        gradeId = intent.getStringExtra("gradeId");
        subjectId = intent.getStringExtra("subjectId");
        semesterId = intent.getStringExtra("semesterId");
        textbookId = intent.getStringExtra("textbookId");
        mEntry = new Gson().fromJson(intent.getStringExtra("mEntryJson"), DictionaryListResp.class);
        treeDataEntity = new Gson().fromJson(intent.getStringExtra("mTreeDataJson"), TreeDataEntity.class);
        initLeftRecycler();
        initLabelAdapter();
        initTreeView();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        evaluationAdapter = new EvaluationAdapter(EvaluationDetailsActivity.this);
        recyclerView.setAdapter(evaluationAdapter);
        PhoenixHeader header = new PhoenixHeader(this);
        //ClassicsHeader header = new ClassicsHeader(this);
        //header.setTextSizeTime(24f);
        //header.setTextSizeTime(26f);
        refreshLayout.setRefreshHeader(header);
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    private void initLeftRecycler() {
        leftAdapter = new SectionedRecyclerViewAdapter();
        GridLayoutManager manager = new GridLayoutManager(getContext(), 12);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 12;
                }else if(position == tagPos+2){
                    return 12;
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
                TreeNode node1 = new TreeNode(new TreeParentItemHolder.IconTreeItem(dataBean.getName())).setViewHolder(new TreeParentItemHolder(getContext()));
                for (TreeDataEntity.DataBean.ChildsBeanXX childsBeanXX : node2List) {
                    List<TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX> node3List = childsBeanXX.getChilds();
                    if (node3List != null && node3List.size() > 0) {
                        TreeNode node2 = new TreeNode(new TreeParentItemHolder.IconTreeItem(childsBeanXX.getName())).setViewHolder(new TreeParentItemHolder(getContext()));
                        node1.addChild(node2);
                        /**第三级*/
                        for (TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX childsBeanX : node3List) {
                            List<TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX.ChildsBean> node4List = childsBeanX.getChilds();
                            if (node4List != null && node4List.size() > 0) {
                                TreeNode node3 = new TreeNode(new TreeParentItemHolder.IconTreeItem(childsBeanX.getName())).setViewHolder(new TreeParentItemHolder(getContext()));
                                node2.addChild(node3);
                                /**第四级*/
                                for (TreeDataEntity.DataBean.ChildsBeanXX.ChildsBeanX.ChildsBean childsBean : node4List) {
                                    TreeNode file4 = new TreeNode(new TreeChildItemHolder.IconTreeItem(childsBean.getName(), String.valueOf(childsBean.getId()), String.valueOf(childsBean.getParentId()))).setViewHolder(new TreeChildItemHolder(getContext()));
                                    node3.addChild(file4);
                                    nodeListenerAndUpdate(file4);
                                    defaultCheck(file4, String.valueOf(childsBean.getId()));
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new TreeChildItemHolder.IconTreeItem(childsBeanX.getName(), String.valueOf(childsBeanX.getId()), String.valueOf(childsBeanX.getParentId()))).setViewHolder(new TreeChildItemHolder(getContext()));
                                node2.addChild(file3);
                                nodeListenerAndUpdate(file3);
                                defaultCheck(file3, String.valueOf(childsBeanX.getId()));
                            }
                        }
                    } else {
                        TreeNode file2 = new TreeNode(new TreeChildItemHolder.IconTreeItem(childsBeanXX.getName(), String.valueOf(childsBeanXX.getId()), String.valueOf(childsBeanXX.getParentId()))).setViewHolder(new TreeChildItemHolder(getContext()));
                        node1.addChild(file2);
                        nodeListenerAndUpdate(file2);
                        defaultCheck(file2, String.valueOf(childsBeanXX.getId()));
                    }
                }
                root.addChild(node1);
            } else {
                /**添加一级跟文件*/
                TreeNode file1 = new TreeNode(new TreeChildItemHolder.IconTreeItem(dataBean.getName(), String.valueOf(dataBean.getId()), String.valueOf(0))).setViewHolder(new TreeChildItemHolder(getContext()));
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
        if (checkId.equals(id)) {
            selectNode = fileNode;
            TreeNode treeNode3 = fileNode.getParent();
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
            fileNode.setExpanded(true);
            fileNode.setSelected(true);
            fileNode.setSelectable(true);
        }
    }

    private void nodeListenerAndUpdate(TreeNode fileNode) {
        fileNode.setClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
                TreeChildItemHolder.IconTreeItem treeItem = (TreeChildItemHolder.IconTreeItem) value;
                String id = treeItem.id;
                String parentId = treeItem.parentId;
                String name = treeItem.text;
                TreeChildItemHolder holder = (TreeChildItemHolder) node.getViewHolder();
                holder.arrowView.setImageResource(R.mipmap.check_icon);
                holder.tvValue.setTextColor(Color.parseColor("#FEAE2D"));
                if (!id.equals(checkId)) {
                    TreeChildItemHolder selectHolder = (TreeChildItemHolder) selectNode.getViewHolder();
                    selectHolder.arrowView.setImageResource(R.mipmap.file_icon);
                    selectHolder.tvValue.setTextColor(Color.parseColor("#333333"));
                    checkId = id;
                    selectNode = node;
                    /**请求数据*/
                }
            }
        });
    }

    private void initLabelAdapter() {
        List<DictionaryListResp.DataBean> list = mEntry.getData();
        leftAdapter.addSection("parent", new EvaluationDetailsParentSection(list,gradeId, new EvaluationDetailsParentSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.notifyDataSetChanged();
            }
        }));

        tagPos = mEntry.getData().size() + list.get(0).getTabInfo().getSubject().size();
        leftAdapter.addSection("subject", new EvaluationDetailsChildSection(list.get(0).getTabInfo().getSubject(), subjectId,new EvaluationDetailsChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");
            }
        }));

        leftAdapter.addSection("textbook", new EvaluationDetailsTagSection(getContext(),list.get(0).getTabInfo().getTextbook(),textbookId, new EvaluationDetailsTagSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("textbook").notifyAllItemsChanged("payloads");

            }
        }));
        leftAdapter.addSection("semester", new EvaluationDetailsChildSection(list.get(0).getTabInfo().getSemester(),semesterId, new EvaluationDetailsChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
            }
        }));
        leftAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onLoadData() {

    }

    @Override
    protected BasePresenter onInitLogicImpl() {
        return super.onInitLogicImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
