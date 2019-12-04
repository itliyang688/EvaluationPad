package cn.fek12.evaluation.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.activity.WebViewActivity;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import cn.fek12.evaluation.view.widget.NoRollWebView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: TopicWrongRecordPageFragment
 * @Description:
 * @CreateDate: 2019/11/8 17:38
 */
public class TopicWrongRecordPageFragment extends BaseFragment {
    @BindView(R.id.webView)
    NoRollWebView webView;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;

    @Override
    protected int getLayoutResource() {
        return R.layout.topic_worng_page_fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onInitView(Bundle savedInstanceState) {
        loadView.showEmpty();
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
        //webView.loadUrl("http://218.245.6.132:11111/html/Record.html");
        //webView.loadUrl("http://192.168.0.46/noc/html/Record.html");
        //webView.loadUrl("http://192.168.0.46:11111/accurateReport/report?userId=413&paperResultId=11425");
        //webView.loadUrl("file:///android_asset/web/Record.html");
        //startActivity(new Intent(getContext(), WebViewActivity.class));
        //MainActivity activity = (MainActivity) getActivity();
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
