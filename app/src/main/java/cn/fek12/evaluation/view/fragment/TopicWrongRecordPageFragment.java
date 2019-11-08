package cn.fek12.evaluation.view.fragment;

import android.os.Bundle;
import android.webkit.WebView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: TopicWrongRecordPageFragment
 * @Description:
 * @CreateDate: 2019/11/8 17:38
 */
public class TopicWrongRecordPageFragment extends BaseFragment {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutResource() {
        return R.layout.topic_worng_page_fragment;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        webView.loadUrl("file:///android_asset/web/Record.html");
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
