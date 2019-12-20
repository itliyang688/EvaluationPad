package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;

import org.zakariya.stickyheaders.StickyHeaderLayoutManager;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMicroLessonRecord;
import cn.fek12.evaluation.model.entity.CollectionListEntity;
import cn.fek12.evaluation.model.entity.CommonEntity;
import cn.fek12.evaluation.presenter.MicroLessonRecordPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.PopupWindow.SubjectPopupWindow;
import cn.fek12.evaluation.view.adapter.MicroLessonRecordAdapter;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonRecordFragment
 * @Description:
 * @CreateDate: 2019/11/20 15:18
 */
public class MicroLessonRecordFragment extends BaseFragment<MicroLessonRecordPresenter> implements IMicroLessonRecord.View, MicroLessonRecordAdapter.OnItemClickListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    @BindView(R.id.titleName)
    TextView titleName;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.tvSubject)
    TextView tvSubject;
    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    private MicroLessonRecordAdapter adapter;
    private int mPageType;//1微课学习2我的收藏
    private boolean isVisibleToUser;
    private String subject = null;
    private SubjectPopupWindow subjectPopupWindow;

    public MicroLessonRecordFragment(int pageType) {
        mPageType = pageType;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.micro_lesson_record_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        collectionList();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if (isVisibleToUser && adapter == null) {
            recyclerView.setLayoutManager(new StickyHeaderLayoutManager());
            adapter = new MicroLessonRecordAdapter(mPageType, getContext());
            adapter.setOnItemClickListener(this);
            recyclerView.setAdapter(adapter);
        }

        collectionList();
    }

    private void collectionList() {
        if (isVisibleToUser) {
            multipleStatusView.showLoading();
            if (mPageType == 1) {//微课学习
                titleName.setText("微课学习");
                mPresenter.microLessonList(getContext(), MyApplication.getMyApplication().getUserId(), subject);
            } else {//我的收藏
                titleName.setText("我的收藏");
                mPresenter.collectionList(getContext(), MyApplication.getMyApplication().getUserId(), subject);
            }
        }
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {

    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    public void loadCollectionSuc(CollectionListEntity entry) {
        multipleStatusView.showContent();
        boolean isEmpty = false;
        if (entry.getData() != null) {
            if (entry.getData().getWeek() != null && entry.getData().getWeek().size() > 0) {
                isEmpty = true;
            }
            if (entry.getData().getMonth() != null && entry.getData().getMonth().size() > 0) {
                isEmpty = true;
            }
            if (entry.getData().getNear() != null && entry.getData().getNear().size() > 0) {
                isEmpty = true;
            }

            if (isEmpty) {
                adapter.notifyChanged(entry.getData());
            } else {
                multipleStatusView.showEmpty();
            }
        } else {
            multipleStatusView.showEmpty();
        }
    }

    @Override
    public void loadMicroLessonSuc(CollectionListEntity entry) {

    }

    @Override
    public void loadMicroLessonEmpty() {
        multipleStatusView.showEmpty();
    }

    @Override
    public void loadCollectionSuc(CommonEntity entry) {
        ToastUtils.popUpToast("已取消收藏");
        multipleStatusView.showLoading();
        mPresenter.collectionList(getContext(), MyApplication.getMyApplication().getUserId(), subject);
    }

    @Override
    public void loadCollectionFail() {
        ToastUtils.popUpToast("取消收藏失败");
    }


    @Override
    public boolean onBackPressed() {
        return false;
    }


    @Override
    public void loadCollectionEmpty() {
        multipleStatusView.showEmpty();
    }

    @Override
    protected MicroLessonRecordPresenter onInitLogicImpl() {
        return new MicroLessonRecordPresenter(this);
    }

    @Override
    public void onItemClick(String cacheKey, String videoId, String videoType) {
        mPresenter.collection(getContext(), cacheKey, videoType, videoId, "0", MyApplication.getMyApplication().getUserId());
    }

    @OnClick(R.id.llSubject)
    public void onViewClicked() {
        subjectPopupWindow = new SubjectPopupWindow(getContext(), new SubjectPopupWindow.OnSelectItmeListener() {
            @Override
            public void onSelectItme(String subjectId, String subjectName) {
                tvSubject.setText(subjectName);
                subject = subjectId.equals("0") ? null : subjectId;
                collectionList();
            }
        });
        AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
        ivArrow.setImageResource(R.mipmap.rise_icon);
        subjectPopupWindow.showAsDropDown(llSubject, -166, 0);
        subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ivArrow.setImageResource(R.mipmap.lower_icon);
            }
        });
    }
}
