package com.example.newtpo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;

public class ViewPDF extends AppCompatActivity
{
    WebView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        pdfView= findViewById(R.id.viewPdf);
        pdfView.getSettings().setJavaScriptEnabled(true);

        String fileName=getIntent().getStringExtra("fileName");
        String fileURL=getIntent().getStringExtra("fileurl");

        ProgressDialog pd=new ProgressDialog(this);
        pd.setTitle(fileName);
        pd.setMessage("Opening...!!!");

        pdfView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });

        String url="";
        try{
            url= URLEncoder.encode(fileURL,"UTF-8");
        }catch (Exception e)
        {}

        pdfView.loadUrl("https://docs.google.com/gview?embedded=true&url="+url);
    }



}