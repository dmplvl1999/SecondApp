package com.hackinghell.secondapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private final LinkedList<String> myList = new LinkedList<>();
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        for (int i = 1; i <= 40; i++) {
            myList.addLast("Word " + mCount);
            Log.i("Word List", myList.getLast());
            mCount += 1;
        }
    }
}