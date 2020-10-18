package com.example.firebaseset;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import static com.google.firebase.analytics.FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS;

public class Privacy extends AppCompatActivity {
    Switch s1,s2;
    private FirebaseAnalytics fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        s1 =(Switch)findViewById(R.id.switch2);
        s2 =(Switch)findViewById(R.id.switch3);
        fb = FirebaseAnalytics.getInstance(this);

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
                    fb.setAnalyticsCollectionEnabled(false);

                    Toast.makeText(getBaseContext(),"Analytics data transfer are not allowed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save",MODE_PRIVATE).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    s1.setChecked(false);
                    fb.setAnalyticsCollectionEnabled(true);
                    Toast.makeText(getBaseContext(),"Analytics data are allowed",Toast.LENGTH_LONG).show();
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
                    fb.setUserProperty( ALLOW_AD_PERSONALIZATION_SIGNALS, "false" );
                    fb.setAnalyticsCollectionEnabled(false);

                    Toast.makeText(getBaseContext(),"Ads are not allowed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SharedPreferences.Editor editor = getSharedPreferences("save1",MODE_PRIVATE).edit();
                    editor.putBoolean("value1",false);
                    editor.apply();
                    s2.setChecked(false);
                    fb.setUserProperty( ALLOW_AD_PERSONALIZATION_SIGNALS, "true" );
                    fb.setAnalyticsCollectionEnabled(true);
                    Toast.makeText(getBaseContext(),"Ads related data are allowed",Toast.LENGTH_LONG).show();
                }
            }
        });




//        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b == true){
//                    fb.setAnalyticsCollectionEnabled(false);
//                    Toast.makeText(getBaseContext(),"analytics not allowed",Toast.LENGTH_LONG).show();
//                }
//
//                else
//                {
//                    fb.setAnalyticsCollectionEnabled(true);
//                    Toast.makeText(getBaseContext(),"analytics allowed",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b==true){
//
//
//                    fb.setUserProperty( ALLOW_AD_PERSONALIZATION_SIGNALS, "false" );
//                    fb.setAnalyticsCollectionEnabled(false);
//                    Toast.makeText(getBaseContext(),"ad not allowed",Toast.LENGTH_LONG).show();
//                }
//                else
//                {
//                    fb.setUserProperty( ALLOW_AD_PERSONALIZATION_SIGNALS, "true" );
//                    fb.setAnalyticsCollectionEnabled(true);
//                    Toast.makeText(getBaseContext(),"ad allowed",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }
}