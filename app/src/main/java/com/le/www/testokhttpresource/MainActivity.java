package com.le.www.testokhttpresource;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.guide.PostExample;
import okhttp3.recipes.AccessHeaders;
import okhttp3.recipes.Authenticate;
import okhttp3.recipes.CacheResponse;
import okhttp3.recipes.CancelCall;
import okhttp3.recipes.LoggingInterceptors;
import okhttp3.recipes.PerCallSettings;
import okhttp3.recipes.PostFile;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "wang";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (addPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

    }
    public static final String url = "http://10.154.157.34:8080/mstore_api/mapi/edit/recommend?pagefrom=1&pagesize=1&code=RANK_HOT";
    public void getMethodSync(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    GetExample.get();
                    MyHttpClient.getSync(url);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.i("wang", "exception..");
                }
            }
        }).start();
    }


    public void getMethodAsync(View v) {
        MyHttpClient.getAsync(url);
    }

    public void postMethod(View v) {
        MyHttpClient.postAsync();
    }


    public void postJson(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostExample.post();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Log.i("wang", "exception..");
                }
            }
        }).start();
    }

    public void postFileAsync(View v) {
        MyHttpClient.postFileAsync();
    }


    public void accessHeaders(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AccessHeaders.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }


    public void Authenticate(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Authenticate.main();
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


    public void CancelCall(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    CancelCall.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public void LoggingInterceptor(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    LoggingInterceptors.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }


    public void PerCallSettings(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PerCallSettings.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    public void PostFile(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    PostFile.main();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }




    public void more(View v) {
        startActivity(new Intent(getApplicationContext(), MoreActivity.class));
    }


    private boolean addPermission(Activity activity, String permission) {
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        return false;
    }

    public void testEx(View view) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        int count = 5;
        for (int i=0; i<count; i++) {
            executorService.execute(new MyRunnable());
        }
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Log.i("wang", "thread id:"+Thread.currentThread().getId() + " start...");
                Thread.sleep(5000);
                Log.i("wang", "thread id:"+Thread.currentThread().getId() + " finish");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
