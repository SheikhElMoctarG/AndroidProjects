package com.sheikh.allnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {

    Bundle extras;
    TextView title, ago, source;
    String link;
    ImageView back;
    WebView webView;
    Button button;
    String[] links1 = {"https://www.saharamedias.net/", "https://www.bellewarmedia.com", "http://kiffainfo.net/", "https://concours.mr/"};
    String[] links2 = {"https://alakhbar.info", "https://mourassiloun.com/", "https://essahraa.net/", "https://zahraa.mr/", "http://aqlame.com/", "http://meyadin.net/"};
    String[] links3 = {"http://tvm.mr/"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.white));
        }
        getSupportActionBar().hide();
        extras = getIntent().getExtras();
        title = findViewById(R.id.title_big);
        ago = findViewById(R.id.ago_big);
        source = findViewById(R.id.source_big);
        back = findViewById(R.id.back);
        webView = findViewById(R.id.webView);
        button = findViewById(R.id.buttonToSource);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title.setText(extras.getString("title"));
        ago.setText(extras.getString("ago"));
        source.setText(extras.getString("source"));
        link = extras.getString("link");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
        new doIT().execute();
//        try {
//            Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
//            String codeHtml = doc.text();
//            Elements text = doc.select("clearfix p");
//            Elements image = doc.select("featured-area");
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
    public class doIT extends AsyncTask<Void,Void,Void> {
        String codeHtml;
        @Override
        protected Void doInBackground(Void... params) {
            try {
            Document doc = Jsoup.connect(link).get();
            for (int i = 0; i <links1.length ; i++) {
                if (link.startsWith(links1[i])){
                    if (link.startsWith(links1[0])){
                        Elements text = doc.select("div.entry-content");
                        codeHtml = "<html dir=\"rtl\"> <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"> <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Almarai:wght@300&display=swap\" rel=\"stylesheet\"> <body><div align=\"center\">"+text.html()+"</div></body> <style> body{ font-family: 'Almarai', sans-serif; width: auto; padding: 10px;} </style> </html>";
                    }else {
                        Elements text = doc.select("div.entry-content");
                        Elements image = doc.select("div.featured-area");
                        codeHtml = "<html dir=\"rtl\"> <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"> <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Almarai:wght@300&display=swap\" rel=\"stylesheet\"> <body> <div align=\"center\">"+image.html()+text.html()+"</div></body> <style> body{ font-family: 'Almarai', sans-serif; width: auto; padding: 10px;} </style> </html>";
                    }
                }
            }
            for (int i2 = 0; i2 <links2.length ; i2++) {
                if (link.startsWith(links2[i2])){
                    Elements text = doc.select("div.field-item");
                    Elements image = doc.select("div.featured-area");
                    codeHtml = "<html dir=\"rtl\"> <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"> <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Almarai:wght@300&display=swap\" rel=\"stylesheet\"> <body> "+image.html()+text.html()+"</body> <style> body{ font-family: 'Almarai', sans-serif; width: auto; padding: 10px;} </style> </html>";
                }
            }
            for (int i3 = 0; i3 <links3.length ; i3++) {
               if (link.startsWith(links3[i3])){
                    Elements text = doc.select("div.entry");
                    Elements image = doc.select("div.featured-area");
                    codeHtml = "<html dir=\"rtl\"> <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"> <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Almarai:wght@300&display=swap\" rel=\"stylesheet\"> <body> "+image.html()+text.html()+"</body> <style> body{ font-family: 'Almarai', sans-serif; width: auto; padding: 10px;} </style> </html>";
                 }
            }
            } catch (IOException e) {
                e.printStackTrace();
            } return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("Details222", "onPostExecute: "+codeHtml);
            if (codeHtml != null) {
                String co = Base64.encodeToString(codeHtml.getBytes(), Base64.NO_PADDING);
                webView.loadData(co, "text/html", "base64");
            } else {
                String html = "<html dir=\"rtl\"> <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\"> <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin> <link href=\"https://fonts.googleapis.com/css2?family=Almarai:wght@300&display=swap\" rel=\"stylesheet\"> <body> <div align=\"center\"> <h1><span style=\"color: rgb(250, 7, 7);\">خطأ ما حدث:</span> <br> هذه المشكلة نتاجة عن تعطل الوصول الموقع الاخبار المستقلة</h1> </div> </body> <style> body{ font-family: 'Almarai', sans-serif; width: auto; padding: 10px; } </style> </html>";
                String co = Base64.encodeToString(html.getBytes(),Base64.NO_PADDING);
                webView.loadData(co, "text/html", "base64");
            }
        }
    }
}