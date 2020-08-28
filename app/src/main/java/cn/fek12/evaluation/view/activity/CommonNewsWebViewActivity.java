package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.jsinterface.JavaScriptinterface;
import cn.fek12.evaluation.view.widget.NoRollWebView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: AnswerWebViewActivity
 * @Description:
 * @CreateDate: 2019/11/15 17:17
 */
public class CommonNewsWebViewActivity extends BaseActivity {
    @BindView(R.id.webView)
    NoRollWebView webView;
    @BindView(R.id.iv_left_back)
    ImageView ivLeftBack;
    @BindView(R.id.titleView)
    LinearLayout titleView;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    private String webUrl;
    public static final int ANSWER = 1;//答题
    public static final int TASKPAGE = 2;//作业或考试

    @Override
    public int setLayoutResource() {
        return R.layout.common_news_webview_activity;
    }

    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    protected void onInitView() {
        titleView.setPadding(0, AppUtils.getStatusBarHeight(CommonNewsWebViewActivity.this), 0, 0);

        Intent intent = getIntent();
        webUrl = intent.getStringExtra("webUrl");
        int typePage = intent.getIntExtra("typePage", ANSWER);
        if (typePage == ANSWER) {
            rootView.setBackgroundResource(R.mipmap.answer_bg);
            ivLeftBack.setImageResource(R.mipmap.answer_back_icon);
        }else if(typePage == TASKPAGE){
            ivLeftBack.setImageResource(R.mipmap.task1_back_icon);
        }
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
        webView.addJavascriptInterface(new JavaScriptinterface(getContext()), "android");
        showLoading();
        webView.loadUrl(webUrl);

        webView.setWebViewClient(new WebViewClient() {
            // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
