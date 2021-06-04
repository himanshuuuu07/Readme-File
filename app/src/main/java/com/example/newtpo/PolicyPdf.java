package com.example.newtpo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.net.URLEncoder;

public class PolicyPdf extends AppCompatActivity {

    WebView pdfView;
    String fileName,fileURL;
    DatabaseReference firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_pdf);

        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("Policy");
        fileName="Policy";
        fileURL="https://firebasestorage.googleapis.com/v0/b/newtpo-be4db.appspot.com/o/Policy%2Frules.pdf?alt=media&token=50ea1746-4d86-4cdb-a5e8-77c512872357";
        pdfView=findViewById(R.id.viewPolicyPdf);
        pdfView.getSettings().setJavaScriptEnabled(true);

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