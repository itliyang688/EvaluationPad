package cn.fek12.evaluation.view.jsinterface;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.webkit.JavascriptInterface;

import com.fek12.basic.application.BaseApplication;

import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.model.config.Configs;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.utils.DialogUtils;
import cn.fek12.evaluation.view.activity.AnswerWebViewActivity;
import cn.fek12.evaluation.view.activity.CommonWebViewBackActivity;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.jsinterface
 * @ClassName: JavaScriptinterface
 * @Description:
 * @CreateDate: 2019/12/18 15:35
 */
public class JavaScriptinterface {
    Context mContext;
    public JavaScriptinterface(Context context) {
        mContext= context;
    }

    /**
     * 与js交互时用到的方法，在js里直接调用的
     */
    @JavascriptInterface
    public void analysis(String analy) {
        PrefUtilsData.setAnalysisCache(analy);
        String url = Configs.WRONGRECORD;
        Intent intent = new Intent(mContext, CommonWebViewBackActivity.class);
        intent.putExtra("webUrl",url);
        mContext.startActivity(intent);
    }

    /**
     * JS调用返回解析数据
     */
    @JavascriptInterface
    public String returnData() {
        return PrefUtilsData.getAnalysisCache();
    }

    /**
     * 错题重做
     */
    @JavascriptInterface
    public void errorRework() {
        String url = Configs.ERRORREWORK;
        Intent intent = new Intent(mContext, CommonWebViewBackActivity.class);
        intent.putExtra("webUrl",url);
        mContext.startActivity(intent);
    }

    /**
     * JS调用获取用户ID
     */
    @JavascriptInterface
    public String getUserId() {
        return MyApplication.getMyApplication().getUserId();
    }

    /**
     * 提醒弹出框
     */
    @JavascriptInterface
    public void dialogRemind() {
        /**弹出提醒框*/
        DialogUtils.showAnswerRemind2(mContext).show();
    }
}
