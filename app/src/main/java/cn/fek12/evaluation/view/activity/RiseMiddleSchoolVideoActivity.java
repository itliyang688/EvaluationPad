package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

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
import cn.fek12.evaluation.impl.IRiseMiddleSchool;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.model.entity.VideoMoreListEntity;
import cn.fek12.evaluation.model.holder.AutoTreeChildItemHolder;
import cn.fek12.evaluation.model.holder.TreeParentItemHolder;
import cn.fek12.evaluation.presenter.RiseMiddleSchoolPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.adapter.VideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: RiseMiddleSchoolVideoActivity
 * @Description:
 * @CreateDate: 2020/7/2 13:14
 */
public class RiseMiddleSchoolVideoActivity extends BaseActivity<RiseMiddleSchoolPresenter> implements IRiseMiddleSchool.View, VideoAdapter.OnItemClickListener {
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    @BindView(R.id.llTitle)
    LinearLayout llTitle;
    @BindView(R.id.tvSelectText)
    TextView tvSelectText;
    @BindView(R.id.tvSelect1)
    TextView tvSelect1;
    @BindView(R.id.tvSelect2)
    TextView tvSelect2;
    @BindView(R.id.tvSelect3)
    TextView tvSelect3;
    @BindView(R.id.llSelectFrame)
    LinearLayout llSelectFrame;
    @BindView(R.id.titleName)
    TextView titleName;
    private TreeDataEntity treeDataEntity;
    private String checkId = null;
    private TreeNode selectNode;
    private VideoAdapter videoAdapter;
    private int tagPos;
    private String gradeId;
    private String subjectId;
    private String semesterId;
    private String textbookId;

    private List<VideoMoreListEntity.DataBean> mList;

    private int currentPage = 1;
    private boolean isLoadMore = false;
    private String paperType = "SWEETOWN";
    private boolean isSelectOpend = false;
    private int selectSubject = 1;
    private boolean isButtionFocus = false;

    @Override
    public int setLayoutResource() {
        return R.layout.rise_middle_school_video;
    }

    @Override
    protected void onInitView() {
        gradeId = "1";
        semesterId = "16";
        subjectId = "13";
        textbookId = "63";
        /**请求知识树*/
        checkId = null;
        mPresenter.initTreeData(RiseMiddleSchoolVideoActivity.this, paperType, gradeId, semesterId, subjectId, textbookId, MyApplication.getMyApplication().getUserId());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(RiseMiddleSchoolVideoActivity.this);
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
                //String parentId = treeItem.parentId;
                //String name = treeItem.text;
                isFocusTreeNode(node, true);
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

    @Override
    protected void onLoadData() {

    }

    @Override
    protected RiseMiddleSchoolPresenter onInitLogicImpl() {
        return new RiseMiddleSchoolPresenter(this, getContext());
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

    @OnClick({R.id.iv_left_back,R.id.llTitle, R.id.tvSelect1, R.id.tvSelect2, R.id.tvSelect3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llTitle:
                int state = llSelectFrame.getVisibility();
                if (isSelectOpend) {
                    colseFrame();
                    isSelectOpend = false;
                } else {
                    if(selectSubject == 1){//语文
                        tvSelect1.setTextColor(Color.parseColor("#E4A047"));
                        tvSelect2.setTextColor(Color.parseColor("#000000"));
                        tvSelect3.setTextColor(Color.parseColor("#000000"));
                    }else if(selectSubject == 2){//数学
                        tvSelect1.setTextColor(Color.parseColor("#000000"));
                        tvSelect2.setTextColor(Color.parseColor("#E4A047"));
                        tvSelect3.setTextColor(Color.parseColor("#000000"));
                    }else if(selectSubject == 3){//英语
                        tvSelect1.setTextColor(Color.parseColor("#000000"));
                        tvSelect2.setTextColor(Color.parseColor("#000000"));
                        tvSelect3.setTextColor(Color.parseColor("#E4A047"));
                    }
                    Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.push_in);
                    //设置动画结束之后是否保持动画的目标状态
                    animation.setFillAfter(true);
                    llSelectFrame.startAnimation(animation);
                    isSelectOpend = true;
                }
                break;
            case R.id.tvSelect1:
                tvSelectText.setText("语文");
                selectSubject = 1;
                colseFrame();
                break;
            case R.id.tvSelect2:
                tvSelectText.setText("数学");
                selectSubject = 2;
                colseFrame();
                break;
            case R.id.tvSelect3:
                tvSelectText.setText("英语");
                selectSubject = 3;
                colseFrame();
                break;
            case R.id.iv_left_back:
                RiseMiddleSchoolVideoActivity.this.finish();
                break;
        }
    }

    public void colseFrame() {
        if(isSelectOpend){
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.push_out);
            //设置动画结束之后是否保持动画的目标状态
            animation.setFillAfter(false);
            llSelectFrame.startAnimation(animation);
            isSelectOpend = false;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        for (int i = 0; i < event.getPointerCount(); i++) {
            float x = event.getX(i);
            float y = event.getY(i);

            if (!AppUtils.touchEventInView(llTitle, x, y)) {
                colseFrame();
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
