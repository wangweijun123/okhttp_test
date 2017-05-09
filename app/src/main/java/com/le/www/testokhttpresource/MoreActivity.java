package com.le.www.testokhttpresource;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

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
}
