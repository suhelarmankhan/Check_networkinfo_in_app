
## NetworkBroadcast.java
```java

package com.example.checkinternetconnection.classes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;

import com.example.checkinternetconnection.databinding.NoInternetConnectionBinding;

public class NetworkBroadcast extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

    if (!isNetworkConnected(context )){

  NoInternetConnectionBinding binding = NoInternetConnectionBinding.inflate(LayoutInflater.from(context));

        AlertDialog.   Builder builder = new AlertDialog.Builder(context);
        builder.setView(binding.getRoot());
        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        binding.bTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNetworkConnected(context)){
                    dialog.dismiss();

                }

            }
        });



    }

    }
    //-----methods start here ---------------------------------------
    private boolean isNetworkConnected(Context context){

        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();

        }catch (Exception e ){
            e.printStackTrace();
            return false;
        }

    }
}


```
<br/>

## MainActivity.java
```java
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



```


## AndroidMenifest.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.CheckInternetConnection"
        tools:targetApi="33">
        <activity
            android:name=".SecondActivity"
            android:exported="false" />
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


        <receiver
            android:name=".classes.NetworkBroadcast"
            android:exported="true">
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE" />
            </intent-filter>
        </receiver>



    </application>

</manifest>

```



