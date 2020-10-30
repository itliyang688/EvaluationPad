package cn.fek12.evaluation.application;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.fek12.basic.application.BaseApplication;
import com.fek12.basic.utils.toast.ToastUtils;
import com.future_education.module_login.IUserData;
import com.future_education.module_login.OnUserDataUpdateListener;
import com.google.gson.Gson;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import cn.fek12.evaluation.ent.CookieReadInterceptor;
import cn.fek12.evaluation.ent.CookiesSaveInterceptor;
import cn.fek12.evaluation.ent.InterceptorUtil;
import cn.fek12.evaluation.ent.ParameterInterceptor;
import cn.fek12.evaluation.model.entity.StudentInfoEntity;
import cn.fek12.evaluation.model.entity.UserInfoEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.view.adapter.ConqueredAdapter;
import okhttp3.OkHttpClient;

public class MyApplication extends BaseApplication {
    public static MyApplication myApp;
    private static OkHttpClient mOkHttpClient;
    private static final int DEFAULT_TIMEOUT = 30;
    //private String userId = "413";
    //private String userId = "8a236697-cd63-45e5-b502-823de2dec1ba";//yanglaoshi
    private String userId = "8a236697-cd63-45e5-b502-823de2dec1ba";
    //private String userId = "094ed121-cc66-46d1-892a-0be39da5b206";
    private static final String TAG = "AIDL_Log";
    private IUserData iUserData;
    private boolean connected;
    private boolean serviceIsSuc = false;

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        bindService();
        initFileDownloader();
        /**解决添加uid后webview报错问题*/
        hookWebView();

    }


    public String getUserId() {
        //return PrefUtilsData.getUserId();
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static MyApplication getMyApp() {
        return myApp;
    }

    public void bindService() {
        if(!serviceIsSuc){
            Intent intent = new Intent();
            intent.setPackage("com.future_education.launcher");
            intent.setAction("com.future_education.launcher.aidlService");
            serviceIsSuc = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
            Log.d(TAG, "bindService: "+ serviceIsSuc);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            connected = true;
            iUserData = IUserData.Stub.asInterface(service);
            if(iUserData != null){
                try {
                    String studentInfo = iUserData.getStudentInfo();
                    StudentInfoEntity entity = new Gson().fromJson(studentInfo, StudentInfoEntity.class);
                    PrefUtilsData.setUserId(entity.getPer_id());
                    PrefUtilsData.setPer_level(String.valueOf(entity.getPer_level()));
                    //如果不使用通知功能，以下代码不是必须的
                    iUserData.registerListener(onUserDataUpdateListener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iUserData = null;
            connected = false;
        }
    };

    public void unbindService(){
        // 使用通知功能后，需要解绑，因onServiceDisconnected已经将iUserData置空，需要放在unbindService前面
        if (iUserData != null && iUserData.asBinder().isBinderAlive()) {
            try {
                iUserData.unregisterListener(onUserDataUpdateListener);
                Log.d(TAG, "onDestroy: unregisterListener");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        unbindService(serviceConnection);
        serviceIsSuc = false;
    }

    /**如果登陆信息和学生信息发生变化退出程序*/
    private OnUserDataUpdateListener onUserDataUpdateListener = new OnUserDataUpdateListener.Stub() {
        @Override
        public void onLoginStateUpdate(boolean isLogin) throws RemoteException {
            Log.d(TAG, "onLoginStateUpdate: "+ isLogin);
            clearData();
            android.os.Process.killProcess(android.os.Process.myPid());
        }

        @Override
        public void onTokenUpdate(String token) throws RemoteException {
            Log.d(TAG, "onTokenUpdate: "+ token);
        }

        @Override
        public void onLoginInfoUpdate(String loginInfo) throws RemoteException {
            Log.d(TAG, "onLoginInfoUpdate: "+loginInfo);
        }

        @Override
        public void onStudentInfoUpdate(String studentInfo) throws RemoteException {
            Log.d(TAG, "onStudentInfoUpdate: "+studentInfo);
            clearData();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    };

    public void clearData(){
        PrefUtilsData.userClear();
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

    private void initFileDownloader(){
        FileDownloader.setupOnApplicationOnCreate(this)
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                ))
                .connectionCountAdapter((downloadId, url, path, totalLength) -> {
                    return 1;//下载文件块数是1,解决偶现的下载任务停止问题。
                })
                .commit();
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

    public void hookWebView() {
        int sdkInt = Build.VERSION.SDK_INT;
        try {
            Class<?> factoryClass = Class.forName("android.webkit.WebViewFactory");
            Field field = factoryClass.getDeclaredField("sProviderInstance");
            field.setAccessible(true);
            Object sProviderInstance = field.get(null);
            if (sProviderInstance != null) {
                Log.i("TAG-hookWebView", "sProviderInstance isn't null");
                return;
            }

            Method getProviderClassMethod;
            if (sdkInt > 22) {
                getProviderClassMethod = factoryClass.getDeclaredMethod("getProviderClass");
            } else if (sdkInt == 22) {
                getProviderClassMethod = factoryClass.getDeclaredMethod("getFactoryClass");
            } else {
                Log.i("TAG-hookWebView", "Don't need to Hook WebView");
                return;
            }
            getProviderClassMethod.setAccessible(true);
            Class<?> factoryProviderClass = (Class<?>) getProviderClassMethod.invoke(factoryClass);
            Class<?> delegateClass = Class.forName("android.webkit.WebViewDelegate");
            Constructor<?> delegateConstructor = delegateClass.getDeclaredConstructor();
            delegateConstructor.setAccessible(true);
            if (sdkInt < 26) {//低于Android O版本
                Constructor<?> providerConstructor = factoryProviderClass.getConstructor(delegateClass);
                if (providerConstructor != null) {
                    providerConstructor.setAccessible(true);
                    sProviderInstance = providerConstructor.newInstance(delegateConstructor.newInstance());
                }
            } else {
                Field chromiumMethodName = factoryClass.getDeclaredField("CHROMIUM_WEBVIEW_FACTORY_METHOD");
                chromiumMethodName.setAccessible(true);
                String chromiumMethodNameStr = (String) chromiumMethodName.get(null);
                if (chromiumMethodNameStr == null) {
                    chromiumMethodNameStr = "create";
                }
                Method staticFactory = factoryProviderClass.getMethod(chromiumMethodNameStr, delegateClass);
                if (staticFactory != null) {
                    sProviderInstance = staticFactory.invoke(null, delegateConstructor.newInstance());
                }
            }

            if (sProviderInstance != null) {
                field.set("sProviderInstance", sProviderInstance);
                Log.i("TAG-hookWebView", "Hook success!");
            } else {
                Log.i("TAG-hookWebView", "Hook failed!");
            }
        } catch (Throwable e) {
            Log.w("TAG-hookWebView", e);
        }
    }

}
