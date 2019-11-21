package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.adapter.CurriculumRecordAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonRecordFragment
 * @Description:
 * @CreateDate: 2019/11/20 15:18
 */
public class CurriculumRecordFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tvSubject)
    TextView tvSubject;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.tvStartDate)
    TextView tvStartDate;
    @BindView(R.id.llStartDate)
    LinearLayout llStartDate;
    @BindView(R.id.tvEndDate)
    TextView tvEndDate;
    @BindView(R.id.llEndDate)
    LinearLayout llEndDate;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private CurriculumRecordAdapter adapter;
    private int mPageType;

    public CurriculumRecordFragment(int pageType) {
        mPageType = pageType;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.curriculum_record_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        adapter = new CurriculumRecordAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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
