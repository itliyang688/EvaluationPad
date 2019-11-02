package cn.fek12.evaluation.application;

import com.fek12.basic.application.BaseApplication;

import java.util.concurrent.TimeUnit;

import cn.fek12.evaluation.ent.CookieReadInterceptor;
import cn.fek12.evaluation.ent.CookiesSaveInterceptor;
import cn.fek12.evaluation.ent.InterceptorUtil;
import okhttp3.OkHttpClient;

public class MyApplication extends BaseApplication {
    public static MyApplication myApp;
    private static OkHttpClient mOkHttpClient;
    private static final int DEFAULT_TIMEOUT = 30;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
    }

    /**
     * 全局httpclient
     *
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
                    .addInterceptor(new CookiesSaveInterceptor())
                    .build();
        }
        return mOkHttpClient;
    }
}
