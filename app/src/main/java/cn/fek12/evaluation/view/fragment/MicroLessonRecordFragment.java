package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import org.zakariya.stickyheaders.StickyHeaderLayoutManager;
import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.adapter.MicroLessonRecordAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonRecordFragment
 * @Description:
 * @CreateDate: 2019/11/20 15:18
 */
public class MicroLessonRecordFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MicroLessonRecordAdapter adapter;
    private int mPageType;
    public MicroLessonRecordFragment(int pageType){
        mPageType = pageType;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.micro_lesson_record_fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser && adapter == null){
            adapter = new MicroLessonRecordAdapter(mPageType,getContext());
            recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
            recyclerView.setAdapter(adapter);
            adapter.notifyAllSectionsDataSetChanged();
        }
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

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
