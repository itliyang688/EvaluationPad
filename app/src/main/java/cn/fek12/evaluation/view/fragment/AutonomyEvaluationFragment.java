package cn.fek12.evaluation.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.fek12.basic.base.BaseFragment;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.IAutonomyEvaluation;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.AutonomyEvaluationPresenter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: AutonomyEvaluationFragment
 * @Description:
 * @CreateDate: 2019/10/29 11:07
 */
public class AutonomyEvaluationFragment extends BaseFragment<AutonomyEvaluationPresenter> implements AutonomyEvaluationPresenter.View {
    @BindView(R.id.layoutTree)
    LinearLayout layoutTree;
    @BindView(R.id.tag_group)
    TagView tagGroup;
    @BindView(R.id.tvEliminate)
    ImageView tvEliminate;
    private String gradeId;
    private String subjectId;
    private String semesterId;
    private String textbookId;
    private String typePage;
    private ArrayList<Tag> tags = new ArrayList<>();
    private Map<String,TreeNode> treeNodeMap = new HashMap<>();

    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_fragment_self;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        tvEliminate.setOnClickListener(onClickListener);

        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                Iterator<Map.Entry<String, TreeNode>> it = treeNodeMap.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, TreeNode> entry = it.next();
                    if(tag.getId().equals(entry.getKey())){
                        TreeNode treeNode = entry.getValue();
                        isFocusTreeNode(treeNode,false);
                    }
                }

                Iterator<Tag> itTag = tags.iterator();
                while(itTag.hasNext()){
                    Tag tagBean = itTag.next();
                    if(tagBean.getId().equals(tag.getId())){
                        itTag.remove();
                    }
                }

                tagGroup.addTags(new ArrayList<Tag>());
                tagGroup.addTags(tags);
            }
        });
    }

    @Override
    protected void onLoadDataRemote() {

    }

    public void queryTreeData(String grade, String semester, String subject, String textbook, String type, String userId) {
        gradeId = grade;
        semesterId = semester;
        subjectId = subject;
        textbookId = textbook;
        typePage = type;
        mPresenter.initTreeData(getContext(), gradeId, semesterId, subjectId, textbookId, userId, type);
    }

    @Override
    protected AutonomyEvaluationPresenter onInitLogicImpl() {
        return new AutonomyEvaluationPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void loadTreeSuc(TreeDataEntity entry) {
        layoutTree.removeAllViews();
        if (entry == null || entry.getData() == null || entry.getData().size() == 0) {
            return;
        }
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
                                    TreeNode file4 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBean.getName(), String.valueOf(childsBean.getId()), String.valueOf(childsBean.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                                    node3.addChild(file4);
                                    nodeListener(file4);
                                }
                            } else {
                                TreeNode file3 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanX.getName(), String.valueOf(childsBeanX.getId()), String.valueOf(childsBeanX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                                node2.addChild(file3);
                                nodeListener(file3);
                            }
                        }
                    } else {
                        TreeNode file2 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(childsBeanXX.getName(), String.valueOf(childsBeanXX.getId()), String.valueOf(childsBeanXX.getParentId()))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                        node1.addChild(file2);
                        nodeListener(file2);
                    }
                }
                root.addChild(node1);
            } else {
                /**添加一级跟文件*/
                TreeNode file1 = new TreeNode(new AutoTreeChildItemHolder.IconTreeItem(dataBean.getName(), String.valueOf(dataBean.getId()), String.valueOf(0))).setViewHolder(new AutoTreeChildItemHolder(getContext()));
                root.addChild(file1);
                nodeListener(file1);
            }
        }
        //创建树形视图
        AndroidTreeView tView = new AndroidTreeView(getContext(), root);

        //设置树形视图开启默认动画
        tView.setDefaultAnimation(true);
        //设置树形视图默认的样式
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleAutonomy, true);
        //设置树形视图默认的ViewHolder
        tView.setDefaultViewHolder(TreeParentItemHolder.class);
        //将树形视图添加到layout中
        layoutTree.addView(tView.getView());
    }

    private void nodeListener(TreeNode fileNode) {
        fileNode.setClickListener(new TreeNode.TreeNodeClickListener() {
            @Override
            public void onClick(TreeNode node, Object value) {
                isFocusTreeNode(node,true);

                AutoTreeChildItemHolder.IconTreeItem treeItem = (AutoTreeChildItemHolder.IconTreeItem) value;
                String id = treeItem.id;
                //String parentId = treeItem.parentId;
                String name = treeItem.text;
                boolean isAdd = false;
                for(Tag tagBean : tags){
                    if(tagBean.getId().equals(id)){
                        isAdd = true;
                    }
                }
                if(!isAdd){
                    Tag tag = new Tag(name);
                    tag.setRadius(10f);
                    tag.setLayoutColor(Color.parseColor("#ECECEC"));
                    tag.setTagTextColor(Color.parseColor("#333333"));
                    tag.setLayoutBorderColor(Color.parseColor("#999999"));
                    tag.setLayoutBorderSize(0.5f);
                    tag.setDeleteIndicatorColor(Color.parseColor("#999999"));
                    tag.setId(id);
                    tag.setDeletable(true);
                    setTags(tag);
                    treeNodeMap.put(id,node);
                }
            }
        });
    }

    private void setTags(Tag tag) {
        tags.add(tag);
        tagGroup.addTags(new ArrayList<Tag>());
        tagGroup.addTags(tags);
        tagGroup.setLineMargin(6f);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tvEliminate:
                    tags.clear();
                    tagGroup.addTags(tags);
                    for(Map.Entry<String, TreeNode> entry : treeNodeMap.entrySet()){
                        isFocusTreeNode(entry.getValue(),false);
                    }
                    treeNodeMap.clear();
                    break;
            }
        }
    };

    private void isFocusTreeNode(TreeNode treeNode,boolean isFocus){
        AutoTreeChildItemHolder selectHolder = (AutoTreeChildItemHolder) treeNode.getViewHolder();
        if(isFocus){
            selectHolder.arrowView.setImageResource(R.mipmap.check_icon);
            selectHolder.tvValue.setTextColor(Color.parseColor("#FEAE2D"));
        }else{
            selectHolder.arrowView.setImageResource(R.mipmap.file_icon);
            selectHolder.tvValue.setTextColor(Color.parseColor("#333333"));
        }
    }


    @Override
    public void loadTreeFail() {

    }

    @Override
    public void loadTreeEmpty() {

    }
}
