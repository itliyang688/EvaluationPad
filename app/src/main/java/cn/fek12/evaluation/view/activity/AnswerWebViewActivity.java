package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: AnswerWebViewActivity
 * @Description:
 * @CreateDate: 2019/11/15 17:17
 */
public class AnswerWebViewActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;

    @Override
    public int setLayoutResource() {
        return R.layout.answer_webview_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle();
       /* WebSettings webSettings = webView.getSettings();
        // 不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setTextZoom(100);
        webView.setInitialScale(50);
        webSettings.setLoadWithOverviewMode(true);
        webView.loadUrl("http://192.168.0.83:11111/index.html");
        //webView.loadUrl("file:///android_asset/web/Record.html");
        startActivity(new Intent(getContext(), WebViewActivity.class));*/
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
