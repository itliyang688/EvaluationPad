package cn.fek12.evaluation.view.activity;

import android.content.Intent;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.impl.IConquered;
import cn.fek12.evaluation.model.entity.ConqueredEntity;
import cn.fek12.evaluation.presenter.ConqueredPresenter;
import cn.fek12.evaluation.view.adapter.ConqueredAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: ConqueredActivity
 * @Description:
 * @CreateDate: 2019/11/13 18:45
 */
public class ConqueredActivity extends BaseActivity<ConqueredPresenter> implements IConquered.View, ConqueredAdapter.OnItemClickListener {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private ConqueredAdapter adapter;
    private String paperResultId;
    private List<ConqueredEntity.DataBean> mList;

    @Override
    public int setLayoutResource() {
        return R.layout.conquered_activity;
    }

    @Override
    protected ConqueredPresenter onInitLogicImpl() {
        return new ConqueredPresenter(this, getContext());
    }


    @Override
    protected void onInitView() {
        setEmptyTitle();
        paperResultId = getIntent().getStringExtra("paperResultId");
        adapter = new ConqueredAdapter(ConqueredActivity.this);
        adapter.setOnItemClickListener(this);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
    }

    @Override
    protected void onLoadData() {
        multipleStatusView.showLoading();
        mPresenter.promoteDetail(ConqueredActivity.this,paperResultId);
    }

    @Override
    public void loadSuc(ConqueredEntity entry) {
        mList = entry.getData();
        if(mList != null && mList.size() > 0){
            multipleStatusView.showContent();
            adapter.notifyChanged(mList);
        }else{
            multipleStatusView.showEmpty();
        }
    }

    @Override
    public void loadEmpty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void onItemClick(int position) {
        String subjectCategoryId = String.valueOf(mList.get(position).getId());
        Intent intent = new Intent(getContext(), VideoPlayListActivity.class);
        intent.putExtra("subjectCategoryId",subjectCategoryId);
        startActivity(intent);
    }
}
