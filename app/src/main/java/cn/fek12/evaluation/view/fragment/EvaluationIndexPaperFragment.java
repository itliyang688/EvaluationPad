package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.ContainListEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.presenter.EvaluationIndexPagerPresenter;
import cn.fek12.evaluation.view.activity.AnswerWebViewActivity;
import cn.fek12.evaluation.view.activity.EvaluationListActivity;
import cn.fek12.evaluation.view.adapter.EvaluationPaperSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationIndexPaperFragment
 * @Description:
 * @CreateDate: 2019/10/28 11:22
 */
public class EvaluationIndexPaperFragment extends BaseFragment<EvaluationIndexPagerPresenter> implements EvaluationIndexPagerPresenter.View {
    @BindView(R.id.contentView)
    RecyclerView contentView;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private SectionedRecyclerViewAdapter adapter;
    private String gradeId;
    private String subjectId;
    private String semesterId = "";
    private String textbookId;
    private String ptype;
    private String paperType;
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
        return R.layout.view_common_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        contentView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new SectionedRecyclerViewAdapter();
        contentView.setAdapter(adapter);
    }

    public void queryIndexPagerData(String grade, String semester, String subject, String textbook, String ptypeId, String userId) {
        loadView.showLoading();
        this.gradeId = grade;
        this.semesterId = semester;
        this.subjectId = subject;
        this.textbookId = textbook;
        this.ptype = ptypeId;
        mPresenter.getIndexPaper(getContext(),grade,semester,subject,textbook,ptype,userId);
    }

    @Override
    protected void onLoadDataRemote() {
        //mPresenter.getIndexPaper(getContext(), "1", "16", "13", "18", "0", "413");
    }

    @Override
    protected EvaluationIndexPagerPresenter onInitLogicImpl() {
        return new EvaluationIndexPagerPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void loginSuc(AssessmentIndexPaperResp entry) {
        if (entry == null || entry.getData() == null) {
            loadView.showEmpty();
        } else {
            loadView.showContent();
            adapter.removeAllSections();
            adapter.addSection(new EvaluationPaperSection(entry.getData().getHot(), 1, new EvaluationPaperSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    /**跳转页面答题*/
                    startActivity(new Intent(getContext(), AnswerWebViewActivity.class));
                }

                @Override
                public void onMore() {/**热门测评查看更多*/
                    startActivityIntent("热门测评","1");
                }
            }));
            adapter.addSection(new EvaluationPaperSection(entry.getData().getUpdate(), 2, new EvaluationPaperSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    /**跳转页面答题*/
                    startActivity(new Intent(getContext(), AnswerWebViewActivity.class));
                }

                @Override
                public void onMore() {/**最近更新查看更多*/
                    startActivityIntent("最近更新","2");
                }
            }));
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void loginEmpty() {
        loadView.showEmpty();
    }

    @Override
    public void loginFail(String msg) {
        loadView.showEmpty();
    }

    private void startActivityIntent(String titleName,String paperListType) {
        ContainListEntity containListEntity = new ContainListEntity();
        containListEntity.setGradeList(gradeList);
        containListEntity.setSemesterList(semesterList);
        containListEntity.setSubjectList(subjectList);
        containListEntity.setTextBookList(textBookList);
        Intent intent = new Intent(getContext(), EvaluationListActivity.class);
        intent.putExtra("gradeId", gradeId);
        intent.putExtra("semesterId", semesterId);
        intent.putExtra("subjectId", subjectId);
        intent.putExtra("textbookId", textbookId);
        intent.putExtra("titleName", titleName);
        intent.putExtra("ptype", ptype);
        intent.putExtra("paperListType", paperListType);
        intent.putExtra("userType", "1");//类型 测评1 自主测2
        intent.putExtra("containListEntityJson", new Gson().toJson(containListEntity));
        getActivity().startActivity(intent);
    }
}
