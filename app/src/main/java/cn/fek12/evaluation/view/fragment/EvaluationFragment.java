package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.view.titleView.BaseTitleView;
import com.zhengsr.viewpagerlib.bean.PageBean;
import com.zhengsr.viewpagerlib.callback.PageHelperListener;
import com.zhengsr.viewpagerlib.indicator.NormalIndicator;
import com.zhengsr.viewpagerlib.view.BannerViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IEvaluation;
import cn.fek12.evaluation.model.entity.HomeEvaluationDeta;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.presenter.EvaluationPresenter;
import cn.fek12.evaluation.view.activity.AnswerWebViewActivity;
import cn.fek12.evaluation.view.adapter.RecommendEvaluationSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import cn.fek12.evaluation.view.widget.RoundImageView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

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
    @BindView(R.id.tvEvaluationCount)
    TextView tvEvaluationCount;
    @BindView(R.id.multipleStatusView)
    MultipleStatusView multipleStatusView;
    private OnExaminationClickListener mOnExaminationClickListener = null;
    private SectionedRecyclerViewAdapter adapter;
    private List<HomeEvaluationDeta.DataBean.RecommendPaperBean> recommendList;
    private boolean isRefreshtBanner =true;

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
        /*setDefaultTitle("测评", false).addRightImgButton(R.mipmap.main_ic_scan_code, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.popUpToast("hahha");
            }
        });*/
        BaseTitleView titleView = setDefaultTitle("测评", true);
        titleView.setDefaultBackButtonImage(R.mipmap.home_back_icon);
        titleView.setDefaultBackLeftTitle("主页",true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext()){
            @Override
            public boolean canScrollVertically() {
                return true;//是否可以滑动
            }
        };
        adapter = new SectionedRecyclerViewAdapter();
        contentView.setLayoutManager(layoutManager);
        contentView.setAdapter(adapter);
    }

    @OnClick(R.id.go_list_btn)
    public void onViewClicked() {
        if (mOnExaminationClickListener != null) {
            mOnExaminationClickListener.onExaminationClick();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null && recommendList != null){
            isRefreshtBanner = false;
            multipleStatusView.showLoading();
            mPresenter.initEvaluation(getContext(), MyApplication.getMyApp().getUserId(),PrefUtilsData.getPer_level());
        }
    }

    @Override
    protected void onLoadDataRemote() {
        multipleStatusView.showLoading();
        mPresenter.initEvaluation(getContext(), MyApplication.getMyApp().getUserId(),PrefUtilsData.getPer_level());
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
                RoundImageView image = view.findViewById(R.id.image);
                Glide.with(getContext()).load(data.getImageUrl()).into(image);
                //Glide.with(getContext()).load(data.getImageUrl()).placeholder(R.mipmap.empty_bg).error(R.mipmap.empty_bg).into(image);
            }
        });
    }

    @Override
    public void loginSuc(HomeEvaluationDeta entry) {
        if(isRefreshtBanner){
            if(entry.getData() != null && entry.getData().getBanner() != null && entry.getData().getBanner().size() > 0){
                initBanner(entry.getData().getBanner());
            }
        }
        HomeEvaluationDeta.DataBean.MyPaperBean paperBean = entry.getData().getMyPaper();
        if (paperBean != null) {
            String count = String.valueOf(paperBean.getCount());
            String data = paperBean.getDate();
            tvEvaluationCount.setText("已测评次数  " + count + "  次" + "\n" + "最近测试时间" + data);
        }
        recommendList = entry.getData().getRecommendPaper();
        if (recommendList != null && recommendList.size() > 0) {
            multipleStatusView.showContent();
            adapter.removeAllSections();
            adapter.addSection(new RecommendEvaluationSection(getContext(), recommendList, new RecommendEvaluationSection.OnSelectItmeListener() {
                @Override
                public void onSelectItme(int pos) {
                    /**跳转页面答题*/
                    Intent intent = new Intent(getContext(), AnswerWebViewActivity.class);
                    intent.putExtra("isanswered", recommendList.get(pos).getIsanswered());
                    intent.putExtra("paperId", recommendList.get(pos).getId());
                    intent.putExtra("titleName", recommendList.get(pos).getName());
                    startActivity(intent);
                }
            }));
            adapter.notifyDataSetChanged();
        }else{
            multipleStatusView.showEmpty();
        }
    }

    @Override
    public void loginFail(String msg) {
        multipleStatusView.showEmpty();
    }

    @Override
    public void showBaner() {

    }
}
