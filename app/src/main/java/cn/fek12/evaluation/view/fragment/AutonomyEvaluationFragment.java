package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;
import com.fek12.basic.base.BaseFragment;
import com.google.gson.Gson;
import com.stx.xmarqueeview.XMarqueeView;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.QueryTopicEntity;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TopicCountEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.AutonomyEvaluationPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.InputFilterMinMax;
import cn.fek12.evaluation.view.activity.AutonomyEvaluationListActivity;
import cn.fek12.evaluation.view.adapter.RecordsListAdapter;

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
    @BindView(R.id.tvTopicType1)
    TextView tvTopicType1;
    @BindView(R.id.tvTopicType2)
    TextView tvTopicType2;
    @BindView(R.id.tvThisYear)
    TextView tvThisYear;
    @BindView(R.id.tvLastYear)
    TextView tvLastYear;
    @BindView(R.id.tvEarlier)
    TextView tvEarlier;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvSingle)
    TextView tvSingle;
    @BindView(R.id.etSingle1)
    EditText etSingle1;
    @BindView(R.id.tvCountSingle1)
    TextView tvCountSingle1;
    @BindView(R.id.etSingle2)
    EditText etSingle2;
    @BindView(R.id.tvCountSingle2)
    TextView tvCountSingle2;
    @BindView(R.id.etSingle3)
    EditText etSingle3;
    @BindView(R.id.tvCountSingle3)
    TextView tvCountSingle3;
    @BindView(R.id.tvMultiple)
    TextView tvMultiple;
    @BindView(R.id.etMultiple1)
    EditText etMultiple1;
    @BindView(R.id.tvCountMultiple1)
    TextView tvCountMultiple1;
    @BindView(R.id.etMultiple2)
    EditText etMultiple2;
    @BindView(R.id.tvCountMultiple2)
    TextView tvCountMultiple2;
    @BindView(R.id.etMultiple3)
    EditText etMultiple3;
    @BindView(R.id.tvCountMultiple3)
    TextView tvCountMultiple3;
    @BindView(R.id.tvJudge)
    TextView tvJudge;
    @BindView(R.id.etJudge1)
    EditText etJudge1;
    @BindView(R.id.tvCountJudge1)
    TextView tvCountJudge1;
    @BindView(R.id.etJudge2)
    EditText etJudge2;
    @BindView(R.id.tvCountJudge2)
    TextView tvCountJudge2;
    @BindView(R.id.etJudge3)
    EditText etJudge3;
    @BindView(R.id.tvCountJudge3)
    TextView tvCountJudge3;
    @BindView(R.id.tvGenerate)
    TextView tvGenerate;
    @BindView(R.id.marqueeView)
    XMarqueeView marqueeView;
    @BindView(R.id.llContainMarquee)
    LinearLayout llContainMarquee;
    private String gradeId;
    private String subjectId;
    private String semesterId;
    private String textbookId;
    private String typePage;
    private ArrayList<Tag> tags = new ArrayList<>();
    private Map<String, TreeNode> treeNodeMap = new HashMap<>();

    private String thisYear = "0";
    private String lastYear = "0";
    private String earlierYear = "0";
    private String single = "0";
    private String multiple = "0";
    private String judge = "0";
    private String topicType = "0";

    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;

    public void setLists(List<ChildSectionEntity> grades,List<SubjectEntity.DataBean> subjects,List<TextbookChildEntity> textBooks,List<ChildSectionEntity> semesters){
        gradeList = grades;
        subjectList = subjects;
        textBookList = textBooks;
        semesterList = semesters;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_fragment_self;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        tvThisYear.setText(String.valueOf(AppUtils.getYear()));
        tvLastYear.setText(String.valueOf(AppUtils.getYear() - 1));
        tvEliminate.setOnClickListener(onClickListener);
        tvTopicType1.setOnClickListener(onClickListener);
        tvTopicType2.setOnClickListener(onClickListener);
        tvThisYear.setOnClickListener(onClickListener);
        tvLastYear.setOnClickListener(onClickListener);
        tvEarlier.setOnClickListener(onClickListener);
        tvSingle.setOnClickListener(onClickListener);
        tvMultiple.setOnClickListener(onClickListener);
        tvJudge.setOnClickListener(onClickListener);
        llContainMarquee.setOnClickListener(onClickListener);

        etSingle1.setFilters(new InputFilter[]{new InputFilterMinMax("0", "200")});

        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
            @Override
            public void onTagClick(Tag tag, int position) {
                Iterator<Map.Entry<String, TreeNode>> it = treeNodeMap.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, TreeNode> entry = it.next();
                    if (tag.getId().equals(entry.getKey())) {
                        TreeNode treeNode = entry.getValue();
                        isFocusTreeNode(treeNode, false);
                    }
                }

                Iterator<Tag> itTag = tags.iterator();
                while (itTag.hasNext()) {
                    Tag tagBean = itTag.next();
                    if (tagBean.getId().equals(tag.getId())) {
                        itTag.remove();
                    }
                }

                tagGroup.addTags(new ArrayList<Tag>());
                tagGroup.addTags(tags);

                queryTopic();
            }
        });
    }


    public void queryTreeData(String grade, String semester, String subject, String textbook, String type, String userId) {
        gradeId = grade;
        semesterId = semester;
        subjectId = subject;
        textbookId = textbook;
        typePage = type;
        mPresenter.initTreeData(getContext(), gradeId, semesterId, subjectId, textbookId, userId, type);
        mPresenter.queryRecordsList(getContext(), userId);
        queryTopic();
    }

    private void queryTopic() {
        List<String> knowledges = new ArrayList<>();
        List<String> years = new ArrayList<>();
        QueryTopicEntity topicEntity = new QueryTopicEntity();
        if (tags.size() > 0) {
            for (Tag tag : tags) {
                knowledges.add(tag.getId());
            }
            topicEntity.setKnowledges(knowledges);
        }

        if (!thisYear.equals("0")) {
            years.add(thisYear);
        }

        if (!lastYear.equals("0")) {
            years.add(lastYear);
        }

        if (!earlierYear.equals("0")) {
            topicEntity.setEarlyYear(tvLastYear.getText().toString());
            years.add(tvLastYear.getText().toString());
        }

        if (years.size() > 0) {
            topicEntity.setYears(years);
        }
        topicEntity.setWay(topicType);
        topicEntity.setGrade(gradeId);
        topicEntity.setSemester(semesterId);
        topicEntity.setSubject(subjectId);
        topicEntity.setTextbook(textbookId);
        String json = new Gson().toJson(topicEntity);
        mPresenter.queryTopicCount(getContext(), json);
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
    public void loadTopicCountSuc(TopicCountEntity entity) {
        TopicCountEntity.DataBean.TopicCountBean singleBean = entity.getData().getSingle();
        if (singleBean != null) {
            int common = singleBean.getCommon();
            int difficult = singleBean.getDifficult();
            int easy = singleBean.getEasy();
            tvCountSingle3.setText(difficult > 999 ? "999+道题可用" : difficult + "道题可用");
            tvCountSingle2.setText(common > 999 ? "999+道题可用" : common + "道题可用");
            tvCountSingle1.setText(easy > 999 ? "999+道题可用" : easy + "道题可用");
        }
        TopicCountEntity.DataBean.TopicCountBean multipleBean = entity.getData().getMultiple();
        if (multipleBean != null) {
            int common = multipleBean.getCommon();
            int difficult = multipleBean.getDifficult();
            int easy = multipleBean.getEasy();
            tvCountMultiple3.setText(difficult > 999 ? "999+道题可用" : difficult + "道题可用");
            tvCountMultiple2.setText(common > 999 ? "999+道题可用" : common + "道题可用");
            tvCountMultiple1.setText(easy > 999 ? "999+道题可用" : easy + "道题可用");
        }
        TopicCountEntity.DataBean.TopicCountBean judgeBean = entity.getData().getJudge();
        if (judgeBean != null) {
            int common = judgeBean.getCommon();
            int difficult = judgeBean.getDifficult();
            int easy = judgeBean.getEasy();
            tvCountJudge3.setText(difficult > 999 ? "999+道题可用" : difficult + "道题可用");
            tvCountJudge2.setText(common > 999 ? "999+道题可用" : common + "道题可用");
            tvCountJudge1.setText(easy > 999 ? "999+道题可用" : easy + "道题可用");
        }
    }

    @Override
    public void loadRecordsListSuc(RecordsEntitiy entity) {
        List<RecordsEntitiy.DataBean> list = entity.getData();
        RecordsListAdapter marqueeFactory = new RecordsListAdapter(list, getContext());
        marqueeView.setAdapter(marqueeFactory);
        if (list != null && list.size() <= 4) {
            marqueeView.stopFlipping();
        }
    }

    @Override
    public void loadTreeSuc(TreeDataEntity entry) {
        layoutTree.removeAllViews();
        tags.clear();
        tagGroup.addTags(tags);
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
                isFocusTreeNode(node, true);

                AutoTreeChildItemHolder.IconTreeItem treeItem = (AutoTreeChildItemHolder.IconTreeItem) value;
                String id = treeItem.id;
                //String parentId = treeItem.parentId;
                String name = treeItem.text;
                boolean isAdd = false;
                for (Tag tagBean : tags) {
                    if (tagBean.getId().equals(id)) {
                        isAdd = true;
                    }
                }
                if (!isAdd) {
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
                    treeNodeMap.put(id, node);
                }
            }
        });
    }

    private void setTags(Tag tag) {
        tags.add(tag);
        tagGroup.addTags(new ArrayList<Tag>());
        tagGroup.addTags(tags);
        tagGroup.setLineMargin(5f);
        /**添加一个tag请求一次数据*/
        queryTopic();
    }

    private void isFocusTreeNode(TreeNode treeNode, boolean isFocus) {
        AutoTreeChildItemHolder selectHolder = (AutoTreeChildItemHolder) treeNode.getViewHolder();
        if (isFocus) {
            selectHolder.arrowView.setImageResource(R.mipmap.check_icon);
            selectHolder.tvValue.setTextColor(Color.parseColor("#FEAE2D"));
        } else {
            selectHolder.arrowView.setImageResource(R.mipmap.file_icon);
            selectHolder.tvValue.setTextColor(Color.parseColor("#333333"));
        }
    }


    @Override
    public void loadTreeFail() {
        layoutTree.removeAllViews();
        tags.clear();
        tagGroup.addTags(tags);
    }

    @Override
    public void loadTreeEmpty() {
        layoutTree.removeAllViews();
        tags.clear();
        tagGroup.addTags(tags);
    }

    private void startActivityIntent() {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setGradeList(gradeList);
        containListEntity.setSemesterList(semesterList);
        containListEntity.setSubjectList(subjectList);
        containListEntity.setTextBookList(textBookList);
        Intent intent = new Intent(getContext(), AutonomyEvaluationListActivity.class);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("containListEntityJson", new Gson().toJson(containListEntity));
        getActivity().startActivity(intent);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.llContainMarquee://点击自主测
                    startActivityIntent();
                    break;
                case R.id.tvJudge://判断题
                    if (judge.equals("0")) {
                        judge = tvJudge.getText().toString();
                        tvJudge.setSelected(true);
                    } else {
                        judge = "0";
                        tvJudge.setSelected(false);
                    }
                    break;
                case R.id.tvMultiple://多选题
                    if (multiple.equals("0")) {
                        multiple = tvMultiple.getText().toString();
                        tvMultiple.setSelected(true);
                    } else {
                        multiple = "0";
                        tvMultiple.setSelected(false);
                    }
                    break;
                case R.id.tvSingle://单选题
                    if (single.equals("0")) {
                        single = tvSingle.getText().toString();
                        tvSingle.setSelected(true);
                    } else {
                        single = "0";
                        tvSingle.setSelected(false);
                    }
                    break;
                case R.id.tvThisYear://今年
                    if (thisYear.equals("0")) {
                        thisYear = tvThisYear.getText().toString();
                        tvThisYear.setSelected(true);
                    } else {
                        thisYear = "0";
                        tvThisYear.setSelected(false);
                    }
                    queryTopic();
                    break;
                case R.id.tvLastYear://上一年
                    if (lastYear.equals("0")) {
                        lastYear = tvLastYear.getText().toString();
                        tvLastYear.setSelected(true);
                    } else {
                        lastYear = "0";
                        tvLastYear.setSelected(false);
                    }
                    queryTopic();
                    break;
                case R.id.tvEarlier://更早
                    if (earlierYear.equals("0")) {
                        earlierYear = "更早";
                        tvEarlier.setSelected(true);
                    } else {
                        earlierYear = "0";
                        tvEarlier.setSelected(false);
                    }
                    queryTopic();
                    break;
                case R.id.tvTopicType1://关联出题
                    topicType = "1";
                    tvTopicType1.setSelected(true);
                    tvTopicType2.setSelected(false);
                    queryTopic();
                    break;
                case R.id.tvTopicType2://精准出题
                    topicType = "2";
                    tvTopicType1.setSelected(false);
                    tvTopicType2.setSelected(true);
                    queryTopic();
                    break;
                case R.id.tvEliminate:
                    tags.clear();
                    tagGroup.addTags(tags);
                    for (Map.Entry<String, TreeNode> entry : treeNodeMap.entrySet()) {
                        isFocusTreeNode(entry.getValue(), false);
                    }
                    treeNodeMap.clear();
                    queryTopic();
                    break;
            }
        }
    };

    @Override
    public void loadRecordsListEmpty() {

    }

    @Override
    public void loadTopicCountEmpty() {
        tvCountSingle3.setText("0道题可用");
        tvCountSingle2.setText("0道题可用");
        tvCountSingle1.setText("0道题可用");

        tvCountMultiple3.setText("0道题可用");
        tvCountMultiple2.setText("0道题可用");
        tvCountMultiple1.setText("0道题可用");

        tvCountJudge3.setText("0道题可用");
        tvCountJudge2.setText("0道题可用");
        tvCountJudge1.setText("0道题可用");
    }

    @Override
    protected void onLoadDataRemote() {

    }
}
