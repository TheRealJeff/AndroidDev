package com.example.profileviewer;

import android.content.Context;
import android.content.SharedPreferences;

//this class handles getting the profile parameters
//and saving the parameters if they correspond to the proper inputs
public class SharedPreferenceHelper {

    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context){
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.ProfileInfoFile), Context.MODE_PRIVATE);
    }

    public void saveProfile(Profile profile){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ProfileName", profile.getName());
        editor.putInt("ProfileAge", profile.getAge());
        editor.putInt("ProfileId", profile.getId());
        editor.apply();
    }

    public String getProfileName(){
        return sharedPreferences.getString("ProfileName", null);
    }

    public int getProfileAge() { return sharedPreferences.getInt("ProfileAge", 0); }

    public int getProfileId(){
        return sharedPreferences.getInt("ProfileId", 0);
    }
}
