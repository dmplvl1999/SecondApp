package com.hackinghell.secondapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hackinghell.secondapp.databinding.ActivityStorageBinding;

public class StorageActivity extends AppCompatActivity {

    private final String filename = "filenamecognizant";
    private static final String NAME = "name";
    private static final String PWD = "pwd";

    private ActivityStorageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_storage);

        binding = ActivityStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }

    @Override
    protected void onPause() {
        super.onPause();
        storeData();
    }

    private void storeData() {
        // Create a file
        SharedPreferences preferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        //Open file
        SharedPreferences.Editor editor = preferences.edit();
        //write to file
        editor.putString(NAME,binding.etName.getText().toString());
        editor.putString(PWD,binding.etPassword.getText().toString());
        // save the file
        editor.apply();
    }

   @Override
    protected void onResume() {
        super.onResume();
        restoreData();
    }

    private void restoreData() {
        //if file exists it will open that file or create it
        SharedPreferences preferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
        String name = preferences.getString(NAME,"");
        String password = preferences.getString(PWD, "");
        binding.etName.setText(name);
        binding.etPassword.setText(password);
    }
}