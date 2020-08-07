package cn.fek12.evaluation.ent;

import java.io.IOException;
import java.util.Date;

import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: Allen.
 * @date: 2018/7/27
 * @description: 读取cookie
 */

public class ParameterInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        //添加请求参数
        /*HttpUrl url=original.url().newBuilder()
                .addQueryParameter("t", String.valueOf(new Date().getTime() / 1000))
                .addQueryParameter("token", PrefUtilsData.getToken())
                .build();
        //添加请求头
        Request request = original.newBuilder()
                .method(original.method(), original.body())
                .url(url)
                .build();*/

        Request.Builder requestBuilder = original.newBuilder()
                .header("t", String.valueOf(new Date().getTime() / 1000))
                .header("token", PrefUtilsData.getToken());
        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}
