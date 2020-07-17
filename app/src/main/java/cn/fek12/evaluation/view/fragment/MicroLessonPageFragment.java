package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import com.google.gson.Gson;

import org.csource.common.MyException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
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
import cn.fek12.evaluation.utils.FastDFSUtil;
import cn.fek12.evaluation.view.activity.FullScreenVideoPlayActivity;
import cn.fek12.evaluation.view.activity.MicroLessonMoreActivity;
import cn.fek12.evaluation.view.activity.MicrolessonVideoPlayActivity;
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
    private String userId;
    private int mTypePage;
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
        this.userId = userId;
        loadView.showLoading();
        initNetData();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mTypePage == 0){
            initNetData();
        }
    }

    private void initNetData(){
        mPresenter.queryAllVideo(getContext(),gradeId,subjectId,semesterId,textbookId,null,userId);
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
    }

    @Override
    public void loadVideoSuc(MicroLessonEnetity entry) {
        boolean isEmpty = false;
        loadView.showContent();
        adapter.removeAllSections();
        if(entry.getData() != null){
            List<MicroLessonEnetity.DataBean.VideoBean> hotList = entry.getData().getHot();
            if(hotList != null && hotList.size() > 0){
                isEmpty =true;
                adapter.addSection("hot",new VideoItemSection(1,hotList, new VideoItemSection.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(int pos) {
                        startVideoPlay(hotList,pos,MicrolessonVideoPlayActivity.class);
                    }

                    @Override
                    public void onMore() {/**热门视频查看更多*/
                        //Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                        startActivityIntent("热门视频",1);
                    }
                }));
            }

            List<MicroLessonEnetity.DataBean.VideoBean> nearList = entry.getData().getNear();
            if(nearList != null && nearList.size() > 0){
                isEmpty =true;
                adapter.addSection("near",new VideoItemSection(2, nearList,new VideoItemSection.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(int pos) {
                        startVideoPlay(nearList,pos,MicrolessonVideoPlayActivity.class);
                    }

                    @Override
                    public void onMore() {/**最近视频查看更多*/
                        //Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                        startActivityIntent("最近更新",2);
                    }
                }));
            }

            List<MicroLessonEnetity.DataBean.VideoBean> recommonedList = entry.getData().getRecommend();
            if(recommonedList != null && recommonedList.size() > 0){
                isEmpty =true;
                adapter.addSection("recommoned",new VideoItemSection(3, recommonedList,new VideoItemSection.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(int pos) {
                        startVideoPlay(recommonedList,pos,MicrolessonVideoPlayActivity.class);
                    }

                    @Override
                    public void onMore() {/**热门视频查看更多*/
                        startActivityIntent("推荐视频",3);
                    }
                }));
            }
            if(!isEmpty){
                loadView.showEmpty();
            }
            adapter.notifyDataSetChanged();
        }else{
            loadView.showEmpty();
        }
    }

    private void startVideoPlay(List<MicroLessonEnetity.DataBean.VideoBean> mList, int position,Class cla){
        String path = "";
        try {
            path = FastDFSUtil.generateSourceUrl(mList.get(position).getVideoUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getContext(), cla);
        intent.putExtra("pathUrl",path);
        intent.putExtra("videoName",mList.get(position).getVideoName());
        intent.putExtra("videoId",mList.get(position).getVideoId());
        intent.putExtra("imgUrl",mList.get(position).getImgUrl());
        intent.putExtra("playScheduleTime",mList.get(position).getPlayScheduleTime());
        intent.putExtra("isCollection",mList.get(position).getIsCollection());
        startActivity(intent);
    }

    @Override
    public void loadVideoEmpty() {
        loadView.showEmpty();
    }

    private void startActivityIntent(String titleName,int clickPos) {
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
        intent.putExtra("clickPos", clickPos);
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

}
