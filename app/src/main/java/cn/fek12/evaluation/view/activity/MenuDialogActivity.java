package cn.fek12.evaluation.view.activity;

import android.os.Bundle;

import com.fek12.basic.base.BaseActivity;
import com.flyco.tablayout.SegmentTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: MenuDialogActivity
 * @Description:
 * @CreateDate: 2019/11/2 16:37
 */
public class MenuDialogActivity extends BaseActivity {

    @Override
    public int setLayoutResource() {
        return R.layout.menu_dialog_activity;
    }

    @Override
    protected void onInitView() {
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
