package com.hackinghell.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


    }

    public void recyclerPage(View view) {
        Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
        startActivity(intent);
    }

    public void openAnyActivity(View view) {
        Intent intent = new Intent(MainActivity.this, AnyActivity.class);
        startActivity(intent);
    }
}

