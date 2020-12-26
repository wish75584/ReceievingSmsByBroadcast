package com.stubborn.receievingsmsbybroadcast;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    String[] PERMISSIONS = {Manifest.permission.READ_SMS};

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private Object SMSBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "inside the permi 1", Toast.LENGTH_LONG).show();

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECEIVE_SMS}, 0);
                Toast.makeText(this, "inside the permi 2", Toast.LENGTH_LONG).show();

            } else {
                // register sms receiver
                IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
                registerReceiver((BroadcastReceiver) SMSBroadcastReceiver, filter);
                Toast.makeText(this, "inside the permi 2 else", Toast.LENGTH_LONG).show();

            }
            requestContactsPermissions();
        }


    }

    private ArrayList requestContactsPermissions() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_SMS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 1);
        }
        return null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "inside the req permi 2", Toast.LENGTH_LONG).show();

        } else {
            // register sms receiver
            IntentFilter filter = new IntentFilter(Telephony.Sms.Intents.SMS_RECEIVED_ACTION);
            registerReceiver((BroadcastReceiver) SMSBroadcastReceiver, filter);

            Toast.makeText(this, "inside the req permi 2 else" , Toast.LENGTH_LONG).show();

        }
    }

}

