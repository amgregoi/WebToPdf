package com.teioh.webtopdf;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{
    public final static String TAG = MainActivity.class.getSimpleName();

    private WebView mWebview;
    private Button mButton;
    private CustomWebClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebview = (WebView) findViewById(R.id.webview);
        mButton = (Button) findViewById(R.id.button);

        mWebview.loadUrl("http://www.d2jsp.org/");
        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.i(TAG, "taking  pic");
                mClient.takePic(mWebview, MainActivity.this);
            }
        });

        mClient = new CustomWebClient();
        mWebview.setWebViewClient(mClient);
    }
}
