package com.example.profileviewer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;



public class profileActivity extends AppCompatActivity {

    protected Toolbar toolbarProfileActivity;
    protected ActionBar actionBar;
    protected TextView textViewName;
    protected TextView textViewAge;
    protected TextView textViewId;
    protected EditText editTextName;
    protected EditText editTextAge;
    protected EditText editTextId;
    protected Button buttonSave = null;
    protected SharedPreferenceHelper sharedPreferenceHelper;
    protected Profile currentProfile;
    protected String TAG = "profileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupUI();

        profileSetup();

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creates and assigns values to the profile parameters from the user input
                currentProfile.setName(editTextName.getText().toString());
                String tempAge = editTextAge.getText().toString();
                currentProfile.setAge(parseInteger(tempAge));
                String tempId = editTextId.getText().toString();
                currentProfile.setId(parseInteger(tempId));

                //check the input user parameters
                //if they are good, the parameters are saved, a "saved" toast is displayed
                //and the edit texts are "disabled" from further modification
                //else, the values aren't changed and an "invalid entries" toast is displayed
                if (currentProfile.getAge() > 17 && !currentProfile.getName().equals("") && currentProfile.getId() != 0){
                    sharedPreferenceHelper.saveProfile(currentProfile);
                    Toast toast = Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG);
                    toast.show();

                    editTextName.setText(currentProfile.getName());
                    editTextAge.setText(String.valueOf(currentProfile.getAge()));
                    editTextId.setText(String.valueOf(currentProfile.getId()));

                    buttonSave.setVisibility(View.INVISIBLE);
                    disableEditText(editTextName);
                    disableEditText(editTextAge);
                    disableEditText(editTextId);
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Invalid Entries", Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }

    //this method displays the profile parameters in the edit texts if some already exist
    private void profileSetup() {
        if(sharedPreferenceHelper.getProfileName() != null){
            editTextName.setText(sharedPreferenceHelper.getProfileName());
        }

        if(sharedPreferenceHelper.getProfileAge() != 0){
            editTextAge.setText(String.valueOf(sharedPreferenceHelper.getProfileAge()));
        }

        if(sharedPreferenceHelper.getProfileId() != 0){
            editTextId.setText(String.valueOf(sharedPreferenceHelper.getProfileId()));
        }
    }

    //sets up the UI elements
    private void setupUI() {
        sharedPreferenceHelper = new SharedPreferenceHelper(profileActivity.this);

        currentProfile = new Profile();

        textViewName = findViewById(R.id.textViewName);
        textViewAge = findViewById(R.id.textViewAge);
        textViewId = findViewById(R.id.textViewId);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        editTextId = findViewById(R.id.editTextId);

        disableEditText(editTextName);
        disableEditText(editTextAge);
        disableEditText(editTextId);

        setFilters();

        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setVisibility(View.INVISIBLE);

        toolbarProfileActivity = findViewById(R.id.toolbarProfileActivity);
        setSupportActionBar(toolbarProfileActivity);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //returns the integer value from a string
    private int parseInteger(String temp){
        int x = 0;
        if(!"".equals(temp)){
            x = Integer.parseInt(temp);
        }
        return x;
    }

    //sets the filters for the parameter edit texts
    private void setFilters(){
        //consists of a length filter and an alphabetic character filter for the name
        int maxNameLength = 40;
        InputFilter[] FilterNameInput = new InputFilter[2];
        FilterNameInput[0] = new InputFilter.LengthFilter(maxNameLength);
        FilterNameInput[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dstart, int dend) {
                for (int i = start; i < end; i++){
                    if (!Character.isAlphabetic(charSequence.charAt(i))){
                        return "";
                    }
                }
                return null;
            }
        };
        editTextName.setFilters(FilterNameInput);

        //consists of a length filter and an numerical character filter for the age
        int maxAgeLength = 2;
        InputFilter[] FilterAgeInput = new InputFilter[2];
        FilterAgeInput[0] = new InputFilter.LengthFilter(maxAgeLength);
        FilterAgeInput[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dstart, int dend) {
                for (int i = start; i < end; i++){
                    if (!Character.isDigit(charSequence.charAt(i))){
                        return "";
                    }
                }
                return null;
            }
        };
        editTextAge.setFilters(FilterAgeInput);

        //consists of a length filter and an numerical character filter for the age
        int maxIdLength = 6;
        InputFilter[] FilterIdInput = new InputFilter[2];
        FilterIdInput[0] = new InputFilter.LengthFilter(maxIdLength);
        FilterIdInput[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int start, int end, Spanned spanned, int dstart, int dend) {
                for (int i = start; i < end; i++){
                    if (!Character.isDigit(charSequence.charAt(i))){
                        return "";
                    }
                }
                return null;
            }
        };
        editTextId.setFilters(FilterIdInput);
    }

    //inflates the menu and overflow menu
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    //method to disable the edit texts when the edit mode isn't engaged
    private void disableEditText(EditText editText){
        editText.setFocusableInTouchMode(false);
        editText.clearFocus();
        editText.setCursorVisible(false);
        }

    //method to enable the edit texts when the edit mode is engaged
    private void enableEditText(EditText editText){
        editText.setFocusableInTouchMode(true);
        editText.setCursorVisible(true);
    }

    //handles clicked the edit overflow menu item, which engages edit mode
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.itemEditText:
                buttonSave.setVisibility(View.VISIBLE);
                enableEditText(editTextName);
                enableEditText(editTextAge);
                enableEditText(editTextId);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}