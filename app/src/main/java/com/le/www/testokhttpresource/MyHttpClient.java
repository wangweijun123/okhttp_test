package com.le.www.testokhttpresource;

import android.util.Log;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by wangweijun1 on 2017/3/20.
 */

public class MyHttpClient {
    // Content-Type: application/x-www-form-urlencoded; charset=UTF-8    store
    //               application/json; charset=utf-8
    // post 请求content type 非常重要(普通文本与json),
    // post body里面是什么参数就写什么类型的content_type
//    public static final MediaType mediaType
//            = MediaType.parse("application/json; charset=UTF-8");
    // mediaType是什么就告诉RequestBody 是什么
    public static final MediaType mediaType
            = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");
    /**
     * get 同步调用
     * @param url
     * @throws Exception
     */
    public static void getSync(String url) throws Exception {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Accept", "application/json; q=0.5")// 添加请求头
                .build();
        Response response = client.newCall(request).execute();
        String result = response.body().string();
        Log.i("wang", "Response Header Server:" + response.header("Server"));
        Log.i("wang", "result:" + result);
    }

    /**
     * get 异步调用
     * @param url
     */
    public static void getAsync(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("wang", "onFailure :  thread id:" + Thread.currentThread().getId());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("wang", "thread id:" + Thread.currentThread().getId());
                Log.i("wang", "result:" + result);
            }
        });
    }


    /**
     * 指定请求参数media type
     * post 异步请求
     */
    public static void postAsync() {
        String postUrl = "http://10.154.157.34:8080/mstore_api/mapi/edit/postrecommend";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, "code=REC_SOWORD_INDEX%2CFOCUS_INDEX%2CREC_CLASSIC_INDEX&record=0%2C0%2C30&versioncodes=1%2C51%2C116%2C5010%2C11%2C10153%2C104%2C18000%2C553%2C13101%2C8703448%2C23%2C25%2C1038%2C788%2C23%2C1%2C3301%2C0%2C174%2C1%2C10000300&packagenames=com.example.android.mobileperf.render%2Ccom.letv.android.letvlive%2Ccom.lesports.glivesports%2Ccom.mt.mtxx.mtxx%2Ccom.letv.bbs%2Ccom.letv.letvshop%2Ccom.baidu.input_letv%2Ccom.letv.android.client%2Ccom.ss.android.article.news%2Ccom.baidu.shucheng_127431121%2Ccom.google.android.gms%2Ccom.google.android.gsf%2Ccom.letv.games%2Ccom.letv.lesophoneclient%2Ccom.baidu.BaiduMap%2Ccom.google.android.gsf.login%2Ccom.le.www.testokhttpresource%2Ccom.sina.weibo%2Ccom.le.www.testokhttpresource.test%2Ccn.wps.moffice_eng%2Cio.hefuyi.zhihudaily%2Ccom.qqreader.leshi");

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("wang", "onFailure :  thread id:" + Thread.currentThread().getId());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("wang", "thread id:" + Thread.currentThread().getId());
                Log.i("wang", "result:" + result);
            }
        });
    }


    private static final String IMGUR_CLIENT_ID = "...";
//    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("application/octet-stream");

    private final OkHttpClient client = new OkHttpClient();

    /**
     * 上传文件
     */
    public static void postFileAsync() {
        String path = "/sdcard/a.zip";
        File file = new File(path);
        boolean f = file.exists();

        OkHttpClient client = new OkHttpClient();
        // Use the imgur image upload API as documented at https://api.imgur.com/endpoints/image
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("content", "Square Logo")
                .addFormDataPart("mobile", "15801097470")
                .addFormDataPart("imgs", "a.zip",
                        RequestBody.create(MEDIA_TYPE_PNG, file))
                .build();

        Request request = new Request.Builder()
                .url("http://10.154.157.34:8080/mstore_api/mapi/userfeedback/submit")
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
                        @Override
            public void onFailure(Call call, IOException e) {
                Log.i("wang", "onFailure :  thread id:" + Thread.currentThread().getId());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("wang", "thread id:" + Thread.currentThread().getId());
                Log.i("wang", "result:" + result);
            }
        });
    }


}
