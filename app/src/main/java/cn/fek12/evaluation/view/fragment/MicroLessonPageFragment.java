package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.IMicroLessonPage;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.MicroLessonEnetity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.presenter.MicroLessonPagePresenter;
import cn.fek12.evaluation.view.activity.FullScreenVideoPlayActivity;
import cn.fek12.evaluation.view.activity.MicroLessonMoreActivity;
import cn.fek12.evaluation.view.activity.SpecialVideoActivity;
import cn.fek12.evaluation.view.adapter.VideoItemSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonPageFragment
 * @Description:
 * @CreateDate: 2019/11/8 11:20
 */
public class MicroLessonPageFragment extends BaseFragment<MicroLessonPagePresenter> implements IMicroLessonPage.View {
    @BindView(R.id.contentView)
    RecyclerView contentView;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private SectionedRecyclerViewAdapter adapter;
    private List<ChildSectionEntity> gradeList;
    private List<SubjectEntity.DataBean> subjectList;
    private List<TextbookChildEntity> textBookList;
    private List<ChildSectionEntity> semesterList;
    private String gradeId;
    private String subjectId;
    private String semesterId = "";
    private String textbookId;
    private int mTypePage;//0全部1同步2专题
    public MicroLessonPageFragment(int typePage){
        mTypePage = typePage;
    }

    public void setLists(List<ChildSectionEntity> grades,List<SubjectEntity.DataBean> subjects,List<TextbookChildEntity> textBooks,List<ChildSectionEntity> semesters){
        gradeList = grades;
        subjectList = subjects;
        textBookList = textBooks;
        semesterList = semesters;
    }

    public void queryIndexPagerData(String grade, String semester, String subject, String textbook, String userId) {
        this.gradeId = grade;
        this.semesterId = semester;
        this.subjectId = subject;
        this.textbookId = textbook;
        loadView.showLoading();
        if(mTypePage == 0){
            mPresenter.queryAllVideo(getContext(),grade,semester,subject,textbook,userId);
        }else if(mTypePage == 2){
            mPresenter.querySpecialVideo(getContext(),grade,semester,subject,textbook,userId);
        }else if(mTypePage == 3){
            mPresenter.querySpecialVideo(getContext(),grade,semester,subject,textbook,userId);
        }
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.view_common_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 5;
                } else {
                    return 1;
                }
            }
        });
        contentView.setLayoutManager(manager);
        adapter = new SectionedRecyclerViewAdapter();
        contentView.setAdapter(adapter);
        initAdapter();
    }

    @Override
    public void loadVideoSuc(MicroLessonEnetity entry) {
        loadView.showContent();
        if(entry.getData() != null){
            List<MicroLessonEnetity.DataBean.VideoBean> hotList = entry.getData().getHot();
            if(hotList != null && hotList.size() > 0){
                VideoItemSection itemSection = (VideoItemSection) adapter.getSection("hot");
                itemSection.updateList(hotList);
                adapter.getAdapterForSection("hot").notifyAllItemsChanged("payloads");
            }

            List<MicroLessonEnetity.DataBean.VideoBean> nearList = entry.getData().getNear();
            if(nearList != null && nearList.size() > 0){
                VideoItemSection itemSection = (VideoItemSection) adapter.getSection("near");
                itemSection.updateList(nearList);
                adapter.getAdapterForSection("near").notifyAllItemsChanged("payloads");
            }

            List<MicroLessonEnetity.DataBean.VideoBean> recommonedList = entry.getData().getRecommoned();
            if(recommonedList != null && recommonedList.size() > 0){
                VideoItemSection itemSection = (VideoItemSection) adapter.getSection("recommoned");
                itemSection.updateList(recommonedList);
                adapter.getAdapterForSection("recommoned").notifyAllItemsChanged("payloads");
            }
        }else{
            loadView.showEmpty();
        }
    }

    @Override
    public void loadVideoEmpty() {
        loadView.showEmpty();
    }

    private void startActivityIntent(String titleName) {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setGradeList(gradeList);
        containListEntity.setSemesterList(semesterList);
        containListEntity.setSubjectList(subjectList);
        containListEntity.setTextBookList(textBookList);
        Intent intent = new Intent(getContext(), MicroLessonMoreActivity.class);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("titleName", titleName);
        intent.putExtra("typePos", mTypePage);
        intent.putExtra("containListEntityJson", new Gson().toJson(containListEntity));
        getActivity().startActivity(intent);
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    protected MicroLessonPagePresenter onInitLogicImpl() {
        return new MicroLessonPagePresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    private void initAdapter(){
        adapter.addSection("hot",new VideoItemSection(1, new VideoItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                if(mTypePage == 2){//专题视频
                    Intent intent = new Intent(getContext(), SpecialVideoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onMore() {/**热门视频查看更多*/
                //Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                startActivityIntent("热门视频");
                if(mTypePage == 0){

                }
            }
        }));

        adapter.addSection("near",new VideoItemSection(2, new VideoItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                if(mTypePage == 2){//专题视频
                    Intent intent = new Intent(getContext(), SpecialVideoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onMore() {/**热门视频查看更多*/
                //Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                startActivityIntent("热门视频");
                if(mTypePage == 0){

                }
            }
        }));

        adapter.addSection("recommoned",new VideoItemSection(3, new VideoItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                if(mTypePage == 2){//专题视频
                    Intent intent = new Intent(getContext(), SpecialVideoActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onMore() {/**热门视频查看更多*/
                //Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                startActivityIntent("热门视频");
                if(mTypePage == 0){

                }
            }
        }));
    }
}
