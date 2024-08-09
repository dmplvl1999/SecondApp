package com.hackinghell.secondapp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;

//import com.hackinghell.secondapp.database.ItemDao;
import com.hackinghell.secondapp.database.Item;
import com.hackinghell.secondapp.database.ItemDao;
import com.hackinghell.secondapp.database.ItemRoomDatabase;
import com.hackinghell.secondapp.databinding.ActivityStorageBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StorageActivity extends AppCompatActivity {

    //private static final int MY_PERMISSIONS_REQUEST_READ_CALL_LOG = 100;
    private final String filename = "filenamecognizant";
    private static final String NAME = "name";
    private static final String PWD = "pwd";

    private ActivityStorageBinding binding;
    private ItemDao dao;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_storage);

        //ItemRoomDatabase database = ItemRoomDatabase.Companion.getDatabase(this);
        //dao = database.itemDao();

        binding = ActivityStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.listView.setSmoothScrollbarEnabled(Boolean.TRUE);

//        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
//            Log.i("tag", "permission is not granted");
//            ActivityCompat.requestPermissions(this,
//            new String[]{Manifest.permission.READ_CALL_LOG},
//            MY_PERMISSIONS_REQUEST_READ_CALL_LOG);
//        } else {
//            Log.i("tag", "permission was already granted");
//        }
    }

    @Override
    protected void onStart() {
                super.onStart();
        binding.btnCrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Creating a new item and inserting it into the database
                        Item item = new Item(21, "fruits", 11.11, 11);
                        dao.insertSync(item);
                        Log.i("GlobalSpace", "Doing the on start with the execute");
                    }
                });
            }
        });

        binding.btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Retrieve the item from the database
                        Item item = dao.getItemSync(21);

                        // Update the UI with the retrieved item
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.textView2.setText(item.toString());
                            }
                        });
                    }
                });
            }
        });
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
        //ContentResolver contentResolver = getContentResolver();

        Cursor logcursor = getContentResolver().query(
                CallLog.Calls.CONTENT_URI,
                null, null, null,
                null);
        if (logcursor != null) {
            String colName = CallLog.Calls.NUMBER;

            //Uri urisms = Uri.parse("content://sms/inbox");
            //ContentResolver contentResolver = getContentResolver();
            //Cursor dataCursor = contentResolver.query(urisms, null, null, null, null);
            //String[] fromColNames = new String[]{"address", "body"};
            String[] fromColNames = {colName};
            //int[] toTextviewsId = new int[]{android.R.id.text1, android.R.id.text2};
            int[] toTextviewsId = new int[]{android.R.id.text1};
            SimpleCursorAdapter cursorAdapt = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, logcursor, fromColNames, toTextviewsId, 0);
            binding.listView.setAdapter(cursorAdapt);
        };
    }
    private void restoreData() {
        //if file exists it will open that file or create it
          SharedPreferences preferences = getSharedPreferences(filename, Context.MODE_PRIVATE);
          String name = preferences.getString(NAME,"");
          String password = preferences.getString(PWD, "");
          binding.etName.setText(name);
          binding.etPassword.setText(password);
          Log.i("tag", "tes test");
    }

    public void openMainActivity(View view) {
        Intent intent = new Intent(StorageActivity.this, MainActivity.class);
        startActivity(intent);
    }
}