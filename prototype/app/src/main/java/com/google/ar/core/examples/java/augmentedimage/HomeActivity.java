package com.google.ar.core.examples.java.augmentedimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends Activity {
    // This variables are meant to be remembered until the app is terminated so they sit in this
    // activity which is effectively the last one to be closed when the app is terminated
    private static boolean debug;
    private static ArrayList<Anchor> anchors = new ArrayList<>();
    private static int currentAnchor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    // Makes sure the View Tessellation button starts the ar activity
    public void switchAR(View view) {
        Intent intent = new Intent(this, AugmentedImageActivity.class);
        startActivity(intent);
    }

    // Makes sure the Information button starts the InfoActivity
    public void switchInfo(View view) {
        Intent intent = new Intent(this, InfoActivity.class);
        startActivity(intent);
    }

    // Makes sure the Settings button starts the SettingsActivity
    public void switchSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    // This functions help other activities read or modify the values of the variables at the top
    public static void Set_Debug(boolean value) {
        debug = value;
    }
    public static boolean Check_Debug(){
        return debug;
    }
    public static void setCurrentAnchor(int anchor) { currentAnchor = anchor; }
    public static void setTranslation(int anchor, float[] t) { anchors.get(anchor).setTranslation(t); }
    public static void setRotation(int anchor, float[] r) { anchors.get(anchor).setRotation(r); }
    public static void setScale(int anchor, float s) { anchors.get(anchor).setScale(s); }
    public static int getCurrentAnchor() { return currentAnchor; }
    public static String getAnchorName(int anchor) { return anchors.get(anchor).getName(); }
    public static float getWidth(int anchor) { return anchors.get(anchor).getWidth(); }
    public static float[] getTranslation(int anchor) { return anchors.get(anchor).getTranslation(); }
    public static float[] getRotation(int anchor) { return anchors.get(anchor).getRotation(); }
    public static float getScale(int anchor) { return anchors.get(anchor).getScale(); }
    public static int num_anchors() { return anchors.size(); }
    public static void add_anchor(String filename, float width, float[] translation, float[] rotation, float scale) {
        Anchor anchor = new Anchor(filename, width, translation, rotation, scale);
        anchors.add(anchor);
    }

}
