package com.teioh.webtopdf;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class CustomWebClient extends WebViewClient
{
    public final static String TAG = CustomWebClient.class.getSimpleName();

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url)
    {
        view.loadUrl(url);
        return true;

    }

    public void takePic(WebView aWebView, Context aContext)
    {
        // do your stuff here
        aWebView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        aWebView.layout(0, 0, aWebView.getMeasuredWidth(), aWebView.getMeasuredHeight());
        aWebView.setDrawingCacheEnabled(true);
        aWebView.buildDrawingCache();
        Bitmap lBitmap = Bitmap.createBitmap(aWebView.getMeasuredWidth(), aWebView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas lCanvas = new Canvas(lBitmap);
        Paint lPaint = new Paint();
        int lHeight = lBitmap.getHeight();

        lCanvas.drawBitmap(lBitmap, 0, lHeight, lPaint);
        aWebView.draw(lCanvas);
        Log.i(TAG, "canvas width: " + lCanvas.getWidth());
        Log.i(TAG, "canvas height:" + lCanvas.getHeight());

        if (lBitmap != null)
        {
            try
            {
                File lFile = new File(aContext.getFilesDir(), "/aaaa.png");
                OutputStream lFOut = new FileOutputStream(lFile);

                lBitmap.compress(Bitmap.CompressFormat.PNG, 50, lFOut);
                lFOut.flush();
                lFOut.close();
                lBitmap.recycle();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

}
