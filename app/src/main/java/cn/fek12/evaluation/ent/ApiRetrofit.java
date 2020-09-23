package cn.fek12.evaluation.ent;

import cn.fek12.evaluation.application.MyApplication;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRetrofit {
    private static ApiRetrofit mApiRetrofit;
    private Retrofit retrofit;
    private ApiServer apiServer;
    //public static String mBaseUrl = "http://192.168.0.46:11111/";
    //public static String mBaseUrl = "http://192.168.0.220:11111/";
    //public static String mBaseUrl = "http://218.245.6.132:11111/";
    //public static String mBaseUrl = "http://121.37.254.233:8080/";
    //public static String mBaseUrl = "http://api.51jxpj.cn/";
    public static String mBaseUrl = "https://api.51jxpj.cn/";
    //public static String mBaseUrl = "http://192.168.0.116:11113/";
    //public static String mBaseUrl = "http://218.245.6.132:11113/";
    //public static String mBaseUrl = "http://192.168.0.197:11113/";
    //public static String mBaseUrl = "http://192.168.0.46/noc/";

    public ApiRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                //支持RxJava2
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(MyApplication.initOKHttp())
                .build();

        apiServer = retrofit.create(ApiServer.class);
    }

    public static ApiRetrofit getInstance() {
        if (mApiRetrofit == null) {
            synchronized (Object.class) {
                if (mApiRetrofit == null) {
                    mApiRetrofit = new ApiRetrofit();
                }
            }
        }
        return mApiRetrofit;
    }

    public ApiServer getApiService() {
        return apiServer;
    }
}
