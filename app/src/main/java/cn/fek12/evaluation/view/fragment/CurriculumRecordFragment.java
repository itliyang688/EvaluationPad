package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import com.fek12.basic.utils.toast.ToastUtils;
import com.ldf.calendar.interf.OnSelectDateListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.utils.DialogUtils;
import cn.fek12.evaluation.view.adapter.CurriculumRecordAdapter;
import cn.fek12.evaluation.view.dialog.SelectDateDialog;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonRecordFragment
 * @Description:
 * @CreateDate: 2019/11/20 15:18
 */
public class CurriculumRecordFragment extends BaseFragment{
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

        llSubject.setOnClickListener(this);
        llStartDate.setOnClickListener(this);
        llEndDate.setOnClickListener(this);
        adapter = new CurriculumRecordAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v, int id) {
        switch (id){
            case R.id.llStartDate:
                SelectDateDialog startDateDialog = new SelectDateDialog(getContext(), "选择起始日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        tvStartDate.setText(date);
                    }
                });
                startDateDialog.show();

                break;
            case R.id.llEndDate:
                SelectDateDialog endDateDialog = new SelectDateDialog(getContext(),"选择结束日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        tvEndDate.setText(date);
                    }
                });
                endDateDialog.show();
                break;
            case R.id.llSubject:
                List<ChildSectionEntity> list = new ArrayList<>();
                for(int i = 0; i < 10; i ++){
                    if(i == 0){
                        ChildSectionEntity sectionEntity = new ChildSectionEntity();
                        sectionEntity.setName("全部");
                        list.add(sectionEntity);
                    }else{
                        ChildSectionEntity sectionEntity = new ChildSectionEntity();
                        sectionEntity.setName("数学"+i);
                        list.add(sectionEntity);
                    }
                }
                DialogUtils.subjectSelectDialog(getContext(),list, new DialogUtils.OnSelectSubjectItmeListener() {
                    @Override
                    public void onSelectItme(int pos) {
                        tvSubject.setText(list.get(pos).getName());
                    }
                });

                break;
        }
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
