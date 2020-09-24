package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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
import cn.fek12.evaluation.impl.IRemediaWeb;
import cn.fek12.evaluation.model.config.HtmlConfigs;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.presenter.RemediaWebPresenter;
import cn.fek12.evaluation.view.jsinterface.JavaScriptinterface;
import cn.fek12.evaluation.view.widget.NoRollWebView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: AnswerWebViewActivity
 * @Description:
 * @CreateDate: 2019/11/15 17:17
 */
public class RemedialPracticeWebActivity extends BaseActivity<RemediaWebPresenter> implements IRemediaWeb.View {
    @BindView(R.id.webView)
    NoRollWebView webView;
    @BindView(R.id.iv_left_back)
    ImageView ivLeftBack;
    @BindView(R.id.titleView)
    LinearLayout titleView;
    @BindView(R.id.imgBg)
    ImageView imgBg;

    private String subjectCategoryId;

    @Override
    public int setLayoutResource() {
        return R.layout.small_webview_activity;
    }

    @Override
    protected RemediaWebPresenter onInitLogicImpl() {
        return new RemediaWebPresenter(this);
    }

    @Override
    protected boolean getFitsSystemWindows() {
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        subjectCategoryId = intent.getStringExtra("subjectCategoryId");
        String url = HtmlConfigs.SMALLWORK + "userId=" + MyApplication.getMyApp().getUserId() + "&subjectCategoryNum=" + subjectCategoryId;
        webView.loadUrl(url);
    }


    @Override
    protected void onInitView() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgBg.setVisibility(View.INVISIBLE);

            }
        }, 2000);//2秒后执行Runnable中的run方法
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.uauth(RemedialPracticeWebActivity.this, MyApplication.getMyApp().getUserId());
            }
        }, 1000);//2秒后执行Runnable中的run方法

        subjectCategoryId = getIntent().getStringExtra("subjectCategoryId");
    }

    private void initWebView() {
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
        //String url = "http://192.168.0.46/noc/html/SmallWork.html";
        //String url = "http://192.168.0.46/noc/html/SmallWork.html?userId=" + MyApplication.getMyApplication().getUserId() + "&subjectCategoryId=" + subjectCategoryId;
        //String url = "http://218.245.6.132:11111/html/SmallWork.html?userId=" + MyApplication.getMyApplication().getUserId() + "&subjectCategoryId=" + subjectCategoryId;
        //showLoading();
        String url = HtmlConfigs.SMALLWORK + "userId=" + MyApplication.getMyApp().getUserId() + "&subjectCategoryNum=" + subjectCategoryId;
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //hideLoading();
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

    @Override
    public void loadSuc(String token) {
        PrefUtilsData.setToken(token);
        initWebView();
    }

    @Override
    public void loadFail(String msg) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(MainActivity.get() == null){
            /**退出清空及解绑服务*/
            MyApplication.getMyApp().clearData();
            try {
                MyApplication.getMyApp().unbindService();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}
