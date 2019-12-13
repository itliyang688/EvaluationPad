package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import ayalma.ir.expandablerecyclerview.ExpandableRecyclerView;
import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.adapter.ExerciseExpandableAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: ExerciseNotesFragment
 * @Description:
 * @CreateDate: 2019/12/10 15:27
 */
public class ExerciseNotesFragment extends BaseFragment implements ExpandableRecyclerView.OnExpandableListener {
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    @BindView(R.id.recyclerView)
    ExpandableRecyclerView recyclerView;
    private int mTypePage = 0;
    private ExerciseExpandableAdapter expandableAdapter;

    public ExerciseNotesFragment(int typePage) {
        mTypePage = typePage;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.exercise_notes_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        Map<Integer,Integer> mapData = new HashMap<>();
        mapData.put(0,0);
        mapData.put(1,0);
        mapData.put(2,0);
        mapData.put(3,0);
        mapData.put(4,0);
        mapData.put(5,0);
        expandableAdapter = new ExerciseExpandableAdapter(mapData);
        expandableAdapter.setOnExpandableListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(expandableAdapter);
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    protected BasePresenter onInitLogicImpl() {
        return null;
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
        expandableAdapter.notifyChanged(group,5);
        expandableAdapter.expand(group);
        viewHolder.expand();
    }
}
