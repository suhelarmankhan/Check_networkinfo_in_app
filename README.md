<!-- Additional Screenshot -->
<img src="https://github.com/suhelarmankhan/Check_networkinfo_in_app/assets/112642509/f4a17314-8a17-41b3-b888-8df214abc908" alt="Additional Screenshot"  height="600">  

It is a project of Android Studio. You can check whether the device has internet or not with this code. If there is no internet then the app will automatically show a no internet page and if there is internet it can be removed.

## Video Link
https://www.facebook.com/suhelarmankhan/videos/279687344870047

# Below I give the codes:


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

## no_internet_connection.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">


    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        android:layout_centerInParent="true"
        android:orientation="vertical">


        <com.airbnb.lottie.LottieAnimationView
            android:layout_width="wrap_content"
            android:layout_height="200dp"
            android:layout_centerInParent="true"
            android:layout_gravity="center"
            android:layout_marginTop="15dp"
            app:lottie_autoPlay="true"
            app:lottie_loop="true"
            app:lottie_rawRes="@raw/no_internet"

            />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            android:text="Ooops!"
            android:textColor="#FF9800"
            android:textSize="30sp"
            android:textStyle="bold" />


        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            android:text="No internet connection"
            android:textColor="@color/green"
            android:textSize="25sp" />


        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="5dp"
            android:text="Please check your connection and try again." />

        <Button
            android:id="@+id/b_try_again"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="5dp"
            android:layout_marginBottom="15dp"
            android:backgroundTint="@color/green"
            android:text="Try Again" />

    </LinearLayout>




</RelativeLayout>


```



<!-- Additional Screenshot -->
<img src="https://github.com/suhelarmankhan/Check_networkinfo_in_app/assets/112642509/f4a17314-8a17-41b3-b888-8df214abc908" alt="Additional Screenshot"  height="400">
It is a project of Android Studio. You can check whether the device has internet or not with this code. If there is no internet then the app will automatically show a no internet page and if there is internet it can be removed.

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

## no_internet_connection.xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">


    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"

        android:layout_centerInParent="true"
        android:orientation="vertical">


        <com.airbnb.lottie.LottieAnimationView
            android:layout_width="wrap_content"
            android:layout_height="200dp"
            android:layout_centerInParent="true"
            android:layout_gravity="center"
            android:layout_marginTop="15dp"
            app:lottie_autoPlay="true"
            app:lottie_loop="true"
            app:lottie_rawRes="@raw/no_internet"

            />

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            android:text="Ooops!"
            android:textColor="#FF9800"
            android:textSize="30sp"
            android:textStyle="bold" />


        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="10dp"
            android:text="No internet connection"
            android:textColor="@color/green"
            android:textSize="25sp" />


        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="5dp"
            android:text="Please check your connection and try again." />

        <Button
            android:id="@+id/b_try_again"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:layout_marginTop="5dp"
            android:layout_marginBottom="15dp"
            android:backgroundTint="@color/green"
            android:text="Try Again" />

    </LinearLayout>




</RelativeLayout>


```



