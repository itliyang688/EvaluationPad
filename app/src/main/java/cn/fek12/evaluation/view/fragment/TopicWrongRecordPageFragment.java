package cn.fek12.evaluation.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.activity.MainActivity;
import cn.fek12.evaluation.view.activity.WebViewActivity;
import cn.fek12.evaluation.view.widget.ImageWebView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: TopicWrongRecordPageFragment
 * @Description:
 * @CreateDate: 2019/11/8 17:38
 */
public class TopicWrongRecordPageFragment extends BaseFragment {
    @BindView(R.id.webView)
    ImageWebView webView;

    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.progress_bar_ct.topic_worng_page_fragment, container, false);
        webView = (WebView) contentView.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        // 不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setTextZoom(100);
        webSettings.setLoadWithOverviewMode(true);
        webView.getSettings().setDomStorageEnabled(true);//打开DOM存储API
        webView.loadUrl("https://www.baidu.com/?tn=98012088_10_dg&ch=3");

        return contentView;
    }*/
    @Override
    protected int getLayoutResource() {
        return R.layout.topic_worng_page_fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onInitView(Bundle savedInstanceState) {
        WebSettings webSettings = webView.getSettings();
        // 不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setTextZoom(100);
        webSettings.setLoadWithOverviewMode(true);
        // 使WebView不可滚动
        //webView.setOnTouchListener((v, event) -> (event.getAction() == MotionEvent.ACTION_MOVE));
        webView.loadUrl("http://192.168.0.46:11111/index.html");
        //webView.loadUrl("file:///android_asset/web/Record.html");
        startActivity(new Intent(getContext(), WebViewActivity.class));
        //BaseActivity activity = (BaseActivity) getActivity();
        //activity.onResumeBuck();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
