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
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.IEvaluationDetails;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.EvaluationDetailsPresenter;
import cn.fek12.evaluation.view.adapter.DictionaryChildSection;
import cn.fek12.evaluation.view.adapter.DictionarySubjectSection;
import cn.fek12.evaluation.view.adapter.DictionaryTagChildSection;
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
public class EvaluationDetailsActivity extends BaseActivity<EvaluationDetailsPresenter> implements EvaluationDetailsPresenter.View {
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
        treeDataEntity = new Gson().fromJson(intent.getStringExtra("mTreeDataJson"), TreeDataEntity.class);
        ContainListEntity containListEntity = new Gson().fromJson(intent.getStringExtra("containListEntityJson"), ContainListEntity.class);
        gradeList = containListEntity.getGradeList();
        subjectList = containListEntity.getSubjectList();
        textBookList = containListEntity.getTextBookList();
        semesterList = containListEntity.getSemesterList();

        tagPos = gradeList.size() + subjectList.size();

        initLeftRecycler();
        initLabelAdapter();
        initTreeView();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        evaluationAdapter = new EvaluationAdapter(EvaluationDetailsActivity.this);
        recyclerView.setAdapter(evaluationAdapter);
    }

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
                                    TreeNode file4 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBean.getName(), String.valueOf(childsBean.getId()), String.valueOf(childsBean.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                                    node3.addChild(file4);
                                    nodeListenerAndUpdate(file4);
                                    defaultCheck(file4, String.valueOf(childsBean.getId()));
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanX.getName(), String.valueOf(childsBeanX.getId()), String.valueOf(childsBeanX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                                node2.addChild(file3);
                                nodeListenerAndUpdate(file3);
                                defaultCheck(file3, String.valueOf(childsBeanX.getId()));
                            }
                        }
                    } else {
                        TreeNode file2 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanXX.getName(), String.valueOf(childsBeanXX.getId()), String.valueOf(childsBeanXX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                        node1.addChild(file2);
                        nodeListenerAndUpdate(file2);
                        defaultCheck(file2, String.valueOf(childsBeanXX.getId()));
                    }
                }
                root.addChild(node1);
            } else {
                /**添加一级跟文件*/
                TreeNode file1 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(dataBean.getName(), String.valueOf(dataBean.getId()), String.valueOf(0))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
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
                if (!id.equals(checkId)) {
                    isFocusTreeNode(selectNode,false);
                    checkId = id;
                    selectNode = node;
                    /**请求数据*/
                }
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

        } else {
            loadView.showEmpty();
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

            /**请求页面数据*/

        }else{
            loadView.showEmpty();
        }
    }

    @Override
    public void loadSemesterSuc(SemesterEntity entry) {
        semesterList = entry.getData();
        if(semesterList != null && semesterList.size() > 0){
            semesterId = String.valueOf(semesterList.get(0).getId());
            EvaluationDetailsChildSection semesterSection = (EvaluationDetailsChildSection) leftAdapter.getSection("semester");
            semesterSection.updateList(semesterList);

            leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");

            /**请求页面数据*/

        }else{
            loadView.showEmpty();
        }
    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    public void loadDictionaryEmpty() {

    }

    @Override
    public void loadPaperTypeEmpty() {

    }
}
