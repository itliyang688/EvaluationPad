package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fek12.basic.base.BaseActivity;
import com.google.gson.Gson;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.ITreeView;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.TreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.TreeViewPresenter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: TreeViewDialogActivity
 * @Description:
 * @CreateDate: 2019/10/29 14:34
 */
public class TreeViewDialogActivity extends BaseActivity<TreeViewPresenter> implements ITreeView.View {
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    @BindView(R.id.tvText)
    TextView tvText;

    private String gradeId;
    private String titleName;
    private String paperType;
    private String subjectId;
    private String semesterId;
    private String textbookId;
    private String ptype;
    private TreeDataEntity mEntry;
    private String containListEntityJson;
    private String typePage;
    private int typePos;

    @Override
    public int setLayoutResource() {
        return R.layout.tree_dialog_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        titleName = intent.getStringExtra("titleName");
        paperType = intent.getStringExtra("paperType");
        typePos = intent.getIntExtra("typePos",0);
        typePage = intent.getStringExtra("typePage");
        gradeId = intent.getStringExtra("gradeId");
        subjectId = intent.getStringExtra("subjectId");
        semesterId = intent.getStringExtra("semesterId");
        textbookId = intent.getStringExtra("textbookId");
        ptype = intent.getStringExtra("ptype");
        containListEntityJson = intent.getStringExtra("containListEntityJson");
    }

    @Override
    protected void onLoadData() {
        loadView.showLoading();
        mPresenter.initTreeData(getContext(), paperType,gradeId, semesterId, subjectId, textbookId, "413");
    }

    @Override
    public void loadSuc(TreeDataEntity entry) {
        if (entry == null || entry.getData() == null || entry.getData().size() == 0) {
            loadView.showEmpty();
            return;
        }
        this.mEntry = entry;
        loadView.showContent();
        //创建根节点
        TreeNode root = TreeNode.root();
        /**第一级*/
        List<TreeDataEntity.DataBean> node1List = entry.getData();
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
                                    TreeNode file4 = new TreeNode(new TreeChildItemHolder.IconTreeItem(childsBean.getName(),String.valueOf(childsBean.getId()),String.valueOf(childsBean.getParentId()))).setViewHolder(new TreeChildItemHolder(getContext()));
                                    node3.addChild(file4);
                                    nodeListener(file4);
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new TreeChildItemHolder.IconTreeItem(childsBeanX.getName(),String.valueOf(childsBeanX.getId()),String.valueOf(childsBeanX.getParentId()))).setViewHolder(new TreeChildItemHolder(getContext()));
                                node2.addChild(file3);
                                nodeListener(file3);
                            }
                        }
                    } else {
                        TreeNode file2 = new TreeNode(new TreeChildItemHolder.IconTreeItem(childsBeanXX.getName(),String.valueOf(childsBeanXX.getId()),String.valueOf(childsBeanXX.getParentId()))).setViewHolder(new TreeChildItemHolder(getContext()));
                        node1.addChild(file2);
                        nodeListener(file2);
                    }
                }
                root.addChild(node1);
            }else{
                /**添加一级跟文件*/
                TreeNode file1 = new TreeNode(new TreeChildItemHolder.IconTreeItem(dataBean.getName(),String.valueOf(dataBean.getId()),String.valueOf(0))).setViewHolder(new TreeChildItemHolder(getContext()));
                root.addChild(file1);
                nodeListener(file1);
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

        tvText.setText("选择知识点");
    }

    private void nodeListener(TreeNode fileNode){
        fileNode.setClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
                TreeChildItemHolder.IconTreeItem treeItem = (TreeChildItemHolder.IconTreeItem) value;
                String id = treeItem.id;
                String parentId = treeItem.parentId;
                String name = treeItem.text;
               if(typePage.equals("1")){
                   startActivityIntent(id,MicroLessonTreeActivity.class);
               }else{
                   startActivityIntent(id,EvaluationDetailsActivity.class);
               }
            }
        });
    }

    private void startActivityIntent(String id,Class clazz) {
        Intent intent = new Intent(TreeViewDialogActivity.this,clazz);
        intent.putExtra("checkId",id);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("titleName",titleName);
        intent.putExtra("ptype",ptype);
        intent.putExtra("paperType", paperType);
        intent.putExtra("typePos", typePos);
        intent.putExtra("userType", "1");//类型 测评1 自主测2
        intent.putExtra("containListEntityJson",containListEntityJson);
        intent.putExtra("mTreeDataJson",new Gson().toJson(mEntry));
        TreeViewDialogActivity.this.startActivity(intent);
        TreeViewDialogActivity.this.finish();
    }

    @Override
    public void loadFail(String msg) {
        loadView.showError();
    }

    @Override
    public void loadEmpty() {
        loadView.showEmpty();
    }

    @Override
    protected TreeViewPresenter onInitLogicImpl() {
        return new TreeViewPresenter(this, this);
    }
}
