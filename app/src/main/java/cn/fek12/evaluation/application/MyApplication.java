package cn.fek12.evaluation.application;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;

import com.fek12.basic.application.BaseApplication;
import com.fek12.basic.utils.toast.ToastUtils;
import com.future_education.module_login.IUserData;
import com.future_education.module_login.OnUserDataUpdateListener;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import cn.fek12.evaluation.ent.CookieReadInterceptor;
import cn.fek12.evaluation.ent.CookiesSaveInterceptor;
import cn.fek12.evaluation.ent.InterceptorUtil;
import cn.fek12.evaluation.ent.ParameterInterceptor;
import cn.fek12.evaluation.model.entity.StudentInfoEntity;
import cn.fek12.evaluation.model.entity.UserInfoEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.utils.AppUtils;
import okhttp3.OkHttpClient;

public class MyApplication extends BaseApplication {
    public static MyApplication myApp;
    private static OkHttpClient mOkHttpClient;
    private static final int DEFAULT_TIMEOUT = 30;
    //private String userId = "413";
    //private String userId = "5a5a4534-0392-416a-9170-c923f563ca00";
    private String userId = "c73a79c5-622b-4d81-81bc-e862f45694cc";
    //private String userId = "00fb8e64-04af-4d8d-84dd-467390ad7000";
    private static final String TAG = "AIDL_Log";
    private IUserData iUserData;
    private boolean connected;
    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        bindService();
        //loadUserInfo();
        //int hight = AppUtils.getNavigationBarHeight(getApplicationContext());
        //int hight1 = AppUtils.getStatusBarHeight(getApplicationContext());
        //Log.e("hight::::::",hight+"-------"+hight1);
    }

    public String getUserId() {
        return PrefUtilsData.getUserId();
        //return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static MyApplication getMyApp() {
        return myApp;
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setPackage("com.future_education.launcher");
        intent.setAction("com.future_education.launcher.aidlService");
        boolean isSuc = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "bindService: "+ isSuc);
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

    private void clearData(){
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

}
