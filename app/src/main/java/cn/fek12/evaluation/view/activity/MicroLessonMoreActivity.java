package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.Footer.BottomProgressView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMicroLessonMore;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.MicroLessonPageEnetity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.model.entity.TreeDataEntity;
import cn.fek12.evaluation.presenter.MicroLessonMorePresenter;
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsChildSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsParentSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsSubjectSection;
import cn.fek12.evaluation.view.adapter.EvaluationDetailsTagSection;
import cn.fek12.evaluation.view.adapter.VideoAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: EvaluationDetailsActivity
 * @Description:
 * @CreateDate: 2019/10/30 13:14
 */
public class MicroLessonMoreActivity extends BaseActivity<MicroLessonMorePresenter> implements IMicroLessonMore.View, VideoAdapter.OnItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView leftRecycler;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    @BindView(R.id.tvTitleName)
    TextView tvTitleName;
    private SectionedRecyclerViewAdapter leftAdapter;
    private String checkId;
    private VideoAdapter videoAdapter;
    private int tagPos;
    private String gradeId;
    private String subjectId;
    private String semesterId;
    private String textbookId;
    private int typePos;
    private int clickPos;

    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;
    private List<MicroLessonPageEnetity.DataBean.RecordsBean> mList;

    private int currentPage = 1;
    private String pageSize = "21";
    private boolean isLoadMore = false;

    @Override
    public int setLayoutResource() {
        return R.layout.evaluation_list_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        setEmptyTitle();
        checkId = intent.getStringExtra("checkId");
        gradeId = intent.getStringExtra("gradeId");
        subjectId = intent.getStringExtra("subjectId");
        semesterId = intent.getStringExtra("semesterId");
        textbookId = intent.getStringExtra("textbookId");
        typePos = intent.getIntExtra("typePos",0);
        clickPos = intent.getIntExtra("clickPos",1);
        tvTitleName.setText(intent.getStringExtra("titleName"));
        ContainListEntity containListEntity = new Gson().fromJson(intent.getStringExtra("containListEntityJson"), ContainListEntity.class);
        gradeList = containListEntity.getGradeList();
        subjectList = containListEntity.getSubjectList();
        textBookList = containListEntity.getTextBookList();
        semesterList = containListEntity.getSemesterList();

        tagPos = gradeList.size() + subjectList.size();

        initLeftRecycler();
        initLabelAdapter();

        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videoAdapter = new VideoAdapter(MicroLessonMoreActivity.this);
        videoAdapter.setOnItemClickListener(this);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 7);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(videoAdapter);

        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(refreshListenerAdapter);
        BottomProgressView bottomProgressView = new BottomProgressView(this);
        bottomProgressView.setAnimatingColor(this.getResources().getColor(R.color.app_bg));
        refreshLayout.setBottomView(bottomProgressView);

        //refreshLayout.setEnableLoadmore(false);
        //refreshLayout.setEnableRefresh(false);

        initData();
    }

    private void initData(){
        loadView.showLoading();
        mPresenter.moreVideoList(this,gradeId,subjectId,semesterId,textbookId,String.valueOf(clickPos),MyApplication.getMyApp().getUserId(),String.valueOf(currentPage),pageSize);
    }

    private RefreshListenerAdapter refreshListenerAdapter = new RefreshListenerAdapter() {
        @Override
        public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = true;
            currentPage += 1;
            mPresenter.moreVideoList(MicroLessonMoreActivity.this,gradeId,subjectId,semesterId,textbookId,String.valueOf(clickPos),MyApplication.getMyApp().getUserId(),String.valueOf(currentPage),pageSize);
        }

        @Override
        public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
            isLoadMore = false;
            currentPage = 1;
            mPresenter.moreVideoList(MicroLessonMoreActivity.this,gradeId,subjectId,semesterId,textbookId,String.valueOf(clickPos),MyApplication.getMyApp().getUserId(),String.valueOf(currentPage),pageSize);
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

        /*List<TextbookChildEntity> tagList = new ArrayList<>();
        for(SubjectEntity.DataBean dataBeans : subjectList){
            TextbookChildEntity childEntity = new TextbookChildEntity();
            childEntity.setId(dataBeans.getId());
            childEntity.setName(dataBeans.getName());
            tagList.add(childEntity);
        }
        leftAdapter.addSection("subject", new EvaluationDetailsTagSection(getContext(), tagList, subjectId, new EvaluationDetailsTagSection.OnSelectItmeListener() {
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

                mPresenter.queryTextBookList(getContext(), gradeId, subjectId);
            }
        }));

        leftAdapter.addSection("textbook", new EvaluationDetailsTagSection(getContext(), textBookList, textbookId, new EvaluationDetailsTagSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("textbook").notifyAllItemsChanged("payloads");
                textbookId = String.valueOf(textBookList.get(pos).getId());

                /**点击版本去查询教材*/
                mPresenter.querySemesterList(getContext(), gradeId, subjectId, textbookId);

            }
        }));
        leftAdapter.addSection("semester", new EvaluationDetailsChildSection(semesterList, semesterId, new EvaluationDetailsChildSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
                semesterId = String.valueOf(semesterList.get(pos).getId());
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
    public void loadListSuc(MicroLessonPageEnetity entry) {
        loadView.showContent();
        if(entry.getData().getPages() == 0){
            loadView.showEmpty();
            return;
        }

        if(entry.getData().getPages() > currentPage){
            refreshLayout.setEnableLoadmore(true);
        }else{
            refreshLayout.setEnableLoadmore(false);
        }

        if(entry.getData().getRecords() != null && entry.getData().getRecords().size() > 0){
            videoAdapter.notifyChanged(entry.getData().getRecords(),isLoadMore);
            if(isLoadMore){
                mList.addAll(entry.getData().getRecords());
            }else{
                mList = entry.getData().getRecords();
            }
        }
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
    }

    @Override
    protected MicroLessonMorePresenter onInitLogicImpl() {
        return new MicroLessonMorePresenter(this, getContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public void loadTreeSuc(TreeDataEntity entry) {

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

            EvaluationDetailsTagSection tagChildSection = (EvaluationDetailsTagSection) leftAdapter.getSection("textbook");
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
            leftAdapter.notifyDataSetChanged();
        }

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
            EvaluationDetailsChildSection semesterSection = (EvaluationDetailsChildSection) leftAdapter.getSection("semester");
            semesterSection.updateList(semesterList);

            leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
        }
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
    public void loadListEmpty() {
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
