package com.example.httptest_txt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //HTTPによる文字列取得
        MyHttpTask httpTask = new MyHttpTask(this);
        httpTask.execute("http://www.bmcomp.jp/hello.html");
        Log.d(TAG,"created");
    }
}
