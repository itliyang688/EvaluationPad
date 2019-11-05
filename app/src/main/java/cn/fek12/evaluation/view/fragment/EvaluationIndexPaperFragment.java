package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.AssessmentIndexPaperResp;
import cn.fek12.evaluation.presenter.EvaluationIndexPagerPresenter;
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

    public void queryIndexPagerData(String grade, String semester, String subject, String textbook, String ptype, String userId) {
        loadView.showLoading();
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
            adapter.addSection(new EvaluationPaperSection(entry.getData().getHot(), 1));
            adapter.addSection(new EvaluationPaperSection(entry.getData().getUpdate(), 2));
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
}
