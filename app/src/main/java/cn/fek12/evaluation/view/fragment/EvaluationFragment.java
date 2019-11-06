package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.NormalIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.base.BaseEntry;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.presenter.EvaluationPresenter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: EvaluationFragment
 * @Description: 首页测评页面
 * @CreateDate: 2019/10/23 15:12
 */
public class EvaluationFragment extends BaseFragment<EvaluationPresenter> implements IEvaluation.View {
    @BindView(R.id.toolbar)
    TextView toolbar;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.loop_viewpager_mz)
    BannerViewPager loopViewpagerMz;
    @BindView(R.id.scale_indicator)
    NormalIndicator scaleIndicator;
    @BindView(R.id.go_list_btn)
    ImageView goListBtn;
    @BindView(R.id.go_list_title)
    TextView goListTitle;
    @BindView(R.id.contentView)
    RecyclerView contentView;
    private OnExaminationClickListener mOnExaminationClickListener = null;
    public interface OnExaminationClickListener {
        void onExaminationClick();
    }
    public void setOnClickListener(OnExaminationClickListener onExaminationClickListener) {
        this.mOnExaminationClickListener = onExaminationClickListener;
    }
    @Override
    protected int getLayoutResource() {
        return R.layout.evaluation_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        setDefaultTitle("测评", false).addRightImgButton(R.mipmap.main_ic_scan_code, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.popUpToast("hahha");
            }
        });
    }

    @OnClick(R.id.go_list_btn)
    public void onViewClicked() {
        if(mOnExaminationClickListener != null){
            mOnExaminationClickListener.onExaminationClick();
        }
    }
    @Override
    protected void onLoadDataRemote() {
        mPresenter.initEvaluation(getContext(),  MyApplication.getMyApplication().getUserId());
    }

    @Override
    protected EvaluationPresenter onInitLogicImpl() {
        return new EvaluationPresenter(this, getContext());
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }

    private void initBanner(List<HomeEvaluationDeta.DataBean.BannerBean> bannerBeans) {
        PageBean pageBean = new PageBean.Builder<HomeEvaluationDeta.DataBean.BannerBean>()
                .data(bannerBeans)
                .indicator(scaleIndicator)
                .builder();
        loopViewpagerMz.setPageListener(pageBean, R.layout.evaluation_list_item_banner, new PageHelperListener<HomeEvaluationDeta.DataBean.BannerBean>() {
            @Override
            public void getItemView(View view, HomeEvaluationDeta.DataBean.BannerBean data) {
                ImageView image = view.findViewById(R.id.image);
                //image.setImageResource(R.drawable.evaluation_btn_go_list);
                Glide.with(getContext()).load(data.getImageUrl()).into(image);
            }
        });
    }

    @Override
    public void loginSuc(HomeEvaluationDeta entry) {
        initBanner(entry.getData().getBanner());
    }

    @Override
    public void loginFail(String msg) {
        ToastUtils.popUpToast(msg);
    }

    @Override
    public void showBaner() {

    }
}
