package cn.fek12.evaluation.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import com.fek12.basic.utils.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.PopupWindow.SubjectPopupWindow;
import cn.fek12.evaluation.view.dialog.SelectDateDialog;
import cn.fek12.evaluation.view.jsinterface.JavaScriptinterface;
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
    WebView webView;
    @BindView(R.id.loadView)
    MultipleStatusView loadView;
    @BindView(R.id.tvStartDate)
    TextView tvStartDate;
    @BindView(R.id.llStartDate)
    LinearLayout llStartDate;
    @BindView(R.id.tvSubject)
    TextView tvSubject;
    @BindView(R.id.llSubject)
    LinearLayout llSubject;
    @BindView(R.id.tvEndDate)
    TextView tvEndDate;
    @BindView(R.id.llEndDate)
    LinearLayout llEndDate;
    @BindView(R.id.ivArrow)
    ImageView ivArrow;
    @BindView(R.id.llContainSubject)
    LinearLayout llContainSubject;
    private String startDate = "";
    private String endDate = "";
    private String subject = "";
    private SubjectPopupWindow subjectPopupWindow;

    @Override
    protected int getLayoutResource() {
        return R.layout.topic_worng_page_fragment;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onInitView(Bundle savedInstanceState) {
        //loadView.showEmpty();
        WebSettings webSettings = webView.getSettings();
        // 不使用缓存：
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setTextZoom(100);
        webSettings.setLoadWithOverviewMode(true);
        webView.addJavascriptInterface(new JavaScriptinterface(getContext()), "android");
        // 使WebView不可滚动
        //webView.setOnTouchListener((v, event) -> (event.getAction() == MotionEvent.ACTION_MOVE));
        //webView.loadUrl("http://218.245.6.132:11111/html/Record.html");
        //webView.loadUrl("http://192.168.0.46/noc/html/Record.html");
        //webView.loadUrl("http://192.168.0.46:11111/accurateReport/report?userId=413&paperResultId=11425");
        //webView.loadUrl("file:///android_asset/web/Record.html");
        //startActivity(new Intent(getContext(), WebViewActivity.class));
        //MainActivity activity = (MainActivity) getActivity();

        //String webUrl = Configs.RECORD + "userId="+MyApplication.getMyApplication().getUserId();
        loadView.showLoading();
        String webUrl = Configs.RECORD + "userId=" + MyApplication.getMyApplication().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject;
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
                loadView.showContent();
            }
        });
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

    @OnClick({R.id.llStartDate, R.id.llSubject, R.id.llEndDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llStartDate:
                SelectDateDialog startDateDialog = new SelectDateDialog(getContext(), "选择起始日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if (!TextUtils.isEmpty(endDate)) {
                            if(AppUtils.dateToTime(endDate) < AppUtils.dateToTime(date)){
                                ToastUtils.popUpToast("不能大于结束时间");
                                return;
                            }
                            String webUrl = Configs.RECORD + "userId=" + MyApplication.getMyApplication().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject;
                            webView.loadUrl(webUrl);
                        }
                        startDate = date;
                        tvStartDate.setText(date);
                    }
                });
                startDateDialog.show();
                break;
            case R.id.llSubject:
                subjectPopupWindow = new SubjectPopupWindow(getContext(), new SubjectPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        //loadView.showLoading();
                        tvSubject.setText(subjectName);
                        subject = subjectId.equals("0") ? "" : subjectId;
                        String webUrl = Configs.RECORD + "userId=" + MyApplication.getMyApplication().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject;
                        webView.loadUrl(webUrl);
                        //webView.loadUrl("http://192.168.0.46/noc/html/ErrorRework.html");
                        //webView.reload(); //刷新
                    }
                });
                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow.setImageResource(R.mipmap.rise_icon);
                subjectPopupWindow.showAsDropDown(llContainSubject, -155, 0);
                subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivArrow.setImageResource(R.mipmap.lower_icon);
                    }
                });

                break;
            case R.id.llEndDate:
                if (TextUtils.isEmpty(startDate)) {
                    ToastUtils.popUpToast("请选择起始日期");
                    return;
                }
                SelectDateDialog endDateDialog = new SelectDateDialog(getContext(), "选择结束日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if(AppUtils.dateToTime(date) < AppUtils.dateToTime(startDate)){
                            ToastUtils.popUpToast("不能小于开始时间");
                            return;
                        }
                        endDate = date;
                        tvEndDate.setText(date);
                        String webUrl = Configs.RECORD + "userId=" + MyApplication.getMyApplication().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject;
                        webView.loadUrl(webUrl);
                    }
                });
                endDateDialog.show();
                break;
        }
    }
}
