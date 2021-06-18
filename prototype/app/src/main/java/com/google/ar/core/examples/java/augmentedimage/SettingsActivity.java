package com.google.ar.core.examples.java.augmentedimage;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class SettingsActivity
        extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    Switch aSwitch;
    TableLayout offsetTable;
    boolean debug;
    Spinner anchorView;
    TextView xView;
    TextView yView;
    TextView zView;
    TextView txView;
    TextView tyView;
    TextView tzView;
    TextView sView;

    String[] anchors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        offsetTable = findViewById(R.id.offsetTable);
        anchorView = findViewById(R.id.anchorValueSpinner);
        xView = findViewById(R.id.initialXValueText);
        yView = findViewById(R.id.initialYValueText);
        zView = findViewById(R.id.initialZValueText);
        txView = findViewById(R.id.initialTXValueText);
        tyView = findViewById(R.id.initialTYValueText);
        tzView = findViewById(R.id.initialTZValueText);
        sView = findViewById(R.id.initialScaleValueText);
        aSwitch = findViewById(R.id.switch1);

        anchors = new String[HomeActivity.num_anchors()];
        for (int i = 0; i < HomeActivity.num_anchors(); i++) {
            anchors[i] = String.valueOf(i);
        }

        anchorView.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, anchors);
        anchorView.setAdapter(adapter);
        getOffsets(anchorView);

        debug = HomeActivity.Check_Debug();

        // Checking the value of debug as transmitted by HomeActivity and behaves accordingly
        if(debug) {
            aSwitch.setChecked(true);
            offsetTable.setVisibility(View.VISIBLE);
        } else {
            aSwitch.setChecked(false);
            offsetTable.setVisibility(View.GONE);
        }

        // Initializing the debug mode switch
        aSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked){
                if (!debug)
                    aSwitch.setChecked(false);
                    Open_Dialog();
                }
            else {
                    debug = false;
                    offsetTable.setVisibility(View.GONE);
                    HomeActivity.Set_Debug(false);
                }
        });
    }

    public void getOffsets(View view) {
        int anchor = HomeActivity.getCurrentAnchor();
        float[] translation = HomeActivity.getTranslation(anchor);
        float[] rotation = HomeActivity.getRotation(anchor);
        float scale = HomeActivity.getScale(anchor);
        anchorView.setSelection(anchor);
        xView.setText(String.valueOf(translation[0]));
        yView.setText(String.valueOf(translation[1]));
        zView.setText(String.valueOf(translation[2]));
        txView.setText(String.valueOf(rotation[0]));
        tyView.setText(String.valueOf(rotation[1]));
        tzView.setText(String.valueOf(rotation[2]));
        sView.setText(String.valueOf(scale));
    }

    // Makes sure the UPDATE OFFSETS button actually updates the offsets
    public void setOffsets(View view) {
        int anchor = HomeActivity.getCurrentAnchor();
        String xText = xView.getText().toString();
        float x = xText.equals("") ? 0.0f : Float.parseFloat(xText);
        String yText = yView.getText().toString();
        float y = yText.equals("") ? 0.0f : Float.parseFloat(yText);
        String zText = zView.getText().toString();
        float z = zText.equals("") ? 0.0f : Float.parseFloat(zText);
        String txText = txView.getText().toString();
        float tx = txText.equals("") ? 0.0f : Float.parseFloat(txText);
        String tyText = tyView.getText().toString();
        float ty = tyText.equals("") ? 0.0f : Float.parseFloat(tyText);
        String tzText = tzView.getText().toString();
        float tz = tzText.equals("") ? 0.0f : Float.parseFloat(tzText);
        String sText = sView.getText().toString();
        float scale = sText.equals("") ? 1.0f : Float.parseFloat(sText);
        float[] translation = {x, y, z};
        float[] rotation = {tx, ty, tz};
        HomeActivity.setTranslation(anchor, translation);
        HomeActivity.setRotation(anchor, rotation);
        HomeActivity.setScale(anchor, scale);
        Toast.makeText(getBaseContext(), "Initial offsets successfully updated", Toast.LENGTH_SHORT).show();
    }

    // Opens the dialogue box that asks for the password
    private void Open_Dialog() {
        if (!debug) {
            // Initializing every element of the box
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialogue_password, null);
            final EditText mPassword = mView.findViewById(R.id.editTextTextPassword);
            Button mConfirm = mView.findViewById(R.id.confirm_button);

            // Displaying the dialogue box
            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();

            // This is the functionality of the button. In case the text field contains exactly the
            // right string it makes all the offset buttons visible and it tells HomeActivity to
            // update the debug variable (Which would let the ar activity know it's in debug mode)
            mConfirm.setOnClickListener(v -> {
                if (!mPassword.getText().toString().isEmpty()) {
                    if (mPassword.getText().toString().equals("Voronoi")) {
                        Toast.makeText(getBaseContext(), "Debugging mode on", Toast.LENGTH_SHORT).show();
                        debug = true;
                        offsetTable.setVisibility(View.VISIBLE);
                        HomeActivity.Set_Debug(true);
                        dialog.dismiss();
                        aSwitch.setChecked(true);
                    } else {
                        mPassword.setText("");
                        Toast.makeText(getBaseContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                } else {
                    Toast.makeText(SettingsActivity.this, "No password found!", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        HomeActivity.setCurrentAnchor(i);
        getOffsets(view);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) { }
}
