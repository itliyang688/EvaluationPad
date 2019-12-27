package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.fek12.basic.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import ayalma.ir.expandablerecyclerview.ExpandableRecyclerView;
import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.entity.ExrGroupListEntity;
import cn.fek12.evaluation.model.entity.PracticeListEntity;
import cn.fek12.evaluation.presenter.ExercisePagePresenter;
import cn.fek12.evaluation.view.adapter.ExerciseExpandableAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: ExerciseNotesFragment
 * @Description:
 * @CreateDate: 2019/12/10 15:27
 */
public class ExerciseNotesFragment extends BaseFragment<ExercisePagePresenter> implements ExpandableRecyclerView.OnExpandableListener,ExercisePagePresenter.View {
    @BindView(R.id.recyclerView)
    ExpandableRecyclerView recyclerView;
    private String mTagTime;
    private ExerciseExpandableAdapter expandableAdapter;
    private List<ExrGroupListEntity> mList;
    private String structId;

    public ExerciseNotesFragment(String tagTime,String structId) {
        mTagTime = tagTime;
        this.structId = structId;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.exercise_notes_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        String times[] = mTagTime.split(",");
        mList = new ArrayList<>();
        for(int i = 0; i < times.length; i ++){
            ExrGroupListEntity exrGroupListEntity = new ExrGroupListEntity();
            exrGroupListEntity.setGroup(times[i]);
            exrGroupListEntity.setData(null);
            mList.add(exrGroupListEntity);
        }
        expandableAdapter = new ExerciseExpandableAdapter(mList,getContext());
        expandableAdapter.setOnExpandableListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(expandableAdapter);
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    protected ExercisePagePresenter onInitLogicImpl() {
        return new ExercisePagePresenter(this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void collapse(int group,ExpandableRecyclerView.GroupViewHolder viewHolder) {
        expandableAdapter.collapse(group);
        viewHolder.collapse();
    }

    @Override
    public void expand(int group, ExpandableRecyclerView.GroupViewHolder viewHolder) {
        showLoading();
        mPresenter.getPracticeList(getContext(), MyApplication.getMyApplication().getUserId(),structId,mList.get(group).getGroup());
        this.group = group;
        this.viewHolder = viewHolder;
    }

    private int group;
    private ExpandableRecyclerView.GroupViewHolder viewHolder;
    @Override
    public void loadSuc(PracticeListEntity entity) {
        hideLoading();
        expandableAdapter.notifyChanged(group,entity.getData(),entity.getMessage());
        expandableAdapter.expand(group);
        viewHolder.expand();
    }

    @Override
    public void loadEmpty() {
        hideLoading();
    }
}
