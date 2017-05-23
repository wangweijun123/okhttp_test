package com.le.www.testokhttpresource;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.recipes.CacheResponse;
import okhttp3.recipes.PostFile;
import okhttp3.recipes.RewriteResponseCacheControl;
import okhttp3.sample.OkHttpContributors;

/**
 * Created by wangweijun1 on 2017/4/17.
 */

public class MoreActivity extends Activity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
    }

    public void OkHttpContributors(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpContributors.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void RewriteResponseCacheControl(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    RewriteResponseCacheControl.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }


    public void CacheResponse(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CacheResponse.main(getApplicationContext());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }



    public void testOkhttpClientSingleInstance(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                    try {
                        final OkHttpClient client = OkHttpUtils.getInstance().getOkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://123.125.91.30/api34/mapi/coop/business")
                                .build();
                        client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Log.i("wang",client + " onFailure");
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Log.i("wang",client + " onResponse..");
                            }
                        });
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
            }
        }).start();
    }
}
