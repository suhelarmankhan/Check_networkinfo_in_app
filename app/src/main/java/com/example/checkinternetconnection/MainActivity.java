package com.example.checkinternetconnection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.checkinternetconnection.classes.NetworkBroadcast;

public class MainActivity extends AppCompatActivity {

   public static BroadcastReceiver broadcastReceiver;
    Button SecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        SecondActivity = findViewById(R.id .SecondActivity);


    broadcastReceiver = new NetworkBroadcast();
    registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


    SecondActivity.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }
    });



    }//onCreate end here--------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }
}