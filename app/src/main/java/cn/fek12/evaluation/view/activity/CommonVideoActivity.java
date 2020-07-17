package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.MicrolessonVideoEntity;
import cn.fek12.evaluation.presenter.CommonVideoPresenter;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.adapter.CommonVideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

public class CommonVideoActivity extends BaseActivity<CommonVideoPresenter> implements CommonVideoPresenter.View,CommonVideoAdapter.OnItemClickListener{
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    @BindView(R.id.iv_left_back)
    ImageView ivLeftBack;
    @BindView(R.id.ivTitleIcon)
    ImageView ivTitleIcon;
    @BindView(R.id.tvTitleName)
    TextView tvTitleName;
    @BindView(R.id.tvIntroduce)
    TextView tvIntroduce;
    private int pageType;
    private String introduce1 = "        本课程围绕小学1-6年级奥数的29个常见题目而创作，分“解法精讲”“典例精析”“举一反三”三个部分进行讲解，解题方法分析到位、练习针对性强、讲练结合，拓展思路。";
    private String introduce2 = "        本课程可有效解决选择课外读物、激发学生阅读兴趣、引导学生高效阅读三大问题，以全面培养孩子的语文学科素养为宗旨。包括读本导读和读本讲解视频两部分。导读视频：3分钟左右视频，以情景问题引入，活泼有趣地展示读本精彩之处，激发学生阅读兴趣。 微课讲解视频：6分钟左右视频，讲解读本中的重点或难点，同时关联课内知识点，聚焦阅读能力点。";
    private String introduce3 = "        本系列课程围绕着小学阶段的作文而创作，涵盖了小学生写作过程中所用到的全部技巧方法和个作文题目解析。通过“小学作文通关秘籍”“小学作文招招鲜”“应用文魔法宝库”“考场作文全揭秘”“小学作文重点题目详解”五个部分完美呈现，力求在最短的时间里传达最实用、最重要的知识，让学生尽可能最快速地掌握并打牢基础。";
    private String introduce4 = "        多动手，使学生的创作个性得以最大限度地发挥。在手工制作中思维较其他活动更开阔，最充分地表达现有水平，用手工制作的方法对想象中的东西进行创造，在从虚到实的过程中发展学生的观察力、记忆力、想象力、创造力，有利于脑部的发育。 在手工制作过程中，往往都需要多方面的配合，特别是与人的配合，同过合作，动手制作的过程，会使儿童更懂得与人沟通，换位思考，促进交往能力发展。";
    private int currentPage = 1;
    private boolean isLoadMore = false;
    private CommonVideoAdapter commonVideoAdapter;
    private List<MicrolessonVideoEntity.DataBean.RecordsBean> mList;
    private int coursePackType = 0;

    @Override
    public int setLayoutResource() {
        return R.layout.common_video_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        pageType = intent.getIntExtra("pageType", 0);
        if(pageType == 1){
            coursePackType = 4;//小学奥数
        }else if(pageType == 2){
            coursePackType = 6;//课外阅读
        }else if(pageType == 3){
            coursePackType = 7;//国学经典
        }else if(pageType == 4){
            coursePackType = 8;//手工坊
        }
        initDataView();

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        refreshLayout.setEnableLoadmore(false);
        //refreshLayout.setEnableRefresh(false);

        commonVideoAdapter = new CommonVideoAdapter(this);
        commonVideoAdapter.setOnItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(commonVideoAdapter);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.queryVideoList(CommonVideoActivity.this,String.valueOf(coursePackType),"", MyApplication.getMyApplication().getUserId(),String.valueOf(currentPage),"12");
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.queryVideoList(CommonVideoActivity.this,String.valueOf(coursePackType),"", MyApplication.getMyApplication().getUserId(),String.valueOf(currentPage),"12");
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        loadView.showLoading();
        mPresenter.queryVideoList(CommonVideoActivity.this,String.valueOf(coursePackType),"", MyApplication.getMyApplication().getUserId(),String.valueOf(currentPage),"12");

    }

    @Override
    protected void onLoadData() {
    }
    @Override
    protected CommonVideoPresenter onInitLogicImpl() {
        return new CommonVideoPresenter(this, getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private void initDataView(){
        tvIntroduce.setMovementMethod(new ScrollingMovementMethod());
        switch (pageType){
            case 1://奥数
                tvIntroduce.setText(introduce1);
                tvTitleName.setText("奥数全能解法及训练");
                ivTitleIcon.setImageResource(R.mipmap.austria_number);
                break;
            case 2://课外阅读
                tvIntroduce.setText(introduce2);
                tvTitleName.setText("课外阅读好向导");
                ivTitleIcon.setImageResource(R.mipmap.read_title);
                break;
            case 3://国学经典
                tvIntroduce.setText(introduce3);
                tvTitleName.setText("《弟子规》");
                ivTitleIcon.setImageResource(R.mipmap.classic_title);
                break;
            case 4://手工坊
                tvIntroduce.setText(introduce4);
                tvTitleName.setText("巧手工坊");
                ivTitleIcon.setImageResource(R.mipmap.manual_title);
                break;
        }
    }

    @OnClick(R.id.iv_left_back)
    public void onViewClicked() {
        CommonVideoActivity.this.finish();
    }

    @Override
    public void onItemClick(int position) {
        String path = "";
        try {
            path = FastDFSUtil.generateSourceUrl(mList.get(position).getVideoUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(CommonVideoActivity.this,MicrolessonVideoPlayActivity.class);
        intent.putExtra("pathUrl",path);
        intent.putExtra("videoName",mList.get(position).getVideoName());
        intent.putExtra("videoId",mList.get(position).getVideoId());
        intent.putExtra("imgUrl",mList.get(position).getImgUrl());
        intent.putExtra("playScheduleTime",mList.get(position).getPlayScheduleTime());
        intent.putExtra("isCollection",mList.get(position).getIsCollection());
        CommonVideoActivity.this.startActivity(intent);
    }

    @Override
    public void loadVideoSuc(MicrolessonVideoEntity entry) {
        if(isLoadMore){
            mList.addAll(entry.getData().getRecords());
        }else{
            mList = entry.getData().getRecords();
        }
        if(entry.getData().getPages() == 0){
            loadView.showEmpty();
            return;
        }
        loadView.showContent();
        if(entry.getData().getPages() > currentPage){
            refreshLayout.setEnableLoadmore(true);
        }else{
            refreshLayout.setEnableLoadmore(false);
        }

        if(mList != null && mList.size() > 0){
            commonVideoAdapter.notifyChanged(mList,isLoadMore);
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadFail(String msg) {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    public void loadVideoEmpty() {
        loadView.showEmpty();
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }
}
