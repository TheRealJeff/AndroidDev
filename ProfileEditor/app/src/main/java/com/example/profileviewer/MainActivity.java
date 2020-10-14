package com.example.profileviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    protected Button buttonProfile = null;
    protected Toolbar toolbarMainActivity;
    protected TextView textViewCurrentProfile;
    protected SharedPreferenceHelper sharedPreferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        //goes to second activity when the button is pressed
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToProfileViewer();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        //if the profile parameters are empty or unchanged, go to profile activity
        //else, change the button to current profile name
        String name = sharedPreferenceHelper.getProfileName();
        int age = sharedPreferenceHelper.getProfileAge();
        int id = sharedPreferenceHelper.getProfileId();

        if(name == null || age == 0 || id == 0 ){
            goToProfileViewer();
        }
        else{
            buttonProfile.setText(name);
        }
    }


    //setup the UI elements
    protected void setupUI(){
        sharedPreferenceHelper = new SharedPreferenceHelper(MainActivity.this);
        buttonProfile = findViewById(R.id.buttonProfileViewer);
        textViewCurrentProfile = findViewById(R.id.textViewCurrentProfile);
        toolbarMainActivity = findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbarMainActivity);

    }

    //intent to go to profile activity
    protected void goToProfileViewer(){
        Intent intent = new Intent(MainActivity.this, profileActivity.class);
        startActivity(intent);
    }

}