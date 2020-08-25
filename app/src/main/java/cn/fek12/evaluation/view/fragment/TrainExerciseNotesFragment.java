package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.fek12.basic.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import ayalma.ir.expandablerecyclerview.TrainExpandableRecyclerView;
import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.ITrainExercisePage;
import cn.fek12.evaluation.model.entity.ExrGroupListEntity;
import cn.fek12.evaluation.model.entity.PracticeListEntity;
import cn.fek12.evaluation.model.entity.TrainExrGroupListEntity;
import cn.fek12.evaluation.model.entity.TrainListEntity;
import cn.fek12.evaluation.presenter.ExercisePagePresenter;
import cn.fek12.evaluation.presenter.TrainExercisePagePresenter;
import cn.fek12.evaluation.view.adapter.ExerciseExpandableAdapter;
import cn.fek12.evaluation.view.adapter.TrainExerciseExpandableAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: ExerciseNotesFragment
 * @Description:
 * @CreateDate: 2019/12/10 15:27
 */
public class TrainExerciseNotesFragment extends BaseFragment<TrainExercisePagePresenter> implements TrainExpandableRecyclerView.OnExpandableListener,TrainExercisePagePresenter.View {
    @BindView(R.id.recyclerView)
    TrainExpandableRecyclerView recyclerView;
    private String mTagTime;
    private TrainExerciseExpandableAdapter expandableAdapter;
    private List<TrainExrGroupListEntity> mList;
    private String structId;

    public TrainExerciseNotesFragment(String tagTime, String structId) {
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
            TrainExrGroupListEntity exrGroupListEntity = new TrainExrGroupListEntity();
            exrGroupListEntity.setGroup(times[i]);
            exrGroupListEntity.setData(null);
            mList.add(exrGroupListEntity);
        }
        expandableAdapter = new TrainExerciseExpandableAdapter(mList,getContext());
        expandableAdapter.setOnExpandableListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(expandableAdapter);
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    protected TrainExercisePagePresenter onInitLogicImpl() {
        return new TrainExercisePagePresenter( this);
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void collapse(int group,TrainExpandableRecyclerView.GroupViewHolder viewHolder) {
        expandableAdapter.collapse(group);
        viewHolder.collapse();
    }

    @Override
    public void expand(int group, TrainExpandableRecyclerView.GroupViewHolder viewHolder) {
        showLoading();
        mPresenter.getPracticeList(getContext(), MyApplication.getMyApp().getUserId(),mList.get(group).getGroup());
        this.group = group;
        this.viewHolder = viewHolder;
    }

    private int group;
    private TrainExpandableRecyclerView.GroupViewHolder viewHolder;


    @Override
    public void loadSuc(TrainListEntity entity) {
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
