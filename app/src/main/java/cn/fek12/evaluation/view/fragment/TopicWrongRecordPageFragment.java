package cn.fek12.evaluation.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;
import com.fek12.basic.utils.toast.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.config.HtmlConfigs;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.DisUtil;
import cn.fek12.evaluation.view.PopupWindow.SubjectAllPopupWindow;
import cn.fek12.evaluation.view.activity.TestWebViewActivity;
import cn.fek12.evaluation.view.dialog.SelectDateDialog;
import cn.fek12.evaluation.view.jsinterface.JavaScriptinterface;
import cn.fek12.evaluation.view.widget.MultipleStatusView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: TopicWrongRecordPageFragment
 * @Description:
 * @CreateDate: 2019/11/8 17:38
 */
public class TopicWrongRecordPageFragment extends BaseFragment {
    //@BindView(R.id.webView)
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
    @BindView(R.id.rlContainView)
    RelativeLayout rlContainView;
    @BindView(R.id.tvSource)
    TextView tvSource;
    @BindView(R.id.ivArrow1)
    ImageView ivArrow1;
    @BindView(R.id.llContainSource)
    LinearLayout llContainSource;
    @BindView(R.id.llSource)
    LinearLayout llSource;
    private String startDate = "";
    private String endDate = "";
    private String subject = "";
    private String source = "";
    private SubjectAllPopupWindow subjectPopupWindow;

    @Override
    protected int getLayoutResource() {
        return R.layout.topic_worng_page_fragment;
    }

    public void initViewData() {
        //loadView.showEmpty();
        getContext().startActivity(new Intent(getContext(), TestWebViewActivity.class));
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
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.addJavascriptInterface(new JavaScriptinterface(getContext()), "android");

        String webUrl = HtmlConfigs.RECORD + "userId=" + MyApplication.getMyApp().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject + "&source=" + source;
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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onInitView(Bundle savedInstanceState) {
        loadView.showLoading();
        rlContainView.postDelayed(new Runnable() {
            @Override
            public void run() {
                webView = new WebView(getContext());
                rlContainView.addView(webView);
                initViewData();
            }
        }, 300);

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

    @OnClick({R.id.llStartDate, R.id.llSource, R.id.llSubject, R.id.llEndDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.llStartDate:
                SelectDateDialog startDateDialog = new SelectDateDialog(getContext(), "选择起始日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if (!TextUtils.isEmpty(endDate)) {
                            if (AppUtils.dateToTime(endDate) < AppUtils.dateToTime(date)) {
                                ToastUtils.popUpToast("不能大于结束时间");
                                return;
                            }
                            String webUrl = HtmlConfigs.RECORD + "userId=" + MyApplication.getMyApp().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject + "&source=" + source;
                            webView.loadUrl(webUrl);
                        }
                        startDate = date;
                        tvStartDate.setText(date);
                    }
                });
                startDateDialog.show();
                break;
            case R.id.llSource:
                subjectPopupWindow = new SubjectAllPopupWindow(getContext(), new SubjectAllPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        //loadView.showLoading();
                        tvSource.setText(subjectName);
                        source = subjectId.equals("ALL") ? "" : subjectId;
                        String webUrl = HtmlConfigs.RECORD + "userId=" + MyApplication.getMyApp().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject + "&source=" + source;
                        webView.loadUrl(webUrl);
                        //webView.loadUrl("http://192.168.0.46/noc/html/ErrorRework.html");
                        //webView.reload(); //刷新
                    }
                },1);

                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow1.setImageResource(R.mipmap.rise_icon);
                int width = (llContainSource.getWidth() - DisUtil.dp2px(getContext(), 150)) / 2;//获取x轴偏移量px
                subjectPopupWindow.showAsDropDown(llContainSource, width, 0);//设置x轴偏移量：注意单位为px
                subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        ivArrow1.setImageResource(R.mipmap.lower_icon);
                    }
                });

                subjectPopupWindow.setFocusable(true);
                subjectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                break;
            case R.id.llSubject:
                subjectPopupWindow = new SubjectAllPopupWindow(getContext(), new SubjectAllPopupWindow.OnSelectItmeListener() {
                    @Override
                    public void onSelectItme(String subjectId, String subjectName) {
                        tvSubject.setText(subjectName);
                        subject = subjectId.equals("0") ? "" : subjectId;
                        String webUrl = HtmlConfigs.RECORD + "userId=" + MyApplication.getMyApp().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject + "&source=" + source;
                        webView.loadUrl(webUrl);
                    }
                },0);

                AppUtils.fitPopupWindowOverStatusBar(subjectPopupWindow, true);
                ivArrow.setImageResource(R.mipmap.rise_icon);
                //subjectPopupWindow.showAsDropDown(llContainSubject);
                int tv_width = llContainSubject.getWidth();//获取对应的控件view宽度px值
                int pop_width = DisUtil.dp2px(getContext(), 150);
                int widthD = (tv_width - pop_width) / 2;//获取x轴偏移量px
                subjectPopupWindow.showAsDropDown(llContainSubject, widthD, 0);//设置x轴偏移量：注意单位为px
                //subjectPopupWindow.showAtLocation(llSubject,Gravity.CENTER_VERTICAL, DisUtil.dp2px(getContext(),-10),0);
                subjectPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //backgroundAlpha(1f);
                        ivArrow.setImageResource(R.mipmap.lower_icon);
                    }
                });

                subjectPopupWindow.setFocusable(true);
                subjectPopupWindow.setBackgroundDrawable(new BitmapDrawable());
                //backgroundAlpha(0.5f);
                break;
            case R.id.llEndDate:
                if (TextUtils.isEmpty(startDate)) {
                    ToastUtils.popUpToast("请选择起始日期");
                    return;
                }
                SelectDateDialog endDateDialog = new SelectDateDialog(getContext(), "选择结束日期", new SelectDateDialog.OnSelectItemDateListener() {
                    @Override
                    public void onDateItme(String date) {
                        if (AppUtils.dateToTime(date) < AppUtils.dateToTime(startDate)) {
                            ToastUtils.popUpToast("不能小于开始时间");
                            return;
                        }
                        endDate = date;
                        tvEndDate.setText(date);
                        String webUrl = HtmlConfigs.RECORD + "userId=" + MyApplication.getMyApp().getUserId() + "&beginDate=" + startDate + "&endDate=" + endDate + "&subject=" + subject + "&source=" + source;
                        webView.loadUrl(webUrl);
                    }
                });
                endDateDialog.show();
                break;
        }
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        activity = (Activity) mContext;
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        activity.getWindow().setAttributes(lp);
    }
}
