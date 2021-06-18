package com.google.ar.core.examples.java.augmentedimage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;


public class MainActivity extends Activity {

    // For how many milliseconds the warning will be up
    private static final int SPLASH_TIMER = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseCSV();

        // This menu is really just a splash screen to warn people into being responsible while
        // using the app
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMER);
    }

    // Set the initial values for the HomeActivity variables
    // (except debug which always starts as false)
    private void parseCSV() {
        String filename = "default.jpeg";
        float width = 0.14f;
        String[] translationStr = new String[3];
        String[] rotationStr = new String[3];
        float[] translation = new float[3];
        float[] rotation = new float[3];
        float scale = 1.0f;
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.initial_offsets);
            BufferedReader csvReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String row;
            int n = 0;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                if (n % 5 == 0) {
                    filename = data[0];
                } else if (n % 5 == 1) {
                    width = Float.parseFloat(data[0]);
                } else if (n % 5 == 2) {
                    translationStr = data;
                } else if (n % 5 == 3) {
                    rotationStr = data;
                } else if (n % 5 == 4) {
                    scale = Float.parseFloat(data[0]);
                    for(int i = 0; i <3; i++){
                        translation[i] = Float.parseFloat(translationStr[i]);
                        rotation[i] = Float.parseFloat(rotationStr[i]);
                    }
                    HomeActivity.add_anchor(filename, width, translation, rotation, scale);
                }
                n++;
            }
            csvReader.close();
        } catch (IOException e) {
            HomeActivity.add_anchor("defaultAnchor.jpg", 0.14f, new float[]{1.0f, 1.0f, 1.0f}, new float[]{1.0f, 1.0f, 1.0f}, 5.0f);
        }
    }
}
