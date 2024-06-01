package com.example.project;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.data.*;
import com.example.project.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    ActivityMainBinding binding;

    ArrayList<Object> List_for_trans = new ArrayList<>();

    private SensorManager sensorManager;
    private boolean running = false;
    private float totalSteps = 0f;
    private float previousTotalSteps = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent myIntent = getIntent();
        String email = myIntent.getStringExtra("email");
        if(email == null) {
            Log.d("==========================", "Email is null");
        } else {
            Log.d("==========================", email);
        }
        loadData();
        resetSteps();

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        if (firstgo.runapp()){
            Intent intent = new Intent(MainActivity.this, splash_screen.class);
            startActivity(intent);
        }
        else{
        setContentView(binding.getRoot());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        replaceFragment(new UslugiFragment());
        binding.bottomNavigation.setOnItemSelectedListener(item ->{

            switch (item.getItemId()){
                case R.id.uslugi_bottom1:
                    replaceFragment(new UslugiFragment());
                    break;
                case R.id.profile_bottom1:
                    try {
                        URL url = new URL("http://10.0.2.2:8080/profile");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setRequestProperty("Content-Type", "text/plain");
                        String email_to_send = email;
                        String message = email_to_send;
                        try (OutputStream outputStream = connection.getOutputStream()) {
                            outputStream.write(message.getBytes());
                        }

                        int responseCode = connection.getResponseCode();
                        System.out.println("Response Code: " + responseCode);

                        if (responseCode == HttpURLConnection.HTTP_OK) {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String line = (reader.readLine());
                            reader.close();
                            Log.d("=========profile===================: ", String.valueOf(line));
                            replaceFragment_profile(new ProfileFragment(), Collections.singletonList(line));}}
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case R.id.menu_bottom1:
                    replaceFragment(new MenuFragment());
                    break;
            }
            return true;
        });
    }}

    @Override
    protected void onResume() {
        super.onResume();
        running = true;

        Sensor stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (stepSensor == null) {
            Toast.makeText(this, "No sensor detected on this device", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    public void resetSteps() {
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat("key1", previousTotalSteps);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        float savedNumber = sharedPreferences.getFloat("key1", 0f);
        Log.d("MainActivity", String.valueOf(savedNumber));
        previousTotalSteps = savedNumber;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
// We do not have to write anything in this function for this app
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
    private void replaceFragment_profile(Fragment fragment, List transactionList) {
        ArrayList itemList1 = new ArrayList<>(transactionList);
        Bundle bundle = new Bundle();
        bundle.putSerializable("transactionList", itemList1);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}
