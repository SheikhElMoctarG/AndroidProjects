package com.sheikh.allnews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import Controller.Adepter;
import Model.News;

public class HomeActivity extends AppCompatActivity {
    private static final String LINK_SERVER = "https://mersal-info.herokuapp.com";
    JSONArray posts = null;
    String errorResponse= null;
    DialogLoading loading = new DialogLoading(HomeActivity.this);
    RecyclerView recyclerView;
    List<News> newsList = new ArrayList<>();
    ImageView darkMode;
    Boolean isDark = false;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            window.setStatusBarColor(getResources().getColor(R.color.status_homeActivity));
        }
        getSupportActionBar().hide();
        if (checkAviableInternet())
        loading();
        internetCheck();
        getRequest();
        step1();
        step2();
        darkMode = findViewById(R.id.moon_icon);
        ImageView policyIcon = findViewById(R.id.policy_icon);
        SharedPreferences sharedPreferences1 = getSharedPreferences("darkmode",MODE_PRIVATE);
        isDark = sharedPreferences1.getBoolean("dark",false);
        if (isDark){
            darkMode.setImageResource(R.drawable.sun);
            policyIcon.setVisibility(View.INVISIBLE);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            darkMode.setImageResource(R.drawable.moon);
            policyIcon.setVisibility(View.VISIBLE);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isDark){
                    darkMode.setImageResource(R.drawable.moon);
                    policyIcon.setVisibility(View.VISIBLE);
                    SharedPreferences preferences1 = getSharedPreferences("darkmode", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putBoolean("dark", false);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else {
                    darkMode.setImageResource(R.drawable.sun);
                    policyIcon.setVisibility(View.INVISIBLE);
                    SharedPreferences preferences1 = getSharedPreferences("darkmode", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences1.edit();
                    editor.putBoolean("dark", true);
                    editor.commit();
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("target",0);
        Boolean newUser = sharedPreferences.getBoolean("is",false);
        if(newUser == false) {
            Typeface titlefont = ResourcesCompat.getFont(HomeActivity.this, R.font.cairo_black);
            Typeface descriFont = ResourcesCompat.getFont(HomeActivity.this, R.font.cairo_reg);
            TapTargetView.showFor(this, TapTarget.forView(findViewById(R.id.moon_icon), getString(R.string.titleCircleDark), getString(R.string.descriptionCircleDark))
                            .outerCircleColor(R.color.circleColor)
                            .targetCircleColor(R.color.white)
                            .titleTextColor(R.color.white)
                            .descriptionTextColor(R.color.white)
                            .titleTextSize(30)
                            .titleTypeface(titlefont)
                            .descriptionTextSize(20)
                            .descriptionTypeface(descriFont)
                            .tintTarget(false)
                            .cancelable(true),
                    new TapTargetView.Listener() {
                        @Override
                        public void onTargetClick(TapTargetView view) {
                            super.onTargetClick(view);
                            if (posts != null) {
                                taptargetButtonSpeech(R.string.titleCircleSpeechButton, R.string.descriptionCircleSpeechButton, R.id.speechButton);
                            }
                            SharedPreferences preferences = getSharedPreferences("target", 0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("is", true);
                            editor.commit();
                        }
                    }
            );
        }
    }
    private boolean checkAviableInternet(){
        try {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = null;
            if(manager != null){
                info = manager.getActiveNetworkInfo();
            }
            return info != null && info.isConnected();
        } catch (NullPointerException e){
            return false;
        }

    }
    private void internetCheck(){
        if (!checkAviableInternet()){
            toPageNoInternet();
        }else if (checkAviableInternet() && posts != null){
            loading("hide");

            LinearLayout placeHolder = findViewById(R.id.pageEmpty);
            getLayoutInflater().inflate(R.layout.home,placeHolder);
            recyclerView = findViewById(R.id.recyclerView);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            Adepter adepter = new Adepter(HomeActivity.this,newsList);
            recyclerView.setAdapter(adepter);
        }else if (checkAviableInternet() && errorResponse != null){
            loading("hide");
            LinearLayout page = findViewById(R.id.pageEmpty);
            getLayoutInflater().inflate(R.layout.error_server,page);
            TextView buttonCall = findViewById(R.id.send_toDeveloper);
            buttonCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        String textMessage = "مرحباٌ، هناك مشكلة بتطبيق اخر الاخبار:"+errorResponse;
                        String number = "22234982406";
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+number+"&text="+textMessage));
                        startActivity(intent);
                    }catch (Exception e){

                    }
                }
            });
        }
    }
    private void loading()  {
        if (checkAviableInternet() && posts == null){
            loading.startDialogLoading();
        } else if (posts != null || errorResponse != null){
            loading.dismessDialogLoading();
        }
    }
    private void loading(String s)  {
        if (s == "hide"){
            loading.dismessDialogLoading();
        }
    }
    private void getRequest(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, LINK_SERVER, response -> {
            try {
                posts = new JSONArray(response);
                Log.d("LengthArray", "getRequest: " +posts.length());
//                for (int d = 0; d < 10; d++) {
//                    Log.d("Sheikh", "getRequest: ");
//                }
                for (int i = 0; i < posts.length(); i++) {
                    JSONObject news = posts.getJSONObject(i);
                    try {
                        if (news.getString("image").startsWith("http")) {
                            Log.d("image:", "onBindViewHolder: " + news.getString("image"));
                            newsList.add(new News(news.getString("title"), news.getString("link"),
                                    news.getString("isoDate"), news.getString("image"),
                                    news.getString("description"), news.getString("name")));
                        }
                    } catch (Exception e){
                        newsList.add(new News(news.getString("title"), news.getString("link"),
                                news.getString("isoDate"), "https://g.top4top.io/p_21045amcw1.png",
                                news.getString("description"), news.getString("name")));
                    }


                }
            } catch (JSONException e) {
               errorResponse = e.toString();
            }
        }, error -> errorResponse = error.toString());
        Volley.newRequestQueue(this).add(stringRequest);
    }
    private void toPageNoInternet(){
        if (checkAviableInternet())
        loading("hide");
        LinearLayout placeHolder = findViewById(R.id.pageEmpty);
        getLayoutInflater().inflate(R.layout.no_internet,placeHolder);
        SharedPreferences sharedPreferences1 = getSharedPreferences("darkmode",MODE_PRIVATE);
        isDark = sharedPreferences1.getBoolean("dark",false);
        if (isDark){
            ImageView v = findViewById(R.id.imageView2);
            v.setVisibility(View.INVISIBLE);
        }
        TextView tryAgain = findViewById(R.id.button_tryagian);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Log.d("function variable", "run: "+"DONE");
    }
    private void step1(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                internetCheck();
                Log.d("posts variable", "run: "+posts);
                Log.d("error variable", "run: "+errorResponse);
            }
        },4000);
    }
    private void step2(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkAviableInternet() && posts == null && errorResponse ==null){
                    toPageNoInternet();
                }
            }
        },6000);
    }
    private void taptargetButtonSpeech(int title, int descri, int view){
        Typeface titlefont = ResourcesCompat.getFont(HomeActivity.this,R.font.cairo_black);
        Typeface descriFont = ResourcesCompat.getFont(HomeActivity.this,R.font.cairo_reg);
        TapTargetView.showFor(this, TapTarget.forView(findViewById(view),getString(title),getString(descri))
                        .outerCircleColor(R.color.circleColor3)
                        .targetCircleColor(R.color.white)
                        .titleTextColor(R.color.white)
                        .descriptionTextColor(R.color.white)
                        .titleTextSize(30)
                        .titleTypeface(titlefont)
                        .descriptionTextSize(20)
                        .descriptionTypeface(descriFont)
                        .tintTarget(false)
                        .cancelable(true),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetClick(TapTargetView view) {
                        super.onTargetClick(view);
                        taptargetButtonLinearpost(R.string.titleCircleLinearpost, R.string.descriptionCircleLinearpost , R.id.linearPost);
                    }
                }
        );
    }
    private void taptargetButtonLinearpost(int title, int descri, int view){
        Typeface titlefont = ResourcesCompat.getFont(HomeActivity.this,R.font.cairo_black);
        Typeface descriFont = ResourcesCompat.getFont(HomeActivity.this,R.font.cairo_reg);
        TapTargetView.showFor(this, TapTarget.forView(findViewById(view),getString(title),getString(descri))
                        .outerCircleColor(R.color.circleColor3)
                        .targetCircleColor(R.color.white)
                        .titleTextColor(R.color.white)
                        .descriptionTextColor(R.color.white)
                        .titleTextSize(30)
                        .titleTypeface(titlefont)
                        .descriptionTextSize(20)
                        .descriptionTypeface(descriFont)
                        .tintTarget(false)
                        .cancelable(true),
                new TapTargetView.Listener(){
                    @Override
                    public void onTargetCancel(TapTargetView view) {
                        super.onTargetCancel(view);
                    }
                }
        );
    }

}