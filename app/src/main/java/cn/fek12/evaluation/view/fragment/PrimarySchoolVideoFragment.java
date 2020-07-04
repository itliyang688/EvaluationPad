package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.activity.CommonVideoActivity;
import cn.fek12.evaluation.view.activity.CompositionTreasureVideoActivity;
import cn.fek12.evaluation.view.activity.PrimarySchoolSynchroVideoActivity;
import cn.fek12.evaluation.view.activity.RiseMiddleSchoolVideoActivity;

public class PrimarySchoolVideoFragment extends BaseFragment {
    @BindView(R.id.ivModular_1)
    ImageView ivModular1;
    @BindView(R.id.ivModular_2)
    ImageView ivModular2;
    @BindView(R.id.ivModular_3)
    ImageView ivModular3;
    @BindView(R.id.ivModular_5)
    ImageView ivModular5;
    @BindView(R.id.ivModular_6)
    ImageView ivModular6;
    @BindView(R.id.ivModular_7)
    ImageView ivModular7;
    @BindView(R.id.ivModular_8)
    ImageView ivModular8;

    @Override
    protected int getLayoutResource() {
        return R.layout.primary_school_video_fragment;
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

    @OnClick({R.id.ivModular_1, R.id.ivModular_2, R.id.ivModular_3, R.id.ivModular_5, R.id.ivModular_6, R.id.ivModular_7, R.id.ivModular_8})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivModular_1://同步视频
                startActivity(new Intent(getContext(), PrimarySchoolSynchroVideoActivity.class));
                break;
            case R.id.ivModular_2://小升初
                startActivity(new Intent(getContext(), RiseMiddleSchoolVideoActivity.class));
                break;
            case R.id.ivModular_3://奥数
                Intent intentModular3 = new Intent(getContext(), CommonVideoActivity.class);
                intentModular3.putExtra("pageType",1);
                startActivity(intentModular3);
                break;
            case R.id.ivModular_5://作文宝典
                startActivity(new Intent(getContext(), CompositionTreasureVideoActivity.class));
                break;
            case R.id.ivModular_6://课外阅读
                Intent intentModular6 = new Intent(getContext(), CommonVideoActivity.class);
                intentModular6.putExtra("pageType",2);
                startActivity(intentModular6);
                break;
            case R.id.ivModular_7:
                Intent intentModular7 = new Intent(getContext(), CommonVideoActivity.class);
                intentModular7.putExtra("pageType",3);
                startActivity(intentModular7);
                break;
            case R.id.ivModular_8:
                Intent intentModular8 = new Intent(getContext(), CommonVideoActivity.class);
                intentModular8.putExtra("pageType",4);
                startActivity(intentModular8);
                break;
        }
    }
}
