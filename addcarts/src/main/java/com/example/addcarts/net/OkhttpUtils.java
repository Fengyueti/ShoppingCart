package com.example.addcarts.net;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkhttpUtils {
    Handler handler=new Handler();
    private final OkHttpClient okHttpClient;
    private static OkhttpUtils mInstance;
    public OkhttpUtils() {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OkhttpUtils getmInstance() {
        if(mInstance==null){
            synchronized (OkhttpUtils.class){
                mInstance=new OkhttpUtils();
            }
        }
        return mInstance;
    }
    public void doPost(String url, HashMap<String,String> params, final OkhttpCallback okhttpCallback){
        final FormBody.Builder builder = new FormBody.Builder();
        if(params!=null){
            for (Map.Entry<String, String> map : params.entrySet()) {
                builder.add(map.getKey(),map.getValue());
            }
        }
        final Request request = new Request.Builder().url(url).post(builder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if(okhttpCallback!=null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            okhttpCallback.failure("网络不稳定");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(okhttpCallback!=null){
                    if(response.code()==200){
                        final String result = response.body().string();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                okhttpCallback.success(result);
                            }
                        });
                    }
                }
            }
        });
    }
    public void cancelAllTask(){
        if(okHttpClient!=null){
            okHttpClient.dispatcher().cancelAll();
        }
    }
}
