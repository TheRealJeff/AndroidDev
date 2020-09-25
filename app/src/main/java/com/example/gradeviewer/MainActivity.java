package com.example.gradeviewer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //declare all elements below
    protected Button button_ViewGrades = null;
    protected TextView text_Welcome;
    protected TextView text_Instructions;
    protected Toolbar toolbar_gradeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI elements link function
        setupUI();

        //Go the gradeActivty when button is pressed
        button_ViewGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                goTogradeView();
            }
        });

    }

    // Link UI elements to declarations
    protected void setupUI(){
        text_Welcome = findViewById(R.id.text_Welcome);
        text_Instructions = findViewById(R.id.text_Instructions);
        button_ViewGrades = findViewById(R.id.button_ViewGrades);
        toolbar_gradeActivity = (Toolbar) findViewById(R.id.toolbar_gradeActivity);
        setSupportActionBar(toolbar_gradeActivity);
    }

    //starts gradeActivity
    protected void goTogradeView(){
        Intent intent = new Intent(this, gradeActivity.class);
        startActivity(intent);
    }

}