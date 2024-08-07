package com.hackinghell.secondapp;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecyclerActivity extends AppCompatActivity {

    private final LinkedList<String> myList = new LinkedList<>();
    private int mCount = 0;

    private RecyclerView myRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycler);

        for (int i = 1; i <= 40; i++) {
            myList.addLast("Word " + mCount);
            Log.i("Word List", myList.getLast());
            mCount += 1;
        }

        // Get handle to the RecyclerView
        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // create am adapter amd supply the data to be displayed
        mAdapter = new WordListAdapter(this, myList);
        // Connect the adapter with the recyclerView
        myRecyclerView.setAdapter(mAdapter);
        // give the recyclerview a default layout manager
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}