package com.example.facebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class privacy extends AppCompatActivity {
    Switch s1,s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        s1 =(Switch)findViewById(R.id.switch2);
        s2 =(Switch)findViewById(R.id.switch3);

        SharedPreferences sharedPreferences = getSharedPreferences("save",MODE_PRIVATE);
        s1.setChecked(sharedPreferences.getBoolean("value",false));
        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s1.isChecked())
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    s1.setChecked(true);
                    FacebookSdk.setAutoLogAppEventsEnabled(false);

                    Toast.makeText(getBaseContext(),"Auto logging events are not allowed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    s1.setChecked(false);
                    FacebookSdk.setAutoLogAppEventsEnabled(true);
                    Toast.makeText(getBaseContext(),"Auto logging events are allowed",Toast.LENGTH_LONG).show();
                }
            }
        });

        SharedPreferences sharedPreference = getSharedPreferences("save1",MODE_PRIVATE);
        s2.setChecked(sharedPreference.getBoolean("value1",false));
        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(s2.isChecked())
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save1",MODE_PRIVATE).edit();
                    editor.putBoolean("value1",true);
                    editor.apply();
                    s2.setChecked(true);
                    FacebookSdk.setAdvertiserIDCollectionEnabled(false);

                    Toast.makeText(getBaseContext(),"Ad ID collections are not allowed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save1",MODE_PRIVATE).edit();
                    editor.putBoolean("value1",false);
                    editor.apply();
                    s2.setChecked(false);
                    FacebookSdk.setAdvertiserIDCollectionEnabled(true);
                    Toast.makeText(getBaseContext(),"Ad ID collections are allowed",Toast.LENGTH_LONG).show();
                }
            }
        });
//        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b == true){
//                    FacebookSdk.setAutoLogAppEventsEnabled(false);
//
//                    Toast.makeText(getBaseContext(),"Auto logging events are not allowed",Toast.LENGTH_LONG).show();
//                }
//
//                else
//                {
//                    FacebookSdk.setAutoLogAppEventsEnabled(true);
//                    Toast.makeText(getBaseContext(),"Auto logging events are allowed",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b==true){
//                  FacebookSdk.setAdvertiserIDCollectionEnabled(false);
//                    Toast.makeText(getBaseContext(),"adid collection not allowed",Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    FacebookSdk.setAdvertiserIDCollectionEnabled(true);
//                    Toast.makeText(getBaseContext(),"adid collection are allowed",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}