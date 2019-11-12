package cn.fek12.evaluation.view.activity;

import com.fek12.basic.base.BaseActivity;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: WebViewActivity
 * @Description:
 * @CreateDate: 2019/11/11 13:21
 */
public class WebViewActivity extends BaseActivity {

    @Override
    public int setLayoutResource() {
        return R.layout.web_view_activity;
    }

    @Override
    protected void onInitView() {
        finish();
        setDefaultTitle("测试布局影响");


    }

    @Override
    protected void onLoadData() {

    }
}
