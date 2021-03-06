package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Build;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.jsinterface.JavaScriptinterface;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: AnswerWebViewActivity
 * @Description:
 * @CreateDate: 2019/11/15 17:17
 */
public class PersonalReportWebViewActivity extends BaseActivity {
    @BindView(R.id.webView)
    WebView webView;
    private String webUrl;
    public static PersonalReportWebViewActivity reportWebViewActivity;

    public static PersonalReportWebViewActivity get() {
        return reportWebViewActivity != null ? reportWebViewActivity : null;
    }


    @Override
    public int setLayoutResource() {
        return R.layout.report_webview_activity;
    }

    @Override
    protected void onInitView() {
        reportWebViewActivity = this;
        Intent intent = getIntent();
        webUrl = intent.getStringExtra("webUrl");
        WebSettings webSettings = webView.getSettings();
        // android 5.0及以上默认不支持Mixed Content
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
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
        //String url = "http://192.168.0.46/noc/html/SmallWork.html";
        //String url = "http://192.168.0.46/noc/html/SmallWork.html?userId=" + MyApplication.getMyApplication().getUserId() + "&subjectCategoryId=" + subjectCategoryId;
        //String url = "http://218.245.6.132:11111/html/SmallWork.html?userId=" + MyApplication.getMyApplication().getUserId() + "&subjectCategoryId=" + subjectCategoryId;
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
            public void onPageFinished(WebView view,String url){
                hideLoading();
            }
        });
    }

    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    protected void onLoadData() {

    }
}
