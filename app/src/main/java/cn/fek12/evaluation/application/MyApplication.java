package cn.fek12.evaluation.application;

import android.text.TextUtils;
import android.util.Log;

import com.fek12.basic.application.BaseApplication;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import cn.fek12.evaluation.ent.CookieReadInterceptor;
import cn.fek12.evaluation.ent.CookiesSaveInterceptor;
import cn.fek12.evaluation.ent.InterceptorUtil;
import cn.fek12.evaluation.ent.ParameterInterceptor;
import cn.fek12.evaluation.model.entity.UserInfoEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.utils.AppUtils;
import okhttp3.OkHttpClient;

public class MyApplication extends BaseApplication {
    public static MyApplication myApp;
    private static OkHttpClient mOkHttpClient;
    private static final int DEFAULT_TIMEOUT = 30;
    private String userId = "413";
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        loadUserInfo();
        //int hight = AppUtils.getNavigationBarHeight(getApplicationContext());
        //int hight1 = AppUtils.getStatusBarHeight(getApplicationContext());
        //Log.e("hight::::::",hight+"-------"+hight1);
    }

    /**读取本地用户信息*/
    private void loadUserInfo(){
        String infoDete = AppUtils.loadUserInfo();
        if(TextUtils.isEmpty(infoDete)){
            return;
        }
        UserInfoEntity entity = new Gson().fromJson(infoDete, UserInfoEntity.class);
        PrefUtilsData.setUserId(entity.getRoot().getAccount().getUserId());
        PrefUtilsData.setPer_level(String.valueOf(entity.getRoot().getAccount().getPer_level()));
    }

    public String getUserId() {
        //return PrefUtilsData.getUserId();
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static MyApplication getMyApplication() {
        return myApp;
    }

    /**
     * 全局httpclient
     * @return
     */
    public static OkHttpClient initOKHttp() {
        if (mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient.Builder()
                    //.cookieJar(new CookieManger(App.getContext()))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置写入超时时间
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                    .retryOnConnectionFailure(true)//错误重联
                    .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                    .addInterceptor(new CookieReadInterceptor())
                    .addInterceptor(new ParameterInterceptor())
                    .addInterceptor(new CookiesSaveInterceptor())
                    .build();
        }
        return mOkHttpClient;
    }
}
