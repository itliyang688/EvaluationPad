package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.adapter.RankingListAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: RankingListFragment
 * @Description:
 * @CreateDate: 2019/12/2 14:48
 */
public class RankingListFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private int typePage;
    private RankingListAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.ranking_list_fragment;
    }

    public RankingListFragment(int typePage) {
        this.typePage = typePage;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RankingListAdapter(getContext(),typePage);
        recycler.setAdapter(adapter);
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
}
