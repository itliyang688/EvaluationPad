package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.view.widget.NoRollWebView;

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
    @BindView(R.id.iv_left_back)
    ImageView ivLeftBack;
    @BindView(R.id.titleView)
    LinearLayout titleView;
    private int paperResult;
    private int isanswered;
    private int paperId;

    @Override
    public int setLayoutResource() {
        return R.layout.answer_webview_activity;
    }

    @Override
    protected void onInitView() {
        Intent intent = getIntent();
        isanswered = intent.getIntExtra("isanswered", 0);
        paperId = intent.getIntExtra("paperId", 0);
        paperResult = intent.getIntExtra("paperResult",0);
        WebSettings webSettings = webView.getSettings();
        // 不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //webSettings.setTextZoom(100);
        //webView.setInitialScale(50);
        webSettings.setLoadWithOverviewMode(true);
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);
        webView.setWebChromeClient(new WebChromeClient());
        String url;
        if (isanswered == 0) {
            //url = "http://192.168.0.46/noc/html/index.html?userId=" + MyApplication.getMyApplication().getUserId() + "&paperId=" + paperId;
            url = "http://218.245.6.132:11111/html/index.html?userId=" + MyApplication.getMyApplication().getUserId() + "&paperId=" + paperId;
        } else {
            //url = "http://192.168.0.46/noc/html/analyze.html?userId=" + MyApplication.getMyApplication().getUserId() + "&paperId=" + paperId + "&paperResult=" + paperResult;
            url = "http://218.245.6.132:11111/html/analyze.html?userId=" + MyApplication.getMyApplication().getUserId() + "&paperId=" + paperId + "&paperResult=" + paperResult;
        }
        showLoading();
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view,String url){
                hideLoading();
            }
        });
    }

    @Override
    protected void onLoadData() {

    }

    @OnClick(R.id.titleView)
    public void onViewClicked() {
        finish();
    }
}
