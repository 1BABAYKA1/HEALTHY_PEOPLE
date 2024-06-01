package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


public class splash_screen extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_splash);

        ImageView logoend = findViewById(R.id.endlogo);

        TextView textend = findViewById(R.id.endtext);
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        textend.setVisibility(View.VISIBLE);
        textend.startAnimation(fadeInAnimation);
        logoend.setVisibility(View.VISIBLE);
        logoend.startAnimation(fadeInAnimation);

        new Handler().postDelayed(() -> {
            if (isServerReachable("10.0.2.2", 8080)) {
                Intent intent = new Intent(splash_screen.this, authorization.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                splash_screen.this.finish();
            } else {
                Intent intent = new Intent(splash_screen.this, NoWeb.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                splash_screen.this.finish();
            }

        }, SPLASH_TIME_OUT);
    }

    public static boolean isServerReachable(String ipAddress, int port) {
        try {
            URL url = new URL("http://10.0.2.2:8080/get");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            Log.d("Response: ", "Get - success");
            Log.d("Response: ", response.toString());
            return true;
        } catch (Exception e) {
            Log.d("Response: ", "=============GET=============");
            e.printStackTrace();
            return false;
        }
    }
}


